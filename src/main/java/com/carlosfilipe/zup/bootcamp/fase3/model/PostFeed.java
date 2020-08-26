package com.carlosfilipe.zup.bootcamp.fase3.model;

import java.time.LocalDate;

public class PostFeed {

    private LocalDate dataHora;
    private String legenda;
    private Integer curtidas;
    private String usuarioFoto;

    public LocalDate getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDate dataHora) {
        this.dataHora = dataHora;
    }

    public String getLegenda() {
        return legenda;
    }

    public void setLegenda(String legenda) {
        this.legenda = legenda;
    }

    public Integer getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(Integer curtidas) {
        this.curtidas = curtidas;
    }
    
    public String getUsuarioFoto() {
        return usuarioFoto;
    }

    public void setUsuarioFoto(String usuarioFoto) {
        this.usuarioFoto = usuarioFoto;
    }
    
}