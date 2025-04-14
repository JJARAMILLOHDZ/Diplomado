package dgtic.core.controller.administrador;

import dgtic.core.model.Persona;
import dgtic.core.service.PersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "personas")
public class PersonaController {

    @Autowired
    MessageSource mensaje;
    @Autowired
    PersonaService servicio;


    @GetMapping("personas")
    public String listarPersonas(Model model) {

        model.addAttribute("encabezado","Alta de personas");
        model.addAttribute("personas", servicio.getAll());
        return "administrador/catalogos/personas";
    }

    @PostMapping("/guardar")
    public String guardarPersona(@Valid Persona persona, BindingResult bindingResult,
                                 Model model)
    {
        model.addAttribute("encabezado","Alta de personas");
        model.addAttribute("personas", servicio.getAll());
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "administrador/catalogos/personas";
        }
        try {
            servicio.guardar(persona);
        }
        catch (Exception e) {
            String msg = mensaje.getMessage("Error.base.duplicado",
                    null, LocaleContextHolder.getLocale());
            ObjectError msgError = new ObjectError("curp", msg);
            bindingResult.addError(msgError);
            bindingResult.rejectValue("curp", "curp", msg);
            return "administrador/catalogos/personas";


        }


        return "administrador/catalogos/personas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPersona(@PathVariable Integer id, BindingResult bindingResult ,Model model)
    {

        try {
            servicio.eliminarPorId(id);
        } catch (Exception e) {
            String msg = mensaje.getMessage("Error al intentar eliminar",
                    null, LocaleContextHolder.getLocale());

        }
        model.addAttribute("personas", servicio.getAll());
        return "administrador/catalogos/personas";
    }
}


