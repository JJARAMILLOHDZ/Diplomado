package dgtic.core.service;

import dgtic.core.model.Categoria;
import dgtic.core.model.Persona;

import java.util.List;

public interface CategoriaService
{
    void guardar(Categoria categoria);
    List<Categoria> getAll();
    void eliminarPorId(Integer id);
}
