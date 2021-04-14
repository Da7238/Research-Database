// Research Database Project 2021 ISTE 330
// Darlene Ardila
// Colton Bailiff
// Weijie Chen
// Ben Donahue

import java.sql.*;

public class researchDatabase {

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String sql;

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
    public void searchMajor(String major) {
        String searchResult = ""; //string containing query results
        String student = ""; //string containing a single student's information that matched the search
        int numberRows = 0; //number of results for search found
        String sql = ""; //string to contain sql statement
        
        try {
            //create and execute query
            stmt = conn.createStatement();
            sql = "select s.studentName, m.majorName, s.email from student s join major m on (s.studentID = m.studentID) where m.majorName like %" + major + "%"; 
            rs = stmt.executeQuery(sql); 
            searchResult += "Student\tMajor\tEmail\n";
            
            //retrieve result set data to put in string
            while(rs.next()) {
                student = "| " + rs.getString(1) + "\t| " + rs.getString(2) + "\t| " + rs.getString(3) + "\t|\n";
                searchResult += student; 
            }//end of while          
        }//end of try
        
        catch (SQLException sqle) {
            System.out.println("Error SQLException in searchMajor | Error message: " + sqle);
        } // end of catch
        
        //get number of results and print the final search result
        searchResult += "\n" + numberRows + " results."; 
        System.out.println(searchResult); 
    }//end of searchMajor
// Ben
    public void searchArticle() {
        String searchResult = ""; //string containing query results
        String articleResult = ""; //string containing a single article's information that matched the search
        int numberRows = 0; //number of results for search found
        String sql = ""; //string to contain sql statement
        
        try {
            //create and execute query
            stmt = conn.createStatement();
            sql = "select a.title, a.author, t.topicTag, a.description from article a join topic t on (a.topicID = t.topicID) where a.title like %" + article + "%";
            rs = stmt.executeQuery(sql); 
            searchResult += "Title\tAuthor\tTopic\tDescription\n";
            
            //retrieve result set data to put in string
            while(rs.next()) {
                articleResult = "| " + rs.getString(1) + "\t| " + rs.getString(2) + "\t| " + rs.getString(3) + "\t| " + rs.getString(4) + "\t|\n";
                searchResult += articleResult; 
            }//end of while
        }//end of try
        
        catch (SQLException sqle) {
            System.out.println("Error SQLException in searchArticle | Error message: " + sqle);
        } // end of catch
        
        //get number of results and print the final search result
        searchResult += "\n" + numberRows + " results.";
        System.out.println(searchResult); 
    }//end of searchArticle
// Weijie
    public void searchAuthor() {

    }
// Weijie
    public void insertEntry() {

    }
    
// Colton (Not finished yet, need some more discussion regarding on what to update)
    public void updateEntry() {
       int numberRows = 0;
       int id = 0; // articleID
       String inputTitle = ""; // temporary variable
       String inputDesc = ""; // temporary variable
       
       // Attempt to create a statement and execute
       try {
            stmt = conn.createStatement();
            
            // Check if user left input(s) empty. If that is the case, it will replace with Untitled and/or No Description
            if (inputTitle == null || inputTitle == "") {
               sql = "UPDATE article SET title = 'Untitled', articleDescription = '" + inputDesc + "' WHERE articleID = " + id + ";";
            }
            if (inputDesc == null || inputDesc == "") {
               sql = "UPDATE article SET title = '" + inputTitle + "', articleDescription = 'No Description' WHERE articleID = " + id + ";";
            }
            else {
               sql = "UPDATE article SET title = '" + inputTitle + "', articleDescription = '" + inputDesc + "' WHERE articleID = " + id + ";";
            }
            
            numberRows = stmt.executeUpdate(sql);
            // Add some kind of notification informing something got updated successfully
         }
         catch (SQLException sqle) {
            System.out.println("Error SQLException in deleteEntry | Error message: " + sqle);
         } // end of catch
    } // end of updateEntry
    
// Colton
    public void deleteEntry() {
       int numberRows = 0;
       String input = ""; // temporary variable
       
       // Attempt to create a statement and execute
       try {
            stmt = conn.createStatement();
      
            sql = "DELETE FROM article WHERE title = '%" + input + "%';"; // uses wildcard to find & delete article's title based on user's input without having to type exact title (might consider using id only)
            numberRows = stmt.executeUpdate(sql);
            // Add some kind of notification informing something is deleted successfully
         }
         catch (SQLException sqle) {
            System.out.println("Error SQLException in deleteEntry | Error message: " + sqle);
         } // end of catch
    } // end of deleteEntry

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
