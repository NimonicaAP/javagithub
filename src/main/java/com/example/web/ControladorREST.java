package com.example.web;

import com.example.dao.IPersonaDao;
import com.example.domain.Persona;
import com.example.servicio.IPersonaService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class ControladorREST {
    //Inyectar dependencia administrado por el contenedor
//    @Autowired
//    private IPersonaDao personaDao;
    @Autowired
    private IPersonaService personaService;
    
    @GetMapping("/")
    //Metodo
    public String comienzo(Model model, @AuthenticationPrincipal User user){
        //Se encuentra mostrando en los datos en la pagina
       // var personas = personaDao.findAll();
        var personas = personaService.listarPersonas();
        log.info("Estoy ejecutando el controlador Spring MVC");
        log.info("Usuario que hizo login" + user);
        model.addAttribute("personas", personas);
        //Declarando variable para contador de clientes y saldo en la pagina de listarClientes
        var saldoTotal = 0D;
        for(var p: personas){
            saldoTotal += p.getSaldo();
        }
        model.addAttribute("saldoTotal", saldoTotal);
        model.addAttribute("totalClientes", personas.size());
        return "index";
    }
    //Path
    @GetMapping("/agregar")//Path th:action="@{/guardar}
    public String agregar(Persona persona){
        return "modificar";//Path index
    }
    
    @PostMapping("/guardar") //Validacion de formulario
    public String guardar(@Valid Persona persona, Errors errores){
        if(errores.hasErrors()){
            return "modificar";
        }
        personaService.guardar(persona);
        return "redirect:/"; //redireccionamos a la pagina de inicio
    }
    
    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model){
        persona = personaService.encontrar(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }
    
    @GetMapping("/eliminar")
    public String eliminar(Persona persona){
        personaService.eliminar(persona);
        return "redirect:/";
    }
}
