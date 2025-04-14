package dgtic.core.repository;

import dgtic.core.model.Marca;
import dgtic.core.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {
}
