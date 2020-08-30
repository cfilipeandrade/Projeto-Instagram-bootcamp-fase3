package com.carlosfilipe.zup.bootcamp.fase3.dao;

import javax.sql.DataSource;

import com.carlosfilipe.zup.bootcamp.fase3.mapper.ConfigMapper;
import com.carlosfilipe.zup.bootcamp.fase3.mapper.PerfilMapper;
import com.carlosfilipe.zup.bootcamp.fase3.model.Configuracao;
import com.carlosfilipe.zup.bootcamp.fase3.model.Perfil;

import org.springframework.jdbc.core.JdbcTemplate;

public class PerfilDAO {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Configuracao getSetting(String nomeUsuario) {
		String sql = "SELECT lingua, contaPrivada FROM configuracao WHERE nomeUsuario = ?";
		return jdbcTemplate.query(sql, new Object[] { nomeUsuario }, new ConfigMapper()).get(0);
	}

	public int atualizarConfiguracao(String nomeUsuario, String lingua, boolean contaPrivada) {
		String sql = "UPDATE configuracao SET (lingua, contaPrivada) VALUES (?, ?) WHERE nomeUsuario = ?";
		return jdbcTemplate.update(sql, lingua, contaPrivada, nomeUsuario);
	}
	
	public Perfil getPerfil(String nomeUsuario) {
		String sql = "SELECT biografia, telefone, sexo, website FROM perfil WHERE nomeUsuario = ?";
		return jdbcTemplate.query(sql, new Object[] {nomeUsuario}, new PerfilMapper()).get(0);
	}
	
	public int atualizarPerfil(String nomeUsuario, String biografia, String telefone, String sexo, String website) {
		String sql = "UPDATE perfil SET (biografia, telefone, sexo, website) VALUES (?, ?, ?, ?) WHERE nomeUsuario = ?";
		return jdbcTemplate.update(sql, biografia, telefone, sexo, website, nomeUsuario);
	}
    
}