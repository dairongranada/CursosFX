package Controllers.ControllerStudent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import conect.Conexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class cursosStudent {    

    @FXML private Button verCursos;
    @FXML private Button verPerfil;
    @FXML private Button btnCrearC;

    @FXML private TextField txtIdentifi;
    @FXML private TextField textfieldRespuesta;
    @FXML private ComboBox<String> cbmCusosFechas;

    Conexion conect = new Conexion();
    Conexion con = new Conexion();

    private String script;
    String query1;
    ResultSet countResult;
    private int idEstudent;    
    private boolean success;
    int validacion_insert;



    @FXML void crearCurso(MouseEvent event) throws SQLException {
        String identi = txtIdentifi.getText();
        String cursos = cbmCusosFechas.getValue();

        if(identi == null || identi.isEmpty() || cursos == null || cursos.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);alert.setHeaderText(null);
            alert.setTitle("Error 504");
            alert.setContentText("Tienes Campos sin llenar"); alert.showAndWait();
        }

        con.conectar();
        try (Statement stm = con.getCon().createStatement()){
            // ACA SE SACA EL ID DEL ESTUDIANTE PARA LUEGO VALDIAR 
            this.script = "select id from personas where identificacion = '"+identi+"';";
            countResult = stm.executeQuery(script);
            if(countResult.next()){
                idEstudent = countResult.getInt("id");
                success = true;
                System.out.println("ID ESTUDIANTE: " + idEstudent);
            }else{
                System.out.println("error identificacion");
                success = false;
            }
        }if (success == true) {
                try (Statement stm2 = con.getCon().createStatement()){
                    this.script =  "SELECT codigo FROM cursos WHERE nombre = '"+cursos+"'";
                    countResult = stm2.executeQuery(script);
                    if(countResult.next()){
                        this.script = "INSERT INTO registro_cursos(alumno,curso) VALUES ("+idEstudent+","+countResult.getInt("codigo")+")";
                        validacion_insert = stm2.executeUpdate(script);
                        if(validacion_insert != 0){
                            textfieldRespuesta.setText("!su registroa este curso a sido exitoso¡");
                            restaurardatos();
                        }else{
                            textfieldRespuesta.setText("error en su registro");
                            restaurardatos();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                con.desconectar();
        }if (success == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);alert.setHeaderText(null);alert.setTitle("Error 504");
            alert.setContentText("El Numero De Indentificacion Ingresado No Existe en Nuestra Base De Datos"); 
            alert.showAndWait();
        }

            
    }
    
    @FXML void initialize() throws IOException, SQLException{
        conect.conectar();
        ResultSet consult;
        try (Statement stm3 = conect.getCon().createStatement()){
            String query = "SELECT c.codigo, c.nombre, h.fecha, h.hora from cursos c,horarios h WHERE c.codigo = h.curso;";
            consult = stm3.executeQuery(query);
            while (consult.next()) {
                String dato = String.format("%s",consult.getString("c.nombre"));
                cbmCusosFechas.getItems().add(dato);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        conect.desconectar();    
    }

    @FXML void restaurardatos(){
        txtIdentifi.clear();
        cbmCusosFechas.setValue("");
    }

    @FXML void openVerCursos(ActionEvent event) throws SQLException{
        try {
            Stage stage = (Stage) verCursos.getScene().getWindow(); 
            stage.close(); 

            Parent root = (new FXMLLoader(getClass().getResource("../../fxml/student/cursosEstudent.fxml"))).load();
            Scene scene =  new Scene(root);
            Stage teatro = new Stage();
            teatro.setScene(scene);
            teatro.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML void openVerPerfil(ActionEvent event) throws SQLException{
        try {
            Stage stage = (Stage) verPerfil.getScene().getWindow(); 
            stage.close(); 

            Parent root = (new FXMLLoader(getClass().getResource("../../fxml/student/homeEstudent.fxml"))).load();
            Scene scene =  new Scene(root);
            Stage teatro = new Stage();
            teatro.setScene(scene);
            teatro.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}



	




