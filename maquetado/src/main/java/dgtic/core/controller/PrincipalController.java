package dgtic.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "colelin")
public class PrincipalController
{
//    http://localhost:8080/spring/
    @RequestMapping(value = "requestmapping",method = RequestMethod.GET)
    public String inicio(Model model)
    {
        model.addAttribute("contenido","@RequestMapping en Clase");
        return "principal/requestmapping";
    }

    @GetMapping("articulo")
    public String getArticulo(Model modelo)
    {
        modelo.addAttribute("contenido","Pagina donde se mostrarán los articulos a detalle");
        return "principal/articulo";
    }

    @GetMapping("galeria")
    public String getGaleria(Model modelo)
    {
        modelo.addAttribute("contenido","Pagina donde se mostrará la galeria/tienda de cada coleccionista/vendero");
        return "principal/galeria";
    }

    @GetMapping("destacados")
    public String getDestacados(Model modelo)
    {
        modelo.addAttribute("contenido","Pagina donde se mostrará la lista de articulos destacados que incluirá filtros");
        return "principal/destacados";
    }


    @GetMapping("categorias")
    public String getCatalogos(Model modelo)
    {
        modelo.addAttribute("contenido","Lista de categorías con las que se cuenta con una imagen de ejemplo por categoria ");
        return "principal/categorias";
    }


}
