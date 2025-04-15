package dgtic.core.controller.rest;
import dgtic.core.model.Usuario;
import dgtic.core.model.UsuarioRol;
import dgtic.core.service.UsuarioRolService;
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
@RequestMapping("/api/usuario-roles")
@Tag(name = "Usuario-Rol", description = "API para gestión de enlaces entre las tablas de usuario y rol")
public class UsuarioRolRestController
{
    @Autowired
    UsuarioRolService service;


    @GetMapping("/")
    @Operation(summary = "Obtener todos los enlaces entre usuario y rol registrados",
            description = "Retorna una lista de usuarios registrados en la base de datos")
    public List<UsuarioRol> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario-rol por id",
            description = "Retorna el usuario-rol registrado con el id solicitado")
    public ResponseEntity<UsuarioRol> getById(@PathVariable Integer id) {
        UsuarioRol usuarioRol = service.findById(id);
        if(usuarioRol != null) {
            return ResponseEntity.ok(usuarioRol);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    @Operation(summary = "Crear un nuevo usuario-rol",
            description = "Registra un nuevo usuario-rol en el sistema")
    public ResponseEntity<?> create(@Valid @RequestBody UsuarioRol usuarioRol, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(service.save(usuarioRol));
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario-rol por id",
            description = "Elimina el registro de un usuario-rol por id ")
    public ResponseEntity<UsuarioRol> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(service.delete(id));
    }
}