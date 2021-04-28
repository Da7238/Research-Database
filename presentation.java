// Research Database Project 2021 ISTE 330
// Darlene Ardila
// Colton Bailiff
// Weijie Chen
// Ben Donahue

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;

import java.util.*;
import java.util.Scanner;
import javax.swing.*;


public class presentation extends Application {
   // Use a external java file to perform backend tasks
   researchDatabase db = new researchDatabase();
   
   private Alert alert;
   private Scanner scanner = new Scanner(System.in);

   private Stage stage;
   private Scene scene, studentScene, facultyScene, newWindowScene;
   
   private VBox root = new VBox(1);
   private VBox rootStudentMenu = new VBox(1);
   private VBox rootFacultyMenu = new VBox(1);
   private VBox rootNewWindow = new VBox(1);
   
   
   private GridPane gridPane;
   private FlowPane flowPane;
   
   private Label titleLabel;
   private Label emailLabel;
   private Label passwordLabel;

   private TextField emailTF = new TextField();
   private PasswordField passwordTF = new PasswordField();
   
   private Button loginBtn;
   
   // Labels, TextFields, and Buttons for student and faculty.
   private Label labelSearch1;
   private Label labelSearch2;
   private Label labelSearch3;
   private Label labelSearch4;
   private Label labelSearch5;
   private Label labelSearch6;
   private Label labelSearch7;
   private Label facultyInsert;
   private Label facultyDeleteEntry;
   private Label facultyUpdateTitle;
   
   private Label articleLabel;
   
   private TextField searchTF1;
   private TextField searchTF2;
   private TextField searchTF3;
   private TextField searchTF4;
   private TextField searchTF5;
   private TextField searchTF6;
   private TextField searchTF7;
   
   private TextArea resultTA;
   
   private Button articleSearch;
   private Button authorSearch;
   private Button emailSearch;
   private Button nameSearch;
   private Button fAbstractSearch;
   private Button departmentSearch;
   private Button studentSearch;
   private Button majorSearch;
   private Button interestSearch;

   private Button searchBtn;
   private Button insertBtn;
   private Button deleteBtn;
   private Button updateBtn;
   
   // Constructor
   public void start(Stage _stage) throws Exception {
      db.connect(); // Connect to the database before render window
      
      stage = _stage;
      stage.setTitle("Research Database");
      
      // Title label settings
      titleLabel = new Label("Login");
      titleLabel.setMinHeight(80);
      titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 30px;");
      
      // Label settings
      emailLabel = new Label("Email:");
      passwordLabel = new Label("Password:");
      
      // Button label
      loginBtn = new Button("Login");
      loginBtn.setOnAction(value -> {
         login();
      });
      
      // Create a new grid
      gridPane = new GridPane();

      // Re-align the Label text to the center right.      
      gridPane.setHalignment(emailLabel, HPos.RIGHT);
      gridPane.setHalignment(passwordLabel, HPos.RIGHT);
      
      // Render a grid panel
      gridPane.addRow(0, emailLabel, emailTF);
      gridPane.addRow(1, passwordLabel, passwordTF);
      
      root.getChildren().addAll(titleLabel, gridPane, loginBtn);
      
      // Finally, put everything at center
      root.setAlignment(Pos.CENTER);
      gridPane.setAlignment(Pos.CENTER);
      
      // Render window
      scene = new Scene(root, 600, 450);
      stage.setScene(scene);
      
      // Add button behaviors
      //loginBtn.setOnAction(this);
      
      stage.show();
   }
   
