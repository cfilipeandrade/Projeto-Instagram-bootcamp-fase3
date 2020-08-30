package com.carlosfilipe.zup.bootcamp.fase3.dao;

import java.util.List;

import javax.sql.DataSource;

import com.carlosfilipe.zup.bootcamp.fase3.mapper.ComentarioMapper;
import com.carlosfilipe.zup.bootcamp.fase3.model.Comentario;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ComentarioDAO {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Comentario> getComentario(final int idFoto, final int inicio, final int limite) {
        final String sql = "SELECT * FROM "
                + "(SELECT idComentario, conteudo, datahora, nomeUsuario, getFotoPerfil(nomeUsuario) AS nomeArquivoFotoPerfil, "
                + "getNumResposta(idComentario) AS numResposta FROM comentario WHERE idFoto = ? "
                + "ORDER BY datahora DESC LIMIT ?, ?) AS tempo ORDER BY tempo.datahora";
            return jdbcTemplate.query(sql, new Object[] {idFoto, inicio, limite}, new ComentarioMapper());
    }

    
}