package app.proyecto_cazadores.application.service;

public class TriangulacionResponse {
    private Posicion posiblePosicionMuzan;
    private double nivelConfianza;
    private String descripcion;

    public TriangulacionResponse() {}
    public TriangulacionResponse(Posicion p, double n, String d) {
        this.posiblePosicionMuzan = p;
        this.nivelConfianza = n;
        this.descripcion = d;
    }

    public Posicion getPosiblePosicionMuzan() { return posiblePosicionMuzan; }
    public double getNivelConfianza() { return nivelConfianza; }
    public String getDescripcion() { return descripcion; }
    public void setPosiblePosicionMuzan(Posicion p) { this.posiblePosicionMuzan = p; }
    public void setNivelConfianza(double n) { this.nivelConfianza = n; }
    public void setDescripcion(String d) { this.descripcion = d; }
}