   // Student Main Menu
   public void studentMenu() {
      // Title label settings
      titleLabel = new Label("Student Main Menu");
      titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 30px;");
      
      // Search label settings
      labelSearch1 = new Label("Search Article By Title");
      labelSearch2 = new Label("Search Article By Abstract");
      labelSearch3 = new Label("Search Article By Author");
      labelSearch4 = new Label("Search Faculty By Email");
      labelSearch5 = new Label("Search Faculty By Name");
      labelSearch6 = new Label("Search Faculty By Abstract");
      labelSearch7 = new Label("Search Faculty By Department");
      
      // Textfields
      searchTF1 = new TextField();
      searchTF3 = new TextField();
      searchTF4 = new TextField();
      searchTF5 = new TextField();
      searchTF6 = new TextField();
      searchTF7 = new TextField();
      
      // Button labels
      articleSearch = new Button("Search");
      authorSearch = new Button("Search");
      emailSearch = new Button("Search");
      nameSearch = new Button("Search");
      fAbstractSearch = new Button("Search");
      departmentSearch = new Button("Search");

      
      // Create a new grid
      gridPane = new GridPane();
      
      // Re-align the Label text to the center right.      
      gridPane.setHalignment(labelSearch1, HPos.LEFT);
      gridPane.setHalignment(labelSearch3, HPos.LEFT);
      gridPane.setHalignment(labelSearch4, HPos.LEFT);
      gridPane.setHalignment(labelSearch5, HPos.LEFT);
      gridPane.setHalignment(labelSearch6, HPos.LEFT);
      gridPane.setHalignment(labelSearch7, HPos.LEFT);
      gridPane.setVgap(10);
      
      // Render a grid panel
      gridPane.addRow(0, labelSearch1, searchTF1, articleSearch);
      gridPane.addRow(2, labelSearch3, searchTF3, authorSearch);
      gridPane.addRow(3, labelSearch4, searchTF4, emailSearch);
      gridPane.addRow(4, labelSearch5, searchTF5, nameSearch);
      gridPane.addRow(5, labelSearch6, searchTF6, fAbstractSearch);
      gridPane.addRow(6, labelSearch7, searchTF7, departmentSearch);
      
      rootStudentMenu.getChildren().addAll(titleLabel, gridPane);
      
      // Finally, put everything at center
      rootStudentMenu.setAlignment(Pos.CENTER);
      gridPane.setAlignment(Pos.CENTER);
      
      // Render a new window page
      studentScene = new Scene(rootStudentMenu, 600, 450);
      stage.setScene(studentScene);
      
      
      // Add button behaviors
      articleSearch.setOnAction(e -> {
         String result = db.searchArticle(searchTF1.getText());
         
         stage = new Stage();
         resultTA = new TextArea();
         stage.setTitle("Search Result");
         resultTA.appendText(result);
         rootNewWindow.getChildren().addAll(resultTA);
         
         // Finally, put everything at center
         rootNewWindow.setAlignment(Pos.CENTER);
         
         // Render a new window page
         newWindowScene = new Scene(rootNewWindow, 800, 250);
         stage.setScene(newWindowScene);
         stage.show();
         });
         
      authorSearch.setOnAction(e -> {
         String result = db.searchAuthor(searchTF3.getText());
         
         stage = new Stage();
         resultTA = new TextArea();
         stage.setTitle("Search Result");
         resultTA.appendText(result);
         rootNewWindow.getChildren().addAll(resultTA);
         
         // Finally, put everything at center
         rootNewWindow.setAlignment(Pos.CENTER);
         
         // Render a new window page
         newWindowScene = new Scene(rootNewWindow, 800, 250);
         stage.setScene(newWindowScene);
         stage.show();
          });
          
      emailSearch.setOnAction(e -> {
         String result = db.searchFacultyEmail(searchTF4.getText());
         
         stage = new Stage();
         resultTA = new TextArea();
         stage.setTitle("Search Result");
         resultTA.appendText(result);
         rootNewWindow.getChildren().addAll(resultTA);
         
         // Finally, put everything at center
         rootNewWindow.setAlignment(Pos.CENTER);
         
         // Render a new window page
         newWindowScene = new Scene(rootNewWindow, 800, 250);
         stage.setScene(newWindowScene);
         stage.show();
         });
         
      nameSearch.setOnAction(e -> {
         String result = db.searchFacultyName(searchTF5.getText());
         
         stage = new Stage();
         resultTA = new TextArea();
         stage.setTitle("Search Result");
         resultTA.appendText(result);
         rootNewWindow.getChildren().addAll(resultTA);
         
         // Finally, put everything at center
         rootNewWindow.setAlignment(Pos.CENTER);
         
         // Render a new window page
         newWindowScene = new Scene(rootNewWindow, 800, 250);
         stage.setScene(newWindowScene);
         stage.show();
         });
         
      fAbstractSearch.setOnAction(e -> {
         String result = db.searchFacultyAbstract(searchTF6.getText());
         
         stage = new Stage();
         resultTA = new TextArea();
         stage.setTitle("Search Result");
         resultTA.appendText(result);
         rootNewWindow.getChildren().addAll(resultTA);
         
         // Finally, put everything at center
         rootNewWindow.setAlignment(Pos.CENTER);
         
         // Render a new window page
         newWindowScene = new Scene(rootNewWindow, 800, 250);
         stage.setScene(newWindowScene);
         stage.show();
         });
         
      departmentSearch.setOnAction(e -> {
         String result = db.searchDepartment(searchTF7.getText());
         
         stage = new Stage();
         resultTA = new TextArea();
         stage.setTitle("Search Result");
         resultTA.appendText(result);
         rootNewWindow.getChildren().addAll(resultTA);
         
         // Finally, put everything at center
         rootNewWindow.setAlignment(Pos.CENTER);
         
         // Render a new window page
         newWindowScene = new Scene(rootNewWindow, 800, 250);
         stage.setScene(newWindowScene);
         stage.show();
         });
   }
   
