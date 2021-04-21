// Research Database Project 2021 ISTE 330
// Darlene Ardila
// Colton Bailiff
// Weijie Chen
// Ben Donahue

import java.util.*;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class presentation extends JFrame {
   // Use a external java file to perform backend tasks
   researchDatabase db = new researchDatabase();

   private JFrame frame;
   private JPanel panel;
   private JButton student;
   private JButton faculty;

   // GUI Private Variables
   // TextField
   // TextArea
   // Label
   // Search bar
   // Drop list

   // ALL GROUP MEMBERS ARE ASSIGNED TO EDIT THIS METHOD
   // Constructor
   public presentation() {
      // Login page
      // db.connect(); // Connect to the database
   }

   // ALL GROUP MEMBERS ARE ASSIGNED TO EDIT THIS METHOD
   // Button pressed events
   public void handle(ActionEvent evt) {
      // Use switch here for each buttons
   }

   // TO BE ASSIGNED
   // Main menu for student after login
   public void studentMenu() {
      panel.setLayout(new GridLayout(2, 1));
      student = new JButton("Student");

      student.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            researchDatabase.student_menu();

         }

      });

   }

   // TO BE ASSIGNED
   // Main menu for faculty after login
   public void facultyMenu() {

   }

   // TO BE ASSIGNED
   // Search bar to look up for author, student, faculty, etc
   public void searchBar() {

   }

   // TO BE ASSIGNED
   // View a article page with title, content, and author name
   public void articlePage() {

   }

   // TO BE ASSIGNED
   // Edit a article
   public void editArticlePage() {

   }

   // TO BE ASSIGNED
   // Add a faculty
   public void addFacultyPage() {

   }

   // Launch the program
   public static void main(String[] args) {
      new presentation();
      System.exit(0);
   }
}