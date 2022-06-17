package Controllers.ControllerAdmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import conect.Conexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControlEstudiantes {






    
@FXML private Button home;
@FXML private Button cursos;
@FXML private Button estudiantes;
@FXML private Button instructores;


@FXML void opensHome(ActionEvent event) throws SQLException{
    try {
        Stage stage = (Stage) home.getScene().getWindow(); 
        stage.close();
        Parent root = (new FXMLLoader(getClass().getResource("../../fxml/admin/home.fxml"))).load();
        Scene scene =  new Scene(root);
        Stage teatro = new Stage();
        teatro.setScene(scene);
        teatro.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

@FXML void openCursos(ActionEvent event) throws SQLException{
    try {
        Stage stage = (Stage) cursos.getScene().getWindow(); 
        stage.close();
        Parent root = (new FXMLLoader(getClass().getResource("../../fxml/admin/cursos.fxml"))).load();
        Scene scene =  new Scene(root);
        Stage teatro = new Stage();
        teatro.setScene(scene);
        teatro.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

@FXML void openEstudiantes(ActionEvent event) throws SQLException{
    try {
        Stage stage = (Stage) estudiantes.getScene().getWindow(); 
        stage.close();
        Parent root = (new FXMLLoader(getClass().getResource("../../fxml/admin/estudiantes.fxml"))).load();
        Scene scene =  new Scene(root);
        Stage teatro = new Stage();
        teatro.setScene(scene);
        teatro.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


@FXML void openInstructores(ActionEvent event) throws SQLException{
    try {
        Stage stage = (Stage) instructores.getScene().getWindow(); 
        stage.close();
        Parent root = (new FXMLLoader(getClass().getResource("../../fxml/admin/instructores.fxml"))).load();
        Scene scene =  new Scene(root);
        Stage teatro = new Stage();
        teatro.setScene(scene);
        teatro.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