   // Faculty Main Menu
   public void facultyMenu() {
      // Title label settings
      titleLabel = new Label("Faculty Main Menu");
      titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 30px;");
      
      // Search label settings
      labelSearch1 = new Label("Search Student By Name");
      labelSearch2 = new Label("Search Student By Major");
      labelSearch3 = new Label("Search Student By Interest");
      labelSearch4 = new Label("Search Student By Email");
      facultyInsert = new Label("Insert Faculty");
      facultyDeleteEntry = new Label("Delete Article By Title");
      facultyUpdateTitle = new Label("Update Article Title");
      
      // Textfields
      searchTF1 = new TextField();
      searchTF2 = new TextField();
      searchTF3 = new TextField();
      searchTF4 = new TextField();
      searchTF5 = new TextField();
      searchTF6 = new TextField();
      searchTF7 = new TextField();
      
      // Button labels
      studentSearch = new Button("Search");
      majorSearch = new Button("Search");
      interestSearch = new Button("Search");
      emailSearch = new Button("Search");
      insertBtn = new Button("Insert");
      deleteBtn = new Button("Delete");
      updateBtn = new Button("Update");
      
      // Create a new grid and flow
      gridPane = new GridPane();
      flowPane = new FlowPane(8,8);
      
      // Re-align the Label text to the center right.      
      gridPane.setHalignment(labelSearch1, HPos.LEFT);
      gridPane.setHalignment(labelSearch2, HPos.LEFT);
      gridPane.setHalignment(labelSearch3, HPos.LEFT);
      gridPane.setHalignment(labelSearch4, HPos.LEFT);
      gridPane.setHalignment(facultyInsert, HPos.LEFT);
      gridPane.setHalignment(facultyDeleteEntry, HPos.LEFT);
      gridPane.setHalignment(facultyUpdateTitle, HPos.LEFT);
      gridPane.setVgap(10);
      
      // Render a grid panel
      gridPane.addRow(0, labelSearch1, searchTF1, studentSearch);
      gridPane.addRow(1, labelSearch2, searchTF2, majorSearch);
      gridPane.addRow(2, labelSearch3, searchTF3, interestSearch);
      gridPane.addRow(3, labelSearch4, searchTF4, emailSearch);
      gridPane.addRow(4, facultyInsert, searchTF5, insertBtn);
      gridPane.addRow(5, facultyDeleteEntry, searchTF6, deleteBtn);
      gridPane.addRow(6, facultyUpdateTitle, searchTF7, updateBtn);
      
      rootFacultyMenu.getChildren().addAll(titleLabel, gridPane, flowPane);
      
      // Finally, put everything at center
      rootFacultyMenu.setAlignment(Pos.CENTER);
      gridPane.setAlignment(Pos.CENTER);
      flowPane.setAlignment(Pos.CENTER);
      
      // Render a new window page
      facultyScene = new Scene(rootFacultyMenu, 600, 450);
      stage.setScene(facultyScene);
      
      // Add button behaviors
      studentSearch.setOnAction(e -> {
         String result = db.searchStudentName(searchTF1.getText());
      });
      majorSearch.setOnAction(e -> {
         String result = db.searchMajor(searchTF2.getText());
      });
      interestSearch.setOnAction(e -> {
         String result = db.searchStudentInterest(searchTF3.getText());
      });
      emailSearch.setOnAction(e -> {
         String result = db.searchStudentEmail(searchTF4.getText());
      });
      insertBtn.setOnAction(e -> {
         searchResult();
      });
      deleteBtn.setOnAction(e -> {
         facultySearch();
      });
      updateBtn.setOnAction(e -> {
         facultySearch();
      });
   }
   
