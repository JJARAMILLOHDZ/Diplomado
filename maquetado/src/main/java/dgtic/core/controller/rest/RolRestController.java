package dgtic.core.controller.rest;
import dgtic.core.model.Rol;
import dgtic.core.model.Usuario;
import dgtic.core.service.RolService;
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
@RequestMapping("/api/roles")
@Tag(name = "Roles", description = "API para gestión de roles")
public class RolRestController
{
    @Autowired
    RolService service;


    @GetMapping("/")
    @Operation(summary = "Obtener todos los roles registrados",
            description = "Retorna una lista de roles registrados en la base de datos")
    public List<Rol> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener rol por id",
            description = "Retorna el rol registrado con el id solicitado")
    public ResponseEntity<Rol> getById(@PathVariable Integer id) {
        Rol rol = service.findById(id);
        if(rol != null) {
            return ResponseEntity.ok(rol);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    @Operation(summary = "Crear un nuevo rol",
            description = "Registra un nuevo rol en el sistema")
    public ResponseEntity<?> create(@Valid @RequestBody Rol rol, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(service.save(rol));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar rol por id",
            description = "Actualiza el registro de un rol ")
    public ResponseEntity<Rol> update(@PathVariable Integer id,
                                                @RequestBody Rol rol){
        return ResponseEntity.ok(service.update(id,rol));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar rol por id",
            description = "Elimina el registro de un rol por id ")
    public ResponseEntity<Rol> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(service.delete(id));
    }
}