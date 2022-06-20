package Controllers.ControllerAdmin;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
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

    Conexion conect = new Conexion();
    Conexion con = new Conexion();

    ResultSet countResult;
    private int amount;



    @FXML private ComboBox<String> cmbInstructor;
    @FXML private TextField txtFecha;
    @FXML private TextField txtHora;
    @FXML private TextField txtName;
    @FXML private Button btnCrear;
    private String script;


    @FXML
    void crearCurso(MouseEvent event) throws SQLException {
        String instructor = cmbInstructor.getValue();
        String nameCurso = txtName.getText();
        String fecha = txtFecha.getText();
        String hora = txtHora.getText();



        if (fecha == null || fecha.isEmpty() || hora == null || hora.isEmpty() || instructor == null || instructor.isEmpty() || nameCurso == null || nameCurso.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);alert.setHeaderText(null);alert.setTitle("Error");
            alert.setContentText("Tienes Campos sin llenar"); alert.showAndWait();
        }else{
                String cadena = instructor;
                String [] fragmentos = cadena.split(" ");
                System.out.println("id Instructor: " + fragmentos[0]);

                con.conectar();
                try (Statement stm = con.getCon().createStatement()){
                    this.script = "SELECT COUNT(codigo) as 'amount' FROM cursos;";
                    countResult = stm.executeQuery(script);
                    if(countResult.next()){
                        amount = countResult.getInt("amount");
                        System.out.println("ID CURSOS: " + amount);
                    }

                    String query1 = "INSERT INTO cursos(codigo,nombre,instructor)values('"+(amount+1) +"', '"+nameCurso+"','"+ fragmentos[0] +"')";
                    String query2 = "INSERT INTO horarios(curso,fecha,hora)values('"+(amount+1) +"','"+ fecha +"', '"+hora +"')";
                    int rest = stm.executeUpdate(query1);
                    int rest2 = stm.executeUpdate(query2);

                    if(rest != 0  || rest2 != 0 ){
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
        ResultSet consult;
        String query = "SELECT id,nombre,apellido FROM personas where tipo = 1;";
        try (Statement stm = conect.getCon().createStatement()){
            consult = stm.executeQuery(query);
            while (consult.next()) {
                String dato = String.format("%d %s %s", consult.getInt("id"),  consult.getString("nombre"), consult.getString("apellido"));
                cmbInstructor.getItems().add(dato);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }


    @FXML
    void limpiarCurses(MouseEvent event) throws SQLException {
        System.out.println("BORRANDO...");
        txtName.clear();
        txtFecha.clear();
        txtHora.clear();
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



	




