/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package naivebayes;

import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static naivebayes.NaiveBayes.JDBC_DRIVER;

/**
 *
 * @author
 */
public class GridView extends javax.swing.JPanel {

    /**
     * Creates new form GridView
     */
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/mdb";
    static final String USER = "root";
    static final String PASS = "root";
    Connection conn;
    Statement stmt;
    public String sql = "";
    ResultSet rs;
    public GridView() {
        initComponents();
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            sql = "select * from movie_vgame;";
            updateData();
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GridView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GridView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    public String genre = "";
    int cls = -1;
    public String searchKey = "";
    public int year = -1;
    public void updateSql(String genre){
        updateSql(genre,cls);
    }
    public void updateSql(int cls){
        updateSql(genre,cls);
    }
    
    public String addSearch(String sql, boolean itsAnd){
        
        if(year==-1){
            if(searchKey.equals("")){
                return sql;
            }else{
                if(itsAnd){
                    sql = sql + " and m.name like '%"+searchKey+"%'";
                }
                else{
                    sql = sql + " where m.name like '%"+searchKey+"%'";
                }
            }
        }else{
            if(searchKey.equals("")){
                if(itsAnd){
                    sql = sql + " and m.ReleaseDate like '"+year+"-%'";
                }
                else{
                    sql = sql + " where m.ReleaseDate like '"+year+"-%'";
                }
            }
            else{
                if(itsAnd){
                    sql = sql + " and m.name like '%"+searchKey+"%' and m.ReleaseDate like '"+year+"-%'";
                }
                else{
                    sql = sql + " where m.name like '%"+searchKey+"%' and m.ReleaseDate like '"+year+"-%'";
                }
            }
        }
        return sql;
    }
    public void updateSql(String genre, int cls){
        this.genre = genre;
        this.cls = cls;
        
        if(cls==-1){
            if(genre.equals("")){
                sql = "select * from movie_vgame m";
                sql = addSearch(sql,false);
            }else{
                try {
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery("select ID from genres where name = '"+genre+"';");
                    if(rs.next()){
                        sql = "select m.ID, m.name, m.ReleaseDate, m.boxoffice, m.boxofficeclass, m.ratings from movie_vgame m, genreMovie g where m.ID = g.Mid and g.Gid = "+rs.getInt(1)+"";
                        sql = addSearch(sql,true);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(GridView.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
        else{
            if(genre.equals("")){
                sql = "select * from movie_vgame m where boxofficeclass = "+cls+"";
                sql = addSearch(sql,true);
            }else{
                try {
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery("select ID from genres where name = '"+genre+"';");
                    if(rs.next()){
                        sql = "select m.ID, m.name, m.ReleaseDate, m.boxoffice, m.boxofficeclass, m.ratings from movie_vgame m, genreMovie g where m.ID = g.Mid and g.Gid = "+rs.getInt(1)+" and m.boxofficeclass = "+cls+"";
                        sql = addSearch(sql,true);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(GridView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        updateData();
    }
    public final void updateData(){
        this.removeAll();
        try {
            rs = stmt.executeQuery(sql);
            GridLayout experimentLayout = new GridLayout(0,3);
            this.setLayout(experimentLayout);
            while(rs.next()){
                MovieItem movie = new MovieItem();
                movie.updateData(rs.getInt(1),rs.getString(2)+" ("+rs.getString(3).substring(0, 4)+")", rs.getFloat(6), rs.getString(8));
                this.add(movie);
                
            }
            this.validate();
            this.repaint();
            MainFrame.mf.getScrollPane().updateUI();
        } catch (SQLException ex) {
            Logger.getLogger(GridView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex){
            ex.printStackTrace();
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setLayout(new java.awt.GridLayout());
        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
