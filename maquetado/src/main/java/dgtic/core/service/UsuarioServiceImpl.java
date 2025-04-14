package dgtic.core.service;

import dgtic.core.model.Persona;
import dgtic.core.model.Rol;
import dgtic.core.model.Usuario;
import dgtic.core.model.UsuarioRol;
import dgtic.core.model.dto.RegistroDto;
import dgtic.core.repository.PersonaRepository;
import dgtic.core.repository.RolRepository;
import dgtic.core.repository.UsuarioRepository;
import dgtic.core.repository.UsuarioRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService
{
    @Autowired
    UsuarioRepository repository;
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    RolRepository rolRepository;
    @Autowired
    UsuarioRolRepository usuarioRolRepository;


    @Override
    @Transactional
    public void guardar(Usuario usuario)
    {
        repository.save(usuario);
    }

    @Override
    public List<Usuario> getAll() {
        return repository.findAll();
    }

    @Override
    public Usuario crear(Usuario usuario) {
        return null;
    }

    @Override
    public boolean existeUsuarioPorNombre(String nombre) {
        return repository.existsByNombre(nombre);
    }

    @Override
    public boolean existePorCorreo(String correo) {
        return repository.existsByCorreo(correo);
    }

    @Override
    public void eliminarPorId(Integer id)
    {
        repository.deleteById(id);

    }

    @Override
    public Usuario findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario no existe"));
    }

    public Usuario loadUserByUsername(String username)
    {
        return repository.findByNombre(username).orElse(null);
    }


    @Transactional
    public void registrarUsuarioConPersona(RegistroDto registroDto) {
        // 1. Crear/validar Persona (asumiendo que Persona tiene CURP)
        Persona persona = new Persona();
        persona.setCurp(registroDto.getCurp());
        persona.setNombre(registroDto.getNombre());
        persona.setApellidos(registroDto.getApellidos());
        persona.setRfc(registroDto.getRfc());
        persona = personaRepository.save(persona);

        // 2. Crear Usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(registroDto.getUsuario().toUpperCase());
        usuario.setPassword(registroDto.getPassword());
        usuario.setPersona(persona);
        usuario.setCorreo(registroDto.getEmail());
        usuario = repository.save(usuario);

        Rol rol = rolRepository.findById(registroDto.getRolId())
                .orElseThrow(() -> new IllegalArgumentException("Rol no válido"));


        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);
        usuarioRol.setStatus(true);
        usuarioRolRepository.save(usuarioRol);

    }




}
