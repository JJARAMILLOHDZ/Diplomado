package dgtic.core.service;

import dgtic.core.model.Persona;
import dgtic.core.model.Rol;
import dgtic.core.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

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

    @Override
    public Persona save(Persona persona) {
        return repository.save(persona);
    }


    @Override
    public Persona update(Integer id, Persona persona) {
        return  repository.findById(id)
                .map(personaExistente -> {
                    if( persona.getNombre() != null  &&  ! persona.getNombre().isEmpty() )
                    {
                        personaExistente.setNombre(persona.getNombre());
                    }
                    if(  persona.getApellidos() != null && ! persona.getApellidos().isEmpty() ) {
                        personaExistente.setApellidos(persona.getApellidos());
                    }
                    if(persona.getCurp() != null && ! persona.getCurp().isEmpty() )
                    {
                        personaExistente.setCurp(persona.getCurp());
                    }
                    if(persona.getRfc() != null &&  ! persona.getRfc().isEmpty() )
                    {
                        personaExistente.setRfc(persona.getRfc());
                    }

                    return repository.save(personaExistente);
                }).orElseThrow( () -> new RuntimeException("Persona no encotnrda ") );
    }

    @Override
    public Persona delete(Integer id) {
        Persona persona = repository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("La persona no se encuentra registrada")
                );
        repository.deleteById(id);
        return persona;

    }
}
