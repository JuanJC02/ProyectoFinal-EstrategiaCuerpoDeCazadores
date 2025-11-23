package app.proyecto_cazadores.application.service;

import app.proyecto_cazadores.adapters.out.persistence.MensajeRepository;
import app.proyecto_cazadores.adapters.out.persistence.PilarRepository;
import app.proyecto_cazadores.domain.exception.BadRequestException;
import app.proyecto_cazadores.domain.exception.MensajeNotFoundException;
import app.proyecto_cazadores.domain.exception.PilarNotFoundException;
import app.proyecto_cazadores.domain.model.Mensaje;
import app.proyecto_cazadores.domain.model.Pilar;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class MensajeService {

    private final MensajeRepository mensajeRepository;
    private final PilarRepository pilarRepository;

    public MensajeService(MensajeRepository mensajeRepository, PilarRepository pilarRepository) {
        this.mensajeRepository = mensajeRepository;
        this.pilarRepository = pilarRepository;
    }

    @Transactional
    public Mensaje crearMensaje(Long pilarId, String contenidoFragmentado) {
        if (contenidoFragmentado == null || contenidoFragmentado.isBlank()) {
            throw new BadRequestException("Contenido fragmentado es requerido");
        }
        Pilar p = pilarRepository.findById(pilarId)
                .orElseThrow(() -> new PilarNotFoundException("No existe un Pilar con ID " + pilarId));
        Mensaje m = new Mensaje();
        m.setPilarId(p.getId());
        m.setContenidoFragmentado(contenidoFragmentado);
        m.setContenidoReconstruido(null);
        m.setTimestamp(LocalDateTime.now());
        return mensajeRepository.save(m);
    }

    @Transactional
    public Mensaje reconstruirMensaje(Long id, String contenidoReconstruido) {
        Mensaje m = mensajeRepository.findById(id)
                .orElseThrow(() -> new MensajeNotFoundException("No existe mensaje con ID " + id));
        if (contenidoReconstruido == null || contenidoReconstruido.isBlank()) {
            throw new BadRequestException("Contenido reconstruido es requerido");
        }
        m.setContenidoReconstruido(contenidoReconstruido);
        return mensajeRepository.save(m);
    }
}
