package dgtic.core.service;

import dgtic.core.model.Categoria;
import dgtic.core.model.Persona;
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

    @Override
    public Rol save(Rol rol) {
        return repository.save(rol);
    }

    @Override
    public Rol update(Integer id, Rol rol) {
        return  repository.findById(id)
                .map(rolExistente -> {
                    rolExistente.setNombre(rol.getNombre());
                    return repository.save(rolExistente);
                }).orElseThrow( () -> new RuntimeException("Rol no encotnrdo ") );
    }

    @Override
    public Rol delete(Integer id) {
        Rol rol = repository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("El rol no se encuentra registrado")
                );
        repository.deleteById(id);
        return rol;

    }
}
