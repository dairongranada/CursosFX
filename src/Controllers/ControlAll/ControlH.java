package Controllers.ControlAll;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class ControlH {

    @FXML
    private Button loginHo;
    @FXML
    private Button registerStudent;

    @FXML
    void openLoginHo(ActionEvent event) {
        try {
            ///////////////////////////////////////////////////
            Stage stage = (Stage) loginHo.getScene().getWindow();
            stage.close(); // CERRAR LA PESTAÑA DE HOME

            Parent root = (new FXMLLoader(getClass().getResource("../../fxml/login.fxml"))).load();
            Scene scene = new Scene(root);
            Stage teatro = new Stage();
            teatro.setScene(scene);
            teatro.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clikStudent(ActionEvent event) {
        try {
            Stage stage = (Stage) registerStudent.getScene().getWindow();
            stage.close();
            Parent root = (new FXMLLoader(getClass().getResource("../../fxml/home/Registroestudiantes.fxml"))).load();
            Scene scene = new Scene(root);
            Stage teatro = new Stage();
            teatro.setScene(scene);
            teatro.setTitle("registro estudiantes");
            teatro.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
