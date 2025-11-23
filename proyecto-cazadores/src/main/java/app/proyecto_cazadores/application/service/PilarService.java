package app.proyecto_cazadores.application.service;

import app.proyecto_cazadores.adapters.out.persistence.PilarRepository;
import app.proyecto_cazadores.domain.exception.PilarNotFoundException;
import app.proyecto_cazadores.domain.model.Pilar;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PilarService {

    private final PilarRepository pilarRepository;

    public PilarService(PilarRepository pilarRepository) {
        this.pilarRepository = pilarRepository;
    }

    public Pilar getPilarById(Long id) {
        return pilarRepository.findById(id)
                .orElseThrow(() -> new PilarNotFoundException("No existe un Pilar con ese ID"));
    }

    @Transactional
    public Pilar updatePosicion(Long pilarId, int posX, int posY, String estado) {
        Pilar p = getPilarById(pilarId);
        p.setPosX(posX);
        p.setPosY(posY);
        p.setEstado(estado);
        return pilarRepository.save(p);
    }

    public TriangulacionResponse calcularTriangulacion() {
        List<Pilar> pilares = pilarRepository.findAll();
        if (pilares.isEmpty()) {
            return new TriangulacionResponse(new Posicion(0,0), 0.0, "No hay datos disponibles.");
        }
        double sumX = 0, sumY = 0;
        for (Pilar p : pilares) {
            sumX += p.getPosX();
            sumY += p.getPosY();
        }
        double avgX = sumX / pilares.size();
        double avgY = sumY / pilares.size();
        double nivelConfianza = Math.min(1.0, (double)pilares.size() / 3.0);
        String descripcion = (nivelConfianza > 0.5) ? "Probabilidad alta de presencia demoníaca en las coordenadas dadas."
                : "Probabilidad baja de presencia demoníaca.";
        return new TriangulacionResponse(new Posicion(avgX, avgY), nivelConfianza, descripcion);
    }
}
