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
    @FXML private ComboBox<String> cbmCusosFechas;

    Conexion conect = new Conexion();
    Conexion con = new Conexion();

    private String script;
    String query1;
    ResultSet countResult;
    private int idEstudent;    
    private boolean success;



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
                System.out.println(" ESE MKA NO EXISTE PAI");
                success = false;
            }
        }if (success == true) {
                String cadena = cursos;
                String [] fragmentos = cadena.split(" ");
                System.out.println(fragmentos[0]);

                try (Statement stm2 = con.getCon().createStatement()){
                    String query1 = "INSERT INTO registro_cursos(alumno,curso)values('"+idEstudent+"','"+ fragmentos[0]+"')";
                    int rest = stm2.executeUpdate(query1);
                    if(rest != 0 ){
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
                String dato = String.format("%d %s",consult.getInt("c.codigo") ,consult.getString("c.nombre"));
                cbmCusosFechas.getItems().add(dato);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }    
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



	




