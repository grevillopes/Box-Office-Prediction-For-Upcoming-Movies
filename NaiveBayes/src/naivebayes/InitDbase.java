/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package naivebayes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author
 */
public class InitDbase {
    static  String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static  String DB_URL = "jdbc:mysql://localhost/";
    static final String USER = "root";
    static final String PASS = "root";
    Connection conn;
    Statement stmt;
    String sql = "";
    int id = -1;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new InitDbase().init();
    }
    public void init2(){
        
        JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        DB_URL = "jdbc:mysql://localhost/mdb";
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void init(){
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            sql = "drop DATABASE if exists mdb";
            stmt.executeUpdate(sql);
            
            ScriptRunner runner = new ScriptRunner(conn, false, false);
            runner.runScript(new BufferedReader(new FileReader("C:\\Shared\\DevSRC\\EclipseProjects\\NaiveBayes\\src\\naivebayes\\init.sql")));
            
            
            sql = "insert into movie_vgame(name,ReleaseDate,imgfile) values('Andha Kanoon','2015-02-10','/naivebayes/imgs/ak.JPG')";
            stmt.executeUpdate(sql);
            
            sql = "select ID from movie_vgame where name = 'Andha Kanoon' and ReleaseDate = '2015-02-10'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                id = rs.getInt(1);
                System.out.println(id+" id");
            }
            
            sql = "insert into personmovie values("+id+",2,'Actor')";
            stmt.executeUpdate(sql);
            
            
            
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try {
            applyClassifier(id);
        } catch (SQLException ex) {
            Logger.getLogger(InitDbase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public InitDbase(){
        
    }
    public int applyClassifier(int movieID) throws SQLException{
        Vector<float[]> classes = new Vector<float[]>();
        float total = getTotalCount();
        if(total==0) return 0;
        float currentTotal;
        System.out.println("ID in classifier is "+movieID);
/*        sql = "select * from personmovie where Mid == "+id;
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            sql = "select * from boxofficeclass;";
            ResultSet rs2 = stmt.executeQuery(sql);
            while(rs2.next()){
                currentTotal = getClassCount(rs2.getInt(0));
                int a[] = {currentTotal/total,currentTotal};
                classes.add(a);
            }
        }
        
 */
        Statement st1 = conn.createStatement(),st2 = conn.createStatement(),st3 = conn.createStatement();
        sql = "select * from boxofficeclass;";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            int classID = rs.getInt(1);
            currentTotal = getClassCount(classID);
            System.out.println("current total : "+currentTotal);
            float a[] = {classID,currentTotal/total,currentTotal};
            sql = "select * from personmovie where Mid = "+id;
            ResultSet rs2 = st1.executeQuery(sql);
            while(rs2.next()){
                
                sql = "select count(m.ID) from movie_vgame m, personmovie p where m.ID = p.Mid and p.Pid = "+rs2.getInt(2)+" and m.boxofficeclass = "+classID+" and p.role = '"+rs2.getString(3)+"' and m.ReleaseDate < '2015-03-01';";
                System.out.println(sql);
                ResultSet rs3 = st2.executeQuery(sql);
                while(rs3.next()){
                    float count = rs3.getFloat(1);
                    System.out.println(count + "count");
                    if(currentTotal!=0){
                        a[1] = a[1] * (count/currentTotal);
                        System.out.println("Yes "+a[1]);
                    }
                    else{
                        
                    }
                }
            }
         /*   sql = "select * from genremovie where Mid = "+movieID+";";
            ResultSet rs4 = st1.executeQuery(sql);
            while(rs4.next()){
                sql = "select count(m.ID) from movie_vgame m, genremovie g where m.ID = g.Mid and g.Gid = "+rs4.getInt(1)+";";
                ResultSet rs5 = st2.executeQuery(sql);
                while(rs5.next()){
                    float count = rs5.getFloat(1);
                    System.out.println(count + "count");
                    if(currentTotal!=0&&count!=0)
                        a[1] = a[1] * (count/currentTotal);
                    else{
                        
                    }
                }
            }
            */
            classes.add(a);
        }
        
        System.out.println();
        float max = 0;
        int index = -1;
        for(int i=0;i<classes.size();i++){
            if(classes.elementAt(i)[1]>=max){
                max = classes.elementAt(i)[1];
                index = i;
            }
            System.out.print(classes.elementAt(i)[1]+" ");
        }
        
        System.out.println((index+1)+" has highest probability");
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
