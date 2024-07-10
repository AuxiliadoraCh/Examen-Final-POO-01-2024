package org.example.parcial;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenu implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {  // 00073923 Método para inicializar el controlador
    }

    @FXML
    public void CrudCliente(ActionEvent event) {  // 00073923 Método para manejar las operaciones CRUD para Cliente
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientesView.fxml"));  // 00073923 Carga el archivo FXML para la vista de Clientes
            Parent root = loader.load();  // 00073923 Carga el nodo raíz
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  // 00073923 Obtiene la ventana actual

            // 00073923 Configura la nueva escena
            Scene scene = new Scene(root, 1000, 600);  // 00073923 Crea una nueva escena con el nodo raíz y establece su tamaño
            stage.setScene(scene);  // 00073923 Establece la escena en la ventana
            stage.show();  // 00073923 Muestra la ventana
        } catch (Exception e) {  // 00073923 Captura cualquier excepción que ocurra durante la carga del archivo FXML
            System.out.println(e.getMessage());  // 00073923 Imprime el mensaje de excepción
        }
    }

    @FXML
    public void CrudTarjeta(ActionEvent event) {  // 00073923 Método para manejar las operaciones CRUD para Tarjeta
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TarjetasView.fxml"));  // 00073923 Carga el archivo FXML para la vista de Tarjetas
            Parent root = loader.load();  // 00073923 Carga el nodo raíz
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  // 00073923 Obtiene la ventana actual

            // 00073923 Configura la nueva escena
            Scene scene = new Scene(root, 1000, 600);  // 00073923 Crea una nueva escena con el nodo raíz y establece su tamaño
            stage.setScene(scene);  // 00073923 Establece la escena en la ventana
            stage.show();  // 00073923 Muestra la ventana
        } catch (Exception e) {  // 00073923 Captura cualquier excepción que ocurra durante la carga del archivo FXML
            System.out.println(e.getMessage());  // 00073923 Imprime el mensaje de excepción
        }
    }

    @FXML
    public void CrudCompra(ActionEvent event) {  // 00073923 Método para manejar las operaciones CRUD para Compra
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ComprasView.fxml"));  // 00073923 Carga el archivo FXML para la vista de Compras
            Parent root = loader.load();  // 00073923 Carga el nodo raíz
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  // 00073923 Obtiene la ventana actual

            // 00073923 Configura la nueva escena
            Scene scene = new Scene(root, 1000, 600);  // 00073923 Crea una nueva escena con el nodo raíz y establece su tamaño
            stage.setScene(scene);  // 00073923 Establece la escena en la ventana
            stage.show();  // 00073923 Muestra la ventana
        } catch (Exception e) {  // 00073923 Captura cualquier excepción que ocurra durante la carga del archivo FXML
            System.out.println(e.getMessage());  // 00073923 Imprime el mensaje de excepción
        }
    }

    @FXML
    public void RegresarVentanaPrincipal(ActionEvent event) {  // 00073923 Método para regresar a la ventana principal
        try {
            // 00073923 Carga el archivo FXML para la vista principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));  // 00073923 Carga el archivo FXML para la vista principal
            Parent root = loader.load();  // 00073923 Carga el nodo raíz
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  // 00073923 Obtiene la ventana actual

            // 00073923 Configura la nueva escena
            Scene scene = new Scene(root, 1000, 600);  // 00073923 Crea una nueva escena con el nodo raíz y establece su tamaño
            stage.setScene(scene);  // 00073923 Establece la escena en la ventana
            stage.show();  // 00073923 Muestra la ventana
        } catch (Exception e) {  // 00073923 Captura cualquier excepción que ocurra durante la carga del archivo FXML
            System.out.println(e.getMessage());  // 00073923 Imprime el mensaje de excepción
        }
    }
}

