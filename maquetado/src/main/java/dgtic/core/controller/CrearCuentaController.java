package dgtic.core.controller;

import dgtic.core.model.dto.RegistroDto;
import dgtic.core.service.PersonaService;
import dgtic.core.service.RolService;
import dgtic.core.service.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "crearCuenta")
public class CrearCuentaController {

    @Autowired
    MessageSource mensaje;
    @Autowired
    PersonaService personaService;
    @Autowired
    RolService rolService;

    @Autowired
    UsuarioServiceImpl usuarioService;

    @GetMapping("registro")
    public String verRegistro(Model modelo){
        modelo.addAttribute("registroDto",new RegistroDto());
        modelo.addAttribute("roles", rolService.getAll());
        modelo.addAttribute("encabezado","Registro de nuevo usuario");
        return "principal/crearCuenta";
    }

    @PostMapping("guardar-registro")
    public String guardarRegistro(@Valid RegistroDto registroDto,
                                  BindingResult bindingResult,
                                  Model modelo)
    {

        if( usuarioService.existeUsuarioPorNombre(registroDto.getUsuario() ) )
        {
            bindingResult.rejectValue("usuario","error.registroDto","El nombre de usuario ya está registrado");
        }
        if( personaService.existePersonaPorCurp(registroDto.getCurp()) )
        {

            bindingResult.rejectValue("curp","error.registroDto","La CURP ya está registrada");
        }
        if (usuarioService.existePorCorreo(registroDto.getEmail())) {
            bindingResult.rejectValue("email", "error.registroDto", "El correo electrónico ya está registrado");
        }
        if (bindingResult.hasErrors())
        {
            for (ObjectError err : bindingResult.getAllErrors()) {
                System.out.println("Error: " + err.getDefaultMessage());
            }
            modelo.addAttribute("roles", rolService.getAll());
            return "principal/crearCuenta";
        }
        try {
            usuarioService.registrarUsuarioConPersona(registroDto);
            return "registroExitoso";
        } catch (Exception e) {
            String msg= mensaje.getMessage("Error.base.duplicado",
                    null, LocaleContextHolder.getLocale());
            return "principal/crearCuenta";
        }





    }
}
