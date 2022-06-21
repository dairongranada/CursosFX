package Controllers.ControllerInstruc;
import conect.*;
import clases.classcurso.*;
import clases.classperson.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


public class cursosInstru {    

    //------------------------- buttons -----------------------------
    @FXML private Button verCursos;
    @FXML private Button verPerfil;
    @FXML private Button btnClear;
    @FXML private Button btnCrear;
    @FXML private Button btnUpdate;
    @FXML private Button btnDelete;
    //---------------------------------------------------------------
    @FXML private TextField textfieldRespuesta;
    @FXML private TextField txtName;
    @FXML private TextField txtFecha;
    @FXML private TextField txtHora;
    @FXML private TextField txtInstructor;
    @FXML private TextField txtidentificacion;
    @FXML private Text nombrecurso;
    @FXML private Text fecha;
    @FXML private Text hora;
    @FXML private Text instructor;

    //----------------------------------------------------------------
    Conexion connect = new Conexion();
    ResultSet Resultset;
    String script,comboBox,valueName,valueFecha,valueHora,valueIdentificacion,dato;
    int rst,id_instructor;

    @FXML void openVerCursos(ActionEvent event) throws SQLException{
        try {
            Stage stage = (Stage) verCursos.getScene().getWindow(); 
            stage.close(); 

            Parent root = (new FXMLLoader(getClass().getResource("../../fxml/Instruct/cursosInstructores.fxml"))).load();
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

            Parent root = (new FXMLLoader(getClass().getResource("../../fxml/Instruct/homeInstructores.fxml"))).load();
            Scene scene =  new Scene(root);
            Stage teatro = new Stage();
            teatro.setScene(scene);
            teatro.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML void clickClear(ActionEvent event){
        try{
            restaurarDatos();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML void clickLupa(MouseEvent event) throws SQLException{
        connect.conectar();
        person info = new person();
        info.setIdentificacion(txtidentificacion.getText());
        try(Statement stm = connect.getCon().createStatement()){
            this.script = "SELECT nombre,apellido FROM personas WHERE identificacion = '"+info.getIdentificacion()+"'";
            Resultset = stm.executeQuery(script);
            if(Resultset.next()){
                textfieldRespuesta.setText("registre un curso");
                visibles();
                dato = String.format("%s %s", Resultset.getString("nombre"),Resultset.getString("apellido"));
                txtInstructor.setText(dato);
                txtInstructor.setDisable(true);
            }else{
                textfieldRespuesta.setText("!identificacion erronea¡");
                restaurarDatos();
                textfieldRespuesta.setVisible(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        connect.desconectar();
    }

    @FXML void clickCrear(ActionEvent event) throws SQLException{
        valueName = txtName.getText();
        valueFecha = txtFecha.getText();
        valueHora = txtHora.getText();
        valueIdentificacion = txtidentificacion.getText();
        if(valueName.isEmpty() || valueName == null){
            textfieldRespuesta.setText("!casilla nombre vacia¡");
        }else if(valueFecha.isEmpty() || valueFecha == null){
            textfieldRespuesta.setText("!casilla fecha vacia¡");
        }else if(valueHora.isEmpty() || valueHora == null){
            textfieldRespuesta.setText("!casilla hora vacia¡");
        }else{
            connect.conectar();
            curso curso = new curso();
            curso.setNombre(valueName);
            curso.setFecha(valueFecha);
            curso.setHora(valueHora);
            try(Statement stm = connect.getCon().createStatement()){
                this.script =  "SELECT id FROM personas WHERE identificacion = '"+valueIdentificacion+"'";
                Resultset = stm.executeQuery(script);
                if(Resultset.next()){
                    id_instructor = Resultset.getInt("id");
                    this.script = "INSERT INTO cursos(nombre,instructor) VALUES ('"+curso.getNombre()+"',"+id_instructor+")";
                    rst = stm.executeUpdate(script);
                    if(rst !=0){
                        this.script = "SELECT codigo FROM cursos WHERE nombre ='"+curso.getNombre()+"' AND instructor = "+id_instructor+"";
                        Resultset = stm.executeQuery(script);
                        if(Resultset.next()){
                            this.script = "INSERT INTO horarios(curso,fecha,hora) VALUES ("+Resultset.getInt("codigo")+",'"+curso.getFecha()+"','"+curso.getHora()+"')";
                            rst = stm.executeUpdate(script);
                            if(rst !=0){
                                textfieldRespuesta.setText("!registro exitoso de curso¡");
                                restaurarDatos();
                            }else{
                                textfieldRespuesta.setText("!error en registro de curso¡");
                            }
                        }else{
                            textfieldRespuesta.setText("!error en registro de curso¡");
                        }
                    }else{
                        System.out.println("error");
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            connect.desconectar();
        }
    }

    @FXML void clickUpdate(ActionEvent event) throws SQLException{
        try {
            
        } catch (Exception e) {
            
        }
        
    }

    @FXML void clickDelete(ActionEvent event){
        try {
            
        } catch (Exception e) {
        }
    }

    @FXML
    void initialize() throws SQLException {
        txtFecha.clear();
        txtName.clear();
        txtHora.clear();
        txtInstructor.clear();
        txtidentificacion.clear();
    }

    @FXML void restaurarDatos(){
        txtFecha.clear();
        txtName.clear();
        txtHora.clear();
        txtidentificacion.clear();
        txtInstructor.clear();
        txtName.setVisible(false);
        txtFecha.setVisible(false);
        txtHora.setVisible(false);
        txtInstructor.setVisible(false);
        nombrecurso.setVisible(false);
        fecha.setVisible(false);
        hora.setVisible(false);
        instructor.setVisible(false);
        btnClear.setVisible(false);
        btnCrear.setVisible(false);
        btnDelete.setVisible(false);
        btnUpdate.setVisible(false);
    }

    @FXML void visibles(){
        txtName.setVisible(true);
        txtFecha.setVisible(true);
        txtHora.setVisible(true);
        txtInstructor.setVisible(true);
        nombrecurso.setVisible(true);
        fecha.setVisible(true);
        hora.setVisible(true);
        instructor.setVisible(true);
        btnClear.setVisible(true);
        btnCrear.setVisible(true);
        btnDelete.setVisible(true);
        btnUpdate.setVisible(true);
        textfieldRespuesta.setVisible(true);
    }
}	




