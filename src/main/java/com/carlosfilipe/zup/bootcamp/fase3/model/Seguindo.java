package com.carlosfilipe.zup.bootcamp.fase3.model;

import java.time.LocalDate;

public class Seguindo extends Usuario {
    private static final long serialVersionUID = 1L;

    public Seguindo(Long id, String nome, String nomeUsuario, String email, String telefone, String senha,
            LocalDate dataCadastro) {
        super(id, nome, nomeUsuario, email, telefone, senha, dataCadastro);
    }

    private Boolean seguidoPeloUsuario;

    public Boolean getSeguidoPeloUsuario() {
        return seguidoPeloUsuario;
    }

    public void setSeguidoPeloUsuario(Boolean seguidoPeloUsuario) {
        this.seguidoPeloUsuario = seguidoPeloUsuario;
    }

   
    
}