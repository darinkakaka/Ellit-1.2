package planes;
public class PlanComidas {
    private int idComida;
    private int idPlan;  // Llave foránea que relaciona con PlanNutricional
    private String diaSemana;
    private String momento;
    private String descripcion;
    private int caloriasAprox;
    private String horaRecomendada;

    public PlanComidas() {}

    public PlanComidas(int idComida, int idPlan, String diaSemana, String momento,
                       String descripcion, int caloriasAprox, String horaRecomendada) {
        this.idComida = idComida;
        this.idPlan = idPlan;
        this.diaSemana = diaSemana;
        this.momento = momento;
        this.descripcion = descripcion;
        this.caloriasAprox = caloriasAprox;
        this.horaRecomendada = horaRecomendada;
    }

    // Getters y Setters

    public int getIdComida() {
        return idComida;
    }

    public void setIdComida(int idComida) {
        this.idComida = idComida;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getMomento() {
        return momento;
    }

    public void setMomento(String momento) {
        this.momento = momento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCaloriasAprox() {
        return caloriasAprox;
    }

    public void setCaloriasAprox(int caloriasAprox) {
        this.caloriasAprox = caloriasAprox;
    }

    public String getHoraRecomendada() {
        return horaRecomendada;
    }

    public void setHoraRecomendada(String horaRecomendada) {
        this.horaRecomendada = horaRecomendada;
    }
}
