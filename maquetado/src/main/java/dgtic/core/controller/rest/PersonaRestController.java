package dgtic.core.controller.rest;

import dgtic.core.model.Persona;
import dgtic.core.service.PersonaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping("/api/personas")
@Tag(name = "Personas", description = "API para gestión de personas")
public class PersonaRestController
{
    @Autowired
    PersonaService service;


    @GetMapping("/")
    @Operation(summary = "Obtener todas las personas",
            description = "Retorna una lista de todas las personas registradas")
    @ApiResponse(responseCode = "200", description = "Lista de personas obtenida exitosamente")
    public List<Persona> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener personas por id",
            description = "Retorna la persona registrada con el id solicitado")
    @ApiResponse(responseCode = "200", description = "Persona encontrada")
    @ApiResponse(responseCode = "400", description = "Persona no encontrada")
    public ResponseEntity<Persona> getById(@PathVariable Integer id) {
        Persona persona = service.findById(id);
        if(persona != null) {
            return ResponseEntity.ok(persona);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    @Operation(summary = "Crear una nueva persona",
            description = "Registra una nueva persona en el sistema")
    @ApiResponse(responseCode = "200", description = "Persona creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    public ResponseEntity<?> create(@Valid @RequestBody Persona persona, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(service.save(persona));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar persona por id",
            description = "Actualiza el registro de una persona ")
    @ApiResponse(responseCode = "200", description = "Persona actualizada exitosamente")
    @ApiResponse(responseCode = "400", description = "Persona no encontrada")
    public ResponseEntity<Persona> updateAlumno(@PathVariable Integer id,
                                               @RequestBody Persona persona){
        return ResponseEntity.ok(service.update(id,persona));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar persona por id",
            description = "Elimina el registro de una persona por id ")
    @ApiResponse(responseCode = "200", description = "Persona eliminada exitosamente")
    @ApiResponse(responseCode = "400", description = "Persona no encontrada")
    public ResponseEntity<Persona> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
