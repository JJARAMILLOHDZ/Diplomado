package dgtic.core.controller;

import dgtic.core.model.Persona;
import dgtic.core.model.Rol;
import dgtic.core.model.Usuario;
import dgtic.core.model.UsuarioRol;
import dgtic.core.model.dto.RegistroDto;
import dgtic.core.service.*;
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

import java.util.List;

@Controller
@RequestMapping(value = "login")
public class LoginController
{

    @Autowired
    UsuarioServiceImpl usuarioService;
    @Autowired
    UsuarioRolService usuarioRolService;



    @GetMapping("login")
    public String getLogin(Model modelo)
    {
        modelo.addAttribute("contenido","Pagina donde se mostrará el login y/o registro de usuarios");
        modelo.addAttribute("usuario", new Usuario());
        return "principal/login";
    }

    @PostMapping("acceder")
    public String acceder( Usuario usuarioFront,
                           BindingResult bindingResult,
                           Model modelo)
    {
        List<UsuarioRol> userRols = null;
        UsuarioRol rolUser= null;
        if( usuarioFront.getNombre().isEmpty() )
        {
            bindingResult.rejectValue("nombre","error.usuario","Usuario requerido");
        }
        Usuario user = usuarioService.loadUserByUsername(usuarioFront.getNombre().toUpperCase());
        if( user == null )
        {
            bindingResult.rejectValue("nombre","error.usuario","Contraseña requerida");
        }
        else {
            if( !user.getPassword().equalsIgnoreCase(usuarioFront.getPassword()) )
            {
                bindingResult.rejectValue("password","error.usuario","La contraseña no coincide");
            }
        }
        if (bindingResult.hasErrors())
        {
            for (ObjectError err : bindingResult.getAllErrors()) {
                System.out.println("Error: " + err.getDefaultMessage());
            }
            return "principal/login";
        }

        userRols = usuarioRolService.findByUserId(user.getId());

        if(userRols.isEmpty())
        {
            bindingResult.rejectValue("password","error.usuario","Error al tratar de identificar el rol");
            return "principal/login";
        }
        rolUser = userRols.get(0);

        switch(rolUser.getRol().getNombre() )
        {
            case "ADMINISTRADOR":
                return "administrador/home";
            case "COLECCIONISTA":
                return "coleccionista/home";
            case "VISITA":
                return "visita/home";
            default:
                return "inicio";
        }
    }


}
