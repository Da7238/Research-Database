// Research Database Project 2021 ISTE 330
// Darlene Ardila
// Colton Bailiff
// Weijie Chen
// Ben Donahue

import java.sql.*;
import java.util.Scanner;

public class researchDatabase {

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String sql;

    final String DEFAULT_DRIVER = "com.mysql.cj.jdbc.Driver";

    /*
     * Connects to the research database
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

    // TODO: Methods to potentially add: searchFacultyName searchFacultyAbstract
    // searchDepartment

    /**
     * @param email    - email entered by a student user
     * @param password - password entered by a user Purpose: Gets login information
     *                 from a student to login. Counts rows of search query, and
     *                 returns that to determine whether the login was a success.
     */
    public int login_student(String email, String password) {
        String sql = ""; // string user to store sql query
        int numberRows = 0; // int used to store the number of results for the email password combo. If 0,
                            // login is invalid

        try {
            stmt = conn.createStatement();

            // check if the student information is in the database
            sql = "select * from student_accounts where email = '" + email + "' and password = '" + password + "'";
            rs = stmt.executeQuery(sql);
            rs.last();
            numberRows = rs.getRow();
        } // end of try

        catch (SQLException sqle) {
            System.out.println("Error SQLException in login | Error message: " + sqle);
        } // end of catch

        return (numberRows);
    }// end of method login_student()

    /**
     * @param email    - email entered by a faculty user
     * @param password - password entered by a user Gets login information from a
     *                 faculty to login Counts rows of search query, and returns
     *                 that to determine whether the login was a success.
     */
    public int login_faculty(String email, String password) {
        String sql = ""; // string used to store sql query
        int numberRows = 0; // int used to store the number of results for the email password combo. If 0,
                            // login is invalid

        try {
            stmt = conn.createStatement();

            // check if the faculty information is in the database
            sql = "select * from faculty_accounts where email = '" + email + "' and password = '" + password + "'";
            rs = stmt.executeQuery(sql);
            rs.last();
            numberRows = rs.getRow();
        } // end of try

        catch (SQLException sqle) {
            System.out.println("Error SQLException in login | Error message: " + sqle);
        } // end of catch

        return (numberRows);
    }// end of method login_faculty

    /**
     * Searches the database for a student based on the inputed name
     * 
     * @param name - a student's name
     */
    public void searchStudentName(String name) {
        String searchResult = ""; // string containing query results
        String student = ""; // string containing a single student's information that matched the search
        int numberRows = 0; // number of results for search found
        String sql = ""; // string to contain sql statement

        try {
            // create and execute query
            stmt = conn.createStatement();
            sql = "SELECT s.studentName, m.majorName, s.email FROM student s JOIN major m ON (s.studentID = m.studentID) WHERE s.studentName LIKE %"
                    + name + "%";
            rs = stmt.executeQuery(sql);
            searchResult += "Student\tMajor\tEmail\n";

            // retrieve result set data to put in string
            while (rs.next()) {
                student = "| " + rs.getString(1) + "\t| " + rs.getString(2) + "\t| " + rs.getString(3) + "\t|\n";
                searchResult += student;
            } // end of while
        } // end of try

        catch (SQLException sqle) {
            System.out.println("Error SQLException in searchStudentName | Error message: " + sqle);
        } // end of catch

        // get number of results and print the final search result
        searchResult += "\n" + numberRows + " results.";
        System.out.println(searchResult);
    } // end of searchStudentName()

