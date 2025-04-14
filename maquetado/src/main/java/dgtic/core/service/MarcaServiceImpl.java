package dgtic.core.service;

import dgtic.core.model.Marca;
import dgtic.core.model.Persona;
import dgtic.core.repository.MarcaRepository;
import dgtic.core.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MarcaServiceImpl implements MarcaService
{
    @Autowired
    MarcaRepository repository;

    @Override
    @Transactional
    public void guardar(Marca marca)
    {
        repository.save(marca);
    }

    @Override
    public List<Marca> getAll() {
        return repository.findAll();
    }

    @Override
    public void eliminarPorId(Integer id)
    {
        repository.deleteById(id);

    }
}
