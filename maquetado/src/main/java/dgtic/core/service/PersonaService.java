package dgtic.core.service;

import dgtic.core.model.Persona;

import java.util.List;

public interface PersonaService
{
    void guardar(Persona persona);
    List<Persona> getAll();
    boolean existePersonaPorCurp(String curp);
    void eliminarPorId(Integer id);
    Persona findById(Integer id);
}
