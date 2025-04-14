package dgtic.core.repository;

import dgtic.core.model.Persona;
import dgtic.core.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    boolean existsByCurp(String curp);
}
