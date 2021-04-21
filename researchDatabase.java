// Research Database Project |  2021 | ISTE 330
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
    private Scanner scanner;

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

    /**
     * Searches the database for a faculty member based on the entered name
     * 
     * @param name - a faculty member's name
     */
    public void searchFacultyName(String name) {
        String searchResult = ""; // string containing query results
        String faculty = ""; // string containing a single student's information that matched the search
        int numberRows = 0; // number of results for search found
        String sql = ""; // string to contain sql statement

        try {
            // create and execute query
            stmt = conn.createStatement();
            sql = "SELECT facultyName, email, d.departmentName, abstract FROM faculty f join department d on (f.departmentID = d.departmentID) WHERE f.facultyName LIKE '%"
                    + name + "%'";
            rs = stmt.executeQuery(sql);
            searchResult += "Faculty Member\tEmail\tDepartment\tAbstract\n";

            // retrieve result set data to put in string
            while (rs.next()) {
                faculty = "| " + rs.getString(1) + "\t| " + rs.getString(2) + "\t| " + rs.getString(3) + "\t| "
                        + rs.getString(4) + "\t|\n";
                searchResult += faculty;
            } // end of while
        } // end of try

        catch (SQLException sqle) {
            System.out.println("Error SQLException in searchStudentName | Error message: " + sqle);
        } // end of catch

        // get number of results and print the final search result
        searchResult += "\n" + numberRows + " results.";
        System.out.println(searchResult);
    } // end of searchFacultyName()

    /**
     * Searches the database for a faculty member based on the entered abstract
     * 
     * @param facultyAbstract - a faculty member's abstract
     */
    public void searchFacultyAbstract(String facultyAbstract) {
        String searchResult = ""; // string containing query results
        String faculty = ""; // string containing a single student's information that matched the search
        int numberRows = 0; // number of results for search found
        String sql = ""; // string to contain sql statement

        try {
            // create and execute query
            stmt = conn.createStatement();
            sql = "SELECT facultyName, email, d.departmentName, abstract FROM faculty f join department d on (f.departmentID = d.departmentID)  WHERE abstract LIKE '%"
                    + facultyAbstract + "%'";
            rs = stmt.executeQuery(sql);
            searchResult += "Faculty Member\tEmail\tDepartment\tAbstract\n";

            // retrieve result set data to put in string
            while (rs.next()) {
                faculty = "| " + rs.getString(1) + "\t| " + rs.getString(2) + "\t| " + rs.getString(3) + "\t| "
                        + rs.getString(4) + "\t|\n";
                searchResult += faculty;
            } // end of while
        } // end of try

        catch (SQLException sqle) {
            System.out.println("Error SQLException in searchStudentName | Error message: " + sqle);
        } // end of catch

        // get number of results and print the final search result
        searchResult += "\n" + numberRows + " results.";
        System.out.println(searchResult);
    } // end of searchFacultyAbstract()

    /**
     * Searches the database for a faculty member based on the entered department
     * 
     * @param department
     */
    public void searchDepartment(String departmentName) {
        // Print out facultyName, email, department, abstract, and topicTag
        String searchResult = ""; // string containing query results
        String department = ""; // string containing department's information that matched the search
        int numberRows = 0; // number of results for search found
        String sql = ""; // string to contain sql statement

        try {
            stmt = conn.createStatement();
            sql = "select f.facultyName, f.email, d.departmentName, f.abstract, t.topicTag FROM faculty f JOIN department d on (f.departmentID = d.departmentID) JOIN topic t ON (f.topicID = t.topicID) where d.departmentName LIKE '%"
                    + departmentName + "%'";
            rs = stmt.executeQuery(sql);
            searchResult += "Faculty Member\tEmail\tDepartment\tAbstract\ttopicTag\n";
            // retrieve result set data to put in string
            while (rs.next()) {
                department = "| " + rs.getString(1) + "\t| " + rs.getString(2) + "\t| " + rs.getString(3) + "\t| "
                        + rs.getString(4) + "\t| " + rs.getString(5) + "\t|\n";
                searchResult += department;
            } // end of while
        } // end of try

        catch (SQLException sqle) {
            System.out.println("Error SQLException in searchDepartmentName | Error message: " + sqle);
        } // end of catch

        // get number of results and print the final search result
        searchResult += "\n" + numberRows + " results.";
        System.out.println(searchResult);
    } // end of searchDepartment

    /**
     * Searches the database for a student based on the entered email
     * 
     * @param email
     */
    public void searchStudentEmail(String studentEmail) {
        // Print out studentName, major, email, and interest
        String searchResult = ""; // string containing query results
        String email = ""; // string containing department's information that matched the search
        int numberRows = 0; // number of results for search found
        String sql = ""; // string to contain sql statement

        try {
            stmt = conn.createStatement();
            sql = "SELECT s.studentName, m.majorName, s.email, i.interestName FROM student s JOIN major m ON (s.majorID = m.majorID) JOIN interest i ON (s.interestID = i.interestID) where s.email LIKE '%"
                    + email + "%'";
            rs = stmt.executeQuery(sql);
            searchResult += "Student Name\tMajor\tEmail\tinterestName\n";
            // retrieve result set data to put in string
            while (rs.next()) {
                email = "| " + rs.getString(1) + "\t| " + rs.getString(2) + "\t| " + rs.getString(3) + "\t| "
                        + rs.getString(4) + "\t|\n";
                searchResult += email;
            } // end of while
        } // end of try

        catch (SQLException sqle) {
            System.out.println("Error SQLException in searchStudentEmail | Error message: " + sqle);
        } // end of catch

        // get number of results and print the final search result
        searchResult += "\n" + numberRows + " results.";
        System.out.println(searchResult);
    } // end of searchStudentEmail()

    /**
     * Searches the database for a faculty member based on the entered email
     * 
     * @param email - email entered by a student
     */
    public void searchFacultyEmail(String email) {
        // Print out facultyName, email, department, abstract, and topicTag
        String searchResult = ""; // string containing query results
        String emailString = ""; // string containing department's information that matched the search
        int numberRows = 0; // number of results for search found
        String sql = ""; // string to contain sql statement

        try {
            stmt = conn.createStatement();
            sql = "SELECT f.facultyName, f.email, d.departmentName, f.abstract, t.topicTag from faculty f join department d on (f.departmentID = d.departmentID) JOIN topic t ON (f.topicID = t.topicID) where f.email LIKE '%"
                    + email + "%'";
            rs = stmt.executeQuery(sql);
            searchResult += "Faculty Member\tEmail\tDepartment\tAbstract\ttopicTag\n";
            // retrieve result set data to put in string
            while (rs.next()) {
                emailString = "| " + rs.getString(1) + "\t| " + rs.getString(2) + "\t| " + rs.getString(3) + "\t| "
                        + rs.getString(4) + "\t| " + rs.getString(5) + "\t|\n";
                searchResult += emailString;
            } // end of while
        } // end of try

        catch (SQLException sqle) {
            System.out.println("Error SQLException in searchFacultyEmail | Error message: " + sqle);
        } // end of catch

        // get number of results and print the final search result
        searchResult += "\n" + numberRows + " results.";
        System.out.println(searchResult);
    }

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
            sql = "SELECT * FROM student_accounts WHERE email=\"" + email + "\" AND accountPassword=\"" + password
                    + "\"";
            rs = stmt.executeQuery(sql);
            rs.next();
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
            sql = "SELECT * FROM faculty_accounts WHERE email=\"" + email + "\" AND accountPassword=\"" + password
                    + "\"";
            rs = stmt.executeQuery(sql);
            rs.next();
            numberRows = rs.getRow();
        } // end of try

        catch (SQLException sqle) {
            System.out.println("Error SQLException in login | Error message: " + sqle);
        } // end of catch

        return (numberRows);
    }// end of method login_faculty

    /**
     * Searches the database for a student based on the entered name
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
            sql = "SELECT s.studentName, m.majorName, s.email FROM student s JOIN major m ON (s.majorID = m.majorID) WHERE s.studentName LIKE '%"
                    + name + "%'";
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
     * Searches the database for a student based on the entered interest
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
            sql = "SELECT s.studentName, m.majorName, s.email, i.interestName FROM student s JOIN major m ON (s.majorID = m.majorID) JOIN interest i ON (s.interestID = i.interestID) WHERE i.interestName LIKE '%"
                    + interest + "%'";
            rs = stmt.executeQuery(sql);
            searchResult += "Student\tMajor\tEmail\tInterest\n";

            // retrieve result set data to put in string
            while (rs.next()) {
                student = "| " + rs.getString(1) + "\t| " + rs.getString(2) + "\t| " + rs.getString(3) + "\t| "
                        + rs.getString(4) + "\t|\n";
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
     * Searches the database for a student based on the entered major
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
            sql = "select s.studentName, m.majorName, s.email from student s join major m on (s.majorID = m.majorID) where m.majorName LIKE '%"
                    + major + "%'";
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
     * Searches the database for an article based on the entered title
     * 
     * @param article - the article that has been entered by the user
     */
    public void searchArticle(String article) {
        String searchResult = ""; // string containing query results
        String articleResult = ""; // string containing a single article's information that matched the search
        int numberRows = 0; // number of results for search found
        String sql = ""; // string to contain sql statement

        try {
            // create and execute query
            stmt = conn.createStatement();
            sql = "select a.title, t.topicTag, a.articleDescription, aw.authorName from article a join topic t on (a.topicID = t.topicID) join author aw on (a.authorID = aw.authorID) where title LIKE '%"
                    + article + "%'";
            rs = stmt.executeQuery(sql);
            searchResult += "Title\tTopic\tDescription\tAuthor\n";

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
            sql = "select a.authorName, a.articlesPublished, art.title FROM author a JOIN article art ON (a.authorID = art.authorID) where a.authorName LIKE '%"
                    + author + "%'";
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
     * Searches for an article based on the entered author
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
     * Prints out option menu for students
     */
    public void student_menu() {
        System.out.println("==== Options ====");
        System.out.println(
                "1. Search article by title\n" + "2. Search article by abstract\n" + "3. Search article by author(s)\n"
                        + "4. Search article by intererst\n" + "5. Search faculty by name\n"
                        + "6. Search faculty by department\n" + "7. Search faculty by email\n" + "8. Quit\n");
    } // end of student_menu()

    /**
     * 
     * @param choice - determine which option a student chose
     */
    public void student_choice(int choice, Scanner scanner) {
        String keyword = "";

        switch (choice) {
        case 1: // search article title
            System.out.println("Enter article title keywords: ");
            keyword = scanner.nextLine();
            searchArticle(keyword);
            break;
        case 2: // search article abstracts
            System.out.println("Enter abstract keywords: ");
            keyword = scanner.nextLine();
            searchFacultyAbstract(keyword);
            break;
        case 3: // search author
            System.out.println("Enter author: ");
            keyword = scanner.nextLine();
            searchAuthor(keyword);
            break;
        case 4: // search interests
            System.out.println("Enter interests: ");
            keyword = scanner.nextLine();
            searchStudentInterest(keyword);
            break;
        case 5: // search faculty
            System.out.println("Enter faculty name: ");
            keyword = scanner.nextLine();
            searchFacultyName(keyword);
            break;
        case 6: // search faculty department
            System.out.println("Enter faculty department: ");
            keyword = scanner.nextLine();
            searchDepartment(keyword);
            break;
        case 7: // search faculty email
            System.out.println("Enter faculty email: ");
            keyword = scanner.nextLine();
            searchFacultyEmail(keyword);
            break;

        default:
            System.out.println("Invalid choice!");
        }// end of switch
    }// end of student choice

    /**
     * Prints out options for a faculty member
     */
    public void faculty_menu() {
        System.out.println("==== Options ====");
        System.out.println(
                "1. Search students by name\n" + "2. Search students by major\n" + "3. Search students by interest\n"
                        + "4. Search students by email\n" + "5. Insert a new faculty member\n"
                        + "6. Update article entry\n" + "7. Delete article entry\n" + "8. Quit\n");
    } // end of faculty_menu()

    /**
     * Enter faculty options - choose which action to perform
     * 
     * @param choice - determine which option a faculty member chose
     */
    public void faculty_choice(int choice, Scanner scanner) {
        // separate strings are needed because multiple faculty options have multiple
        // parameters
        String title = "";
        String facultyAbstract = "";
        String ID = "";
        String name = "";
        String department = "";
        String email = "";
        String interest = "";
        String major = "";

        switch (choice) {
        case 1: // student name
            System.out.println("Enter student name: ");
            name = scanner.nextLine();
            searchStudentName(name);
            break;
        case 2: // student major
            System.out.println("Enter student major: ");
            major = scanner.nextLine();
            searchStudentName(major);
            break;
        case 3: // student interests
            System.out.println("Enter student interests: ");
            interest = scanner.nextLine();
            searchStudentInterest(interest);
            break;
        case 4: // student email
            System.out.println("Enter student email: ");
            email = scanner.nextLine();
            searchStudentEmail(email);
            break;
        case 5: // insert faculty
            System.out.println("Enter faculty ID: ");
            ID = scanner.nextLine();
            System.out.println("Enter faculty name: ");
            name = scanner.nextLine();
            System.out.println("Enter faculty department: ");
            department = scanner.nextLine();
            System.out.println("Enter faculty abstract: ");
            facultyAbstract = scanner.nextLine();
            System.out.println("Enter faculty email: ");
            email = scanner.nextLine();
            insertFaculty(ID, name, department, facultyAbstract, email);
            break;
        case 6: // update entry
            System.out.println("Enter title: ");
            title = scanner.nextLine();
            System.out.println("Enter abstract: ");
            facultyAbstract = scanner.nextLine();
            updateEntry(title, facultyAbstract);
            break;
        case 7: // delete entry
            System.out.println("Enter title: ");
            title = scanner.nextLine();
            deleteEntry(title);
            break;
        default:
            System.out.println("Invalid choice!");
        }// end of switch
    }// end of method faculty_choice
    /*
     * public void signIn(Scanner scanner) { String userType = ""; // string used to
     * store whether it's a student or faculty member String email = ""; // string
     * used to store the email a user tries to log in with String password = ""; //
     * string used to store the password a user tries to log in with int loginResult
     * = 0; // int used to determine log in success, based on number of results from
     * search // query. 0 if it fails, 1 if it succeeeds. connect();
     * 
     * // determine if it's student or faculty member
     * System.out.println("Enter user type (student/faculty)"); userType =
     * scanner.nextLine();
     * 
     * // error handling while (!userType.equals("student") &&
     * !userType.equals("faculty")) { System.out.println("Invalid user type!");
     * System.out.println("Enter user type (student/faculty)"); userType =
     * scanner.nextLine(); } // end of while
     * 
     * // get login information from user System.out.println("Enter email: "); email
     * = scanner.nextLine(); System.out.println("Enter password: "); password =
     * scanner.nextLine();
     * 
     * // login depending on whether it is a student or faculty if
     * (userType.equals("student")) { loginResult = login_student(email, password);
     * } // end of if else { loginResult = login_faculty(email, password); } // end
     * of else if
     * 
     * // if the resultset returns an empty selection, the user information was //
     * incorrect while (loginResult == 0) {
     * System.out.println("Invalid login information!");
     * System.out.println("Enter email: "); email = scanner.nextLine();
     * System.out.println("Enter password: "); password = scanner.nextLine(); if
     * (userType == "student") { loginResult = login_student(email, password); } //
     * end of if else if (userType == "faculty") { loginResult =
     * login_faculty(email, password); } // end of else if } // end of while }
     */

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
        while (!userType.equals("student") && !userType.equals("faculty")) {
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
        if (userType.equals("student")) {
            loginResult = someObject.login_student(email, password);
        } // end of if
        else {
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

        // =============================== end of login information
        // ================================
        String input_choice = ""; // used to determine user's choice
        int choice = 0;

        // open student options for interacting with the database
        if (userType.equals("student")) {
            while (choice != 8) {
                someObject.student_menu();
                System.out.println("Enter choice: ");
                input_choice = scanner.nextLine();
                choice = Integer.parseInt(input_choice);
                someObject.student_choice(choice, scanner);
            }
        }
        // open faculty options for interacting with the database
        else if (userType.equals("faculty")) {
            while (choice != 8) {
                someObject.faculty_menu();
                System.out.println("Enter choice: ");
                input_choice = scanner.nextLine();
                choice = Integer.parseInt(input_choice);
                someObject.faculty_choice(choice, scanner);
            }
        }
        scanner.close(); // closes the scanner
    } // end of main
} // end of researchDatabase