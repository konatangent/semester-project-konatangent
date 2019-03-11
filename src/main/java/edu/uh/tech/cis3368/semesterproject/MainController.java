package edu.uh.tech.cis3368.semesterproject;

import ch.qos.logback.core.db.dialect.DBUtil;
import com.sun.source.tree.WhileLoopTree;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//sql stuff
import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class MainController {



    @Autowired
    private EmployeeRepository eRepository;


    @Autowired
    public JobsRepository jobsRepository;

    //Main Window
    @FXML public Button employeeManagementButton;
    @FXML public Button jobManagementButton;

    //Employee Manager Window
    @FXML private TableView<Employee> employeeTableView;
    @FXML private TableColumn<Employee, Integer> idColumn;
    @FXML private TableColumn<Employee, String> firstNameColumn;
    @FXML private TableColumn<Employee, String> lastNameColumn;
    @FXML private TableColumn<Employee, String> phoneColumn;
    @FXML private TableColumn<Employee, String> emailColumn;
    @FXML public TextField firstNameTextField;
    @FXML public TextField lastNameTextField;
    @FXML public TextField phoneTextField;
    @FXML public TextField emailTextField;
    @FXML public TextField idTextField;
    @FXML public Button addEmployeeButton;
    @FXML public Button editEmployeeButton;
    @FXML public Button deleteEmployeeButton;

    //Job Manager Window
    @FXML private TableView<Jobs> jobsTableView;
    @FXML private TableColumn<Jobs, String> jobsIdColumn;
    @FXML private TableColumn<Jobs, String> jobsFirstNameColumn;
    @FXML private TableColumn<Jobs, String> jobsLastNameColumn;
    @FXML private TableColumn<Jobs, String> jobsPhoneColumn;
    @FXML private TableColumn<Jobs, String> jobsAddressColumn;
    @FXML private TableColumn<Jobs, String> jobsNameColumn;
    @FXML private TableColumn<Jobs, String> jobsDescriptionColumn;
    @FXML private TableColumn<Jobs, String> jobsStageColumn;
    @FXML private TextField jobsIdField;
    @FXML private TextField jobsFirstNameField;
    @FXML private TextField jobsLastNameField;
    @FXML private TextField jobsPhoneField;
    @FXML private TextField jobsAddressField;
    @FXML private TextField jobsNameField;
    @FXML private TextField jobsDescriptionField;
    @FXML private TextField jobsStageField;
    @FXML public Button addJobButton;




    //Employee Management Window
    @FXML
    void openEmployeeManagement(ActionEvent event) {

        System.out.println("Opening employee management window");

        try {
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("EmployeeManager.fxml"));
            Parent root2 = (Parent) fxmlLoader2.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root2));
            stage2.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }

    //Job Management Window
    public void openJobManagement(ActionEvent actionEvent) {

        System.out.println("Opening job management window");

        try {
            FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getResource("JobManager.fxml"));
            Parent root3 = (Parent) fxmlLoader3.load();
            Stage stage3 = new Stage();
            stage3.setScene(new Scene(root3));
            stage3.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }




    //SQL METHODS
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:~/project";
    private static final String USER = "sa";
    private static final String PASSWORD = "1234";

    //Add employee via SQL
    public void doSQL(ActionEvent event) throws SQLException {

        Employee newEmployee = new Employee(firstNameTextField.getText(), lastNameTextField.getText(), phoneTextField.getText(), emailTextField.getText());

        try {
            Class.forName(DB_DRIVER);
            Connection connect = DriverManager.getConnection(URL,USER,PASSWORD);
            Statement stmt = connect.createStatement();
            stmt.execute("INSERT INTO EMPLOYEE (FIRST_NAME, LAST_NAME, PHONE, EMAIL) " +
                    "VALUES ('"+newEmployee.getFirstName()+"', '"+newEmployee.getLastName()+
                    "', '"+newEmployee.getPhone()+"', '"+newEmployee.getEmail()+"');");

            stmt.close();
            connect.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Edit employee via SQL
    public void editEmployeeSQL(ActionEvent event) throws SQLException {

        try {
            Class.forName(DB_DRIVER);
            Connection connect = DriverManager.getConnection(URL,USER,PASSWORD);
            Statement stmt = connect.createStatement();
            stmt.execute("UPDATE EMPLOYEE SET " +
                    "FIRST_NAME = '"+firstNameTextField.getText()+"', "+
                    "LAST_NAME = '"+lastNameTextField.getText()+"', "+
                    "PHONE = '"+phoneTextField.getText()+"', "+
                    "EMAIL = '"+emailTextField.getText()+"' "+
                    "WHERE ID= '"+idTextField.getText()+"';");

            stmt.close();
            connect.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Delete employee via SQL
    public void deleteEmployeeSQL(ActionEvent event) throws SQLException {

        try {
            Class.forName(DB_DRIVER);
            Connection connect = DriverManager.getConnection(URL,USER,PASSWORD);
            Statement stmt = connect.createStatement();
            stmt.execute("DELETE FROM EMPLOYEE WHERE "+
                    "ID= '"+idTextField.getText()+"';");

            stmt.close();
            connect.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }







    //Failed add employee
    public void newEmployeeButtonAction(ActionEvent actionEvent) {

        System.out.println("adding Employee");
        Employee newEmployee = new Employee(firstNameTextField.getText(), lastNameTextField.getText(), phoneTextField.getText(), emailTextField.getText());
        newEmployee.setId(newEmployee.getId());
        System.out.println("ID: " + newEmployee.getId() + '\n' + "First Name: " + newEmployee.getFirstName() + '\n' + "Last Name: " +
                newEmployee.getLastName() + '\n' + "Phone: " + newEmployee.getPhone() + '\n' +
                newEmployee.getEmail() + '\n');
//         eRepository.save(newEmployee);

    }


    //Get employee function
    public void loadEmployeeButtonAction(ActionEvent actionEvent)
    {
        System.out.println("Loading Employee");

    }

    public void loadEmployeeTable() {
        String JDBC_DRIVER = "org.h2.Driver";
        String DB_URL = "jdbc:h2:file:C:/MyBeautifulCherrishabledb";
        String USER = "sa";
        String PASS = "1234";
        Connection conn = null;
    }

    //Job Functions
    public void newJobButtonAction(ActionEvent actionEvent) {

        System.out.println("adding Job");
        Jobs newJob = new Jobs(jobsFirstNameField.getText(), jobsLastNameField.getText(),
                jobsPhoneField.getText(), jobsAddressField.getText() , jobsNameField.getText() ,
                jobsDescriptionField.getText(), jobsStageField.getText());



    }

}