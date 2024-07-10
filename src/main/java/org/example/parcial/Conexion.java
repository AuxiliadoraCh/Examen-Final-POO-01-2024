package org.example.parcial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {  // 00156823 Declara una clase pública llamada Conexion

    private static Conexion instance;  // 00156823 Atributo estático privado para almacenar la única instancia de Conexion
    private Connection con;  // 00156823 Atributo privado para almacenar la conexión a la base de datos
    private String url = "jdbc:mysql://localhost:3306/parcialdb";  // 00156823 Atributo privado para almacenar la URL de la base de datos
    private String usuario = "root";  // 00156823 Atributo privado para almacenar el nombre de usuario de la base de datos
    private String pass = "12345";  // 00156823 Atributo privado para almacenar la contraseña de la base de datos


    // 00156823 Constructor privado para inicializar la conexión a la base de datos
    private Conexion() throws SQLException {
        try {this.con = DriverManager.getConnection(url, usuario, pass);  // 00156823 Establece una conexión a la base de datos
        } catch (Exception e) {  // 00156823 Captura cualquier excepción que ocurra durante la conexión
            System.out.println(e);  // 00156823 Imprime el mensaje de la excepción
        }
    }

    // 00156823 Método para obtener la conexión a la base de datos
    public Connection getConnection() // 00156823 Obtiene la conexion
    {
        return con;  // 00156823 Devuelve la conexión a la base de datos
    }

    // 00156823 Método para obtener la única instancia de Conexion
    public static Conexion getInstance() throws SQLException {
        if (instance == null) {  // 00156823 Si no hay instancia, crea una nueva
            instance = new Conexion();  // 00156823 Crea una nueva instancia de Conexion
        } else if (instance.getConnection().isClosed()) {  // 00156823 Si la conexión está cerrada, crea una nueva instancia
            instance = new Conexion();  // 00156823 Crea una nueva instancia de Conexion
        }
        return instance;  // 00156823 Devuelve la instancia de Conexion
    }
}
