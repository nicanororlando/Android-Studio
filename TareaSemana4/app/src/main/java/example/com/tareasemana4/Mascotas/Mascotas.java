package example.com.tareasemana4.Mascotas;

public class Mascotas {
    private String nombre;
    private int foto;
    private boolean mg;

    //  ctrl + N ---> "Constructor" :
    public Mascotas(int foto, String nombre, boolean mg) {
        this.nombre = nombre;
        this.foto = foto;
        this.mg = mg;
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

    public boolean getMg() {
        return mg;
    }

    public void setMg(int foto) {
        this.mg = mg;
    }
}
