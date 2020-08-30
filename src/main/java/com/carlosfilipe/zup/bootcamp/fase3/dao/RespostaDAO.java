package com.carlosfilipe.zup.bootcamp.fase3.dao;

import java.util.List;

import javax.sql.DataSource;

import com.carlosfilipe.zup.bootcamp.fase3.mapper.RespostaMapper;
import com.carlosfilipe.zup.bootcamp.fase3.model.Resposta;

import org.springframework.jdbc.core.JdbcTemplate;

public class RespostaDAO {

    @SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Resposta> getResposta(int idComentario, int inicio, int limite) {
		String sql = "SELECT * FROM "
				+ "(SELECT idResposta, datahora, conteudo, nomeUsuario, getFotoPerfil(nomeUsuario) AS nomeArquivoFotoPerfil "
				+ "FROM resposta WHERE idComentario = ? "
				+ "ORDER BY datahora DESC LIMIT ?, ?) AS tempo "
				+ "ORDER BY tempo.datahora";
		return jdbcTemplate.query(sql, new Object[] {idComentario, inicio, limite}, new RespostaMapper());
	}
    
}