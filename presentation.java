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


public class presentation extends Application implements EventHandler<ActionEvent> {
   // Use a external java file to perform backend tasks
   researchDatabase db = new researchDatabase();
   
   private Alert alert;
   private Scanner scanner = new Scanner(System.in);

   private Stage stage;
   private Scene scene;
   
   //Student menu
   private Stage studentstage;
   private Scene studentScene;
   
   //Faculty menu
   private Stage facultystage;
   private Scene facultyScene;
   
   private VBox root = new VBox(1);
   private GridPane gridPane = new GridPane();
   //private FlowPane flowPane = new FlowPane(8,8);
   
   private Label titleLabel;
   private Label emailLabel = new Label("Email:");
   private Label passwordLabel = new Label("Password:");

   private TextField emailTF = new TextField();
   private PasswordField passwordTF = new PasswordField();
   
   private Button loginBtn = new Button("Login");
   
   // Label, TextFile, Button for student.
   private Label studentOptions;
   private Label studentSearch1 = new Label("Search Article By Title");
   private Label studentSearch2 = new Label("Search Article By Abstract");
   private Label studentSearch3 = new Label("Search Article By Author");
   private Label studentSearch4 = new Label("Search Faculty By Email");
   private Label studentSearch5 = new Label("Search Faculty By Name");
   private Label studentSearch6 = new Label("Search Faculty By Abstract");
   private Label studentSearch7 = new Label("Search Faculty By Department");
   
   private TextField studentText1 = new TextField();
   private TextField studentText2 = new TextField();
   private TextField studentText3 = new TextField();
   private TextField studentText4 = new TextField();
   private TextField studentText5 = new TextField();
   private TextField studentText6 = new TextField();
   private TextField studentText7 = new TextField();

   
   // Label, TextFile, Button for student.
   private Label facultyOptions;
   private Label facultySearch1 = new Label("Search Student By Name");
   private Label facultySearch2 = new Label("Search Student By Major");
   private Label facultySearch3 = new Label("Search Student By Interest");
   private Label facultySearch4 = new Label("Search Student By Email");
   private Label facultyInsert = new Label("Insert Faculty");
   private Label facultyDeleteEntry = new Label("Delete Article By Title");
   private Label facultyUpdateTitle = new Label("Update Article Title");
   
   private TextField facultyText1 = new TextField();
   private TextField facultyText2 = new TextField();
   private TextField facultyText3 = new TextField();
   private TextField facultyText4 = new TextField();
   private TextField facultyText5 = new TextField();
   private TextField facultyText6 = new TextField();
   private TextField facultyText7 = new TextField();

   private Button searchBtn = new Button("Search");
   private Button insertBtn = new Button("Insert");
   private Button deleteBtn = new Button("delete");
   private Button updateBtn = new Button("update");
   
   // Constructor
   public void start(Stage _stage) throws Exception {
      db.connect(); // Connect to the database before render window
      
      stage = _stage;
      stage.setTitle("Research Database");
      
      // Title label settings
      titleLabel = new Label("Login");
      titleLabel.setMinHeight(80);
      titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 30px;");
      
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
      scene = new Scene(root, 500, 350);
      stage.setScene(scene);
      
      // Add button behaviors
      loginBtn.setOnAction(this);
      
      stage.show();
   }
   
   
   public void student(Stage _studentstage) throws Exception {
      db.connect(); // Connect to the database before render window
      
      stage = _studentstage;
      stage.setTitle("Research Database");
      
      // Title label settings
      titleLabel = new Label("student optin menu");
   
      titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 30px;");
      
      // Re-align the Label text to the center right.      
      gridPane.setHalignment(studentSearch1, HPos.LEFT);
      gridPane.setHalignment(studentSearch2, HPos.RIGHT);
      gridPane.setHalignment(studentSearch3, HPos.LEFT);
      gridPane.setHalignment(studentSearch4, HPos.RIGHT);
      gridPane.setHalignment(studentSearch5, HPos.LEFT);
      gridPane.setHalignment(studentSearch6, HPos.RIGHT);
      gridPane.setHalignment(studentSearch7, HPos.LEFT);
      
      // Render a grid panel
      gridPane.addRow(0, studentSearch1, studentText1);
      gridPane.addRow(1, studentSearch2, studentText2);
      gridPane.addRow(2, studentSearch3, studentText3);
      gridPane.addRow(3, studentSearch4, studentText4);
      gridPane.addRow(4, studentSearch5, studentText5);
      gridPane.addRow(5, studentSearch6, studentText6);
      gridPane.addRow(6, studentSearch7, studentText7);
      
      root.getChildren().addAll(studentOptions, gridPane, searchBtn);
      
      // Finally, put everything at center
      root.setAlignment(Pos.CENTER);
      gridPane.setAlignment(Pos.CENTER);
      
      // Render window
      scene = new Scene(root, 500, 350);
      stage.setScene(scene);
      
      // Add button behaviors
      searchBtn.setOnAction(this);
      
      stage.show();
   }
   
