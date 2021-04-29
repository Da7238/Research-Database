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
   private VBox rootNewWindow;

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

   private TextField facultyID;
   private TextField facultyName;
   private TextField department;
   private TextField abstrac;
   private TextField email;
   private TextField topicID;
   private TextField title;
   private TextField description;
   private TextField deleteTitle;
   private TextField author;
   private TextField datePub;
   private TextField articleID;

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
   private Button insertWinBtn;
   private Button updateWinBtn;

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
      // loginBtn.setOnAction(this);

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

         searchResult(result);
      });

      authorSearch.setOnAction(e -> {
         String result = db.searchAuthor(searchTF3.getText());

         searchResult(result);
      });

      emailSearch.setOnAction(e -> {
         String result = db.searchFacultyEmail(searchTF4.getText());

         searchResult(result);
      });

      nameSearch.setOnAction(e -> {
         String result = db.searchFacultyName(searchTF5.getText());

         searchResult(result);
      });

      fAbstractSearch.setOnAction(e -> {
         String result = db.searchFacultyAbstract(searchTF6.getText());

         searchResult(result);
      });

      departmentSearch.setOnAction(e -> {
         String result = db.searchDepartment(searchTF7.getText());

         searchResult(result);
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
      facultyDeleteEntry = new Label("Delete Article By Title");

      // Textfields
      searchTF1 = new TextField();
      searchTF2 = new TextField();
      searchTF3 = new TextField();
      searchTF4 = new TextField();
      deleteTitle = new TextField();

      // Button labels
      studentSearch = new Button("Search");
      majorSearch = new Button("Search");
      interestSearch = new Button("Search");
      emailSearch = new Button("Search");
      insertWinBtn = new Button("Insert New Faculty..");
      deleteBtn = new Button("Delete");
      updateWinBtn = new Button("Update Article..");

      // Create a new grid and flow
      gridPane = new GridPane();

      // Re-align the Label text to the center right.
      gridPane.setHalignment(labelSearch1, HPos.LEFT);
      gridPane.setHalignment(labelSearch2, HPos.LEFT);
      gridPane.setHalignment(labelSearch3, HPos.LEFT);
      gridPane.setHalignment(labelSearch4, HPos.LEFT);
      gridPane.setHalignment(facultyDeleteEntry, HPos.LEFT);
      gridPane.setVgap(10);

      // Render a grid panel
      gridPane.addRow(0, labelSearch1, searchTF1, studentSearch);
      gridPane.addRow(1, labelSearch2, searchTF2, majorSearch);
      gridPane.addRow(2, labelSearch3, searchTF3, interestSearch);
      gridPane.addRow(3, labelSearch4, searchTF4, emailSearch);
      gridPane.addRow(4, facultyDeleteEntry, deleteTitle, deleteBtn);
      gridPane.addRow(5, insertWinBtn, updateWinBtn);

      rootFacultyMenu.getChildren().addAll(titleLabel, gridPane);

      // Finally, put everything at center
      rootFacultyMenu.setAlignment(Pos.CENTER);
      gridPane.setAlignment(Pos.CENTER);

      // Render a new window page
      facultyScene = new Scene(rootFacultyMenu, 600, 500);
      stage.setScene(facultyScene);

      // Add button behaviors
      studentSearch.setOnAction(e -> {
         String result = db.searchStudentName(searchTF1.getText());

         searchResult(result);
      });
      majorSearch.setOnAction(e -> {
         String result = db.searchMajor(searchTF2.getText());

         searchResult(result);
      });
      interestSearch.setOnAction(e -> {
         String result = db.searchStudentInterest(searchTF3.getText());

         searchResult(result);
      });
      emailSearch.setOnAction(e -> {
         String result = db.searchStudentEmail(searchTF4.getText());

         searchResult(result);
      });
      insertWinBtn.setOnAction(e -> {
         insertArticleWindow();
      });
      deleteBtn.setOnAction(e -> {
         db.deleteEntry(deleteTitle.getText());
         // deleteEntryWindow();
         try {
            alert = new Alert(AlertType.INFORMATION, "Article has deleted.");
            alert.showAndWait();
         } catch (Exception ex) {
            alert = new Alert(AlertType.ERROR, ex.toString());
            alert.showAndWait();
         }
      });
      updateWinBtn.setOnAction(e -> {
         updateArticleWindow();
      });
   }

   public void login() {
      String email = emailTF.getText();
      String password = passwordTF.getText();

      // Check if there is a existing login information before accessing to the
      // database GUI
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

   // Launch the program
   public static void main(String[] args) {
      launch(args);
      System.exit(0);
   }

   // Open a new window to display search result
   public String searchResult(String result) {
      try {
         stage = new Stage();
         resultTA = new TextArea();
         rootNewWindow = new VBox(1);
         stage.setTitle("Search Result");
         resultTA.appendText(result);
         rootNewWindow.getChildren().addAll(resultTA);

         // Finally, put everything at center
         rootNewWindow.setAlignment(Pos.CENTER);

         // Render a new window page
         newWindowScene = new Scene(rootNewWindow, 800, 250);
         stage.setScene(newWindowScene);
         stage.show();
      } catch (Exception ex) {
         alert = new Alert(AlertType.ERROR, ex.toString());
         alert.showAndWait();
      }
      return result;
   }

   public void updateArticleWindow() {
      stage = new Stage();
      rootNewWindow = new VBox(1);
      stage.setTitle("Update Article");

      titleLabel = new Label("Update Article");
      titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 30px;");

      labelSearch1 = new Label("Topic ID");
      labelSearch2 = new Label("Title");
      labelSearch3 = new Label("AuthorID");
      labelSearch4 = new Label("Description");
      labelSearch5 = new Label("Date Published");
      labelSearch6 = new Label("Article ID");

      updateBtn = new Button("Update");

      topicID = new TextField();
      title = new TextField();
      author = new TextField();
      description = new TextField();
      datePub = new TextField();
      articleID = new TextField();

      gridPane = new GridPane();

      gridPane.addRow(0, labelSearch1, topicID);
      gridPane.addRow(2, labelSearch2, title);
      gridPane.addRow(3, labelSearch3, author);
      gridPane.addRow(4, labelSearch4, description);
      gridPane.addRow(5, labelSearch5, datePub);
      gridPane.addRow(6, labelSearch6, articleID);
      gridPane.addRow(7, updateBtn);

      gridPane.setHalignment(labelSearch1, HPos.LEFT);
      gridPane.setHalignment(labelSearch2, HPos.LEFT);

      rootNewWindow.getChildren().addAll(titleLabel, gridPane);

      // Finally, put everything at center
      rootNewWindow.setAlignment(Pos.CENTER);
      gridPane.setAlignment(Pos.CENTER);

      // Render a new window page
      newWindowScene = new Scene(rootNewWindow, 800, 350);
      stage.setScene(newWindowScene);
      stage.show();

      updateBtn.setOnAction(e -> {
         db.updateArticle(topicID.getText(), title.getText(), author.getText(), description.getText(), datePub.getText(), articleID.getText());

         try {
            alert = new Alert(AlertType.INFORMATION, "Article has updated successfully.");
            alert.showAndWait();
         } catch (Exception ex) {
            alert = new Alert(AlertType.ERROR, ex.toString());
            alert.showAndWait();
         }
      });
   }

   public void insertArticleWindow() {
      stage = new Stage();
      rootNewWindow = new VBox(1);
      stage.setTitle("Insert New Article");

      titleLabel = new Label("Insert New Article");
      titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 30px;");

      labelSearch1 = new Label("Article ID");
      labelSearch2 = new Label("Topic ID");
      labelSearch3 = new Label("Title");
      labelSearch4 = new Label("Author");
      labelSearch5 = new Label("Article Description");
      labelSearch6 = new Label("Date Published");

      insertBtn = new Button("Insert");

      articleID = new TextField();
      topicID = new TextField();
      title = new TextField();
      author = new TextField();
      description = new TextField();
      datePub = new TextField();

      gridPane = new GridPane();

      gridPane.addRow(0, labelSearch1, articleID);
      gridPane.addRow(1, labelSearch2, topicID);
      gridPane.addRow(2, labelSearch3, title);
      gridPane.addRow(3, labelSearch4, author);
      gridPane.addRow(4, labelSearch5, description);
      gridPane.addRow(5, labelSearch6, datePub);
      gridPane.addRow(6, insertBtn);

      gridPane.setHalignment(labelSearch1, HPos.LEFT);
      gridPane.setHalignment(labelSearch2, HPos.LEFT);
      gridPane.setHalignment(labelSearch3, HPos.LEFT);
      gridPane.setHalignment(labelSearch4, HPos.LEFT);
      gridPane.setHalignment(labelSearch5, HPos.LEFT);
      gridPane.setHalignment(labelSearch6, HPos.LEFT);

      rootNewWindow.getChildren().addAll(titleLabel, gridPane);

      // Finally, put everything at center
      rootNewWindow.setAlignment(Pos.CENTER);
      gridPane.setAlignment(Pos.CENTER);

      // Render a new window page
      newWindowScene = new Scene(rootNewWindow, 800, 250);
      stage.setScene(newWindowScene);
      stage.show();

      insertBtn.setOnAction(e -> {
         db.insertArticle(articleID.getText(), topicID.getText(), title.getText(), author.getText(),
               description.getText(), datePub.getText());

         try {
            alert = new Alert(AlertType.INFORMATION, "New faculty has been inserted successfully.");
            alert.showAndWait();
         } catch (Exception ex) {
            alert = new Alert(AlertType.ERROR, ex.toString());
            alert.showAndWait();
         }
      });
   }

   public void deleteEntryWindow() {
      stage = new Stage();
      rootNewWindow = new VBox(1);
      stage.setTitle("Delete entry");

      titleLabel = new Label("Delete article entry");
      titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 30px;");

      labelSearch1 = new Label("Aritcle title");

      deleteBtn = new Button("Delete");

      deleteTitle = new TextField();

      gridPane = new GridPane();

      gridPane.addRow(0, labelSearch1, deleteTitle);
      gridPane.setHalignment(labelSearch1, HPos.LEFT);

      rootNewWindow.getChildren().addAll(titleLabel, gridPane);

      // Finally, put everything at center
      rootNewWindow.setAlignment(Pos.CENTER);
      gridPane.setAlignment(Pos.CENTER);

      newWindowScene = new Scene(rootNewWindow, 800, 250);
      stage.setScene(newWindowScene);
      stage.show();

      deleteBtn.setOnAction(e -> {
         db.deleteEntry(deleteTitle.getText());

         try {
            alert = new Alert(AlertType.INFORMATION, "Article has been deleted.");
            alert.showAndWait();
         } // end of try
         catch (Exception ex) {
            alert = new Alert(AlertType.ERROR, ex.toString());
            alert.showAndWait();
         } // end of catch
      }); // end of deleteBtn.setOnAction

   }// end of deleteEntryWindow

}