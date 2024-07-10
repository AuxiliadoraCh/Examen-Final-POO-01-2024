package org.example.parcial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

public class Compra {  // 00225023 Declara una clase publica llamada Compra

    private int id;  // 00225023 Atributo privado entero para guardar el id de la compra
    private Date fecha;  // 00225023 Atributo privado de tipo Date para la fecha de la compra
    private float monto;  // 00225023 Atributo privado de tipo float para el monto de la compra
    private String descripcion;  // 00225023 Atributo privado de tipo String para la descripción de la compra
    private int id_tarjeta;  // 00225023 Atributo privado entero para el id de la tarjeta asociada a la compra


    public Compra(int id, Date fecha, float monto, String descripcion, int id_tarjeta) {  // 00225023 Constructor que inicializa los atributos de la compra
        this.id = id;  // 00225023 Asigna el id de la compra
        this.fecha = fecha;  // 00225023 Asigna la fecha de la compra
        this.monto = monto;  // 00225023 Asigna el monto de la compra
        this.descripcion = descripcion;  // 00225023 Asigna la descripción de la compra
        this.id_tarjeta = id_tarjeta;  // 00225023 Asigna el id de la tarjeta asociada a la compra
    }


    public Compra() {  // 00225023 Constructor vacío de la clase Compra
    }

    public int getId() {  // 00225023 Método que obtiene el id de la compra
        return id;  // 00225023 Devuelve el id de la compra
    }

    public void setId(int id) {  // 00225023 Método para establecer el id de la compra
        this.id = id;  // 00225023 Asigna el id dado al atributo id
    }

    public Date getFecha() {  // 00225023 Método que obtiene la fecha de la compra
        return fecha;  // 00225023 Devuelve la fecha de la compra
    }

    public void setFecha(Date fecha) {  // 00225023 Método para establecer la fecha de la compra
        this.fecha = fecha;  // 00225023 Asigna la fecha dada al atributo fecha
    }

    public float getMonto() {  // 00225023 Método que obtiene el monto de la compra
        return monto;  // 00225023 Devuelve el monto de la compra
    }

    public void setMonto(float monto) {  // 00225023 Método para establecer el monto de la compra
        this.monto = monto;  // 00225023 Asigna el monto dado al atributo monto
    }

    public String getDescripcion() {  // 00225023 Método que obtiene la descripción de la compra
        return descripcion;  // 00225023 Devuelve la descripción de la compra
    }

    public void setDescripcion(String descripcion) {  // 00225023 Método para establecer la descripción de la compra
        this.descripcion = descripcion;  // 00225023 Asigna la descripción dada al atributo descripcion
    }

    public int getId_tarjeta() {  // 00225023 Método que obtiene el id de la tarjeta asociada a la compra
        return id_tarjeta;  // 00225023 Devuelve el id de la tarjeta
    }

    public void setId_tarjeta(int id_tarjeta) {  // 00225023 Método para establecer el id de la tarjeta asociada a la compra
        this.id_tarjeta = id_tarjeta;  // 00225023 Asigna el id de la tarjeta dado al atributo id_tarjeta
    }


    // 00225023 Método para añadir una nueva compra a la base de datos
    public void agregarCompra(Date fecha, float monto, String descripcion, int idTarjeta) {
        // 00225023 Consulta SQL para insertar una nueva compra en la tabla Compra
        String sql = "INSERT INTO Compra (fecha, monto, descripcion, id_tarjeta) VALUES (?, ?, ?, ?)";

        // 00225023 Bloque try-with-resources para establecer una conexión a la base de datos y preparar la declaración SQL
        try (Connection con = Conexion.getInstance().getConnection();  // 00225023 Obtiene una conexión a la base de datos desde el pool de conexiones
             PreparedStatement pstm = con.prepareStatement(sql)) {  // 00225023 Prepara la declaración del SQL con la conexión
            pstm.setString(1, String.valueOf(fecha));  // 00225023 Establece el primer parámetro (fechaCompra) en la declaración SQL
            pstm.setFloat(2, monto);  // 00225023 Establece el segundo parámetro (monto) en la declaración SQL
            pstm.setString(3, descripcion);  // 00225023 Establece el tercer parámetro (descripcion) en la declaración SQL
            pstm.setInt(4, idTarjeta);  // 00225023 Establece el cuarto parámetro (tarjetaID) en la declaración SQL

            pstm.executeUpdate();  // 00225023 Ejecuta la declaración SQL para insertar la nueva compra
            pstm.close();  // 00225023 Cierra el PreparedStatement
            System.out.println("Compra agregado");  // 00225023 Imprime un mensaje indicando que la compra fue agregada exitosamente

        } catch (Exception e) {  // 00225023 Captura cualquier excepción que ocurra durante la operación en la base de datos
            System.out.println(e);  // 00225023 Imprime el mensaje de la excepción
        }
    }

