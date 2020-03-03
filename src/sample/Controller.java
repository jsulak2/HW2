package sample;

import com.jfoenix.controls.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.*;
import java.net.FileNameMap;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.UUID;


public class Controller implements Initializable
{
    @FXML
    JFXButton createButton;
    @FXML
    JFXButton popButton;
    @FXML
    JFXButton delButton;
    @FXML
    JFXButton filterButton;
    @FXML
    JFXProgressBar progressBar;
    @FXML
    JFXListView studentListView;
    @FXML
    JFXTextField nameText;
    @FXML
    JFXTextField ageText;
    @FXML
    JFXTextField majorText;
    @FXML
    JFXTextField GPAText;
    @FXML
    JFXTreeTableView tableView;
    @FXML
    JFXTreeTableRow stud1;
    @FXML
    JFXButton filterNameBut;
    @FXML
    JFXButton filterAgeBut;
    @FXML
    JFXButton filterMajorBut;
    @FXML
    JFXButton filterGPABut;


    final String hostname = "studentdb.cchu0uapco1s.us-east-1.rds.amazonaws.com";
    final String dbName = "studentdb";
    final String port = "3306";
    final String username = "admin";
    final String password = "password";
    final String AWS_URL = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + username + "&password=" + password;


    private void createDatabase(String url)
    {
        try{

            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();

            try
            {
                stmt.execute("CREATE TABLE Student (" +
                        "StudName VARCHAR(36), " +
                        "ID VARCHAR(36), " +
                        "Age VARCHAR(36), " +
                        "Major VARCHAR(36), " +
                        "GPA VARCHAR(36))");

                System.out.println("TABLE CREATED");
            }
            catch (Exception ex)
            {
                System.out.println("TABLE ALREADY EXISTS, NOT CREATED");
            }
//1
            Student stud = new Student("Bob Smith", "20", "General Studies", "3.4");
            String sql = "INSERT INTO Student VALUES" +
                    "('" + stud.getname() + "', '" + stud.getid() + "', '" + stud.getage() + "', '" + stud.getmajor() +"', '" + stud.getgpa() + "')";
            stmt.executeUpdate(sql);
//2
            stud = new Student("Sue Rodriguez", "21", "Biology", "2.9");
            sql = "INSERT INTO Student VALUES" +
                    "('" + stud.getname() + "', '" + stud.getid() + "', '" + stud.getage() + "', '" + stud.getmajor() +"', '" + stud.getgpa() + "')";
            stmt.executeUpdate(sql);
//3
            stud = new Student("Angelina Lopez", "21", "Chemistry", "3.1");
            sql = "INSERT INTO Student VALUES" +
                    "('" + stud.getname() + "', '" + stud.getid() + "', '" + stud.getage() + "', '" + stud.getmajor() +"', '" + stud.getgpa() + "')";
            stmt.executeUpdate(sql);
//4
            stud = new Student("Brianna Jordan", "18", "General Studies", "3.8");
            sql = "INSERT INTO Student VALUES" +
                    "('" + stud.getname() + "', '" + stud.getid() + "', '" + stud.getage() + "', '" + stud.getmajor() +"', '" + stud.getgpa() + "')";
            stmt.executeUpdate(sql);
//5
            stud = new Student("Cassie Schneider", "25", "Business", "4.0");
            sql = "INSERT INTO Student VALUES" +
                    "('" + stud.getname() + "', '" + stud.getid() + "', '" + stud.getage() + "', '" + stud.getmajor() +"', '" + stud.getgpa() + "')";
            stmt.executeUpdate(sql);
//6
            stud = new Student("Samantha Friend", "20", "Biology", "3.4");
            sql = "INSERT INTO Student VALUES" +
                    "('" + stud.getname() + "', '" + stud.getid() + "', '" + stud.getage() + "', '" + stud.getmajor() +"', '" + stud.getgpa() + "')";
            stmt.executeUpdate(sql);
//7
            stud = new Student("Olaf Frozen", "20", "Biology", "3.4");
            sql = "INSERT INTO Student VALUES" +
                    "('" + stud.getname() + "', '" + stud.getid() + "', '" + stud.getage() + "', '" + stud.getmajor() +"', '" + stud.getgpa() + "')";
            stmt.executeUpdate(sql);
//8
            stud = new Student("Angelina Jeffories", "34", "Business", "3.7");
            sql = "INSERT INTO Student VALUES" +
                    "('" + stud.getname() + "', '" + stud.getid() + "', '" + stud.getage() + "', '" + stud.getmajor() +"', '" + stud.getgpa() + "')";
            stmt.executeUpdate(sql);
//9
            stud = new Student("Alex Lifeson", "26", "Engineering", "2.9");
            sql = "INSERT INTO Student VALUES" +
                    "('" + stud.getname() + "', '" + stud.getid() + "', '" + stud.getage() + "', '" + stud.getmajor() +"', '" + stud.getgpa() + "')";
            stmt.executeUpdate(sql);
//10
            stud = new Student("Justin Case", "21", "Computer Science", "3.2");
            sql = "INSERT INTO Student VALUES" +
                    "('" + stud.getname() + "', '" + stud.getid() + "', '" + stud.getage() + "', '" + stud.getmajor() +"', '" + stud.getgpa() + "')";
            stmt.executeUpdate(sql);


            System.out.println("TABLE FILLED");

            stmt.close();
            conn.close();
            progressBar.setOpacity(0.0);
        }
        catch (Exception ex)
        {
            String msg = ex.getMessage();
            System.out.println(msg);
        }
    }