    /**
     * Searches the database for a student based on the inputed interest
     * 
     * @param - interest a student's interest
     */
    public void searchStudentInterest(String interest) {
        String searchResult = ""; // string containing query results
        String student = ""; // string containing a single student's information that matched the search
        int numberRows = 0; // number of results for search found
        String sql = ""; // string to contain sql statement

        try {
            // create and execute query
            stmt = conn.createStatement();
            sql = "SELECT s.studentName, m.majorName, s.email FROM student s JOIN major m ON (s.studentID = m.studentID) JOIN interest i ON (s.interestID = i.interestID) WHERE i.interestID LIKE %"
                    + interest + "%";
            rs = stmt.executeQuery(sql);
            searchResult += "Student\tMajor\tEmail\n";

            // retrieve result set data to put in string
            while (rs.next()) {
                student = "| " + rs.getString(1) + "\t| " + rs.getString(2) + "\t| " + rs.getString(3) + "\t|\n";
                searchResult += student;
            } // end of while
        } // end of try

        catch (SQLException sqle) {
            System.out.println("Error SQLException in searchStudentName | Error message: " + sqle);
        } // end of catch

        // get number of results and print the final search result
        searchResult += "\n" + numberRows + " results.";
        System.out.println(searchResult);

    } // end of searchStudentInterest()

    /**
     * Searches the database for a student based on the inputed major
     * 
     * @param major - a student's major
     */
    public void searchMajor(String major) {
        String searchResult = ""; // string containing query results
        String student = ""; // string containing a single student's information that matched the search
        int numberRows = 0; // number of results for search found
        String sql = ""; // string to contain sql statement

        try {
            // create and execute query
            stmt = conn.createStatement();
            sql = "select s.studentName, m.majorName, s.email from student s join major m on (s.studentID = m.studentID) where m.majorName like %"
                    + major + "%";
            rs = stmt.executeQuery(sql);
            searchResult += "Student\tMajor\tEmail\n";

            // retrieve result set data to put in string
            while (rs.next()) {
                student = "| " + rs.getString(1) + "\t| " + rs.getString(2) + "\t| " + rs.getString(3) + "\t|\n";
                searchResult += student;
            } // end of while
        } // end of try

        catch (SQLException sqle) {
            System.out.println("Error SQLException in searchMajor | Error message: " + sqle);
        } // end of catch

        // get number of results and print the final search result
        searchResult += "\n" + numberRows + " results.";
        System.out.println(searchResult);
    }// end of searchMajor()

    /**
     * Searches the database for an article based on the inputed title
     * 
     * @param article - the article that has been inputed by the user
     */
    public void searchArticle(String article) {
        String searchResult = ""; // string containing query results
        String articleResult = ""; // string containing a single article's information that matched the search
        int numberRows = 0; // number of results for search found
        String sql = ""; // string to contain sql statement

        try {
            // create and execute query
            stmt = conn.createStatement();
            sql = "select a.title, a.author, t.topicTag, a.description from article a join topic t on (a.topicID = t.topicID) where a.title like %"
                    + article + "%";
            rs = stmt.executeQuery(sql);
            searchResult += "Title\tAuthor\tTopic\tDescription\n";

            // retrieve result set data to put in string
            while (rs.next()) {
                articleResult = "| " + rs.getString(1) + "\t| " + rs.getString(2) + "\t| " + rs.getString(3) + "\t| "
                        + rs.getString(4) + "\t|\n";
                searchResult += articleResult;
            } // end of while
        } // end of try

        catch (SQLException sqle) {
            System.out.println("Error SQLException in searchArticle | Error message: " + sqle);
        } // end of catch

        // get number of results and print the final search result
        searchResult += "\n" + numberRows + " results.";
        System.out.println(searchResult);
    }// end of searchArticle()

