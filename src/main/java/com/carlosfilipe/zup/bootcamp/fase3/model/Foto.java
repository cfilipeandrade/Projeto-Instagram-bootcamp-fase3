package com.carlosfilipe.zup.bootcamp.fase3.model;

import java.util.List;

public class Foto {

    int idFoto;
    String titulo;
    String nomeArquivo;
    String datahora;
    String dataAtualizacao;
    String local;
    String nomeUsuario;
    String nome;
    String fotoPerfil;
    int numLike;
    int numComentario;
    List<Comentario> comentario;

    public int getIdFoto() {
        return idFoto;
    }
    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }

    public List<Comentario> getComentario() {
        return comentario;
    }
    public void setComentario(List<Comentario> comentario) {
        this.comentario = comentario;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public String getDatahora() {
		return datahora;
	}
	public void setDatahora(String datahora) {
		this.datahora = datahora;
	}
	public String getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(String dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getNumLike() {
		return numLike;
	}
	public void setNumLike(int numLike) {
		this.numLike = numLike;
	}
	public int getNumComentario() {
		return numComentario;
	}
	public void setNumComentario(int numComentario) {
		this.numComentario = numComentario;
	}

}