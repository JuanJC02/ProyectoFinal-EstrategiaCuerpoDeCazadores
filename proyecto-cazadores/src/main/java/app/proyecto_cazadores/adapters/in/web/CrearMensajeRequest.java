package app.proyecto_cazadores.adapters.in.web;

public class CrearMensajeRequest {
    private Long pilarId;
    private String contenidoFragmentado;

    public CrearMensajeRequest() {}

    public Long getPilarId() { return pilarId; }
    public String getContenidoFragmentado() { return contenidoFragmentado; }

    public void setPilarId(Long pilarId) { this.pilarId = pilarId; }
    public void setContenidoFragmentado(String contenidoFragmentado) { this.contenidoFragmentado = contenidoFragmentado; }
}
