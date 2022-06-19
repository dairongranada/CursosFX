package Controllers.ControllerAdmin;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import conect.Conexion;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button; 
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControlCursos{    
    Conexion con = new Conexion();

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String dato, query , query2;
    
    Conexion conect = new Conexion();


    @FXML private ComboBox<String> cmbHorario;
    @FXML private ComboBox<String> cmbInstructor;
    @FXML private TextField txtName;
    @FXML private Button btnCrear;


    @FXML
    void crearCurso(MouseEvent event) throws SQLException {
        String horario = cmbHorario.getValue();
        String instructor = cmbInstructor.getValue();
        String nameCurso = txtName.getText();
        if (horario == null || horario.isEmpty() || instructor == null || instructor.isEmpty() || nameCurso == null || nameCurso.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);alert.setHeaderText(null);alert.setTitle("Error");
            alert.setContentText("Tienes Campos sin llenar"); alert.showAndWait();
        }else{
                String cadena = instructor;
                String [] fragmentos = cadena.split(" ");
                System.out.println("=======" + fragmentos[0]);
                String query1 = "INSERT INTO cursos(nombre,instructor)values('"+nameCurso+"','"+fragmentos[0] +"')";
                con.conectar();
                try (Statement stm = con.getCon().createStatement()){
                    int rest = stm.executeUpdate(query1);
                    if(rest != 0){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);alert.setTitle("Error");
                        alert.setContentText("Datos Registrados con exito"); alert.showAndWait();
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);alert.setTitle("Error");
                        alert.setContentText("Error al guardar los datos por favor verifique"); alert.showAndWait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                con.desconectar();
            }
    }


    @FXML void initialize() throws IOException, SQLException{
        conect.conectar();
        ResultSet consult,consult2;
        query = "SELECT id,nombre,apellido FROM personas where tipo = 1;";
        try (Statement stm = conect.getCon().createStatement()){
            consult = stm.executeQuery(query);
            while (consult.next()) {
                dato = String.format("%d %s %s", consult.getInt("id"),  consult.getString("nombre"), consult.getString("apellido"));
                cmbInstructor.getItems().add(dato);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        query2 = "SELECT fecha,hora FROM horarios;";
        try (Statement stm2 = conect.getCon().createStatement()){
            consult2 = stm2.executeQuery(query2);                
            System.out.println("Entre A la consulta");
            while (consult2.next()) {
                dato = String.format( "%s %s", consult2.getString("fecha"),consult2.getString("hora"));
                cmbHorario.getItems().add(dato);
            }
        } catch (Exception e) {
        }
        
    }

    @FXML
    void limpiarCurses(MouseEvent event) throws SQLException {
        System.out.println("BORRANDO...");
        txtName.clear();
        cmbHorario.getItems().clear();
        cmbInstructor.getItems().clear();
    }


/////////////////////////       H E A D E R     /////////////////////////

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



	




