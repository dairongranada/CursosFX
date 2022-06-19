package Controllers.ControllerAdmin;
import conect.*;
import clases.classperson.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControlInstru{

    // --------------- button of instructores --------------------
    @FXML private Button home;
    @FXML private Button cursos;
    @FXML private Button estudiantes;
    @FXML private Button instructores;
    @FXML private Button btnCreate;
    @FXML private Button btnUpdate;
    @FXML private Button btnbtnClear;
    @FXML private Button btnDelete;
    // -------------- casillas de datos -------------------------------
    @FXML private TextField txtIdentifi;
    @FXML private TextField txtName;
    @FXML private TextField txtApellido;
    @FXML private TextField txtEmail;
    @FXML private TextField textfieldRespuesta;
    
    //-----------------------------------------------------------------------------------

    Conexion connect = new Conexion();
    private String script;
    private int rst,contador_for;
    ResultSet resultset;

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

        }
    }

    @FXML void clickCreate(ActionEvent event) throws SQLException{
        if (txtIdentifi.getText().equals(null) || txtIdentifi.getText().isEmpty()) {
            textfieldRespuesta.setText("casilla vacia en identificacion");
        } else if (txtName.getText().equals(null) || txtName.getText().isEmpty()) {
            textfieldRespuesta.setText("casilla vacia en nombre");
        } else if (txtApellido.getText().equals(null) || txtApellido.getText().isEmpty()) {
            textfieldRespuesta.setText("casilla vacia en apellidos");
        } else if (txtEmail.getText().equals(null) || txtEmail.getText().isEmpty()) {
            textfieldRespuesta.setText("casilla edad vacia");
        } else {
            connect.conectar();
            try(Statement stm = connect.getCon().createStatement()){
                this.script = "SELECT COUNT(id) as 'id personas' FROM personas";
                resultset = stm.executeQuery(script);
                if(resultset.next()){
                    contador_for =resultset.getInt("id personas");
                    person info = new person(1,txtIdentifi.getText(),txtName.getText(),txtApellido.getText(),null,txtEmail.getText());
                    if(contador_for == 0){
                        this.script = "INSERT INTO personas(identificacion,nombre,apellido,edad,tipo,email)"+
                        "VALUES ('"+info.getIdentificacion()+"','"+info.getNombre()+"','"+info.getApellidos()+"','"+info.getEdad()+"',"+info.getTipo()+",'"+info.getEmail()+"')";
                        rst = stm.executeUpdate(script);
                        if(rst !=0){
                            textfieldRespuesta.setText("!registro exitoso¡");
                            restaurarDatos();
                        }else{
                            textfieldRespuesta.setText("!error en registro¡");
                            restaurarDatos();
                        }
                    }else{
                        for (int i=1;i <= contador_for;i++){
                            this.script = "SELECT identificacion FROM personas WHERE id = "+i+"";
                            resultset = stm.executeQuery(script);
                            if(resultset.next()){
                                if(resultset.getString("identificacion").equals(txtIdentifi.getText())){
                                    textfieldRespuesta.setText("este estudiante ya se encuentra registrado.");
                                    restaurarDatos();
                                    break;
                                }
                                if(i == contador_for){
                                    this.script = "INSERT INTO personas(identificacion,nombre,apellido,edad,tipo,email)"+
                                    "VALUES ('"+info.getIdentificacion()+"','"+info.getNombre()+"','"+info.getApellidos()+"','"+info.getEdad()+"',"+info.getTipo()+",'"+info.getEmail()+"')";
                                    rst = stm.executeUpdate(script);
                                    if(rst !=0){
                                        textfieldRespuesta.setText("!registro exitoso¡");
                                        restaurarDatos();
                                    }else{
                                        textfieldRespuesta.setText("!error en registro¡");
                                        restaurarDatos();
                                    }
                                }
                            }else{
                                System.out.println("error en ciclo de validacion");
                            }
                        }
                    }
                }else{
                    System.out.println("error en script de consulta de total de datos");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            connect.desconectar();
        }
    }

    @FXML void ClickDelete(ActionEvent event) throws SQLException{
        if(txtName.getText() == null || txtName.getText().isEmpty()){
            textfieldRespuesta.setText("casilla de nombre vacia");
        }else if (txtApellido.getText() == null || txtApellido.getText().isEmpty()){
            textfieldRespuesta.setText("casilla de apellido vacio");
        }else if (txtIdentifi.getText() == null || txtIdentifi.getText().isEmpty()){
            textfieldRespuesta.setText("casilla identificacion vacia");
        }else if(txtEmail.getText() == null || txtEmail.getText().isEmpty()){
            textfieldRespuesta.setText("casilla edad vacia");
        }else{
            connect.conectar();
            try(Statement stm = connect.getCon().createStatement()){
                person info = new person();
                info.setIdentificacion(txtIdentifi.getText());
                this.script = "UPDATE personas SET estado = 'I' WHERE identificacion = '"+info.getIdentificacion()+"' AND estado = 'A'";
                rst = stm.executeUpdate(script);
                if(rst !=0){
                    textfieldRespuesta.setText("!eliminacion exitosa¡");
                    restaurarDatos();
                }else{
                    textfieldRespuesta.setText("!erro en eliminacion");
                    restaurarDatos();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            connect.desconectar();
        }
    }

    @FXML void ClickUpdate(ActionEvent event) throws SQLException{
        if(txtName.getText() == null || txtName.getText().isEmpty()){
            textfieldRespuesta.setText("casilla de nombre vacia");
        }else if (txtApellido.getText() == null || txtApellido.getText().isEmpty()){
            textfieldRespuesta.setText("casilla de apellido vacio");
        }else if (txtIdentifi.getText() == null || txtIdentifi.getText().isEmpty()){
            textfieldRespuesta.setText("casilla identificacion vacia");
        }else if(txtEmail.getText() == null || txtEmail.getText().isEmpty()){
            textfieldRespuesta.setText("casilla edad vacia");
        }else{
            person info = new person();
            info.setIdentificacion(txtIdentifi.getText());
            info.setNombre(txtName.getText());
            info.setApellidos(txtApellido.getText());
            info.setEmail(txtEmail.getText());
            connect.conectar();
            try(Statement stm = connect.getCon().createStatement()){
                this.script = "UPDATE personas SET nombre = '"+info.getNombre()+"',apellido = '"+info.getApellidos()+"',"+
                "email = '"+info.getEmail()+"' WHERE identificacion = "+info.getIdentificacion()+" AND estado ='A'";
                rst = stm.executeUpdate(script);
                if(rst != 0){
                    textfieldRespuesta.setText("!se actuaizo exitosamente");
                    restaurarDatos();
                }else{
                    textfieldRespuesta.setText("error en actualizacion");
                    restaurarDatos();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            connect.desconectar();
        }
    }

    @FXML void ClickClear (ActionEvent event){
        try {
            restaurarDatos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        txtIdentifi.clear();
        txtName.clear();
        txtApellido.clear();
        txtEmail.clear();
    }

    @FXML void restaurarDatos(){
        txtIdentifi.clear();
        txtName.clear();
        txtApellido.clear();
        txtEmail.clear();
    }
}