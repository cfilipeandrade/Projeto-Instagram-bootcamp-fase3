package com.carlosfilipe.zup.bootcamp.fase3.model;

import java.time.LocalDate;

public class Perfil extends Usuario {

    public Perfil(Long id, String nome, String nomeUsuario, String email, String telefone, String senha,
            LocalDate dataCadastro) {
        super(id, nome, nomeUsuario, email, telefone, senha, dataCadastro);
    }

    private static final long serialVersionUID = 1L;

    private String fotoPerfil;

    private String biografia;


    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
    
}