package dgtic.core.repository;

import dgtic.core.model.Categoria;
import dgtic.core.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
