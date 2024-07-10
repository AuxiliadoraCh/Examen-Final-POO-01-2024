package org.example.parcial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

public class Tarjeta {  // 00225023 Declara una clase pública llamada Tarjeta

    private int id; // 00225023 Atributo privado entero para guardar el id de la tarjeta
    private int id_cliente; // 00225023 Atributo privado entero para el id del cliente asociado a la tarjeta
    private String tarjeta_num; // 00225023 Atributo privado de tipo String para el número de la tarjeta
    private Date exp_date; // 00225023 Atributo privado de tipo Date para la fecha de expiración de la tarjeta
    private String tipo; // 00225023 Atributo privado de tipo String para el tipo de la tarjeta
    private int id_facilitador; // 00225023 Atributo privado entero para el id del facilitador asociado a la tarjeta

    public Tarjeta(int id, int id_cliente, String tarjeta_num, Date exp_date, String tipo, int id_facilitador) { // 00225023 Constructor que inicializa los atributos de la tarjeta
        this.id = id; // 00225023 Asigna el id de la tarjeta
        this.id_cliente = id_cliente; // 00225023 Asigna el id del cliente
        this.tarjeta_num = tarjeta_num; // 00225023 Asigna el número de la tarjeta
        this.exp_date = exp_date; // 00225023 Asigna la fecha de expiración de la tarjeta
        this.tipo = tipo; // 00225023 Asigna el tipo de la tarjeta
        this.id_facilitador = id_facilitador; // 00225023 Asigna el id del facilitador
    }

    public Tarjeta() { // 00225023 Constructor vacío de la clase Tarjeta
    }

    public int getId() { // 00225023 Método que obtiene el id de la tarjeta
        return id; // 00225023 Devuelve el id de la tarjeta
    }

    public void setId(int id) { // 00225023 Método para establecer el id de la tarjeta
        this.id = id; // 00225023 Asigna el id dado al atributo id
    }

    public int getId_cliente() { // 00225023 Método que obtiene el id del cliente asociado a la tarjeta
        return id_cliente; // 00225023 Devuelve el id del cliente
    }

    public void setId_cliente(int id_cliente) { // 00225023 Método para establecer el id del cliente asociado a la tarjeta
        this.id_cliente = id_cliente; // 00225023 Asigna el id del cliente dado al atributo id_cliente
    }

    public String getTarjeta_num() { // 00225023 Método que obtiene el número de la tarjeta
        return tarjeta_num; // 00225023 Devuelve el número de la tarjeta
    }

    public void setTarjeta_num(String tarjeta_num) { // 00225023 Método para establecer el número de la tarjeta
        this.tarjeta_num = tarjeta_num; // 00225023 Asigna el número de la tarjeta dado al atributo tarjeta_num
    }

    public Date getExp_date() { // 00225023 Método que obtiene la fecha de expiración de la tarjeta
        return exp_date; // 00225023 Devuelve la fecha de expiración de la tarjeta
    }

    public void setExp_date(Date exp_date) { // 00225023 Método para establecer la fecha de expiración de la tarjeta
        this.exp_date = exp_date; // 00225023 Asigna la fecha de expiración dada al atributo exp_date
    }

    public String getTipo() { // 00225023 Método que obtiene el tipo de la tarjeta
        return tipo; // 00225023 Devuelve el tipo de la tarjeta
    }

    public void setTipo(String tipo) { // 00225023 Método para establecer el tipo de la tarjeta
        this.tipo = tipo; // 00225023 Asigna el tipo dado al atributo tipo
    }

    public int getId_facilitador() { // 00225023 Método que obtiene el id del facilitador asociado a la tarjeta
        return id_facilitador; // 00225023 Devuelve el id del facilitador
    }

    public void setId_facilitador(int id_facilitador) { // 00225023 Método para establecer el id del facilitador asociado a la tarjeta
        this.id_facilitador = id_facilitador; // 00225023 Asigna el id del facilitador dado al atributo id_facilitador
    }

