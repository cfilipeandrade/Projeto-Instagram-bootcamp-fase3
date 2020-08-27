package com.carlosfilipe.zup.bootcamp.fase3.model;

import java.time.LocalDate;

public class Seguidor extends Usuario {

    private static final long serialVersionUID = 1L;

    public Seguidor(Long id, String nome, String nomeUsuario, String email, String telefone, String senha,
            LocalDate dataCadastro) {
        super(id, nome, nomeUsuario, email, telefone, senha, dataCadastro);
        // TODO Auto-generated constructor stub
    }

    private Boolean segueUsuario;

    public Boolean getSegueUsuario() {
        return segueUsuario;
    }

    public void setSegueUsuario(Boolean segueUsuario) {
        this.segueUsuario = segueUsuario;
    }

}