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

public class ControllerCompra implements Initializable {

    @FXML
    private TextField txtDeletId;  // 00073923 Campo privado para el TextField usado para ingresar el ID a eliminar

    @FXML
    private TextField txtFechaCompraUpdate, txtMontoUpdate, txtDescripcionUpdate, txtIdTarjetaUpdate, txtIdActualizar;  // 00073923 Campos privados para los TextFields usados para ingresar la información actualizada de la compra

    @FXML
    private TableView<Compra> TableCompra;  // 00073923 Campo privado para el TableView que muestra las compras

    @FXML
    private TableColumn<Compra, Integer> ColumnId;  // 00073923 Campo privado para la TableColumn que muestra los IDs de las compras

    @FXML
    private TableColumn<Compra, String> ColumnFechaCompra;  // 00073923 Campo privado para la TableColumn que muestra las fechas de las compras

    @FXML
    private TableColumn<Compra, Float> ColumnMonto;  // 00073923 Campo privado para la TableColumn que muestra los montos de las compras

    @FXML
    private TableColumn<Compra, String> ColumnDescripcion;  // 00073923 Campo privado para la TableColumn que muestra las descripciones de las compras

    @FXML
    private TableColumn<Compra, Integer> ColumnTarjetaId;  // 00073923 Campo privado para la TableColumn que muestra los IDs de las tarjetas

    @FXML
    private TextField txtIdSeleccion;  // 00073923 Campo privado para el TextField usado para ingresar el ID a seleccionar

    @FXML
    private TextField txtFechaCompra, txtMontoPagado, txtDescripcion, txtIdTarjeta;  // 00073923 Campos privados para los TextFields usados para ingresar la información de una nueva compra

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {  // 00073923 Método para inicializar el controlador
        ConfigurarTable();  // 00073923 Llama al método para configurar el TableView
    }

    @FXML
    public void AgregarTarjeta(ActionEvent event) {  // 00073923 Método para agregar una nueva compra
        if (txtFechaCompra.getText().isEmpty() || txtMontoPagado.getText().isEmpty() ||  // 00073923 Verifica si alguno de los campos de texto está vacío
                txtDescripcion.getText().isEmpty() || txtIdTarjeta.getText().isEmpty()) {  // 00073923 Verifica si el campo de texto para el ID de la tarjeta está vacío
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Campos Vacíos", "Por favor, complete todos los campos antes de agregar la compra.");  // 00073923 Muestra una alerta de error si algún campo está vacío
            return;  // 00073923 Retorna y no ejecuta el resto del método
        }

        try {
            Date fechaCompra = Date.valueOf(txtFechaCompra.getText());  // 00073923 Convierte el texto del campo de fecha a un objeto Date
            float montoPagado = Float.parseFloat(txtMontoPagado.getText());  // 00073923 Convierte el texto del campo de monto a un float
            String descripcion = txtDescripcion.getText();  // 00073923 Obtiene el texto del campo de descripción
            int idTarjeta = Integer.parseInt(txtIdTarjeta.getText());  // 00073923 Convierte el texto del campo de ID de tarjeta a un int

            Compra compra = new Compra();  // 00073923 Crea un nuevo objeto Compra
            compra.agregarCompra(fechaCompra, montoPagado, descripcion, idTarjeta);  // 00073923 Llama al método para agregar la compra a la base de datos

            Alerta.mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Compra Agregada", "La compra ha sido agregada correctamente.");  // 00073923 Muestra una alerta informativa indicando que la compra fue agregada correctamente

            txtFechaCompra.setText("");  // 00073923 Limpia el campo de texto para la fecha de compra
            txtMontoPagado.setText("");  // 00073923 Limpia el campo de texto para el monto pagado
            txtDescripcion.setText("");  // 00073923 Limpia el campo de texto para la descripción
            txtIdTarjeta.setText("");  // 00073923 Limpia el campo de texto para el ID de la tarjeta
        } catch (NumberFormatException e) {  // 00073923 Captura cualquier excepción de formato de número que ocurra durante la conversión de texto a número
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error de Formato", "Formato Inválido", "Por favor, ingrese datos válidos en los campos.");  // 00073923 Muestra una alerta de error de formato si ocurre una excepción
        } catch (Exception e) {  // 00073923 Captura cualquier otra excepción que ocurra durante la operación
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error al agregar compra", "Ocurrió un error al agregar la compra. Por favor, intente nuevamente.");  // 00073923 Muestra una alerta de error si ocurre una excepción
            e.printStackTrace();  // 00073923 Imprime la traza de la excepción
        }
    }


