
package com.carlosfilipe.zup.bootcamp.fase3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuariosController {
    /*
    Vamos chamar o método de listar(), pois ele será responsável por listar os
usuarios
     */
    private static final String USUARIOS = "/usuarios";

    @GetMapping(USUARIOS)
        public String listar() {
        return "ListaUsuarios";
    }
 
}