package dgtic.core.service;

import dgtic.core.model.Persona;
import dgtic.core.model.Rol;
import dgtic.core.model.Usuario;

import java.util.List;

public interface RolService
{
    void guardar(Rol rol);
    List<Rol> getAll();
    void eliminarPorId(Integer id);
    Rol findById(Integer id);
}
