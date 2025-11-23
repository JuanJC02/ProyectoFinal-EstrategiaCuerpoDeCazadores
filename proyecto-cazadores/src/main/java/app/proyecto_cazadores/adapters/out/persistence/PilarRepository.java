package app.proyecto_cazadores.adapters.out.persistence;

import app.proyecto_cazadores.domain.model.Pilar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PilarRepository extends JpaRepository<Pilar, Long> {
}
