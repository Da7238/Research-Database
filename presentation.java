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

   private VBox root = new VBox(1);
   private GridPane gridPane = new GridPane();
   //private FlowPane flowPane = new FlowPane(8,8);
   
   private Label titleLabel;
   private Label emailLabel = new Label("Email:");
   private Label passwordLabel = new Label("Password:");
   
   private TextField emailTF = new TextField();
   private PasswordField passwordTF = new PasswordField();
   
   private Button loginBtn = new Button("Login");

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

   // Button pressed events
   public void handle(ActionEvent evt) {
      Button btn = (Button)evt.getSource();
      
      // Login button (Used alert for testing purpose)
      switch(btn.getText()) {
         case "Login":
            login();
            break;
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