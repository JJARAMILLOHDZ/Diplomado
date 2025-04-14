package dgtic.core.controller.administrador;

import dgtic.core.model.Rol;
import dgtic.core.service.RolService;
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
@RequestMapping(value = "roles")
public class RolController {

    @Autowired
    MessageSource mensaje;
    @Autowired
    RolService servicio;


    @GetMapping("roles")
    public String listar(Model model) {

        model.addAttribute("encabezado","Alta de personas");
        model.addAttribute("roles", servicio.getAll());
        return "administrador/catalogos/roles";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Rol rol, BindingResult bindingResult,
                          Model model)
    {


        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            model.addAttribute("roles", servicio.getAll());
            return "administrador/catalogos/roles";
        }
        try {
            servicio.guardar(rol);
        }
        catch (Exception e) {
            String msg = mensaje.getMessage("Error.base.duplicado",
                    null, LocaleContextHolder.getLocale());
            ObjectError msgError = new ObjectError("nombre", msg);
            bindingResult.addError(msgError);
            bindingResult.rejectValue("nombre", "nombre", msg);
            return "administrador/catalogos/roles";


        }
        model.addAttribute("roles", servicio.getAll());
        return "administrador/catalogos/roles";
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
        model.addAttribute("roles", servicio.getAll());
        return "administrador/catalogos/roles";
    }
}
