package dgtic.core.service;

import dgtic.core.model.Categoria;
import dgtic.core.model.Rol;
import dgtic.core.model.Usuario;
import dgtic.core.repository.CategoriaRepository;
import dgtic.core.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolServiceImpl implements RolService
{
    @Autowired
    RolRepository repository;

    @Override
    @Transactional
    public void guardar(Rol rol)
    {
        repository.save(rol);
    }

    @Override
    public List<Rol> getAll() {
        return repository.findAll();
    }
    @Override
    public void eliminarPorId(Integer id)
    {
        repository.deleteById(id);

    }

    @Override
    public Rol findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario no existe"));
    }
}
