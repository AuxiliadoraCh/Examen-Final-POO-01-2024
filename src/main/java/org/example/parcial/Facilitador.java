package org.example.parcial;

public class Facilitador { // 00156823 Declara una clase pública llamada Facilitador

    private int id; // 00156823 Atributo privado entero para guardar el id del facilitador
    private String facilitador; // 00156823 Atributo privado de tipo String para el nombre del facilitador

    public Facilitador(int id, String facilitador) { // 00156823 Constructor que inicializa los atributos del facilitador
        this.id = id; // 00156823 Asigna el id del facilitador
        this.facilitador = facilitador; // 00156823 Asigna el nombre del facilitador
    }

    public Facilitador() { // 00156823 Constructor vacío de la clase Facilitador
    }

    public int getId() {return id;} // 00156823 Método que obtiene el id del facilitador y lo devueleve

    public void setId(int id) {this.id = id;} // 00156823 Método para establecer el id del facilitador

    public String getFacilitador() {return facilitador;} // 00156823 Método que obtiene el nombre del facilitador

    public void setFacilitador(String facilitador) {this.facilitador = facilitador;} // 00156823 Método para establecer el nombre del facilitador

    @Override
    // 00156823 Anotación que indica que este método sobrescribe el método toString de la clase Object
    public String toString() {return facilitador;} // 00156823 Método que devuelve una representación en String del facilitador
}
