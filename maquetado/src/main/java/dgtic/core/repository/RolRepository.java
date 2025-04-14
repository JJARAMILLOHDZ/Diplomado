package dgtic.core.repository;

import dgtic.core.model.Marca;
import dgtic.core.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Integer> {
}
