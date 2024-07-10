package org.example.parcial;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {  // 00132623 Declara una clase pública llamada HelloApplication que extiende Application

    @Override  // 00132623 Anotación para sobrescribir el método de la clase Application
    public void start(Stage stage) throws IOException {  // 00132623 Método para iniciar la aplicación
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));  // 00132623 Carga el archivo FXML para la vista del menú
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);  // 00132623 Crea una nueva escena con el nodo raíz del archivo FXML y establece su tamaño
        stage.setTitle("Menu CRUD's");  // 00132623 Establece el título de la ventana (stage)
        stage.setScene(scene);  // 00132623 Establece la escena en la ventana (stage)
        stage.show();  // 00132623 Muestra la ventana (stage)
    }

    public static void main(String[] args) {  // 00132623 Método principal para iniciar la aplicación
        launch();  // 00132623 Lanza la aplicación
    }
}
