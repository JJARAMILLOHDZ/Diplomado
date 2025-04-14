package dgtic.core.repository;

import dgtic.core.model.Usuario;
import dgtic.core.model.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Integer>
{
    @Query(value = "SELECT * FROM usuario_rol WHERE usuario_id = :usuarioId", nativeQuery = true)
    List<UsuarioRol> findActiveRolesByUsuarioId(@Param("usuarioId") Integer usuarioId);


}
