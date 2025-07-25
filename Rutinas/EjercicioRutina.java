package Rutinas;
// ===============================
// Clase EjercicioRutina.java
// ===============================
// Contiene los atributos de los ejercicios de cada rutina

public class EjercicioRutina {
    private int id;
    private int rutinaId;
    private String diaSemana;
    private String nombreEjercicio;
    private int series;
    private int repeticiones;

    // Constructor vacío
    public EjercicioRutina() {}

    // Constructor con parámetros
    public EjercicioRutina(int id, int rutinaId, String diaSemana, 
                          String nombreEjercicio, int series, int repeticiones) {
        this.id = id;
        this.rutinaId = rutinaId;
        this.diaSemana = diaSemana;
        this.nombreEjercicio = nombreEjercicio;
        this.series = series;
        this.repeticiones = repeticiones;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRutinaId() {
        return rutinaId;
    }

    public void setRutinaId(int rutinaId) {
        this.rutinaId = rutinaId;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    // Método toString
    @Override
    public String toString() {
        return String.format("%s - %d series x %d repeticiones", nombreEjercicio, series, repeticiones);
    }
}