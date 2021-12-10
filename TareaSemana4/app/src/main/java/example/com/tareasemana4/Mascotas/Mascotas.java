package example.com.tareasemana4.Mascotas;

public class Mascotas {
    private String nombre;
    private int foto;

    //  ctrl + N ---> "Constructor" :
    public Mascotas(int foto, String nombre) {
        this.nombre = nombre;
        this.foto = foto;
    }

    //  ctrl + N ---> "Getter and Setter" :
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