    private void deleteTable(String url)
    {
        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE Student");
            stmt.close();
            conn.close();
            studentListView.setItems(null);
            System.out.println("TABLE DROPPED");
        }
        catch (Exception ex)
        {
            String msg = ex.getMessage();
            System.out.println("TABLE NOT DROPPED");
            System.out.println(msg);
        }
        progressBar.setOpacity(0.0);
    }

    private void loadData(String url)
    {
        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT StudName, ID, Age, Major, GPA FROM Student";
            ResultSet result = stmt.executeQuery(sqlStatement);

            ObservableList<Student> dbStudentList = FXCollections.observableArrayList();

            while (result.next())
            {
                Student stud = new Student();
                stud.name = result.getString("StudName");
                stud.id = UUID.fromString(result.getString("ID"));
                stud.age = result.getString("Age");
                stud.major = result.getString("Major");
                stud.gpa = result.getString("GPA");
                dbStudentList.add(stud);
            }
            studentListView.setItems(dbStudentList);
            tableView.getColumns().setAll("Name", "ID", "Age", "Major", "GPA");



            System.out.println("DATA LOADED");
            stmt.close();
            conn.close();
        }
        catch (Exception ex)
        {
            String msg = ex.getMessage();
            System.out.println("DATA NOT LOADED");
            System.out.println(msg);
        }
        progressBar.setOpacity(0.0);
    }

    private void filterName(String url, String n)
    {
        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT StudName, ID, Age, Major, GPA FROM Student";
            ResultSet result = stmt.executeQuery(sqlStatement);

            ObservableList<Student> dbStudentList = FXCollections.observableArrayList();

            while (result.next())
            {
                if (result.getString("StudName").equals(n)) {
                    Student stud = new Student();
                    stud.name = result.getString("StudName");
                    stud.id = UUID.fromString(result.getString("ID"));
                    stud.age = result.getString("Age");
                    stud.major = result.getString("Major");
                    stud.gpa = result.getString("GPA");
                    dbStudentList.add(stud);
                }
            }
            studentListView.setItems(dbStudentList);
            tableView.getColumns().setAll("Name", "ID", "Age", "Major", "GPA");

            System.out.println("DATA LOADED");
            stmt.close();
            conn.close();
        }
        catch (Exception ex)
        {
            String msg = ex.getMessage();
            System.out.println("DATA NOT LOADED");
            System.out.println(msg);
        }
        progressBar.setOpacity(0.0);
    }

    private void filterAge(String url, int a)
    {
        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT StudName, ID, Age, Major, GPA FROM Student";
            ResultSet result = stmt.executeQuery(sqlStatement);

            ObservableList<Student> dbStudentList = FXCollections.observableArrayList();

            while (result.next())
            {
                int res = Integer.parseInt(result.getString("Age"));
                if (res >= a) {
                    Student stud = new Student();
                    stud.name = result.getString("StudName");
                    stud.id = UUID.fromString(result.getString("ID"));
                    stud.age = result.getString("Age");
                    stud.major = result.getString("Major");
                    stud.gpa = result.getString("GPA");
                    dbStudentList.add(stud);
                }
            }
            studentListView.setItems(dbStudentList);
            tableView.getColumns().setAll("Name", "ID", "Age", "Major", "GPA");

            System.out.println("DATA LOADED");
            stmt.close();
            conn.close();
        }
        catch (Exception ex)
        {
            String msg = ex.getMessage();
            System.out.println("DATA NOT LOADED");
            System.out.println(msg);
        }
        progressBar.setOpacity(0.0);
    }

    private void filterMajor(String url, String m)
    {
        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT StudName, ID, Age, Major, GPA FROM Student";
            ResultSet result = stmt.executeQuery(sqlStatement);

            ObservableList<Student> dbStudentList = FXCollections.observableArrayList();

            while (result.next())
            {
                if (result.getString("Major").equals(m)) {
                    Student stud = new Student();
                    stud.name = result.getString("StudName");
                    stud.id = UUID.fromString(result.getString("ID"));
                    stud.age = result.getString("Age");
                    stud.major = result.getString("Major");
                    stud.gpa = result.getString("GPA");
                    dbStudentList.add(stud);
                }
            }
            studentListView.setItems(dbStudentList);
            tableView.getColumns().setAll("Name", "ID", "Age", "Major", "GPA");

            System.out.println("DATA LOADED");
            stmt.close();
            conn.close();
        }
        catch (Exception ex)
        {
            String msg = ex.getMessage();
            System.out.println("DATA NOT LOADED");
            System.out.println(msg);
        }
        progressBar.setOpacity(0.0);
    }

    private void filterGPA(String url, double g)
    {
        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT StudName, ID, Age, Major, GPA FROM Student";
            ResultSet result = stmt.executeQuery(sqlStatement);

            ObservableList<Student> dbStudentList = FXCollections.observableArrayList();

            while (result.next())
            {
                int G = Integer.parseInt(result.getString("GPA"));
                if (G >= g) {
                    Student stud = new Student();
                    stud.name = result.getString("StudName");
                    stud.id = UUID.fromString(result.getString("ID"));
                    stud.age = result.getString("Age");
                    stud.major = result.getString("Major");
                    stud.gpa = result.getString("GPA");
                    dbStudentList.add(stud);
                }
            }
            studentListView.setItems(dbStudentList);
            tableView.getColumns().setAll("Name", "ID", "Age", "Major", "GPA");

            System.out.println("DATA LOADED");
            stmt.close();
            conn.close();
        }
        catch (Exception ex)
        {
            String msg = ex.getMessage();
            System.out.println("DATA NOT LOADED");
            System.out.println(msg);
        }
        progressBar.setOpacity(0.0);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                progressBar.setOpacity(1.0);
                createDatabase(AWS_URL);
            }
        });
        popButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                progressBar.setOpacity(1.0);
                loadData(AWS_URL);
            }
        });
        delButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                progressBar.setOpacity(1.0);

                deleteTable(AWS_URL);
            }
        });
        filterNameBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                filterName(AWS_URL, nameText.getText());
            }
        });
        filterAgeBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                filterAge(AWS_URL, Integer.parseInt(ageText.getText()));
            }
        });
        filterMajorBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                filterMajor(AWS_URL, majorText.getText());
            }
        });
        filterGPABut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                filterGPA(AWS_URL, Double.parseDouble(GPAText.getText()));
            }
        });
    }
}
