package app.proyecto_cazadores.adapters.in.web;

import app.proyecto_cazadores.application.service.PilarService;
import app.proyecto_cazadores.domain.model.Pilar;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pilares")
public class PilarController {

    private final PilarService pilarService;

    public PilarController(PilarService pilarService) {
        this.pilarService = pilarService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pilar> getPilar(@PathVariable Long id) {
        Pilar p = pilarService.getPilarById(id);
        return ResponseEntity.ok(p);
    }

    @PostMapping("/actualizar-posicion")
    public ResponseEntity<?> actualizarPosicion(@RequestBody ActualizarPilarRequest req) {
        Pilar actualizado = pilarService.updatePosicion(req.getPilarId(), req.getPosX(), req.getPosY(), req.getEstado());
        Map<String, Object> response = Map.of(
                "mensaje", "Posición actualizada exitosamente.",
                "pilar", actualizado
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
