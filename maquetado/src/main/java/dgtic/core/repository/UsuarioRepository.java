package dgtic.core.repository;

import dgtic.core.model.Marca;
import dgtic.core.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>
{
    boolean existsByNombre(String nombre);
    boolean existsByCorreo(String correo);
    Optional<Usuario> findByNombre(String nombre);
}
