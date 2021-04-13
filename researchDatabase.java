// Research Database Project
// Darlene Ardila
// Colton Bailiff
// Weijie Chen
// Ben Donahue

import java.sql.*;

public class researchDatabase {

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    // private String sql;

    final String DEFAULT_DRIVER = "com.mysql.cj.jdbc.Driver";

    /**
     * Connects top the database
     */
    public boolean connect() {
        conn = null;
        String userName = "root";
        String password = "student";
        String url = "jdbc:mysql://localhost/researchDatabase";

        try {
            Class.forName(DEFAULT_DRIVER);
            conn = DriverManager.getConnection(url, userName, password);
            DatabaseMetaData md = conn.getMetaData();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("No DB Connected!");
            System.out.println("Class");
            System.out.println("ERROR-> " + cnfe);
            System.exit(0);
        } catch (SQLException sqle) {
            System.out.println("ERROR SQLException in connect()!");
            System.out.println("ERROR-> " + sqle);
            sqle.printStackTrace();
            System.exit(0);
        } // end of catch
        return (conn != null);
    } // end of connect()
// Darlene 
    public void searchName() {

    }
// Darlene
    public void searchInterest() {

    }
// Ben
    public void searchMajor() {

    }
// Ben
    public void searchArticle() {

    }
// Weijie
    public void searchAuthor() {

    }
// Weijie
    public void insertEntry() {

    }
// Colton
    public void updateEntry() {

    }
// Colton
    public void deleteEntry() {

    }

    public void close() {
        try {
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException sqle) {
            System.out.println("ERROR IN close()");
            System.out.println("ERROR MESSAGE ->" + sqle);
        } // end of catch
    } // end of close

    public static void main(String[] args) {
        researchDatabase someObject = new researchDatabase();
        someObject.connect();

    }
} // end of researchDatabase