    // 00225023 Método para añadir una nueva tarjeta a la base de datos
    public void agregarTarjeta(int id_cliente, String tarjeta_num, Date exp_date, String tipo, int id_facilitador) {
        // 00225023 Consulta SQL para insertar una nueva tarjeta en la tabla Tarjeta
        String sql = "INSERT INTO Tarjeta (id_cliente, tarjeta_num, exp_date, tipo, id_facilitador) VALUES (?, ?, ?, ?, ?)";

        // 00225023 Bloque try-with-resources para establecer una conexión a la base de datos y preparar la declaración SQL
        try (Connection con = Conexion.getInstance().getConnection(); // 00225023 Obtiene una conexión a la base de datos desde el pool de conexiones
             PreparedStatement pstm = con.prepareStatement(sql)) { // 00225023 Prepara la declaración del SQL con la conexión
            pstm.setInt(1, id_cliente); // 00225023 Establece el primer parámetro (idCliente) en la declaración SQL
            pstm.setString(2, tarjeta_num); // 00225023 Establece el segundo parámetro (numeroTarjeta) en la declaración SQL
            pstm.setString(3, String.valueOf(exp_date)); // 00225023 Establece el tercer parámetro (fechaExp) en la declaración SQL
            pstm.setString(4, tipo); // 00225023 Establece el cuarto parámetro (tipo) en la declaración SQL
            pstm.setInt(5, id_facilitador); // 00225023 Establece el quinto parámetro (idFacilitador) en la declaración SQL

            pstm.executeUpdate(); // 00225023 Ejecuta la declaración SQL para insertar la nueva tarjeta
            pstm.close(); // 00225023 Cierra el PreparedStatement
            System.out.println("Tarjeta agregada"); // 00225023 Imprime un mensaje indicando que la tarjeta fue agregada exitosamente

        } catch (
                Exception e) { // 00225023 Captura cualquier excepción que ocurra durante la operación en la base de datos
            System.out.println(e); // 00225023 Imprime el mensaje de la excepción
        }
    }

    // 00225023 Método para eliminar una tarjeta por id
    public void DeleteIdTarjeta(String id) {
        // 00225023 Consulta SQL para eliminar una tarjeta de la tabla Tarjeta por id
        String sql = "DELETE FROM Tarjeta WHERE id = ?";

        // 00225023 Bloque try-with-resources para establecer una conexión a la base de datos y preparar la declaración SQL
        try (Connection con = Conexion.getInstance().getConnection(); // 00225023 Esto obtiene una conexión a la base de datos desde el pool de conexiones
             PreparedStatement pstm = con.prepareStatement(sql)) { // 00225023 Prepara la declaración SQL con la conexión
            pstm.setString(1, id); // 00225023 Establece el primer parámetro (id) en la declaración SQL

            pstm.executeUpdate(); // 00225023 Ejecuta la declaración SQL para eliminar la tarjeta
            pstm.close(); // 00225023 Cierra el PreparedStatement
            System.out.println("Eliminado exitosamente"); // 00225023 Imprime un mensaje indicando que la tarjeta fue eliminada exitosamente

        } catch (Exception e) { // 00225023 Captura cualquier excepción que ocurra durante la operación en la base de datos
            System.out.println(e); // 00225023 Imprime el mensaje de la excepción ocurrida
        }
    }

    // 00225023 Método para actualizar una tarjeta por su id
    public void updateCliente(int id, int id_cliente, String tarjeta_num, Date exp_date, String tipo, int id_facilitador) {
        // 00225023 Consulta SQL para actualizar los detalles de una tarjeta en la tabla Tarjeta por id
        String sql = "UPDATE Tarjeta set id_cliente = ?, tarjeta_num = ?, exp_date = ?, tipo = ?, id_facilitador = ? WHERE id = ?";

        // 00225023 Bloque try-with-resources para establecer una conexión a la base de datos y preparar la declaración SQL
        try (Connection con = Conexion.getInstance().getConnection(); // 00225023 Obtiene la conexión a la base de datos desde el pool de conexiones
             PreparedStatement pstm = con.prepareStatement(sql)) { // 00225023 Prepara la declaración SQL con la conexión
            pstm.setInt(1, id_cliente); // 00225023 Establece el primer parámetro (idCliente) en la declaración SQL
            pstm.setString(2, tarjeta_num); // 00225023 Establece el segundo parámetro (numeroTarjeta) en la declaración SQL
            pstm.setString(3, String.valueOf(exp_date)); // 00225023 Establece el tercer parámetro (fechaExp) en la declaración SQL
            pstm.setString(4, tipo); // 00225023 Establece el cuarto parámetro (tipo) en la declaración SQL
            pstm.setInt(5, id_facilitador); // 00225023 Establece el quinto parámetro (idFacilitador) en la declaración SQL
            pstm.setInt(6, id); // 00225023 Establece el sexto parámetro (id) en la declaración SQL

            pstm.executeUpdate(); // 00225023 Ejecuta la declaración SQL para actualizar los detalles de la tarjeta
            pstm.close(); // 00225023 Cierra el PreparedStatement
            System.out.println("Actualizado exitosamente"); // 00225023 Imprime un mensaje indicando que la tarjeta fue actualizada exitosamente

        } catch (Exception e) { // 00225023 Captura cualquier excepción que ocurra durante la operación en la base de datos
            System.out.println(e); // 00225023 Imprime el mensaje de la excepción ocurrida
        }
    }
}

