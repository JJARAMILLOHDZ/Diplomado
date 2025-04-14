package dgtic.core.controller.administrador;

import dgtic.core.model.Persona;
import dgtic.core.model.Usuario;
import dgtic.core.service.PersonaService;
import dgtic.core.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "usuarios")
public class UsuarioController {

    @Autowired
    MessageSource mensaje;
    @Autowired
    UsuarioService servicio;


    @GetMapping("usuarios")
    public String listar(Model model) {

        model.addAttribute("encabezado","Alta de personas");
        model.addAttribute("usuarios", servicio.getAll());
        return "administrador/catalogos/usuarios";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Usuario usuario, BindingResult bindingResult,
                          Model model)
    {


        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            model.addAttribute("usuarios", servicio.getAll());
            return "administrador/catalogos/usuarios";
        }
        try {
            servicio.guardar(usuario);
        }
        catch (Exception e) {
            String msg = mensaje.getMessage("Error.base.duplicado",
                    null, LocaleContextHolder.getLocale());
            ObjectError msgError = new ObjectError("nombre", msg);
            bindingResult.addError(msgError);
            bindingResult.rejectValue("nombre", "nombre", msg);
            return "administrador/catalogos/usuarios";


        }
        model.addAttribute("usuarios", servicio.getAll());
        return "administrador/catalogos/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, BindingResult bindingResult ,Model model)
    {

        try {
            servicio.eliminarPorId(id);

        } catch (Exception e) {
            String msg = mensaje.getMessage("Error al intentar eliminar",
                    null, LocaleContextHolder.getLocale());

        }
        model.addAttribute("usuarios", servicio.getAll());
        return "administrador/catalogos/usuarios";
    }
}
