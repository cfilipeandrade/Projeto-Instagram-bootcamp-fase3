package com.carlosfilipe.zup.bootcamp.fase3.model;


public class DetalhesErro {

    private String titulo;
    private Long status;
    private Long timestamp;
    private String mensagemDesenvolvedor;

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getMensagemDesenvolvedor() {
        return mensagemDesenvolvedor;
    }
    public void setMensagemDesenvolvedor(String mensagemDesenvolvedor) {
        this.mensagemDesenvolvedor = mensagemDesenvolvedor;
    }

    public Long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getStatus() {
        return status;
    }
    public void setStatus(Long status) {
        this.status = status;
    }
}
