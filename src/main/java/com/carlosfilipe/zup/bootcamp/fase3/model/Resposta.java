package com.carlosfilipe.zup.bootcamp.fase3.model;

public class Resposta {
    
    String idResposta;
	String conteudo;
	String dataHora;
	String nomeUsuario;
    String fotoPerfil;

    public String getIdResposta() {
		return idResposta;
	}
	public void setIdResposta(String idResposta) {
		this.idResposta = idResposta;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public String getDataHora() {
		return dataHora;
	}
	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getFotoPerfil() {
		return fotoPerfil;
	}
	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
    
}