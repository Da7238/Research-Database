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
    public void searchArticle(String article) {
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
      public void searchAuthor(String author) {
        String searchResult = ""; // string containing query results
        String authorResult = ""; // string containing a single article's information that matched the search
        int numberRows = 0; // number of results for search found
        String sql = ""; // string to contain sql statement

        try {
            // create and execute query
            stmt = conn.createStatement();
            sql = "select a.authorName, a.articlePublished art.title FROM author a JOIN article art ON (a.authorID = art.authorID) where a.author like %"
                    + author + "%";
            rs = stmt.executeQuery(sql);
            searchResult += "Author\tArticlesPublished\tTitle\n";

            // retrieve result set data to put in string
            while (rs.next()) {
                authorResult = "| " + rs.getString(1) + "\t| " + rs.getString(2) + "\t| " + rs.getString(4) + "\t|\n";
                searchResult += authorResult;
            } // end of while
        } // end of try

        catch (SQLException sqle) {
            System.out.println("Error SQLException in searchArticle | Error message: " + sqle);
        } // end of catch

        // get number of results and print the final search result
        searchResult += "\n" + numberRows + " results.";
        System.out.println(searchResult);
    }


// Weij1ie
 
    // TODO: Weijie

    /**
     * Searches for an article based on the inputed author
     * 
     * @param ID          - a faculty ID
     * @param facultyName - the name of a faculty member
     * @param department  - the department of a faculty member
     * @param abstrac     - the abstract of a faculty's research
     * @param email       - the email of a faculty member
     * @return
     */
    public int insertFaculty(String facultyID, String facultyName, String department, String abstrac, String email) {

        int result = 0;
        int primaryKey = Integer.parseInt(facultyID);
        try {
            PreparedStatement stmt2;

            stmt2 = conn.prepareStatement(
                    "INSERT INTO faculty(facultyID, facultyName, department, abstract)  VALUES (?,?,?,?,?)");
            stmt2.setInt(1, primaryKey);
            stmt2.setString(2, facultyName);
            stmt2.setString(3, department);
            stmt2.setString(4, abstrac);
            stmt2.setString(5, email);

            result = stmt2.executeUpdate();
        } // end of try
        catch (Exception e) {
            System.out.println("Error whlie trying to insert a faculty member.");
            System.out.println("Error message is --> " + e);
        } // end of catch
        return (result); // return the result to presentation layer
    }// end of method
    
   public int insertpublic(String publicID, String publicName, String userName, String pubEmail, String interestId, String password)
   {            
        int result = 0;
        int primaryKey = Integer.parseInt(publicID);
        
        try {
        PreparedStatement stmt;

         stmt = conn.prepareStatement("INSERT INTO contact (publicID, publicName, userName, pubEmail, interestId, password) VALUES (?, ?, ?, ?, ?, ?)");
         stmt.setInt(1, primaryKey);
         stmt.setString(2, publicName);
         stmt.setString(3, userName);
         stmt.setString(4, pubEmail);
         stmt.setString(5, interestId);
         stmt.setString(6, password);
      
         result = stmt.executeUpdate();
      
      } catch (Exception ex) {
         System.out.println("unable to insert");
         System.out.println(ex.getMessage());
      } return (result); // return the result to presentation layer
    }
   
      public int insertstudent(String studentID, String studentName, String interestId, String email)
   {            
        int result = 0;
        int primaryKey = Integer.parseInt(studentID);
        
        try {
        PreparedStatement stmt;

         stmt = conn.prepareStatement("INSERT INTO contact (studentID, studentName, interestId, email) VALUES (?, ?, ?, ?)");
         stmt.setInt(1, primaryKey);
         stmt.setString(2, studentName);
         stmt.setString(3, interestId);
         stmt.setString(4, email);
      
         result = stmt.executeUpdate();
      
      } catch (Exception ex) {
         System.out.println("unable to insert");
         System.out.println(ex.getMessage());
      } return (result); // return the result to presentation layer
    }

    
// Colton (Not finished yet, need some more discussion regarding on what to update)
    public void updateEntry(String inputTitle, String inputDesc) {
       int numberRows = 0;
       int id = 0; // articleID (need to pass from somewhere that is currently viewing a article or something)
       
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
            System.out.println(numberRows + " row(s) updated.");
         }
         catch (SQLException sqle) {
            System.out.println("Error SQLException in deleteEntry | Error message: " + sqle);
         } // end of catch
    } // end of updateEntry
    
// Colton
    public void deleteEntry(String inputTitle) {
       int numberRows = 0;
       
       // Attempt to create a statement and execute
       try {
            stmt = conn.createStatement();
      
            sql = "DELETE FROM article WHERE title LIKE '%" + inputTitle + "%';"; // uses wildcard to find & delete article's title based on user's input without having to type exact title (might consider using id only)
            numberRows = stmt.executeUpdate(sql);
            System.out.println(numberRows + " row(s) deleted.");
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
