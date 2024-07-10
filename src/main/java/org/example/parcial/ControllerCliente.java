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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerCliente implements Initializable {

    @FXML
    private TextField txtDeletId; // 00073923 Campo privado para el TextField usado para ingresar el ID a eliminar

    @FXML
    private TextField txtNombreUpdate, txtApellidosUpdate, txtDireccionUpdate, txtTelefonoUpdate, txtIdActualizar; // 00073923 Campos privados para los TextFields usados para actualizar la información del cliente

    @FXML
    private TableView<Cliente> TableCliente; // 00073923 Campo privado para el TableView que muestra los clientes

    @FXML
    private TableColumn<Cliente, Integer> ColumnId; // 00073923 Campo privado para la TableColumn que muestra el ID del cliente

    @FXML
    private TableColumn<Cliente, String> ColumnNombres; // 00073923 Campo privado para la TableColumn que muestra los nombres del cliente

    @FXML
    private TableColumn<Cliente, String> ColumnApellidos; // 00073923 Campo privado para la TableColumn que muestra los apellidos del cliente

    @FXML
    private TableColumn<Cliente, String> ColumnDireccion; // 00073923 Campo privado para la TableColumn que muestra la dirección del cliente

    @FXML
    private TableColumn<Cliente, String> ColumnTelefono; // 00073923 Campo privado para la TableColumn que muestra el teléfono del cliente

    @FXML
    private TextField txtNombre, txtApellidos, txtDireccion, txtTelefono, txtIdSeleccion; // 00073923 Campos privados para los TextFields usados para ingresar nuevos clientes y seleccionar un cliente por ID

    // 00073923 Método privado para configurar las columnas del TableView
    private void ConfigurarTable() {
        ColumnId.setCellValueFactory(new PropertyValueFactory<>("id")); // 00073923 Vincula la columna ID con la propiedad "id" del cliente
        ColumnNombres.setCellValueFactory(new PropertyValueFactory<>("nombres")); // 00073923 Vincula la columna Nombres con la propiedad "nombres" del cliente
        ColumnApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos")); // 00073923 Vincula la columna Apellidos con la propiedad "apellidos" del cliente
        ColumnDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion")); // 00073923 Vincula la columna Dirección con la propiedad "direccion" del cliente
        ColumnTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono")); // 00073923 Vincula la columna Teléfono con la propiedad "telefono" del cliente
    }

    // 00073923 Método privado para cargar todos los datos de los clientes en el TableView
    private void CargarDatosTodos() {
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(); // 00073923 Crea una lista observable para almacenar los clientes

        String sql = "SELECT * FROM Cliente"; // 00073923 Consulta SQL para seleccionar todos los clientes

        // 00073923 Try-with-resources para establecer una conexión a la base de datos y ejecutar la consulta
        try (Connection con = Conexion.getInstance().getConnection(); // 00073923 Obtiene una conexión a la base de datos desde el pool de conexiones
             Statement pstm = con.createStatement(); // 00073923 Crea una declaración
             ResultSet rs = pstm.executeQuery(sql)) { // 00073923 Ejecuta la consulta y obtiene el resultado

            // 00073923 Itera sobre los resultados y agrega los clientes a la lista observable
            while (rs.next()) {
                int id = rs.getInt("id"); // 00073923 Obtiene el ID del cliente del resultado
                String nombre = rs.getString("nombres"); // 00073923 Obtiene los nombres del cliente del resultado
                String apellidos = rs.getString("apellidos"); // 00073923 Obtiene los apellidos del cliente del resultado
                String direccion = rs.getString("direccion"); // 00073923 Obtiene la dirección del cliente del resultado
                String telefono = rs.getString("telefono"); // 00073923 Obtiene el teléfono del cliente del resultado

                Cliente cliente = new Cliente(id, nombre, apellidos, direccion, telefono); // 00073923 Crea un nuevo objeto Cliente
                clientes.add(cliente); // 00073923 Agrega el cliente a la lista observable
            }
        } catch (Exception e) { // 00073923 Captura cualquier excepción que ocurra durante la operación de base de datos
            System.out.println(e); // 00073923 Imprime el mensaje de excepción
        }
        TableCliente.setItems(clientes); // 00073923 Establece los elementos del TableView a la lista observable
    }

    private void CargarDatosId() { // 00073923 Método privado para cargar los datos de un cliente por su ID
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(); // 00073923 Crea una lista observable para almacenar los clientes

        if(txtIdSeleccion.getText().isEmpty()){ // 00073923 Verifica si el campo de texto para la selección de ID está vacío
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Campos Vacíos", "Por favor, complete todos los campos antes de buscar un cliente."); // 00073923 Muestra una alerta de error si el campo está vacío
            return; // 00073923 Retorna para evitar continuar con la ejecución si el campo está vacío
        }

        String idconsulta = txtIdSeleccion.getText(); // 00073923 Obtiene el texto del campo de selección de ID

        String sql = "SELECT * FROM Cliente WHERE ID =" + idconsulta; // 00073923 Define la consulta SQL para seleccionar un cliente por ID

        // 00073923 Try-with-resources para establecer una conexión a la base de datos y ejecutar la consulta
        try (Connection con = Conexion.getInstance().getConnection(); // 00073923 Obtiene una conexión a la base de datos desde el pool de conexiones
             Statement pstm = con.createStatement(); // 00073923 Crea una declaración
             ResultSet rs = pstm.executeQuery(sql)) { // 00073923 Ejecuta la consulta y obtiene el resultado

            // 00073923 Itera sobre los resultados y agrega los clientes a la lista observable
            while (rs.next()) { // 00073923 Mientras haya resultados en el ResultSet
                int id = rs.getInt("id"); // 00073923 Obtiene el ID del cliente del resultado
                String nombre = rs.getString("nombres"); // 00073923 Obtiene los nombres del cliente del resultado
                String apellidos = rs.getString("apellidos"); // 00073923 Obtiene los apellidos del cliente del resultado
                String direccion = rs.getString("direccion"); // 00073923 Obtiene la dirección del cliente del resultado
                String telefono = rs.getString("telefono"); // 00073923 Obtiene el teléfono del cliente del resultado

                Cliente cliente = new Cliente(id, nombre, apellidos, direccion, telefono); // 00073923 Crea un nuevo objeto Cliente con los datos obtenidos
                clientes.add(cliente); // 00073923 Agrega el cliente a la lista observable
            }
        } catch (Exception e) { // 00073923 Captura cualquier excepción que ocurra durante la operación de base de datos
            System.out.println(e); // 00073923 Imprime el mensaje de excepción
        }

        TableCliente.setItems(clientes); // 00073923 Establece los elementos del TableView a la lista observable de clientes
    }


    @FXML
    public void AgregarCliente(ActionEvent event) { // 00073923 Método público para agregar un nuevo cliente
        if (txtNombre.getText().isEmpty() || txtApellidos.getText().isEmpty() || // 00073923 Verifica si alguno de los campos de texto está vacío
                txtDireccion.getText().isEmpty() || txtTelefono.getText().isEmpty()) { // 00073923 Verifica si alguno de los campos de texto está vacío
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Campos Vacíos", "Por favor, complete todos los campos antes de agregar un cliente."); // 00073923 Muestra una alerta de error si algún campo está vacío
        } else { // 00073923 Si todos los campos están llenos
            Cliente cliente = new Cliente(); // 00073923 Crea un nuevo objeto Cliente
            cliente.agregarCliente(txtNombre.getText(), txtApellidos.getText(), txtDireccion.getText(), txtTelefono.getText()); // 00073923 Llama al método para agregar el cliente a la base de datos

            Alerta.mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cliente Agregado", "El cliente ha sido agregado exitosamente."); // 00073923 Muestra una alerta informativa indicando que el cliente fue agregado exitosamente

            txtNombre.setText(""); // 00073923 Limpia el campo de texto para el nombre
            txtApellidos.setText(""); // 00073923 Limpia el campo de texto para los apellidos
            txtDireccion.setText(""); // 00073923 Limpia el campo de texto para la dirección
            txtTelefono.setText(""); // 00073923 Limpia el campo de texto para el teléfono
        }
    }


    @FXML
    public void RegresarMenu(ActionEvent event) { // 00073923 Método público para regresar al menú
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuView.fxml")); // 00073923 Carga el archivo FXML para el menú
            Parent root = loader.load(); // 00073923 Carga el nodo raíz
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // 00073923 Obtiene la ventana actual

            Scene scene = new Scene(root, 1000, 600); // 00073923 Crea una nueva escena con el nodo raíz y establece su tamaño
            stage.setScene(scene); // 00073923 Establece la escena a la ventana
            stage.show(); // 00073923 Muestra la ventana
        } catch (Exception e) { // 00073923 Captura cualquier excepción que ocurra durante la carga del archivo FXML
            System.out.println(e.getMessage()); // 00073923 Imprime el mensaje de excepción
        }
    }

    @FXML
    public void SeleccionAll(ActionEvent event) { // 00073923 Método público para cargar todos los clientes
        CargarDatosTodos(); // 00073923 Llama al método para cargar todos los datos de los clientes
    }

    @FXML
    public void SeleccionId(ActionEvent event) { // 00073923 Método público para cargar un cliente por su ID
        CargarDatosId(); // 00073923 Llama al método para cargar los datos del cliente por ID
    }

    @FXML
    public void ActualizarDB(ActionEvent event) {  // 00073923 Método para actualizar un cliente
        if (txtIdActualizar.getText().isEmpty() || txtNombreUpdate.getText().isEmpty() || // 00073923 Verifica si alguno de los campos de texto está vacío
                txtApellidosUpdate.getText().isEmpty() || txtDireccionUpdate.getText().isEmpty() || // 00073923 Verifica si alguno de los campos de texto está vacío
                txtTelefonoUpdate.getText().isEmpty()) { // 00073923 Verifica si alguno de los campos de texto está vacío
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Campo Vacío", "Por favor, complete todos los campos."); // 00073923 Muestra una alerta de error si algún campo está vacío
            return; // 00073923 Retorna y no ejecuta el resto del método
        }

        try {
            Cliente cliente = new Cliente();  // 00073923 Crea un nuevo objeto Cliente
            cliente.updateCliente(txtIdActualizar.getText(), txtNombreUpdate.getText(), // 00073923 Llama al método para actualizar el cliente en la base de datos
                    txtApellidosUpdate.getText(), txtDireccionUpdate.getText(), // 00073923 Pasa los valores de los campos de texto como parámetros
                    txtTelefonoUpdate.getText());  // 00073923 Pasa los valores de los campos de texto como parámetros

            Alerta.mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cliente Actualizado", "El cliente ha sido actualizado correctamente."); // 00073923 Muestra una alerta informativa indicando que el cliente fue actualizado correctamente

            txtIdActualizar.setText("");  // 00073923 Limpia el campo de texto para el ID
            txtNombreUpdate.setText("");  // 00073923 Limpia el campo de texto para el nombre
            txtApellidosUpdate.setText("");  // 00073923 Limpia el campo de texto para los apellidos
            txtDireccionUpdate.setText("");  // 00073923 Limpia el campo de texto para la dirección
            txtTelefonoUpdate.setText("");  // 00073923 Limpia el campo de texto para el teléfono
        } catch (Exception e) {
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error de SQL", "Error en la Base de Datos", "Ocurrió un error al actualizar el cliente en la base de datos."); // 00073923 Muestra una alerta de error si ocurre una excepción
            e.printStackTrace(); // 00073923 Imprime la traza de la excepción
        }
    }


    @FXML
    public void Delete() {  // 00073923 Método para eliminar un cliente
        if (txtDeletId.getText().isEmpty()) {  // 00073923 Verifica si el campo de texto para el ID está vacío
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Campo Vacío", "Por favor, ingrese un ID para eliminar.");  // 00073923 Muestra una alerta de error si el campo está vacío
            return;  // 00073923 Retorna y no ejecuta el resto del método
        }

        try {
            Cliente cliente = new Cliente();  // 00073923 Crea un nuevo objeto Cliente
            cliente.DeleteId(txtDeletId.getText());  // 00073923 Elimina el cliente de la base de datos usando el ID proporcionado

            Alerta.mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cliente Eliminado", "El cliente ha sido eliminado correctamente.");  // 00073923 Muestra una alerta informativa indicando que el cliente fue eliminado correctamente
            txtDeletId.setText("");  // 00073923 Limpia el campo de texto para el ID
        } catch (Exception e) {  // 00073923 Captura cualquier excepción que ocurra durante la operación de base de datos
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error de SQL", "Error en la Base de Datos", "Ocurrió un error al eliminar el cliente en la base de datos.");  // 00073923 Muestra una alerta de error si ocurre una excepción
            e.printStackTrace();  // 00073923 Imprime la traza de la excepción
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { // 00073923 Método para inicializar el controlador
        ConfigurarTable(); // 00073923 Llama al método para configurar la tabla
    }

}