   public void login() {
      String email = emailTF.getText();
      String password = passwordTF.getText();
      
      // Check if there is a existing login information before accessing to the database GUI
      // Student login
      if (db.login_student(email, password) == 1 && db.login_faculty(email, password) == 0) {
         studentMenu();
      }
      // Faculty login
      else if (db.login_student(email, password) == 0 && db.login_faculty(email, password) == 1) {
         facultyMenu();
      }
      // If not exist, display a dialog message
      else {
         alert = new Alert(AlertType.WARNING, "LOGIN ERROR");
         alert.showAndWait();
      }
   }
   

   // Student Search functionality
   public void studentSearch() {
      String studentSearch1 = searchTF1.getText();
      /*String studentSearch2 = searchTF2.getText();
      String studentSearch3 = searchTF3.getText();
      String studentSearch4 = searchTF4.getText();
      String studentSearch5 = searchTF5.getText();
      String studentSearch6 = searchTF6.getText();
      String studentSearch7 = searchTF7.getText();*/
      String searchArticle = db.searchArticle(studentSearch1);
   
   //String searchArticle = db.searchArticle(studentSearch1);
   }
   
   // Faculty Search functionality
   public void facultySearch() {
   String facultySearch1 = searchTF1.getText();
   String facultySearch2 = searchTF2.getText();
   String facultySearch3 = searchTF3.getText();
   String facultySearch4 = searchTF4.getText();
   String facultyInsert = searchTF5.getText();
   String facultyDeleteEntry = searchTF6.getText();
   String facultyUpdateTitle = searchTF7.getText();
   
   //String facultyDeleteEntry = db.deleteEntry(facultyText6);
   }
   
   // Launch the program
   public static void main(String[] args) {
      launch(args);
      System.exit(0);
   }
   
   
   

// -------------------------------------------

// -------------------------------------------

   // Open a new window to display search result
   public void searchResult() {
      stage = new Stage();
      resultTA = new TextArea();
      
      stage.setTitle("Search Result");
      
      //resultTA.appendText(db.searchArticle());
      
      rootNewWindow.getChildren().addAll(resultTA);
      
      // Finally, put everything at center
      rootNewWindow.setAlignment(Pos.CENTER);
      
      // Render a new window page
      newWindowScene = new Scene(rootNewWindow, 650, 250);
      stage.setScene(newWindowScene);
      stage.show();
   }

   // TO BE ASSIGNED
   // View a article page with title, content, and author name
   public void articlePage(String result) {       
       /*titleLabel = new Label("Results");
       titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 30px;");
       
       labelArticleTitle = new Label("Article Title:");
       labelTopic = new Label("Topic:");
       labelDescription = new Label("Description:")
       labelAuthor = new Label("Author:"); 
       
       gridPane = new GridPane();
       gridPane.setHgap(5); 
       gridPane.setAlignment(Pos.CENTER);
       
       gridPane.add(titleLabel, 0, 0);
       gridPane.add(labelArticleTitle, 0, 1);
       gridPane.add(labelTopic, 1, 1);
       gridPane.add(labelDescription, 2, 1);
       gridPane.add(labelAuthor, 3, 1);
       
       int i = 0;
       String results[] = result.split("|");*/
       /*iterate over the results to build entries. Because each result string contains 4 piece of information and
         splitting it by "|" with an empty value at the start and end of each result, increment by 6 and use array
         positions 1, 2, 3, and 4.*/
       /*for(i = 0; i < results.length; i+= 6) {
           gridPane.addRow(results[i + 1], results[i + 2], results[i + 3], results[i + 4]);
       }*/
       
        
       System.exit(0);
   }

   // TO BE ASSIGNED
   // Edit a article
   public void editArticlePage() {
System.exit(0);
   }

   // TO BE ASSIGNED
   // Add a faculty
   public void addFacultyPage() {
System.exit(0);
   }
}