package model;

public class Cita {
    private int id;
    private String nombre;
    private String telefono;
    private String servicio;
    private String fecha;
    private String hora;

    // Constructor vacío
    public Cita() {}

    // Constructor con datos
    public Cita(int id, String nombre, String telefono, String servicio, String fecha, String hora) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.servicio = servicio;
        this.fecha = fecha;
        this.hora = hora;
    }

    // Getters y Setters (Esto permite que otras clases lean estos datos)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getServicio() { return servicio; }
    public void setServicio(String servicio) { this.servicio = servicio; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }
}