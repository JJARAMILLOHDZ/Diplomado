package dgtic.core.controller.administrador;

import dgtic.core.model.Categoria;
import dgtic.core.model.Marca;
import dgtic.core.model.Persona;
import dgtic.core.service.CategoriaService;
import dgtic.core.service.MarcaService;
import dgtic.core.service.PersonaService;
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
@RequestMapping(value = "admin")
public class CatalogosController
{
    @Autowired
    MessageSource mensaje;
    @Autowired
    PersonaService personaService;
    @Autowired
    MarcaService marcaService;
    @Autowired
    CategoriaService categoriaService;

    @GetMapping("home")
    public String home(Model modelo){
        return "administrador/home";
    }


    @GetMapping("personas")
    public String verPersona(Model modelo){
        modelo.addAttribute("persona",new Persona());
        modelo.addAttribute("encabezado","Alta de personas");
        return "administrador/catalogos/personas";
    }

    @PostMapping("recibir-persona")
    public String recibirPersona(@Valid Persona persona,
                                 BindingResult bindingResult,
                                 Model modelo)
    {
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "catalogos/personas";
        }
        try {
            personaService.guardar(persona);
        }catch (Exception e){
            String msg= mensaje.getMessage("Error.base.duplicado",
                    null, LocaleContextHolder.getLocale());
            ObjectError msgError=new ObjectError("curp",msg);
            //bindingResult.addError(msgError);
            bindingResult.rejectValue("curp","curp",msg);
            return "catalogos/personas";
        }

        String cadena="Persona: "+persona.getNombre()+" "+persona.getApellidos() + " Creada exitosamente";
        modelo.addAttribute("persona",new Persona());
        modelo.addAttribute("contenido","Los datos que ingresas son:");
        modelo.addAttribute("info",cadena);
        return "catalogos/personas";
    }

    @GetMapping("ver-marca")
    public String verMarca(Model modelo){
        modelo.addAttribute("marca",new Marca());
        modelo.addAttribute("encabezado","Alta de marcas");
        return "catalogos/marcas";
    }

    @PostMapping("recibir-marca")
    public String recibirMarca(@Valid Marca marca,
                                 BindingResult bindingResult,
                                 Model modelo)
    {
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "catalogos/marcas";
        }
        try {
            marcaService.guardar(marca);
        }catch (Exception e){
            String msg= mensaje.getMessage("Error.base.duplicado",
                    null, LocaleContextHolder.getLocale());

            return "catalogos/marcas";
        }

        String cadena="Marca : "+marca.getNombre()+ " Registrada exitosamente";
        modelo.addAttribute("marca",new Marca());
        modelo.addAttribute("info",cadena);
        return "catalogos/marcas";
    }

    @GetMapping("ver-categoria")
    public String verCategoria(Model modelo){
        modelo.addAttribute("categoria",new Categoria());
        modelo.addAttribute("encabezado","Alta de categorias");
        return "catalogos/categorias";
    }

    @PostMapping("recibir-categoria")
    public String recibirCategoria(@Valid Categoria categoria,
                                 BindingResult bindingResult,
                                 Model modelo)
    {
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "catalogos/categorias";
        }
        try {
            categoriaService.guardar(categoria);
        }catch (Exception e){
            String msg= mensaje.getMessage("Error.base.duplicado",
                    null, LocaleContextHolder.getLocale());
            ObjectError msgError=new ObjectError("nombre",msg);
            //bindingResult.addError(msgError);
            bindingResult.rejectValue("nombre","nombre",msg);
            return "catalogos/categorias";
        }

        String cadena="Categoria : "+categoria.getNombre()+ " Creada exitosamente";
        modelo.addAttribute("categoria",new Categoria());
        modelo.addAttribute("info",cadena);
        return "catalogos/categorias";
    }

}
