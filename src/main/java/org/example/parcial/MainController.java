package org.example.parcial;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {  // 00132623 Declara una clase pública llamada MainController que implementa Initializable

    @FXML
    private Button crud;  // 00132623 Campo privado para el botón CRUD

    @FXML
    private Button reports;  // 00132623 Campo privado para el botón de informes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {  // 00132623 Método para inicializar el controlador

    }

    public void crudOnAction() {  // 00132623 Método para manejar la acción del botón CRUD
        try {
            Stage stage = (Stage) crud.getScene().getWindow();  // 00132623 Obtiene la ventana (stage) actual del botón CRUD

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MenuView.fxml"));  // 00132623 Carga el archivo FXML para la vista del menú
            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);  // 00132623 Crea una nueva escena con el nodo raíz del archivo FXML y establece su tamaño
            stage.setResizable(false);  // 00132623 Establece la ventana (stage) como no redimensionable
            stage.setTitle("CRUD");  // 00132623 Establece el título de la ventana (stage)
            stage.setScene(scene);  // 00132623 Establece la escena en la ventana (stage)
            stage.show();  // 00132623 Muestra la ventana (stage)
        } catch (Exception e) {  // 00132623 Captura cualquier excepción que ocurra durante la carga del archivo FXML
            System.out.println(e.getMessage());  // 00132623 Imprime el mensaje de excepción
        }
    }

    public void reportsOnAction(ActionEvent event) throws IOException {  // 00132623 Método para manejar la acción del botón de informes
        try {
            // 00132623 Carga el archivo FXML para la vista de informes
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Report.fxml"));  // 00132623 Carga el archivo FXML para la vista de informes
            Parent root = loader.load();  // 00132623 Carga el nodo raíz
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  // 00132623 Obtiene la ventana (stage) actual
            stage.setTitle("Reportes"); //00132623 se le pone un titulo a la ventana que se genera

            // 00132623 Configura la nueva escena
            Scene scene = new Scene(root, 1000, 600);  // 00132623 Crea una nueva escena con el nodo raíz y establece su tamaño
            stage.setScene(scene);  // 00132623 Establece la escena en la ventana (stage)
            stage.show();  // 00132623 Muestra la ventana (stage)
        } catch (Exception e) {  // 00132623 Captura cualquier excepción que ocurra durante la carga del archivo FXML
            System.out.println(e.getMessage());  // 00132623 Imprime el mensaje de excepción
        }
    }
}

