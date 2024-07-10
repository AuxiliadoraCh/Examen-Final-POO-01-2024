package org.example.parcial;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class Alerta {

    // 00073923 Método estático para mostrar una alerta
    public static void mostrarAlerta(Alert.AlertType tipoAlerta, String titulo, String encabezado, String contenido) {
        // 00073923 Crear una nueva instancia de la clase Alert con el tipo de alerta especificado
        Alert alerta = new Alert(tipoAlerta);

        // 00073923 Establecer el título de la alerta
        alerta.setTitle(titulo);

        // 00073923 Establecer el encabezado de la alerta
        alerta.setHeaderText(encabezado);

        // 00073923 Establecer el contenido del mensaje de la alerta
        alerta.setContentText(contenido);

        // 00073923 Mostrar la alerta y esperar a que el usuario cierre el cuadro de diálogo
        alerta.showAndWait();
    }
}



