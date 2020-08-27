package com.carlosfilipe.zup.bootcamp.fase3.model;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@Table(name = "nomeUsuario")
public class Usuario implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private String nomeUsuario;

    private String email;

    private String telefone;

    private String senha;

    private LocalDate dataCadastro;

    public Usuario(final Long id, final String nome, final String nomeUsuario, final String email,
            final String telefone, final String senha, final LocalDate dataCadastro) {

        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.dataCadastro = dataCadastro;
    }


    public Long getId() {
        return id;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    final public void setDataCadastro(final LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getSenha() {
        return senha;
     }

    public void setSenha(final String senha) {
        this.senha = senha;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(final String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(final String telefone) {
       this.telefone = telefone;
   }
   
//para garantir que o nome de usuario não se repita.
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Usuario other = (Usuario) obj;
		if (nomeUsuario == null) {
			if (other.nomeUsuario != null)
				return false;
		} else if (!nomeUsuario.equals(other.nomeUsuario))
			return false;
		return true;
    }

 }   
