package Controllers.ControllerAdmin;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class ControllHome {    

    @FXML private Button hoInstructores;
    @FXML private Button hoEstudiantes;
    @FXML private Button cursos;

    @FXML void homeInstructores(ActionEvent event) throws SQLException{
        try {
            ///////////////////////////////////////////////////
            Stage stage = (Stage) hoInstructores.getScene().getWindow(); 
            stage.close(); //CERRAR LA PESTAÑA DE HOME

            Parent root = (new FXMLLoader(getClass().getResource("../../fxml/admin/instructores.fxml"))).load();
            Scene scene =  new Scene(root);
            Stage teatro = new Stage();
            teatro.setScene(scene);
            teatro.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML void homeEstudiantes(ActionEvent event) throws SQLException{
        try {
            ///////////////////////////////////////////////////
            Stage stage = (Stage) hoEstudiantes.getScene().getWindow(); 
            stage.close(); //CERRAR LA PESTAÑA DE HOME

            Parent root = (new FXMLLoader(getClass().getResource("../../fxml/admin/cursos.fxml"))).load();
            Scene scene =  new Scene(root);
            Stage teatro = new Stage();
            teatro.setScene(scene);
            teatro.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML void homeCursos(ActionEvent event) throws SQLException{
        try {
            ///////////////////////////////////////////////////
            Stage stage = (Stage) cursos.getScene().getWindow(); 
            stage.close(); //CERRAR LA PESTAÑA DE HOME

            Parent root = (new FXMLLoader(getClass().getResource("../../fxml/admin/cursos.fxml"))).load();
            Scene scene =  new Scene(root);
            Stage teatro = new Stage();
            teatro.setScene(scene);
            teatro.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}



	




