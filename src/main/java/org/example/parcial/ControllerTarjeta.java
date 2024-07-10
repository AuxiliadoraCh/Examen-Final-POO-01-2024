package org.example.parcial;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerTarjeta implements Initializable {  // 00132623 Declara una clase pública llamada ControllerTarjeta que implementa Initializable

    @FXML
    private TextField txtDeletId;  // 00132623 Campo privado para el TextField usado para ingresar el ID a eliminar

    @FXML
    private TextField txtClienteIdUpdate, txtNumeroTarjetaUpdate, txtFechaExpUpdate, txtTipoTarjetaUpdate, txtIdActualizar, txtFacilitadorUpdate;  // 00132623 Campos privados para los TextFields usados para ingresar la información actualizada de la tarjeta

    @FXML
    private TextField txtClienteId, txtNumTarjeta, txtFechaExp, txtTipoTarjeta, txtFacilitador;  // 00132623 Campos privados para los TextFields usados para ingresar la información de una nueva tarjeta

    @FXML
    private TextField txtIdSeleccion;  // 00132623 Campo privado para el TextField usado para ingresar el ID a seleccionar

    @FXML
    private TableView<Tarjeta> TableTarjeta;  // 00132623 Campo privado para el TableView que muestra las tarjetas

    @FXML
    private TableColumn<Tarjeta, Integer> ColumnId;  // 00132623 Campo privado para la TableColumn que muestra los IDs de las tarjetas

    @FXML
    private TableColumn<Tarjeta, Integer> ColumnClienteId;  // 00132623 Campo privado para la TableColumn que muestra los IDs de los clientes

    @FXML
    private TableColumn<Tarjeta, String> ColumnNumTarjeta;  // 00132623 Campo privado para la TableColumn que muestra los números de las tarjetas

    @FXML
    private TableColumn<Tarjeta, String> ColumnFexhaExpiracion;  // 00132623 Campo privado para la TableColumn que muestra las fechas de expiración de las tarjetas

    @FXML
    private TableColumn<Tarjeta, String> ColumnTipoTarjeta;  // 00132623 Campo privado para la TableColumn que muestra los tipos de tarjetas

    @FXML
    private TableColumn<Tarjeta, Integer> ColumnFacilitadorId;  // 00132623 Campo privado para la TableColumn que muestra los IDs de los facilitadores

    @FXML
    public void AgregarTarjeta(ActionEvent event) {  // 00132623 Método para agregar una nueva tarjeta
        if (txtClienteId.getText().isEmpty() || txtNumTarjeta.getText().isEmpty() ||  // 00132623 Verificar si el campo de texto del ID del cliente está vacío
                txtFechaExp.getText().isEmpty() || txtTipoTarjeta.getText().isEmpty() ||  // 00132623 Verificar si el campo de texto del número de tarjeta está vacío
                txtFacilitador.getText().isEmpty()) {  // 00132623 Verificar si el campo de texto del facilitador está vacío
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Campo Vacío", "Por favor, complete todos los campos.");  // 00132623 Mostrar una alerta de error si algún campo está vacío
            return;  // 00132623 Salir del método si algún campo está vacío
        }

        try {
            Tarjeta tarjeta = new Tarjeta();  // 00073923 Crear un nuevo objeto Tarjeta
            tarjeta.agregarTarjeta(Integer.parseInt(txtClienteId.getText()), txtNumTarjeta.getText(),  // 00132623 Agregar la tarjeta a la base de datos
                    Date.valueOf(txtFechaExp.getText()), txtTipoTarjeta.getText(), Integer.parseInt(txtFacilitador.getText()));  // 00132623 Convertir y pasar los valores necesarios

            Alerta.mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Tarjeta Agregada", "La tarjeta ha sido agregada correctamente.");  // 00132623 Mostrar una alerta de información si la tarjeta se agregó correctamente

            txtClienteId.setText("");  // 00132623 Limpiar el campo de texto del ID del cliente
            txtNumTarjeta.setText("");  // 00132623 Limpiar el campo de texto del número de tarjeta
            txtFechaExp.setText("");  // 00132623 Limpiar el campo de texto de la fecha de expiración
            txtTipoTarjeta.setText("");  // 00132623 Limpiar el campo de texto del tipo de tarjeta
            txtFacilitador.setText("");  // 00132623 Limpiar el campo de texto del ID del facilitador
        } catch (NumberFormatException e) {  // 00132623 Capturar excepciones de formato de número
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error de Formato", "Formato Incorrecto", "Asegúrese de que todos los campos tengan el formato correcto.");  // 00132623 Mostrar una alerta de error si el formato es incorrecto
        } catch (Exception e) {  // 00132623 Capturar cualquier otra excepción
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error de SQL", "Error en la Base de Datos", "Ocurrió un error al agregar la tarjeta a la base de datos.");  // 00132623 Mostrar una alerta de error si ocurre un error en la base de datos
            e.printStackTrace();  // 00132623 Imprimir la traza de la pila de la excepción
        }
    }


    @FXML
    public void RegresarMenu(ActionEvent event) {  // 00132623 Método para regresar al menú
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuView.fxml"));  // 00132623 Carga el archivo FXML para el menú
            Parent root = loader.load();  // 00132623 Carga el nodo raíz
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  // 00132623 Obtiene la ventana actual

            // 00132623 Configura la nueva escena
            Scene scene = new Scene(root, 1000, 600);  // 00132623 Crea una nueva escena con el nodo raíz y establece su tamaño
            stage.setScene(scene);  // 00132623 Establece la escena en la ventana
            stage.show();  // 00132623 Muestra la ventana
        } catch (Exception e) {  // 00132623 Captura cualquier excepción que ocurra durante la carga del archivo FXML
            System.out.println(e.getMessage());  // 00132623 Imprime el mensaje de excepción
        }
    }

    // 00132623 Método para configurar las columnas del TableView
    private void ConfigurarTable() {
        ColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));  // 00132623 Vincula la columna ID con la propiedad "id" de Tarjeta
        ColumnClienteId.setCellValueFactory(new PropertyValueFactory<>("id_cliente"));  // 00132623 Vincula la columna ClienteId con la propiedad "clienteID" de Tarjeta
        ColumnNumTarjeta.setCellValueFactory(new PropertyValueFactory<>("tarjeta_num"));  // 00132623 Vincula la columna NumTarjeta con la propiedad "numTarjeta" de Tarjeta
        ColumnFexhaExpiracion.setCellValueFactory(new PropertyValueFactory<>("exp_date"));  // 00132623 Vincula la columna FechaExpiracion con la propiedad "fechaExp" de Tarjeta
        ColumnTipoTarjeta.setCellValueFactory(new PropertyValueFactory<>("tipo"));  // 00132623 Vincula la columna TipoTarjeta con la propiedad "tipo" de Tarjeta
        ColumnFacilitadorId.setCellValueFactory(new PropertyValueFactory<>("id_facilitador"));  // 00132623 Vincula la columna FacilitadorId con la propiedad "facilitadorID" de Tarjeta
    }

    // 00132623 Método para cargar todos los datos de las tarjetas en el TableView
    private void CargarDatosTodos() {
        ObservableList<Tarjeta> tarjeta = FXCollections.observableArrayList();  // 00132623 Crea una lista observable para almacenar las tarjetas

        String sql = "SELECT * FROM Tarjeta";  // 00132623 Consulta SQL para seleccionar todas las tarjetas

        // 00132623 Try-with-resources para establecer una conexión a la base de datos y ejecutar la consulta
        try (Connection con = Conexion.getInstance().getConnection();  // 00132623 Obtiene una conexión a la base de datos desde el pool de conexiones
             Statement pstm = con.createStatement();  // 00132623 Crea una declaración
             ResultSet rs = pstm.executeQuery(sql)) {  // 00132623 Ejecuta la consulta y obtiene el resultado

            // 00132623 Itera sobre los resultados y agrega las tarjetas a la lista observable
            while (rs.next()) {
                int id = rs.getInt("id");  // 00132623 Obtiene el ID de la tarjeta del resultado
                int clienteID = rs.getInt("id_cliente");  // 00132623 Obtiene el ID del cliente del resultado
                String numTarjeta = rs.getString("tarjeta_num");  // 00132623 Obtiene el número de la tarjeta del resultado
                String tipo = rs.getString("tipo");  // 00132623 Obtiene el tipo de tarjeta del resultado
                Date fechaexp = rs.getDate("exp_date");  // 00132623 Obtiene la fecha de expiración de la tarjeta del resultado
                int facilitador = rs.getInt("id_facilitador");  // 00132623 Obtiene el ID del facilitador del resultado

                Tarjeta tarjetas = new Tarjeta(id, clienteID, numTarjeta, fechaexp, tipo, facilitador);  // 00132623 Crea un nuevo objeto Tarjeta
                tarjeta.add(tarjetas);  // 00132623 Agrega la tarjeta a la lista observable
            }
        } catch (Exception e) {  // 00132623 Captura cualquier excepción que ocurra durante la operación de base de datos
            System.out.println(e);  // 00132623 Imprime el mensaje de excepción
        }
        TableTarjeta.setItems(tarjeta);  // 00132623 Establece los elementos del TableView a la lista observable
    }

    // 00132623 Método para cargar los datos de una tarjeta por ID en el TableView
    private void CargarDatosId() {
        ObservableList<Tarjeta> tarjeta = FXCollections.observableArrayList();  // 00132623 Crear una lista observable para almacenar las tarjetas
        String idconsulta = txtIdSeleccion.getText();  // 00132623 Obtener el ID a seleccionar del campo de texto

        if (idconsulta.isEmpty()) {  // 00132623 Verificar si el campo de texto está vacío
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Campo Vacío", "Por favor, ingrese un ID antes de intentar cargar los datos.");  // 00132623 Mostrar una alerta de error si el campo está vacío
            return;  // 00132623 Salir del método si el campo está vacío
        }

        String sql = "SELECT * FROM Tarjeta WHERE ID =" + idconsulta;  // 00132623 Consulta SQL para seleccionar una tarjeta por ID

        // 00132623 Intentar establecer una conexión a la base de datos y ejecutar la consulta
        try (Connection con = Conexion.getInstance().getConnection();  // 00132623 Obtener una conexión a la base de datos desde el pool de conexiones
             Statement pstm = con.createStatement();  // 00132623 Crear una declaración
             ResultSet rs = pstm.executeQuery(sql)) {  // 00132623 Ejecutar la consulta y obtener el conjunto de resultados

            // 00132623 Recorrer el conjunto de resultados y agregar las tarjetas a la lista observable
            while (rs.next()) {
                int id = rs.getInt("id");  // 00132623 Obtener el ID de la tarjeta del conjunto de resultados
                int clienteID = rs.getInt("id_cliente");  // 00132623 Obtener el ID del cliente del conjunto de resultados
                String numTarjeta = rs.getString("tarjeta_num");  // 00132623 Obtener el número de la tarjeta del conjunto de resultados
                String tipo = rs.getString("tipo");  // 00132623 Obtener el tipo de tarjeta del conjunto de resultados
                Date fechaexp = rs.getDate("exp_date");  // 00132623 Obtener la fecha de expiración de la tarjeta del conjunto de resultados
                int facilitador = rs.getInt("id_facilitador");  // 00132623 Obtener el ID del facilitador del conjunto de resultados

                Tarjeta tarjetas = new Tarjeta(id, clienteID, numTarjeta, fechaexp, tipo, facilitador);  // 00073923 Crear un nuevo objeto Tarjeta
                tarjeta.add(tarjetas);  // 00132623 Agregar la tarjeta a la lista observable
            }
        } catch (Exception e) {  // 00132623 Capturar cualquier excepción que ocurra durante la operación de base de datos
            System.out.println(e);  // 00132623 Imprimir el mensaje de excepción
        }
        TableTarjeta.setItems(tarjeta);  // 00132623 Establecer los elementos del TableView a la lista observable
    }


    @FXML
    public void SeleccionAll(ActionEvent event) {  // 00132623 Método para cargar todas las tarjetas
        CargarDatosTodos();  // 00132623 Llama al método para cargar todos los datos de las tarjetas
    }

    @FXML
    public void SeleccionId(ActionEvent event) {  // 00132623 Método para cargar una tarjeta por ID
        CargarDatosId();  // 00132623 Llama al método para cargar los datos de la tarjeta por ID
    }

    @FXML
    public void ActualizarDB(ActionEvent event) {  // 00132623 Método para actualizar una tarjeta
        if (txtIdActualizar.getText().isEmpty() || txtClienteIdUpdate.getText().isEmpty() || txtNumeroTarjetaUpdate.getText().isEmpty() || txtTipoTarjetaUpdate.getText().isEmpty() || txtFechaExpUpdate.getText().isEmpty() || txtFacilitadorUpdate.getText().isEmpty()) {  // 00132623 Verificar si algún campo está vacío

            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Campos Vacíos", "Por favor, complete todos los campos.");  // 00132623 Mostrar una alerta de error si hay campos vacíos
            return;  // 00132623 Salir del método si hay campos vacíos
        }

        try {
            Tarjeta tarjeta = new Tarjeta();  // 00132623 Crear un nuevo objeto Tarjeta
            tarjeta.updateCliente(  // 00132623 Llamar al método para actualizar la tarjeta en la base de datos
                    Integer.parseInt(txtIdActualizar.getText()),  // 00132623 Convertir el ID de texto a entero y pasar como parámetro
                    Integer.parseInt(txtClienteIdUpdate.getText()),  // 00132623 Convertir el ID del cliente de texto a entero y pasar como parámetro
                    txtNumeroTarjetaUpdate.getText(),  // 00132623 Pasar el número de la tarjeta como parámetro
                    Date.valueOf(txtFechaExpUpdate.getText()),  // 00132623 Convertir la fecha de expiración de texto a Date y pasar como parámetro
                    txtTipoTarjetaUpdate.getText(),  // 00132623 Pasar el tipo de tarjeta como parámetro
                    Integer.parseInt(txtFacilitadorUpdate.getText())  // 00132623 Convertir el ID del facilitador de texto a entero y pasar como parámetro
            );  // 00132623 Actualizar la tarjeta en la base de datos

            Alerta.mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Actualización Exitosa", "La tarjeta ha sido actualizada correctamente.");  // 00132623 Mostrar una alerta de información si la actualización fue exitosa

            txtIdActualizar.setText("");  // 00132623 Limpiar el campo de texto del ID
            txtClienteIdUpdate.setText("");  // 00132623 Limpiar el campo de texto del ID del cliente
            txtNumeroTarjetaUpdate.setText("");  // 00132623 Limpiar el campo de texto del número de tarjeta
            txtTipoTarjetaUpdate.setText("");  // 00132623 Limpiar el campo de texto del tipo de tarjeta
            txtFechaExpUpdate.setText("");  // 00132623 Limpiar el campo de texto de la fecha de expiración
            txtFacilitadorUpdate.setText("");  // 00132623 Limpiar el campo de texto del ID del facilitador
        } catch (NumberFormatException e) {  // 00132623 Capturar cualquier excepción de formato de número
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error de Formato", "Formato Inválido", "Por favor, ingrese valores numéricos válidos en los campos correspondientes.");  // 00132623 Mostrar una alerta de error si hay un formato inválido
        } catch (Exception e) {  // 00132623 Capturar cualquier otra excepción
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error de SQL", "Error en la Base de Datos", "Ocurrió un error al actualizar la tarjeta en la base de datos.");  // 00132623 Mostrar una alerta de error si ocurre una excepción al actualizar en la base de datos
            e.printStackTrace();  // 00132623 Imprimir la traza de la excepción
        }
    }



    @FXML
    public void Delete(ActionEvent event) {  // 00132623 Método para eliminar una tarjeta
        if (txtDeletId.getText().isEmpty()) {  // 00132623 Verificar si el campo de ID está vacío
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Campo Vacío", "Por favor, complete el campo ID.");  // 00132623 Mostrar una alerta de error si el campo está vacío
            return;  // 00132623 Salir del método si el campo está vacío
        }

        try {
            Tarjeta tarjeta = new Tarjeta();  // 00132623 Crear un nuevo objeto Tarjeta
            tarjeta.DeleteIdTarjeta(txtDeletId.getText());  // 00132623 Eliminar la tarjeta de la base de datos

            Alerta.mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Eliminación Exitosa", "La tarjeta ha sido eliminada correctamente.");  // 00132623 Mostrar una alerta de información si la eliminación fue exitosa

            txtDeletId.setText("");  // 00132623 Limpiar el campo de texto del ID
        } catch (Exception e) {  // 00132623 Capturar cualquier excepción que ocurra durante la operación de base de datos
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error de SQL", "Error en la Base de Datos", "Ocurrió un error al eliminar la tarjeta de la base de datos.");  // 00132623 Mostrar una alerta de error si ocurre una excepción
            e.printStackTrace();  // 00132623 Imprimir la traza de la excepción
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {  // 00132623 Método para inicializar el controlador
        ConfigurarTable();  // 00132623 Llama al método para configurar el TableView
    }
}


