package dgtic.core.controller.rest;
import dgtic.core.model.Usuario;
import dgtic.core.service.PersonaService;
import dgtic.core.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "API para gestión de usuarios")
public class UsuarioRestController
{
    @Autowired
    UsuarioService service;


    @GetMapping("/")
    @Operation(summary = "Obtener todos los usuarios registrados",
            description = "Retorna una lista de usuarios registrados en la base de datos")
    public List<Usuario> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por id",
            description = "Retorna el usuario registrado con el id solicitado")
    public ResponseEntity<Usuario> getById(@PathVariable Integer id) {
        Usuario usuario = service.findById(id);
        if(usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    @Operation(summary = "Crear un nuevo usuario",
            description = "Registra un nuevo usuario en el sistema")
    public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(service.save(usuario));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario por id",
            description = "Actualiza el registro de un usuario ")
    public ResponseEntity<Usuario> update(@PathVariable Integer id,
                                                @RequestBody Usuario usuario){
        return ResponseEntity.ok(service.update(id,usuario));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario por id",
            description = "Elimina el registro de un usuario por id ")
    public ResponseEntity<Usuario> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(service.delete(id));
    }
}