    @FXML
    public void RegresarMenu(ActionEvent event) {  // 00073923 Método para regresar al menú
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuView.fxml"));  // 00073923 Carga el archivo FXML para el menú
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
    public void SeleccionAll(ActionEvent event) {  // 00073923 Método para cargar todas las compras
        CargarDatosTodos();  // 00073923 Llama al método para cargar todos los datos de las compras
    }

    @FXML
    public void SeleccionId(ActionEvent event) {  // 00073923 Método para cargar una compra por ID
        CargarDatosId();  // 00073923 Llama al método para cargar los datos de la compra por ID
    }

    @FXML
    public void ActualizarDB(ActionEvent event) {  // 00073923 Método para actualizar una compra
        if (txtIdActualizar.getText().isEmpty() || txtFechaCompraUpdate.getText().isEmpty() ||  // 00073923 Verifica si alguno de los campos de texto está vacío
                txtMontoUpdate.getText().isEmpty() || txtDescripcionUpdate.getText().isEmpty() ||  // 00073923 Verifica si el campo de texto para la descripción está vacío
                txtIdTarjetaUpdate.getText().isEmpty()) {  // 00073923 Verifica si el campo de texto para el ID de la tarjeta está vacío
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Campos Vacíos", "Por favor, complete todos los campos antes de actualizar la compra.");  // 00073923 Muestra una alerta de error si algún campo está vacío
            return;  // 00073923 Retorna y no ejecuta el resto del método
        }

        try {
            int idActualizar = Integer.parseInt(txtIdActualizar.getText());  // 00073923 Convierte el texto del campo de ID a un entero
            Date fechaCompra = Date.valueOf(txtFechaCompraUpdate.getText());  // 00073923 Convierte el texto del campo de fecha a un objeto Date
            float montoUpdate = Float.parseFloat(txtMontoUpdate.getText());  // 00073923 Convierte el texto del campo de monto a un float
            String descripcionUpdate = txtDescripcionUpdate.getText();  // 00073923 Obtiene el texto del campo de descripción
            int idTarjetaUpdate = Integer.parseInt(txtIdTarjetaUpdate.getText());  // 00073923 Convierte el texto del campo de ID de tarjeta a un int

            Compra compra = new Compra();  // 00073923 Crea un nuevo objeto Compra
            compra.updateCompra(idActualizar, fechaCompra, montoUpdate, descripcionUpdate, idTarjetaUpdate);  // 00073923 Llama al método para actualizar la compra en la base de datos

            Alerta.mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Compra Actualizada", "La compra ha sido actualizada correctamente.");  // 00073923 Muestra una alerta informativa indicando que la compra fue actualizada correctamente

            txtIdActualizar.setText("");  // 00073923 Limpia el campo de texto para el ID
            txtFechaCompraUpdate.setText("");  // 00073923 Limpia el campo de texto para la fecha de compra
            txtMontoUpdate.setText("");  // 00073923 Limpia el campo de texto para el monto
            txtDescripcionUpdate.setText("");  // 00073923 Limpia el campo de texto para la descripción
            txtIdTarjetaUpdate.setText("");  // 00073923 Limpia el campo de texto para el ID de la tarjeta
        } catch (NumberFormatException e) {  // 00073923 Captura cualquier excepción de formato de número que ocurra durante la conversión de texto a número
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error de Formato", "Formato Inválido", "Por favor, ingrese datos válidos en los campos.");  // 00073923 Muestra una alerta de error de formato si ocurre una excepción
        } catch (Exception e) {  // 00073923 Captura cualquier otra excepción que ocurra durante la operación
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error al actualizar compra", "Ocurrió un error al actualizar la compra. Por favor, intente nuevamente.");  // 00073923 Muestra una alerta de error si ocurre una excepción
            e.printStackTrace();  // 00073923 Imprime la traza de la excepción
        }
    }


    @FXML
    public void Delete(ActionEvent event) {  // 00073923 Método para eliminar una compra
        if (txtDeletId.getText().isEmpty()) {  // 00073923 Verifica si el campo de texto para el ID está vacío
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Campo Vacío", "Por favor, ingrese el ID de la compra que desea eliminar.");  // 00073923 Muestra una alerta de error si el campo está vacío
            return;  // 00073923 Retorna y no ejecuta el resto del método
        }

        try {
            int id = Integer.parseInt(txtDeletId.getText());  // 00073923 Convierte el texto del campo de ID a un entero

            Compra compra = new Compra();  // 00073923 Crea un nuevo objeto Compra
            compra.DeleteId(id);  // 00073923 Elimina la compra de la base de datos

            Alerta.mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Compra Eliminada", "La compra ha sido eliminada correctamente.");  // 00073923 Muestra una alerta informativa indicando que la compra fue eliminada correctamente

            txtDeletId.setText("");  // 00073923 Limpia el campo de texto para el ID
        } catch (NumberFormatException e) {  // 00073923 Captura cualquier excepción de formato de número que ocurra durante la conversión de texto a número
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error de Formato", "Formato Inválido", "Por favor, ingrese un ID válido.");  // 00073923 Muestra una alerta de error de formato si ocurre una excepción
        } catch (Exception e) {  // 00073923 Captura cualquier otra excepción que ocurra durante la operación
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error al eliminar compra", "Ocurrió un error al eliminar la compra. Por favor, intente nuevamente.");  // 00073923 Muestra una alerta de error si ocurre una excepción
            e.printStackTrace();  // 00073923 Imprime la traza de la excepción
        }
    }


    // 00073923 Método para configurar las columnas del TableView
    private void ConfigurarTable() {
        ColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));  // 00073923 Vincula la columna ID con la propiedad "id" de Compra
        ColumnFechaCompra.setCellValueFactory(new PropertyValueFactory<>("fecha"));  // 00073923 Vincula la columna FechaCompra con la propiedad "fechaCompra" de Compra
        ColumnMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));  // 00073923 Vincula la columna Monto con la propiedad "monto" de Compra
        ColumnDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));  // 00073923 Vincula la columna Descripcion con la propiedad "descripcion" de Compra
        ColumnTarjetaId.setCellValueFactory(new PropertyValueFactory<>("id_tarjeta"));  // 00073923 Vincula la columna TarjetaId con la propiedad "tarjetaID" de Compra
    }

    // 00073923 Método para cargar todos los datos de las compras en el TableView
    private void CargarDatosTodos() {
        ObservableList<Compra> compras = FXCollections.observableArrayList();  // 00073923 Crea una lista observable para almacenar las compras

        String sql = "SELECT * FROM Compra";  // 00073923 Consulta SQL para seleccionar todas las compras

        // 00073923 Try-with-resources para establecer una conexión a la base de datos y ejecutar la consulta
        try (Connection con = Conexion.getInstance().getConnection();  // 00073923 Obtiene una conexión a la base de datos desde el pool de conexiones
             Statement pstm = con.createStatement();  // 00073923 Crea una declaración
             ResultSet rs = pstm.executeQuery(sql)) {  // 00073923 Ejecuta la consulta y obtiene el resultado

            // 00073923 Itera sobre los resultados y agrega las compras a la lista observable
            while (rs.next()) {
                int id = rs.getInt("id");  // 00073923 Obtiene el ID de la compra del resultado
                Date fechaCompra = rs.getDate("fecha");  // 00073923 Obtiene la fecha de la compra del resultado
                float monto = rs.getFloat("monto");  // 00073923 Obtiene el monto de la compra del resultado
                String descripcion = rs.getString("descripcion");  // 00073923 Obtiene la descripción de la compra del resultado
                int tarjetaID = rs.getInt("id_tarjeta");  // 00073923 Obtiene el ID de la tarjeta del resultado

                // 00073923 Crea un nuevo objeto Compra
                Compra compra = new Compra(id, fechaCompra, monto, descripcion, tarjetaID);
                compras.add(compra);  // 00073923 Agrega la compra a la lista observable
            }
        } catch (Exception e) {  // 00073923 Captura cualquier excepción que ocurra durante la operación de base de datos
            System.out.println(e);  // 00073923 Imprime el mensaje de excepción
        }
        TableCompra.setItems(compras);  // 00073923 Establece los elementos del TableView a la lista observable
    }


    private void CargarDatosId() {
        ObservableList<Compra> compras = FXCollections.observableArrayList();  // 00073923 Crear una lista observable para almacenar las compras
        String idconsulta = txtIdSeleccion.getText();  // 00073923 Obtener el ID para seleccionar del campo de texto

        if (idconsulta.isEmpty()) {  // 00073923 Verificar si el campo de texto del ID está vacío
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Campo Vacío", "Por favor, ingrese un ID antes de intentar cargar los datos.");  // 00073923 Mostrar una alerta de error si el campo está vacío
            return;  // 00073923 Salir del método si el campo está vacío
        }

        String sql = "SELECT * FROM Compra WHERE ID =" + idconsulta;  // 00073923 Consulta SQL para seleccionar una compra por ID

        // 00073923 Try-with-resources para establecer una conexión a la base de datos y ejecutar la consulta
        try (Connection con = Conexion.getInstance().getConnection();  // 00073923 Obtener una conexión a la base de datos desde el pool de conexiones
             Statement pstm = con.createStatement();  // 00073923 Crear una declaración
             ResultSet rs = pstm.executeQuery(sql)) {  // 00073923 Ejecutar la consulta y obtener el conjunto de resultados

            // 00073923 Iterar sobre el conjunto de resultados y agregar compras a la lista observable
            while (rs.next()) {
                int id = rs.getInt("id");  // 00073923 Obtener el ID de la compra del conjunto de resultados
                Date fechaCompra = rs.getDate("fecha");  // 00073923 Obtener la fecha de la compra del conjunto de resultados
                float monto = rs.getFloat("monto");  // 00073923 Obtener el monto de la compra del conjunto de resultados
                String descripcion = rs.getString("descripcion");  // 00073923 Obtener la descripción de la compra del conjunto de resultados
                int tarjetaID = rs.getInt("id_tarjeta");  // 00073923 Obtener el ID de la tarjeta del conjunto de resultados

                // 00073923 Crear un nuevo objeto Compra
                Compra compra = new Compra(id, fechaCompra, monto, descripcion, tarjetaID);
                compras.add(compra);  // 00073923 Agregar la compra a la lista observable
            }
        } catch (Exception e) {  // 00073923 Capturar cualquier excepción que ocurra durante la operación de base de datos
            System.out.println(e);  // 00073923 Imprimir el mensaje de excepción
        }
        TableCompra.setItems(compras);  // 00073923 Establecer los elementos del TableView a la lista observable
    }

}


