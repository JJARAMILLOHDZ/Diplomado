package dgtic.core.service;

import dgtic.core.model.Usuario;
import dgtic.core.model.UsuarioRol;
import dgtic.core.repository.UsuarioRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioRolServiceImpl implements UsuarioRolService
{
    @Autowired
    UsuarioRolRepository repository;

    @Override
    public void guardar(UsuarioRol usuarioRol)
    {
        repository.save(usuarioRol);
    }

    @Override
    public List<UsuarioRol> getAll() {
       return repository.findAll();
    }

    @Override
    public List<UsuarioRol> findByUserId(Integer userId) {
        return repository.findActiveRolesByUsuarioId(userId);
    }

    @Override
    public void eliminarPorId(Integer id)
    {
        repository.deleteById(id);

    }

    @Override
    public UsuarioRol findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario-Rol no existe"));
    }
}
