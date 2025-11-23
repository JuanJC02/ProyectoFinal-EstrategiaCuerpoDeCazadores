package app.proyecto_cazadores.adapters.in.web;

import app.proyecto_cazadores.application.service.MensajeService;
import app.proyecto_cazadores.domain.model.Mensaje;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    private final MensajeService mensajeService;

    public MensajeController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    @PostMapping
    public ResponseEntity<Mensaje> crearMensaje(@RequestBody CrearMensajeRequest req) {
        Mensaje m = mensajeService.crearMensaje(req.getPilarId(), req.getContenidoFragmentado());
        return ResponseEntity.status(HttpStatus.CREATED).body(m);
    }

    @PutMapping("/{id}/reconstruir")
    public ResponseEntity<Mensaje> reconstruir(@PathVariable Long id, @RequestBody ReconstruirMensajeRequest req) {
        Mensaje m = mensajeService.reconstruirMensaje(id, req.getContenidoReconstruido());
        return ResponseEntity.ok(m);
    }
}