    // 00225023 Método para eliminar una compra por id
    public void DeleteId(int id) {
        // 00225023 Consulta SQL para eliminar una compra de la tabla Compra por id
        String sql = "DELETE FROM Compra WHERE id = ?";

        // 00225023 Bloque try-with-resources para establecer una conexión a la base de datos y preparar la declaración SQL
        try (Connection con = Conexion.getInstance().getConnection();  // 00225023 Obtiene una conexión a la base de datos desde el pool de conexiones
             PreparedStatement pstm = con.prepareStatement(sql)) {  // 00225023 Prepara la declaración SQL con la conexión
            pstm.setInt(1, id);  // 00225023 Establece el primer parámetro (id) en la declaración SQL

            pstm.executeUpdate();  // 00225023 Ejecuta la declaración SQL para eliminar la compra
            pstm.close();  // 00225023 Cierra el PreparedStatement
            System.out.println("Eliminado exitosamente");  // 00225023 Imprime un mensaje indicando que la compra fue eliminada exitosamente

        } catch (Exception e) {  // 00225023 Captura cualquier excepción que ocurra durante la operación en la base de datos
            System.out.println(e);  // 00225023 Imprimie el mensaje de la excepción
        }
    }

    // 00225023 Método para actualizar una compra por id
    public void updateCompra(int id, Date fecha, float monto, String descripcion, int idTarjeta) {
        // 00225023 Consulta SQL para actualizar los detalles de una compra en la tabla Compra por id
        String sql = "UPDATE Compra set fecha = ?, monto = ?, descripcion = ?, id_tarjeta = ? WHERE id = ?";

        // 00225023 Bloque try-with-resources para establecer una conexión a la base de datos y preparar la declaración SQL
        try (Connection con = Conexion.getInstance().getConnection();  // 00225023 Obtiene una conexión a la base de datos desde el pool de conexiones
             PreparedStatement pstm = con.prepareStatement(sql)) {  // 00225023 Prepara la declaración SQL con la conexión
            pstm.setString(1, String.valueOf(fecha));  // 00225023 Establece el primer parámetro (fechaCompra) en la declaración SQL
            pstm.setFloat(2, monto);  // 00225023 Establece el segundo parámetro (monto) en la declaración SQL
            pstm.setString(3, descripcion);  // 00225023 Establece el tercer parámetro (descripcion) en la declaración SQL
            pstm.setInt(4, idTarjeta);  // 00225023 Establece el cuarto parámetro (tarjetaID) en la declaración SQL
            pstm.setInt(5, id);  // 00225023 Establece el quinto parámetro (id) en la declaración SQL

            pstm.executeUpdate();  // 00225023 Ejecuta la declaración SQL para actualizar la compra
            pstm.close();  // 00225023 Cierra el PreparedStatement
            System.out.println("Se ha actualizado exitosamente");  // 00225023 Imprime un mensaje indicando que la compra fue actualizada exitosamente

        } catch (Exception e) {  // 00225023 Captura cualquier excepción que ocurra durante la operación en la base de datos
            System.out.println(e);  // 00225023 Imprime el mensaje de la excepción
        }
    }
}

