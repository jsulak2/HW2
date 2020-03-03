package sample;
/**
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.UUID;

public class Controllers implements Initializable
{
    @FXML
    JFXButton createButton;
    @FXML
    JFXButton popButton;
    @FXML
    JFXButton delButton;
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
            System.out.println("TABLE DROPPED");
        }
        catch (Exception ex)
        {
            String msg = ex.getMessage();
            System.out.println("TABLE NOT DROPPED");
            System.out.println(msg);
        }
    }

    private void loadData(String url)
    {
        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT StudName, ID, Age, Major, GPA FROM Student";
            ResultSet result = stmt.executeQuery(sqlStatement);
            /**
            ObservableList<Employee> dbEmployeeList = FXCollections.observableArrayList();
            while (result.next())
            {
                Employee employee = new Employee();
                employee.id = UUID.fromString(result.getString("Id"));
                employee.firstName = result.getString("FirstName");
                employee.lastName = result.getString("LastName");
                employee.isActive = result.getBoolean("IsActive");
                dbEmployeeList.add(employee);
            }
            if(url.equals(DB_URL))
                materialListView.setItems(dbEmployeeList);
            else
                normalListView.setItems(dbEmployeeList);

             *//*
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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                createDatabase(AWS_URL);
            }
        });
        popButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                loadData(AWS_URL);
            }
        });
        delButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                deleteTable(AWS_URL);
            }
        });
    }

/*
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                //progressBar.setOpacity(1.0);
                createDatabase(AWS_URL);
            }
        });
    /*    popButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                loadData(AWS_URL);
            }
        });
     *
        delButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                deleteTable(AWS_URL);
            }
        });

    /*
        ObservableList<Student> items = studentListView.getItems();
        Employee employee1 = new Employee();
        employee1.firstName = "Alyssa";
        employee1.lastName = "Anderson";
        Employee employee2 = new Employee();
        employee2.firstName = "Robert";
        employee2.lastName = "Smith";

        items.add(employee1);
        items.add(employee2);

        for(int i = 0; i < 10; i++)
        {
            Employee employee = new Employee();
            employee.firstName = "EMPLOYEE" + i;
            employee.lastName = "Incognito";
            employee.isActive = true;
            employee.id = UUID.randomUUID();
            items.add(employee);
        }

        Staff staff1 = new Staff();
        staff1.firstName = "Some Staff";
        staff1.lastName = "Lee";
        items.add(staff1);

        Faculty faculty1 = new Faculty();
        faculty1.firstName = "Some Faculty";
        faculty1.lastName = "Diaz";
        items.add(faculty1);
    *


    }
    */
/*
}
*/

//<JFXTreeTableView fx:id="tableView" prefHeight="315.0" prefWidth="204.0" />
//<JFXTreeTableRow fx:id="stud1"/>