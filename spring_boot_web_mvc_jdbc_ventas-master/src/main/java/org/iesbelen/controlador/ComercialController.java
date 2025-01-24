package org.iesbelen.controlador;

import org.iesbelen.modelo.Comercial;
import org.iesbelen.service.ComercialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ComercialController {

    private ComercialService comercialService;

    public ComercialController(ComercialService comercialService) {
        this.comercialService = comercialService;
    }

    @GetMapping("/comerciales")
    public String listar(Model model) {

        List<Comercial> listaComerciales = comercialService.listAll();
        model.addAttribute("listaComerciales", listaComerciales);

        return "comerciales/comerciales";
    }

    @GetMapping("/comerciales/{id}")
    public String detalle(Model model, @PathVariable int id) {

        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);

        return "comerciales/detalle-comerciales";
    }

    @GetMapping("/comerciales/crear")
    public String crear(Model model) {

        Comercial comercial = new Comercial();
        model.addAttribute("comercial", comercial);

        return "comerciales/crear-comerciales";
    }

    @PostMapping("/comerciales/crear")
    public RedirectView submitCrear(@ModelAttribute("comercial") Comercial comercial) {

        comercialService.newComercial(comercial);

        return new RedirectView("/comerciales");
    }

    @GetMapping("/comerciales/editar/{id}")
    public String editar(Model model, @PathVariable int id) {

        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);

        return "comerciales/editar-comerciales";
    }

    @PostMapping("/comerciales/editar/{id}")
    public RedirectView submitEditar(@ModelAttribute("comercial") Comercial comercial, @PathVariable int id) {

        comercialService.replaceComercial(comercial);

        return new RedirectView("/comerciales");
    }

    @PostMapping("/comerciales/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable int id) {

        comercialService.deleteComercial(id);

        return new RedirectView("/comerciales");
    }

}