   public void faculty(Stage _facultystage) throws Exception {
      db.connect(); // Connect to the database before render window
      
      stage = _facultystage;
      stage.setTitle("Research Database");
      
      // Title label settings
      titleLabel = new Label("Faculty Option menu");
   
      titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 30px;");
      
      // Re-align the Label text to the center right.      
      gridPane.setHalignment(facultySearch1, HPos.LEFT);
      gridPane.setHalignment(facultySearch2, HPos.RIGHT);
      gridPane.setHalignment(facultySearch3, HPos.LEFT);
      gridPane.setHalignment(facultySearch4, HPos.RIGHT);
      gridPane.setHalignment(facultyInsert, HPos.LEFT);
      gridPane.setHalignment(facultyDeleteEntry, HPos.RIGHT);
      gridPane.setHalignment(facultyUpdateTitle, HPos.LEFT);
      
      // Render a grid panel
      gridPane.addRow(0, facultySearch1, facultyText1);
      gridPane.addRow(1, facultySearch2, facultyText2);
      gridPane.addRow(2, facultySearch3, facultyText3);
      gridPane.addRow(3, facultySearch4, facultyText4);
      gridPane.addRow(4, facultyInsert, facultyText5);
      gridPane.addRow(5, facultyDeleteEntry, facultyText6);
      gridPane.addRow(6, facultyUpdateTitle, facultyText7);
      
      root.getChildren().addAll(facultyOptions, gridPane, searchBtn);
      root.getChildren().addAll(facultyOptions, gridPane, insertBtn);
      root.getChildren().addAll(facultyOptions, gridPane, deleteBtn);
      root.getChildren().addAll(facultyOptions, gridPane, deleteBtn);
      
      // Finally, put everything at center
      root.setAlignment(Pos.CENTER);
      gridPane.setAlignment(Pos.CENTER);
      
      // Render window
      scene = new Scene(root, 500, 350);
      stage.setScene(scene);
      
      // Add button behaviors
      searchBtn.setOnAction(this);
      insertBtn.setOnAction(this);
      updateBtn.setOnAction(this);
      deleteBtn.setOnAction(this);
      
      stage.show();
   }
   
   

   // Button pressed events
   public void handle(ActionEvent evt) {
      Button btn = (Button)evt.getSource();
      
      // Login button (Used alert for testing purpose)
      switch(btn.getText()) {
         case "Login":
            login();
            break;
         case "Student":
            student();
            break;
         case "Faculty":
      }
   }
   
   
   public void login() {
      String email = emailTF.getText();
      String password = passwordTF.getText();
      
      // Using student login for now.
      int loginInfo = db.login_student(email, password);
      
      // Testing purpose, might keep this.
      if (loginInfo == 1) {
         alert = new Alert(AlertType.INFORMATION, "Logged in!");
         alert.showAndWait();
      }
      else {
         alert = new Alert(AlertType.INFORMATION, "Login error");
         alert.showAndWait();
      }
   }
   
   // student menu
   public void student() {
   String studentSearch1 = studentText1.getText();
   String studentSearch2 = studentText2.getText();
   String studentSearch3 = studentText3.getText();
   String studentSearch4 = studentText4.getText();
   String studentSearch5 = studentText5.getText();
   String studentSearch6 = studentText6.getText();
   String studentSearch7 = studentText7.getText();
   
   String searchArticle = db.searchArticle(studentSearch1);
   
   }
   
   // faculty menu
   public void faculty() {
   String facultySearch1 = facutlyText1.getText();
   String facultySearch2 = facutlyText2.getText();
   String facultySearch3 = facutlyText3.getText();
   String facultySearch4 = facutlyText4.getText();
   String facultyInsert = facutlyText5.getText();
   String facultyDeleteEntry = facutlyText6.getText();
   String facultyUpdateTitle = facutlyText7.getText();
   
   String facultydelte = db.deleteEntry(facultyText6);
   }
   
   // Launch the program
   public static void main(String[] args) {
      launch(args);
      System.exit(0);
   }
   
   
   

// -------------------------------------------

   // TO BE ASSIGNED
   // Main menu for student after login
   public void studentMenu() {
   System.exit(0);
      // panel.setLayout(new GridLayout(2, 1));
//       student = new JButton("Student");
// 
//       student.addActionListener(new ActionListener() {
// 
//          @Override
//          public void actionPerformed(ActionEvent e) {
//             researchDatabase.student_menu();
// 
//          }
// 
//       });

   }

   // TO BE ASSIGNED
   // Main menu for faculty after login
   public void facultyMenu() {
System.exit(0);
   }

   // TO BE ASSIGNED
   // Search bar to look up for author, student, faculty, etc
   public void searchBar() {
System.exit(0);
   }

   // TO BE ASSIGNED
   // View a article page with title, content, and author name
   public void articlePage() {
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