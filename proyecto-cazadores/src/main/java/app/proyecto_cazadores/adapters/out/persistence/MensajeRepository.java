package app.proyecto_cazadores.adapters.out.persistence;

import app.proyecto_cazadores.domain.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
}
