package org.example.parcial;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class Cliente {  // 00156823 Declara una clase publica llamada Cliente
    private int id; // 00156823 Atributo privado entero para guardar el id del cliente
    private String nombres; // 00156823  Atributo privado de tipo String para nombres del cliente
    private String apellidos; // 00156823 Atributo privado de tipo String que permite guardar los apellidos
    private String direccion; // 00156823 Atributo privado de tipo String qe almacena la direccion del cliente
    private String telefono; // 00156823 Atributo privado de tipo String que guarda el telefono del cliente

    public Cliente(int id, String nombres, String apellidos, String direccion, String telefono) // 00156823 Constructor que inicializa los atributos del cliente
    {
        this.id = id; // 00156823 Asigna el ID del cliente
        this.nombres = nombres; // 00156823 Asigna los nombres del cliente
        this.apellidos = apellidos; // 00156823 Asigna los apellidos del cliente
        this.direccion = direccion; // 00156823 Asigna la dirección del cliente
        this.telefono = telefono; // 00156823 Asigna el teléfono del cliente
    }

    public Cliente(){} // 00156823 Constructor de la clase Cliente

    public int getId() {
        return id;
    } // 00156823 Metodo que obtiene el ID del clientes

    public void setId(int id) {this.id = id;} // 00156823 Metodo para establecer el valor del ID y asignar el dado al atributo

    public String getNombres() {
        return nombres;
    } // 00156823 Metodo que obtiene los nombres del cliente

    public void setNombres(String nombres) {this.nombres = nombres;} // 00156823 Metodo para establecer el valor del atributo nombres, y asignar el dado al atributo

    public String getApellidos() {
        return apellidos;
    } // 00156823 Metodo que obtiene los apellidos del cliente

    public void setApellidos(String apellidos) {this.apellidos = apellidos;} // 00156823 Metodo para establecer Apellidos del cliente y asignar al atributo

    public String getDireccion() {
        return direccion;
    } // 00156823 Metodo que obtiene la direccion del cliente

    public void setDireccion(String direccion) {this.direccion = direccion;} // 00156823 Metodo para establecer direccion y asignar al atributo

    public String getTelefono() {
        return telefono;
    } // 00156823 Metodo que obtiene el numero de telefono del cliente

    public void setTelefono(String telefono) {this.telefono = telefono;} // 00156823 Metodo para establecer telefono y asignar al atributo

    // 00156823 Metodo que añade un nuevo cliente a la base de datos
    public void agregarCliente(String nombres, String apellidos, String direccion, String telefono) {
        // 00156823 Consulta de SQL para insertar un nuevo cliente en la tabla Cliente
        String sql = "INSERT INTO Cliente (nombres, apellidos, direccion, telefono) VALUES (?, ?, ?, ?)";

        // 00156823 Bloque try-with-resources para establecer una conexión a la base de datos y preparar la declaración SQL
        try (Connection con = Conexion.getInstance().getConnection();  // 00156823 Obtener una conexión a la base de datos desde el pool de conexiones
             PreparedStatement pstm = con.prepareStatement(sql)) {  // 00156823 Preparar la declaración SQL con la conexión
            pstm.setString(1, nombres);  // 00156823 Establecer el primer parámetro (nombres) en la declaración SQL
            pstm.setString(2, apellidos);  // 00156823 Establecer el segundo parámetro (apellidos) en la declaración SQL
            pstm.setString(3, direccion);  // 00156823 Establecer el tercer parámetro (dirección) en la declaración SQL
            pstm.setString(4, telefono);  // 00156823 Establecer el cuarto parámetro (teléfono) en la declaración SQL

            pstm.executeUpdate();  // 00156823 Ejecutar la declaración SQL para insertar el nuevo cliente
            pstm.close();  // 00156823 Cerrar el PreparedStatement
            System.out.println("Empleado agregado");  // 00156823 Imprimir un mensaje indicando que el cliente fue agregado exitosamente
        } catch (Exception e) {  // 00156823 Capturar cualquier excepción que ocurra durante la operación en la base de datos
            System.out.println(e);  // 00156823 Imprimir el mensaje de la excepción
        }
    }

    // 00156823 Método para eliminar un cliente por id
    public void DeleteId(String id) {
        // 00156823 Consulta SQL para eliminar un cliente de la tabla Cliente por id
        String sql = "DELETE FROM Cliente WHERE id = ?";

        // 00156823 Bloque try-with-resources para establecer una conexión a la base de datos y preparar la declaración SQL
        try (Connection con = Conexion.getInstance().getConnection();  // 00156823 Obtener una conexión a la base de datos desde el pool de conexiones
             PreparedStatement pstm = con.prepareStatement(sql)) {  // 00156823 Prepara la declaración SQL con la conexión
            pstm.setString(1, id);  // 00156823 Establece el primer parámetro (id) en la declaración SQL

            pstm.executeUpdate();  // 00156823 Ejecuta la declaración SQL para eliminar el cliente
            pstm.close();  // 00156823 Cierra el PreparedStatement
            System.out.println("Eliminado Exitosamente");  // 00156823 Imprimir un mensaje indicando que el cliente fue eliminado exitosamente

        } catch (Exception e) {  // 00156823 Captura cualquier excepción que ocurra durante la operación en la base de datos
            System.out.println(e);  // 00156823 Imprime el mensaje de la excepción
        }
    }

    // 00156823 Método para actualizar un cliente por id
    public void updateCliente(String id, String nombres, String apellidos, String direccion, String telefono) {
        // 00156823 Consulta SQL para actualizar los detalles de un cliente en la tabla Cliente por id
        String sql = "UPDATE Cliente set nombres = ?, apellidos = ?, direccion = ?, telefono = ? WHERE id = ?";

        // 00156823 Bloque try-with-resources para establecer una conexión a la base de datos y preparar la declaración SQL
        try (Connection con = Conexion.getInstance().getConnection();  // 00156823 Obtener una conexión a la base de datos desde el pool de conexiones
             PreparedStatement pstm = con.prepareStatement(sql)) {  // 00156823 Prepara la declaración SQL con la conexión
            pstm.setString(1, nombres);  // 00156823 Establece el primer parámetro (nombres) en la declaración SQL
            pstm.setString(2, apellidos);  // 00156823 Establece el segundo parámetro (apellidos) en la declaración SQL
            pstm.setString(3, direccion);  // 00156823 Establece el tercer parámetro (dirección) en la declaración SQL
            pstm.setString(4, telefono);  // 00156823 Establece el cuarto parámetro (teléfono) en la declaración SQL
            pstm.setString(5, id);  // 00156823 Establece el quinto parámetro (id) en la declaración SQL

            pstm.executeUpdate();  // 00156823 Ejecuta la declaración SQL para actualizar el cliente
            pstm.close();  // 00156823 Cierra el PreparedStatement
            System.out.println("Actualizado exitosamente");  // 00156823 Imprimir un mensaje indicando que el cliente fue actualizado exitosamente

        } catch (Exception e) {  // 00156823 Capturar cualquier excepción que ocurra durante la operación en la base de datos
            System.out.println(e);  // 00156823 Imprimir el mensaje de la excepción
        }
    }

    @Override
    // 00156823 Sobreescribe el método toString para devolver una representación en cadena del objeto Cliente
    public String toString() {
        // 00156823 Devuelve los nombres y apellidos del cliente con un espacio entre ellos
        return nombres + " " + apellidos;
    }
}


