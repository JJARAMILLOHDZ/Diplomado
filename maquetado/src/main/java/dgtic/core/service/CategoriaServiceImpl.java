package dgtic.core.service;

import dgtic.core.model.Categoria;
import dgtic.core.model.Marca;
import dgtic.core.repository.CategoriaRepository;
import dgtic.core.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService
{
    @Autowired
    CategoriaRepository repository;

    @Override
    @Transactional
    public void guardar(Categoria categoria)
    {
        repository.save(categoria);
    }

    @Override
    public List<Categoria> getAll() {
        return repository.findAll();
    }

    @Override
    public void eliminarPorId(Integer id)
    {
        repository.deleteById(id);

    }
}
