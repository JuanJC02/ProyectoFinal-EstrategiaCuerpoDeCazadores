package app.proyecto_cazadores.adapters.in.web;

import app.proyecto_cazadores.application.service.PilarService;
import app.proyecto_cazadores.application.service.TriangulacionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inteligencia")
public class InteligenciaController {

    private final PilarService pilarService;

    public InteligenciaController(PilarService pilarService) {
        this.pilarService = pilarService;
    }

    @GetMapping("/triangulacion")
    public ResponseEntity<TriangulacionResponse> triangulacion() {
        TriangulacionResponse res = pilarService.calcularTriangulacion();
        return ResponseEntity.ok(res);
    }
}
