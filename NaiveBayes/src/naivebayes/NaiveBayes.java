/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package naivebayes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author
 */
public class NaiveBayes {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/mdb";
    static final String USER = "root";
    static final String PASS = "root";
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    Connection conn;
    Statement stmt;
    String sql = "";
    int id = -1;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new NaiveBayes();
    }
    public NaiveBayes(){
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            
            /*
            ScriptRunner runner = new ScriptRunner(conn, false, false);
            runner.runScript(new BufferedReader(new FileReader("C:\\Shared\\DevSRC\\EclipseProjects\\NaiveBayes\\src\\naivebayes\\init.sql")));
            
            
            sql = "insert into movie_vgame(name,ReleaseDate) values('Andha Kanoon','2015-01-01')";
            stmt.executeUpdate(sql);
            
            sql = "select ID from movie_vgame where name = 'Andha Kanoon' and ReleaseDate = '2015-01-01'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                id = rs.getInt(1);
            }
            sql = "insert into genremovie values("+id+",1)";
            stmt.executeUpdate(sql);
            sql = "insert into genremovie values("+id+",2)";
            stmt.executeUpdate(sql);
            sql = "insert into personmovie values("+id+",3,'Actor')";
            stmt.executeUpdate(sql);
            sql = "insert into personmovie values("+id+",16,'Actor')";
            stmt.executeUpdate(sql);
            sql = "insert into personmovie values("+id+",3,'Director')";
            stmt.executeUpdate(sql);
            */
            
            
            
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try {
            applyClassifier(id);
        } catch (SQLException ex) {
            Logger.getLogger(NaiveBayes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public float applyClassifier(int movieID) throws SQLException{
        Vector<float[]> classes = new Vector<float[]>();
        float total = getTotalCount();
        if(total==0) return -1;
        float currentTotal;
        System.out.println(id+" id");

        Statement st1 = conn.createStatement(),st2 = conn.createStatement(),st3 = conn.createStatement();
        sql = "select * from boxofficeclass;";
        ResultSet rs = stmt.executeQuery(sql);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -30);
        boolean touched = false;
        while(rs.next()){
            int classID = rs.getInt(1);
            currentTotal = getClassCount(classID);
            System.out.println("current total : "+currentTotal);
            float a[] = {classID,currentTotal/total,currentTotal};
            
            sql = "select * from personmovie where Mid = "+movieID;
            ResultSet rs2 = st1.executeQuery(sql);
            while(rs2.next()){
                sql = "select count(m.ID) from movie_vgame m, personmovie p where m.ID = p.Mid and p.Pid = "+rs2.getInt(2)+" and m.boxofficeclass = "+classID+" and p.role = '"+rs2.getString(3)+"'  and m.ReleaseDate < '2015-03-01';";
                System.out.println(sql);
                ResultSet rs3 = st2.executeQuery(sql);
                while(rs3.next()){
                    float count = rs3.getFloat(1);
                    System.out.println(count + "count");
                    if(currentTotal!=0){
                        a[1] = a[1] * (count/currentTotal);
                        touched = true;
                    }
                    else{
                        
                    }
                }
            }
            /*sql = "select * from genremovie where Mid = "+movieID+";";
            ResultSet rs4 = st1.executeQuery(sql);
            while(rs4.next()){
                
                sql = "select count(m.ID) from movie_vgame m, genremovie g where m.ID = g.Mid and g.Gid = "+rs4.getInt(1)+" and ReleaseDate < "+sf.format(cal.getTime())+";";
                ResultSet rs5 = st2.executeQuery(sql);
                while(rs5.next()){
                    float count = rs5.getFloat(1);
                    System.out.println(count + "count");
                    if(currentTotal!=0){
                        a[1] = a[1] * (count/currentTotal);
                        touched = true;
                    }
                    else{
                        
                    }
                }
            }*/
            //if(touched == false) {a[1]=0;}
            classes.add(a);
        }
        float max = 0;
        float index = -1;
        for(int i=0;i<classes.size();i++){
            if(classes.elementAt(i)[1]>=max){
                max = classes.elementAt(i)[1];
                index = i;
            }
            System.out.print(classes.elementAt(i)[1]+" ");
        }
        if(max==0||touched==false) return -1;
        return index+1;
        
    }
    public int getTotalCount() throws SQLException{
        sql = "select count(ID) from movie_vgame;";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }
    public int getClassCount(int classID) throws SQLException{
        sql = "select count(boxofficeclass) from movie_vgame where boxofficeclass="+classID+";";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }
    
}
