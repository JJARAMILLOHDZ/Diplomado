package dgtic.core.service;

import dgtic.core.model.Persona;
import dgtic.core.model.Usuario;
import dgtic.core.model.UsuarioRol;

import java.util.List;

public interface UsuarioRolService
{
    void guardar(UsuarioRol usuarioRol);
    List<UsuarioRol> getAll();
    List<UsuarioRol> findByUserId(Integer userId);
    void eliminarPorId(Integer id);
    UsuarioRol findById(Integer id);
}
