package Controllers.ControllerStudent;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class cursosStudent {    

    
    @FXML private Button verCursos;
    @FXML private Button verPerfil;



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



	




