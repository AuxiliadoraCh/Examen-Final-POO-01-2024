package org.example.parcial;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

//00225023 Clase para manejar la lógica de la interfaz y gestionar interacciones con la base de datos
public class HelloController implements Initializable {

    @FXML
    private DatePicker fecha1; // 00225023 Seleccionador de fecha inicial para reportes
    @FXML
    private DatePicker fecha2; // 00225023 Selecciónador de fecha final para reportes
    @FXML
    private ComboBox<Cliente> clientes; // 00225023 Campo de ComboBox para cliente en reportes
    @FXML
    private ComboBox<Cliente> clientesB; // 00225023 Campo de ComboBox para cliente en Reporte B
    @FXML
    private ComboBox<String> Mes; // 00225023 Campo del ComboBox para mes en Reporte B
    @FXML
    private ComboBox<Integer> Año; // 00225023 ComboBox para año en Reporte B
    @FXML
    private ComboBox<Cliente> clientesC; // 00225023 ComboBox para cliente en Reporte C
    @FXML
    private TextArea reporteC; // 00225023 Área de texto para mostrar el Reporte C
    @FXML
    private ComboBox<Facilitador> facilitadoresTarjeta; // 00225023 ComboBox para facilitador en Reporte D
    @FXML
    private TableView<Map<String, Object>> reporteDTable; // 00225023 Tabla para mostrar el Reporte D
    @FXML
    private TableView<Map<String, Object>> reporteATable; // 00225023 Tabla para mostrar el Reporte A
    @FXML
    private TableView<Map<String, Object>> reporteBTable; // 00225023 Tabla para mostrar el Reporte B
    @FXML
    private TableColumn<Map<String, Object>, Float> colTotalMes; // 00225023 Columna en tabla de Reporte B para total del mes
    @FXML
    private TableColumn<Map<String, Object>, String> colNombres; // 00225023 Columna en tabla de Reporte D para nombres
    @FXML
    private TableColumn<Map<String, Object>, String> colApellidos; // 00225023 Columna en tabla de Reporte D para apellidos
    @FXML
    private TableColumn<Map<String, Object>, Integer> colCantidadCompras; // 00225023 Columna en tabla de Reporte D para cantidad de compras
    @FXML
    private TableColumn<Map<String, Object>, Float> colTotalGastado; // 00225023 Columna en tabla de Reporte D para total gastado
    @FXML
    private TableColumn<Map<String, Object>, String> colNombresA; // 00225023 Columna en tabla de Reporte A para nombres
    @FXML
    private TableColumn<Map<String, Object>, String> colApellidosA; // 00225023 Columna en tabla de Reporte A para apellidos
    @FXML
    private TableColumn<Map<String, Object>, String> colTipoA; // 00225023 Columna en tabla de Reporte A para tipo de transacción
    @FXML
    private TableColumn<Map<String, Object>, String> colFechaA; // 00225023 Columna en tabla de Reporte A para fecha de la transacción
    @FXML
    private TableColumn<Map<String, Object>, Float> colMontoA; // 00225023 Columna en tabla de Reporte A para monto de la transacción
    @FXML
    private TableColumn<Map<String, Object>, String> colDescripcionA; // 00225023 Columna en tabla de Reporte A para descripción de

    // 00225023 Método que se llama al inicializar la interfaz, este inicializa los ComboBox, las tablas y las columnas con los datos necesarios.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        llenarComboBox(); // 00225023 Llena los ComboBox de clientes
        llenarComboBoxMes(); // 00225023 Llena el ComboBox de meses
        llenarComboBoxAños(); // 00225023 Llena el ComboBox de años
        llenarComboBoxFacilitadores(); // 00225023 Llena el ComboBox de facilitadores

