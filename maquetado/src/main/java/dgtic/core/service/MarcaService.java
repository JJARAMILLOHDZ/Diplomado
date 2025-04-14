package dgtic.core.service;

import dgtic.core.model.Marca;
import dgtic.core.model.Persona;

import java.util.List;

public interface MarcaService
{
    void guardar(Marca marca);
    List<Marca> getAll();
    void eliminarPorId(Integer id);
}
