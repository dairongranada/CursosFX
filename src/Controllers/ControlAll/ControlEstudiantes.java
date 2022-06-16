package Controllers.ControlAll;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import conect.Conexion;
import clases.classperson.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControlEstudiantes {

    // C O D E

    ///////////////////////// H E A D E R /////////////////////////

    @FXML
    protected Button home;
    @FXML
    protected Button estudiantes;
    @FXML
    protected Button btnregister;
    @FXML
    protected TextField txtIdentifi;
    @FXML
    protected TextField txtName;
    @FXML
    protected TextField txtedad;
    @FXML
    protected TextField txtApellido;
    @FXML
    protected TextField textfieldRespuesta;

    private String script;
    private int rst,contador_for;
    ResultSet resultset;
    Connection con = null;
    PreparedStatement preparedStatement = null;
    Conexion connect = new Conexion();

    @FXML
    void opensHome(ActionEvent event) {
        try {
            Stage stage = (Stage) home.getScene().getWindow();
            stage.close();
            Parent root = (new FXMLLoader(getClass().getResource("../../fxml/home/homePrincipal.fxml"))).load();
            Scene scene = new Scene(root);
            Stage teatro = new Stage();
            teatro.setScene(scene);
            teatro.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openEstudiantes(ActionEvent event) {
        try {
            Stage stage = (Stage) estudiantes.getScene().getWindow();
            stage.close();
            Parent root = (new FXMLLoader(getClass().getResource("../../fxml/student/Registroestudiantes.fxml"))).load();
            Scene scene = new Scene(root);
            Stage teatro = new Stage();
            teatro.setScene(scene);
            teatro.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clickRegister(ActionEvent event) throws IOException, SQLException {
        if (txtIdentifi.getText().equals(null) || txtIdentifi.getText().isEmpty()) {
            textfieldRespuesta.setText("casilla vacia en identificacion");
        } else if (txtName.getText().equals(null) || txtName.getText().isEmpty()) {
            textfieldRespuesta.setText("casilla vacia en nombre");
        } else if (txtApellido.getText().equals(null) || txtApellido.getText().isEmpty()) {
            textfieldRespuesta.setText("casilla vacia en apellidos");
        } else if (txtedad.getText().equals(null) || txtedad.getText().isEmpty()) {
            textfieldRespuesta.setText("casilla edad vacia");
        } else {
            connect.conectar();
            try(Statement stm = connect.getCon().createStatement()){
                this.script = "SELECT COUNT(id) as 'id personas' FROM personas";
                resultset = stm.executeQuery(script);
                if(resultset.next()){
                    contador_for =resultset.getInt("id personas");
                    person info = new person(1,txtIdentifi.getText(),txtName.getText(),txtApellido.getText(),txtedad.getText(),null);
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
                                    textfieldRespuesta.setText("este estudiante ya se encuentra registrado,!logiarse¡.");
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

    @FXML
    void initialize() {
        txtIdentifi.clear();
        txtName.clear();
        txtApellido.clear();
        txtedad.clear();
    }

    @FXML void restaurarDatos(){
        txtIdentifi.clear();
        txtName.clear();
        txtApellido.clear();
        txtedad.clear();
    }
}