        // 00225023 Configura las celdas de las tablas con las propiedades de los datos
        colNombres.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("nombres")));// 00225023 Configura la celda de la columna de nombres con la propiedad "nombres" de los datos
        colApellidos.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("apellidos")));// 00225023 Configura la celda de la columna de apellidos con "apellidos" de los datos
        colCantidadCompras.setCellValueFactory(cellData -> new SimpleIntegerProperty((Integer) cellData.getValue().get("cantidad_compras")).asObject());// 00225023 Configura la columna de cantidad_compras con la propiedad "cantidad_compras" de los datos
        colTotalGastado.setCellValueFactory(cellData -> new SimpleFloatProperty((Float) cellData.getValue().get("total_gastado")).asObject()); // 00225023 Configura la celda de la columna de total gastado con la propiedad "total_gastado" de los datos
        colNombresA.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("nombres"))); // 00225023 Configura la celda de la columna de nombres A con la propiedad "nombres" de los datos
        colApellidosA.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("apellidos"))); // 00225023 Configura la celda de la columna de apellidos A con los "apellidos" de los datos
        colTipoA.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("tipo"))); // 00225023 Configura la celda de la columna de tipo A con la propiedad "tipo" de los datos

        // 00225023 Configura la celda de la columna de fecha A con la propiedad "fecha" de los datos
        colFechaA.setCellValueFactory(cellData -> {
            Date date = (Date) cellData.getValue().get("fecha"); // 00225023 Obtiene la fecha de los datos
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 00225023 Define el formato de fecha
            return new SimpleStringProperty(dateFormat.format(date)); // 00225023 Devuelve la fecha formateada como una propiedad de cadena
        });

        colMontoA.setCellValueFactory(cellData -> new SimpleFloatProperty((Float) cellData.getValue().get("monto")).asObject()); // 00225023 Configura la celda de la columna de monto A con la propiedad "monto" de los datos
        colDescripcionA.setCellValueFactory(cellData -> new SimpleStringProperty((String) cellData.getValue().get("descripcion"))); // 00225023 Configura la celda de la columna de descripción A con la propiedad "descripcion" de los datos
        colTotalMes.setCellValueFactory(cellData -> new SimpleFloatProperty((Float) cellData.getValue().get("Total")).asObject()); // 00225023 Configura la celda de la columna de total del mes con la propiedad "Total" de los datos

    }

    // 00225023 Metodo para llenar los comboBox con la base
    public void llenarComboBox() {
        try {
            Connection connection = Conexion.getInstance().getConnection(); // 00225023 Establece la conexión a la base de datos
            Statement statement = connection.createStatement(); // 00225023 Se hace la creacion de la declaración SQL
            ResultSet rs = statement.executeQuery("Select * from Cliente"); // 00225023 Ejecución de la consulta SQL
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt("id"), rs.getString("nombres"), rs.getString("apellidos"), rs.getString("direccion"), rs.getString("telefono")); // 00225023 Creación del objeto Cliente
                clientes.getItems().add(cliente); // 00225023 Añade el cliente al ComboBox
                clientesB.getItems().add(cliente); // 00225023 Añade el cliente al ComboBox B
                clientesC.getItems().add(cliente); // 00225023 Añade el cliente al ComboBox C
            }
        } catch (SQLException e) { // 00225023 Maneja la excepcion
            System.out.println(e); // 00225023 Imprime el mensaje de excepción SQL
        }
    }

    // 000225023 Metodo para llenar los comboBox de los años
    public void llenarComboBoxAños() {
        for (int year = 2024; year >= 2010; year--) { // 00225023 For con el rango de años
            Año.getItems().add(year); // 00225023 Obtiene el año y lo añade al ComboBox
        }
    }

    // 000225023 Metodo para llenar los comboBox de los meses
    public void llenarComboBoxMes() {
        ObservableList<String> meses = FXCollections.observableArrayList( // 00225023 Utiliza collections y maneja la lista de meses
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        );
        Mes.setItems(meses); // 00225023 Añade la lista de meses al ComboBox
    }

    // 000225023 Metodo para llenar los comboBox de los facilitadores
    public void llenarComboBoxFacilitadores() {
        try {
            Connection connection = Conexion.getInstance().getConnection(); // 00225023 Hace la conexión a la base de datos
            Statement statement = connection.createStatement(); // 00225023 Creación de la declaración SQL
            ResultSet rs = statement.executeQuery("SELECT * FROM facilitador"); // 00225023 Ejecuta la consulta SQL
            while (rs.next()) {
                Facilitador facilitador = new Facilitador(rs.getInt("id"), rs.getString("facilitador")); // 00225023 Creación del objeto Facilitador
                facilitadoresTarjeta.getItems().add(facilitador); // 00225023 Añade al facilitador al ComboBox
            }
        } catch (SQLException e) { // 00225023 Maneja la excepcion
            System.out.println(e); // 00225023 Imprime el mensaje de excepción SQL
        }
    }

    @FXML
    // 00225023 Método que se llama al hacer clic en el botón para generar el Reporte A
    public void ReporteA() {

        // 00225023 Verifica si la fecha inicial es nula, la fecha final es nula o no hay un cliente seleccionado
        if (fecha1.getValue() == null || fecha2.getValue() == null || clientes.getSelectionModel().getSelectedItem() == null) {
            // 00225023 Muestra una alerta de error indicando que hay campos vacíos
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Campos Vacíos", "Por favor, complete todos los campos antes de generar el reporte.");
            // 00225023 Termina la ejecución del método si algún campo está vacío
            return;
        }


        String fechaHoraActual = obtenerFechaHoraActual(); // 00225023 Obtiene la fecha y hora actual

        // 00225023 Consulta SQL para obtener datos de clientes, tarjetas y compras en un rango de fechas
        String consulta = "select c.nombres,c.apellidos,t.tipo,co.fecha,co.monto,co.descripcion from " +
                "Cliente c inner join Tarjeta t on c.id = t.id_cliente " +
                "inner join Compra co on t.id = co.id_tarjeta where c.id = ? and co.fecha between " +
                "? and ?";
        Cliente clienteSeleccionado = clientes.getSelectionModel().getSelectedItem(); // 00225023 Obtiene el cliente seleccionado del ComboBox
        ObservableList<Map<String, Object>> data = FXCollections.observableArrayList(); // 00225023 Crea una lista observable para almacenar los datos obtenidos en la consulta

        try {
            Connection connection = Conexion.getInstance().getConnection(); // 00225023 Establece la conexión con la base de datos
            PreparedStatement ps = connection.prepareStatement(consulta); // 00225023 Prepara la declaración SQL con la consulta
            ps.setInt(1, clienteSeleccionado.getId()); // 00225023 Establece el ID del cliente en el primer parámetro de la consulta
            ps.setDate(2, Date.valueOf(fecha1.getValue())); // 00225023 Establece la fecha inicial en el segundo parámetro de la consulta
            ps.setDate(3, Date.valueOf(fecha2.getValue())); // 00225023 Establece la fecha final en el tercer parámetro de la consulta
            ResultSet rs = ps.executeQuery(); // 00225023 Ejecuta la consulta y obtiene el conjunto de resultados

            // 00225023 Itera sobre los resultados de la consulta
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>(); // 00225023 Crea un mapa para almacenar la fila de resultados
                row.put("nombres", rs.getString("nombres")); // 00225023 Añade el nombre al mapa
                row.put("apellidos", rs.getString("apellidos")); // 00225023 Añade el apellido al mapa
                row.put("tipo", rs.getString("tipo"));  // 00225023 Añade el tipo de tarjeta al mapa
                row.put("fecha", rs.getDate("fecha")); // 00225023 Añade la fecha de la compra al mapa
                row.put("monto", rs.getFloat("monto")); // 00225023 Añade el monto de la compra al mapa
                row.put("descripcion", rs.getString("descripcion")); // 00225023 Añade la descripción de la compra al mapa
                data.add(row); // 00225023 Añade los resultados a la lista observable
            }
            reporteATable.setItems(data); // 00225023 Establece los elementos de la tabla con los datos obtenidos

            guardarRegistro("A", fechaHoraActual, generarContenidoReporte(data)); // 00225023 Guarda el registro del reporte generado

            connection.close(); //00225023 Se cerro coneccion
        } catch (SQLException e)  // 00225023 Maneja la excepción SQL
        {
            System.out.println(e); //00225023 Imprime mensaje de excepcion
        }
    }

    @FXML
    // 00225023 Método que se llama al hacer clic en el botón para generar el Reporte B
    public void ReporteB() {

        // 00225023 Verifica si el valor de Año, Mes o cliente seleccionado es nulo.
        if (Año.getValue() == null || Mes.getValue() == null || clientesB.getSelectionModel().getSelectedItem() == null) {
            // 00225023 Muestra una alerta de error indicando que hay campos vacíos.
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Campos Vacíos", "Por favor, complete todos los campos antes de generar el reporte.");
            // 00225023 Termina la ejecución del método si hay campos vacíos.
            return;
        }


        String fechaHoraActual = obtenerFechaHoraActual(); // 00225023 Obtiene la fecha y hora actual

        // 00225023 Consulta SQL para obtener el total de compras de un cliente en un mes y año específicos
        String consulta = "select sum(co.monto) as Total from Compra co inner join " +
                "Tarjeta t on co.id_tarjeta = t.id inner join Cliente c on t.id_cliente = " +
                "c.id where c.id = ? and year(co.fecha) = " +
                "? and month(co.fecha) = ?";

        Cliente clienteSeleccionado = clientesB.getSelectionModel().getSelectedItem(); // 00225023 Obtiene el cliente seleccionado del ComboBox
        String selectedMonth = Mes.getValue(); // 00225023 Obtiene el mes seleccionado del ComboBox

        try {
            Connection connection = Conexion.getInstance().getConnection(); // 00225023 Establece la conexión con la base de datos
            PreparedStatement ps = connection.prepareStatement(consulta); // 00225023 Prepara la declaración SQL con la consulta
            ps.setInt(1, clienteSeleccionado.getId());  // 00225023 Establece el ID del cliente en el primer parámetro de la consulta
            ps.setInt(2, Año.getValue()); // 00225023 Establece el año en el segundo parámetro de la consulta
            ps.setInt(3, Month.valueOf(selectedMonth.toUpperCase()).getValue()); // 00225023 Establece el mes en el tercer parámetro de la consulta
            ResultSet rs = ps.executeQuery(); // 00225023 Ejecuta la consulta y obtiene los resultados

            // 00225023 Crea una lista observable para almacenar los datos de la consulta
            ObservableList<Map<String, Object>> data = FXCollections.observableArrayList();

            // 00225023 Verifica si hay resultados en la consulta
            if (rs.next()) {
                Map<String, Object> row = new HashMap<>(); // 00225023 Crea un mapa para almacenar una fila de resultados
                row.put("Total", rs.getFloat("Total")); // 00225023 Añade el total al mapa
                data.add(row); // 00225023 Añade los resultados a la lista observable
            }
            reporteBTable.setItems(data); // 00225023 Establece los elementos de la tabla con los datos obtenidos
            guardarRegistro("B", fechaHoraActual, generarContenidoReporte(data)); // 00225023 Guarda el registro del reporte generado

            connection.close(); //00225023 Se cerro coneccion
        } catch (SQLException e) // 00225023 Manejo de excepción SQL
        {
            System.out.println(e); // 00225023 Imprime mensaje de expcecion
        }
    }


    // 00225023 Define un método público que encripta un número de tarjeta.
    public String encriptarTarjeta(String numeroTarjeta) {
        // 00225023 Obtiene los últimos 4 dígitos del número de tarjeta.
        String last4 = numeroTarjeta.substring(numeroTarjeta.length() - 4);
        // 00225023 Retorna la cadena encriptada con "XXXX XXXX XXXX " seguido de los últimos 4 dígitos.
        return "XXXX XXXX XXXX " + last4;
    }

    @FXML
    // 00225023 Método que se llama al hacer clic en el botón para generar el Reporte C
    public void ReporteC() {

        // 00225023 Verifica si no se ha seleccionado un cliente en clientesC.
        if (clientesC.getSelectionModel().getSelectedItem() == null) {
            // 00225023 Muestra una alerta de error indicando que no se ha seleccionado un cliente.
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Campos Vacíos", "Por favor, seleccione un cliente antes de generar el reporte.");
            // 00225023 Termina la ejecución del método si no se ha seleccionado un cliente.
            return;
        }


        String fechaHoraActual = obtenerFechaHoraActual();// 00225023 Obtiene la fecha y hora actual

        // 00225023 Consulta SQL para obtener los datos de las tarjetas de un cliente
        String consulta = "select c.nombres, c.apellidos, t.tarjeta_num, t.tipo from Tarjeta t inner join " +
                "Cliente c on t.id_cliente = c.id where c.id = ?";
        Cliente clienteSeleccionado = clientesC.getSelectionModel().getSelectedItem(); // 00225023 Obtiene al cliente seleccionado del ComboBox
        StringBuilder reporte = new StringBuilder(); // 00225023 StringBuilder para poder construir el reporte
        String tarjetasCredito = "Tarjetas de Crédito:\n"; // 00225023 Inicializa las secciones de tarjetas de crédito
        String tarjetasDebito = "Tarjetas de Débito:\n"; // 00225023 Inicializa las secciones de tarjetas de débito

        boolean tieneTarjetasDebito = false; // 00225023 Verifica si el cliente tiene tarjetas de débito
        boolean tieneTarjetasCredito = false;  // 00225023 Verifica si el cliente tiene tarjetas de crédito

        try {
            Connection connection = Conexion.getInstance().getConnection(); // 00225023 Hace la conexión con la base de datos
            PreparedStatement ps = connection.prepareStatement(consulta); // 00225023 Prepara la declaración SQL con la consulta
            ps.setInt(1, clienteSeleccionado.getId()); // 00225023 Establece el ID del cliente en el parámetro de la consulta
            ResultSet rs = ps.executeQuery(); // 00225023 Ejecuta la consulta y obtiene resultados

            // 00225023 Itera sobre los resultados de la consulta
            while (rs.next()) {
                String numeroTarjeta = rs.getString("tarjeta_num"); // 00225023 Obtiene el número de tarjeta del resultado
                String tipo = rs.getString("tipo"); // 00225023 Obtiene el tipo de tarjeta del resultado
                String numeroEncriptado = encriptarTarjeta(numeroTarjeta); // 00225023 Encripta el número de tarjeta

                // 00225023 Añade el número encriptado a la sección correspondiente del reporte
                if ("Credito".equalsIgnoreCase(tipo)) { // 00225023 Establece la condicion
                    tarjetasCredito += numeroEncriptado + "\n"; // 00225023 Evalua esta condicion si tarjeta de credito es igual al encriptado
                    tieneTarjetasCredito = true; // 00225023 Establece la bandera de tarjeta de crédito a true
                } else if ("Debito".equalsIgnoreCase(tipo)) { // 00225023 Establece la condicion
                    tarjetasDebito += numeroEncriptado + "\n"; // 00225023 Evalua esta condicion si tarjeta de debito es igual al encriptado
                    tieneTarjetasDebito = true; // 00225023 Establece la bandera de tarjeta de débito a true
                }
            }

            // 00225023 Añade "N/A" si el cliente no tiene tarjetas de crédito
            if (!tieneTarjetasCredito) {
                tarjetasCredito += "N/A\n"; // 000225023 Se cumple la condicion del if
            }

            // 00225023 Añade "N/A" si el cliente no tiene tarjetas de débito
            if (!tieneTarjetasDebito) {
                tarjetasDebito += "N/A"; // 00225023 Se cumple la condicion del if
            }

            reporte.append(tarjetasCredito).append("\n").append(tarjetasDebito); // 00225023 Añade las secciones de tarjetas de crédito y débito al reporte
            reporteC.setText(reporte.toString()); // 00225023 Establece el texto del área de reporte con el contenido del reporte

            // 00225023 Guarda el registro del reporte generado
            guardarRegistro("C", fechaHoraActual, reporte.toString());

            connection.close(); //00225023 Se cerro coneccion
        } catch (SQLException e) {
            // 00225023 Manejo de excepción SQL
            System.out.println(e);
        }
    }

    @FXML
    // 00225023 Método que se llama al hacer clic en el botón para generar el Reporte D
    public void ReporteD() {

        // 00225023 Verifica si no se ha seleccionado un facilitador en facilitadoresTarjeta.
        if (facilitadoresTarjeta.getSelectionModel().getSelectedItem() == null) {
            // 00225023 Muestra una alerta de error indicando que no se ha seleccionado un facilitador.
            Alerta.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Campos Vacíos", "Por favor, seleccione un facilitador antes de generar el reporte.");
            // 00225023 Termina la ejecución del método si no se ha seleccionado un facilitador.
            return;
        }


        String fechaHoraActual = obtenerFechaHoraActual(); // 00225023 Obtiene la fecha y hora actual

        // 00225023 Consulta SQL para obtener los datos de compras y gastos de clientes de un facilitador
        String consulta = "select c.nombres, c.apellidos, count(co.id) as cantidad_compras, sum(co.monto) as total_gastado from Cliente c " +
                "inner join Tarjeta t on c.id = t.id_cliente " +
                "inner join Compra co on t.id = co.id_tarjeta " +
                "where t.id_facilitador = ? group by c.id";

        Facilitador facilitadorSeleccionado = facilitadoresTarjeta.getSelectionModel().getSelectedItem(); // 00225023 Obtiene el facilitador seleccionado del ComboBox
        ObservableList<Map<String, Object>> data = FXCollections.observableArrayList(); // 00225023 Crea una lista observable para almacenar los datos del reporte

        try {
            Connection connection = Conexion.getInstance().getConnection(); // 00225023 Establece la conexión con la base de datos
            PreparedStatement ps = connection.prepareStatement(consulta); // 00225023 Prepara la declaración SQL con su consulta
            ps.setInt(1, facilitadorSeleccionado.getId()); // 00225023 Establece el ID del facilitador en el parámetro de la consulta
            ResultSet rs = ps.executeQuery(); // 00225023 Ejecuta la consulta y obtiene el conjunto de resultados

            // 00225023 Itera sobre los resultados de la consulta
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>(); // 00225023 Crea un mapa para almacenar cada fila de resultados

                // 00225023 Añade los datos obtenidos del resultado al mapa
                row.put("nombres", rs.getString("nombres"));// 00225023 añade nombres
                row.put("apellidos", rs.getString("apellidos")); // 00225023 añade apellidos
                row.put("cantidad_compras", rs.getInt("cantidad_compras")); //00225023 añade cantidad de compras
                row.put("total_gastado", rs.getFloat("total_gastado")); // 00225023 añade total gastado

                // 00225023 Añade el mapa a la lista observable
                data.add(row);
            }

            reporteDTable.setItems(data); // 00225023 Establece los datos en la tabla del reporte

            // 00225023 Guarda el registro del reporte generado
            guardarRegistro("D", fechaHoraActual, generarContenidoReporte(data));

            connection.close(); //00225023 Se cerro coneccion
        } catch (SQLException e) {
            // 00225023 Manejo de excepción SQL
            System.out.println(e);
        }
    }

    // 00225023 Método para generar el contenido del reporte a partir de los datos
    private String generarContenidoReporte(ObservableList<Map<String, Object>> data) {
        StringBuilder contenido = new StringBuilder(); // 00225023 Inicializa un StringBuilder para construir el contenido del reporte
        for (Map<String, Object> row : data) { // 00225023 Itera sobre cada fila de datos
            for (Map.Entry<String, Object> entry : row.entrySet()) { // 00225023 Itera sobre cada entrada en la fila
                contenido.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n"); // 00225023 Añade la clave y el valor al contenido del reporte
            }
            contenido.append("\n"); // 00225023 Añade una línea en blanco entre filas
        }
        return contenido.toString(); // 00225023 Retorna el contenido del reporte como un String
    }


    // 00225023 Método para guardar el registro del reporte en un archivo
    private void guardarRegistro(String letraReporte, String fechaHora, String reporte) {
        // 00225023 Define la ruta del archivo utilizando la letra del reporte y la fecha y hora actual
        String ruta = "src/main/java/Reportes/Reporte_" + letraReporte + "_" + fechaHora.replace(":", "-") + ".txt"; // Reemplaza los caracteres no permitidos

        // 00225023 Intenta abrir un FileWriter para escribir en el archivo
        try (FileWriter writer = new FileWriter(new File(ruta))) {
            writer.write(reporte);  // 00225023 Escribe el contenido del reporte en el archivo
        } catch (IOException e) {
            // 00225023 Maneja cualquier excepción de entrada/salida que ocurra al guardar
            e.printStackTrace();
        }
    }

    // 00225023 Método para obtener la fecha y hora actual
    private String obtenerFechaHoraActual() {
        LocalDateTime date = LocalDateTime.now(); // 00225023 Obtiene la fecha y hora actual
        return date.toString().replace(":", "-"); // 00225023 Retorna la fecha y hora como String, reemplazando los dos puntos (:) por guiones (-)
    }

    @FXML
    // 00225023 Método público que se ejecuta cuando se hace clic en el botón "Regresar".
    public void btnRegresarOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));  // 00225023 Cargar el archivo para la vista principal.
            Parent root = loader.load();  // 00225023 Cargar el nodo raíz.
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  // 00225023 Obtener la ventana actual.
            Scene scene = new Scene(root, 1000, 600);  // 00225023 Crear una nueva escena con el nodo raíz y establecer su tamaño.
            stage.setScene(scene);  // 00225023 Establecer la escena en la ventana.
            stage.show();  // 00225023 Mostrar la ventana.
        } catch (Exception e) {  // 00225023 Capturar cualquier excepción que ocurra durante la carga del archivo
            System.out.println(e.getMessage());  // 00225023 Imprimir el mensaje de la excepción.
        }
    }

}
