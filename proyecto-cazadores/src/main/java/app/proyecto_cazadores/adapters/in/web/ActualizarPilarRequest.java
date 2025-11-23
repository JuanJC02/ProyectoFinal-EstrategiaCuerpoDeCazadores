package app.proyecto_cazadores.adapters.in.web;

public class ActualizarPilarRequest {
    private Long pilarId;
    private int posX;
    private int posY;
    private String estado;

    public ActualizarPilarRequest() {}

    public Long getPilarId() { return pilarId; }
    public int getPosX() { return posX; }
    public int getPosY() { return posY; }
    public String getEstado() { return estado; }

    public void setPilarId(Long pilarId) { this.pilarId = pilarId; }
    public void setPosX(int posX) { this.posX = posX; }
    public void setPosY(int posY) { this.posY = posY; }
    public void setEstado(String estado) { this.estado = estado; }
}
