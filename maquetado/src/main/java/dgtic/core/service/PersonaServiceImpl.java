package dgtic.core.service;

import dgtic.core.model.Persona;
import dgtic.core.model.Rol;
import dgtic.core.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService
{
    @Autowired
    PersonaRepository repository;

    @Override
    @Transactional
    public void guardar(Persona persona)
    {
        repository.save(persona);
    }

    @Override
    public List<Persona> getAll() {
        return repository.findAll();
    }

    @Override
    public boolean existePersonaPorCurp(String curp) {
        return repository.existsByCurp(curp);
    }

    @Override
    public void eliminarPorId(Integer id)
    {
        repository.deleteById(id);
    }
    @Override
    public Persona findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Persona no existe"));
    }
}
