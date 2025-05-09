package dgtic.core.service;

import dgtic.core.model.Categoria;
import dgtic.core.model.Persona;
import dgtic.core.model.Usuario;
import dgtic.core.model.UsuarioRol;

import java.util.List;

public interface UsuarioService
{
    void guardar(Usuario usuario);
    List<Usuario> getAll();
    Usuario crear(Usuario usuario);
    boolean existeUsuarioPorNombre(String nombre);
    boolean existePorCorreo(String correo);
    void eliminarPorId(Integer id);
    Usuario findById(Integer id);
    Usuario update(Integer id, Usuario usuario);
    Usuario save(Usuario usuario);
    Usuario delete(Integer id);
}
