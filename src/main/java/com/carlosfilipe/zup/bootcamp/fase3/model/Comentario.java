package com.carlosfilipe.zup.bootcamp.fase3.model;
import java.util.List;

public class Comentario {

    int idComentario;
    String conteudo;
	String dataHora;
    String nomeUsuario;
    String fotoPerfil;
    int numResposta;
    
    List<Resposta> resposta;
    
    public int getIdComentario() {
		return idComentario;
	}
	public void setIdComentario(final int idComentario) {
        this.idComentario = idComentario;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(final String conteudo) {
        this.conteudo = conteudo;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(final String dataHora) {
        this.dataHora = dataHora;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(final String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public int getNumResposta() {
        return numResposta;
    }

    public void setNumResposta(final int numResposta) {
		this.numResposta = numResposta;
	}

    public Comentario(List<Resposta> resposta) {
        this.resposta = resposta;
    }

    public List<Resposta> getResposta() {
        return resposta;
    }
    public void setResposta(List<Resposta> resposta) {
        this.resposta = resposta;
    }
}