    /**
     * Searches the database for an article based on the author
     * 
     * @param author - the author of an article
     */
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
    } // end of searchAuthor()

    /**
     * Searches for an article based on the inputed author
     * 
     * @param facultyID   - a faculty ID
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
                    "INSERT INTO faculty(facultyID, facultyName, department, abstract, email)  VALUES (?,?,?,?,?)");
            stmt2.setInt(1, primaryKey);
            stmt2.setString(2, facultyName);
            stmt2.setString(3, department);
            stmt2.setString(4, abstrac);
            stmt2.setString(5, email);

            result = stmt2.executeUpdate(); // Performs the update command
        } // end of try
        catch (Exception e) {
            System.out.println("Error whlie trying to insert a faculty member.");
            System.out.println("Error message is --> " + e);
        } // end of catch
        return (result); // return the result to presentation layer
    }// end of insertFaculty()

    /**
     * Updates the fields of an article
     * 
     * @param inputTitle - the updated article
     * @param inputDesc  - the updated description of an article
     */
    public void updateEntry(String inputTitle, String inputDesc) {
        int numberRows = 0;
        int id = 0; // articleID (need to pass from somewhere that is currently viewing a article or
                    // something)

        // Attempt to create a statement and execute
        try {
            stmt = conn.createStatement();

            // Check if user left input(s) empty. If that is the case, it will replace with
            // Untitled and/or No Description
            if (inputTitle == null || inputTitle == "") {
                sql = "UPDATE article SET title = 'Untitled', articleDescription = '" + inputDesc
                        + "' WHERE articleID = " + id + ";";
            }
            if (inputDesc == null || inputDesc == "") {
                sql = "UPDATE article SET title = '" + inputTitle
                        + "', articleDescription = 'No Description' WHERE articleID = " + id + ";";
            } else {
                sql = "UPDATE article SET title = '" + inputTitle + "', articleDescription = '" + inputDesc
                        + "' WHERE articleID = " + id + ";";
            }

            numberRows = stmt.executeUpdate(sql);
            System.out.println(numberRows + " row(s) updated.");
        } catch (SQLException sqle) {
            System.out.println("Error SQLException in deleteEntry | Error message: " + sqle);
        } // end of catch
    } // end of updateEntry()

    /**
     * Deletes an article based on the title name
     * 
     * @param inputTitle - the title of an article
     */
    public void deleteEntry(String inputTitle) {
        int numberRows = 0;

        // Attempt to create a statement and execute
        try {
            stmt = conn.createStatement();

            sql = "DELETE FROM article WHERE title LIKE '%" + inputTitle + "%';"; // uses wildcard to find & delete
                                                                                  // article's title based on user's
                                                                                  // input without having to type exact
                                                                                  // title (might consider using id
                                                                                  // only)
            numberRows = stmt.executeUpdate(sql);
            System.out.println(numberRows + " row(s) deleted.");
        } catch (SQLException sqle) {
            System.out.println("Error SQLException in deleteEntry | Error message: " + sqle);
        } // end of catch
    } // end of deleteEntry()

    /**
     * Closes all connections to the database
     */
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
        String userType = ""; // string used to store whether it's a student or faculty member
        String email = ""; // string used to store the email a user tries to log in with
        String password = ""; // string used to store the password a user tries to log in with
        int loginResult = 0; // int used to determine log in success, based on number of results from search
                             // query. 0 if it fails, 1 if it succeeeds.

        Scanner scanner = new Scanner(System.in);

        someObject.connect();

        // determine if it's student or faculty member
        System.out.println("Enter user type (student/faculty)");
        userType = scanner.nextLine();

        // error handling
        while (userType != "student" || userType != "faculty") {
            System.out.println("Invalid user type!");
            System.out.println("Enter user type (student/faculty)");
            userType = scanner.nextLine();
        } // end of while

        // get login information from user
        System.out.println("Enter email: ");
        email = scanner.nextLine();
        System.out.println("Enter password: ");
        password = scanner.nextLine();

        // login depending on whether it is a student or faculty
        if (userType == "student") {
            loginResult = someObject.login_student(email, password);
        } // end of if
        else if (userType == "faculty") {
            loginResult = someObject.login_faculty(email, password);
        } // end of else if

        // if the resultset returns an empty selection, the user information was
        // incorrect
        while (loginResult == 0) {
            System.out.println("Invalid login information!");
            System.out.println("Enter email: ");
            email = scanner.nextLine();
            System.out.println("Enter password: ");
            password = scanner.nextLine();
            if (userType == "student") {
                loginResult = someObject.login_student(email, password);
            } // end of if
            else if (userType == "faculty") {
                loginResult = someObject.login_faculty(email, password);
            } // end of else if
        } // end of while

        scanner.close(); // closes the scanner
    } // end of main
} // end of researchDatabase