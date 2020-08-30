package com.carlosfilipe.zup.bootcamp.fase3.model;

public class PagPerfil {

    String nome;
	String nomeUsuario;
	String fotoPerfil;
	int numFoto;
	int numSegidor;
	int numSeguindo;
	boolean segue;
	String biografia;
    String website;
    
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public int getNumFoto() {
		return numFoto;
	}
	public void setNumFoto(int numFoto) {
		this.numFoto = numFoto;
	}
	public int getNumSegidores() {
		return numSegidor;
	}
	public void setNumSegidores(int numSegidores) {
		this.numSegidor = numSegidores;
	}
	public int getNumSeguindo() {
		return numSeguindo;
	}
	public void setNumSeguindo(int numSeguindo) {
		this.numSeguindo = numSeguindo;
	}
	public boolean isSegue() {
		return segue;
	}
	public void setSegue(boolean segue) {
		this.segue = segue;
	}
	public String getBiografia() {
		return biografia;
	}
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
    
}