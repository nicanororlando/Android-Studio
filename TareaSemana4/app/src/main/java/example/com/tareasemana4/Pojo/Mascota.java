package example.com.tareasemana4.Pojo;

public class Mascota {
    private int id;
    private String nombre;
    private int foto;
    private boolean mg;
    private int likes;

    //  ctrl + N ---> "Constructor" :
    public Mascota(int foto, String nombre, boolean mg, int likes) {
        this.nombre = nombre;
        this.foto = foto;
        this.mg = mg;
        this.likes = likes;
    }

    public Mascota() {

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

    public void setMg(boolean mg) {
        this.mg = mg;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
