import clases.classperson.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import conect.Conexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginFX{    

    @FXML private TextField loginUser;
    @FXML private PasswordField loginPassword;
    @FXML private Label loginMensage;
    @FXML private Button loginSingUp;
    

    Connection con = null;
    ResultSet resultSet = null;

	@FXML void ingresarBtn(ActionEvent event) throws SQLException{
        // OBTENCION DE VALORES DE LOS INPUTS
        person info = new person();
        info.setNombre(loginUser.getText());
        info.setIdentificacion(loginPassword.getText());
        Conexion conect = new Conexion();
        conect.conectar();

    // ACA SIRVE LA VALIDACION PARA EL ADMIN 
        if(conect.isConectado()){
            String query = "SELECT id from personas where nombre = '"+info.getNombre()+"' AND identificacion = '"+info.getIdentificacion()+"' AND tipo = 3 ";
            try (Statement stmA = conect.getCon().createStatement()){
                ResultSet rst = stmA.executeQuery(query);
                if(rst.next()){
                    Stage stage = (Stage) loginSingUp.getScene().getWindow();
                    stage.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/admin/home.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                    conect.desconectar();
     // ACA SIRVE LA VALIDACION DE ESTUDIANTE
        }if(conect.isConectado()){
            String queryInstructores = "SELECT id from personas where nombre = '"+ info.getNombre()+"' AND identificacion = '"+ info.getIdentificacion()+"' AND tipo = 2 ";
            try (Statement stmE = conect.getCon().createStatement()){
                ResultSet rste = stmE.executeQuery(queryInstructores);
                if(rste.next()){
                    Stage stage = (Stage) loginSingUp.getScene().getWindow();
                    stage.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/student/homeEstudent.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                    conect.desconectar();
                }
            }
    // ACA SIRVE LA VALIDACION PARA EL INSTRUCTOR 
        }if (conect.isConectado()){
            String queryEstudiantes = "SELECT id from personas where email = '"+info.getNombre()+"' AND identificacion = '"+info.getIdentificacion()+"' AND tipo = 1 ";
            try (Statement stmE = conect.getCon().createStatement()){
                ResultSet rste = stmE.executeQuery(queryEstudiantes);
                if(rste.next()){
                    Stage stage = (Stage) loginSingUp.getScene().getWindow();
                    stage.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Instruct/homeInstructores.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                    conect.desconectar();
                }
            }
        }if(info.getNombre() == null || info.getNombre().isEmpty() ||info.getIdentificacion() == null || info.getIdentificacion().isEmpty()){
            loginMensage.setText("Usuario o Contraseña Invaldilos");}
            } catch (Exception e) {
                e.printStackTrace();
        }
        }
	}


    @FXML private Button homeL;

    @FXML void openHomeL(ActionEvent event) throws SQLException{
        try {
            Stage stage = (Stage) homeL.getScene().getWindow(); 
            stage.close();
            Parent root = (new FXMLLoader(getClass().getResource("fxml/home/homePrincipal.fxml"))).load();
            Scene scene =  new Scene(root);
            Stage teatro = new Stage();
            teatro.setScene(scene);
            teatro.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}