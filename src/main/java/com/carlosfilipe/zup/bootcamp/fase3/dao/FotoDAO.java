package com.carlosfilipe.zup.bootcamp.fase3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.carlosfilipe.zup.bootcamp.fase3.mapper.FotoMapper;
import com.carlosfilipe.zup.bootcamp.fase3.mapper.PagPerfilMapper;
import com.carlosfilipe.zup.bootcamp.fase3.mapper.StoryMapper;
import com.carlosfilipe.zup.bootcamp.fase3.model.Foto;
import com.carlosfilipe.zup.bootcamp.fase3.model.PagPerfil;
import com.carlosfilipe.zup.bootcamp.fase3.model.Story;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class FotoDAO {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Foto> getNovoFeed(String nomeUsuario, int inicio, int limite) {
        String sql = "SELECT idFoto, titulo, nomeArquivo, datahora, dataAtualizacao, local, nomeUsuario, "
                + "getFotoPerfil(nomeUsuario) AS fotoPerfil, getNumLike(idFoto) AS numLike, "
                + "getNumComentario(idFoto) AS numComentario, getNome(nomeUsuario) AS nome FROM foto "
                + "LEFT JOIN local ON foto.idLocal = local.idLocal "
                + "WHERE nomeUsuario IN (SELECT nomeUsuario2 FROM segue WHERE nomeUsuario1 = ?) "
                + "ORDER BY datahora DESC LIMIT ?, ?";
        return jdbcTemplate.query(sql, new Object[] { nomeUsuario, inicio, limite }, new FotoMapper());
    }

    public String getNomeArquivoFoto(int idFoto) {
        String sql = "SELECT nomeArquivo FROM foto WHERE idFoto = ?";
        return jdbcTemplate.queryForList(sql, idFoto).get(0).get("nomeArquivo").toString();
    }

    public String getNomeArquivoFotoPerfil(int idFotoPerfil) {
        String sql = "SELECT nomeArquivo FROM fotoPerfil WHERE idFotoPerfil = ? ORDER BY datahora DESC LIMIT 0, 1";
        return jdbcTemplate.queryForList(sql, idFotoPerfil).get(0).get("nomeArquivo").toString();
    }

    public String getNomeArquivoStory(int idStory) {
        String sql = "SELECT nomeArquivo FROM story WHERE idStory = ?";
        return jdbcTemplate.queryForList(sql, idStory).get(0).get("nomeArquivo").toString();
    }

    public List<String> getLikeComNomeUsuario(int idFoto) {
        String sql = "SELECT nomeUsuario FROM `like` WHERE idFoto = ?";
        return jdbcTemplate.queryForList(sql, new Object[] { idFoto }, String.class);
    }

    public String getNomeUsuarioComIdFoto(int idFoto) {
        String sql = "SELECT nomeUsuario FROM foto WHERE idFoto = ?";
        return jdbcTemplate.queryForList(sql, idFoto).get(0).get("nomeUsuario").toString();
    }

    public String getNomeUsuarioComIdComentario(int idComentario) {
        String sql = "SELECT foto.nomeUsuario FROM foto " + "JOIN comentario ON foto.idFoto = comentario.idFoto "
                + "WHERE comentario.idComentario = ?";
        return jdbcTemplate.queryForList(sql, idComentario).get(0).get("nomeUsuario").toString();
    }

    public String getNomeUsuarioComNomeArquivo(String nomeArquivo) {
        String sql = "SELECT nomeUsuario FROM foto WHERE nomeArquivo = ?";
        return jdbcTemplate.queryForList(sql, nomeArquivo).get(0).get("nomeUsuario").toString();
    }

    public String getNomeUsuarioComNomeArquivoFotoPerfil(String nomeArquivo) {
        String sql = "SELECT nomeUsuario FROM fotoPerfil WHERE nomeArquivo = ?";
        return jdbcTemplate.queryForList(sql, nomeArquivo).get(0).get("nomeUsuario").toString();
    }

    public String getNomeUsuarioComNomeArquivoStory(String nomeArquivo) {
        String sql = "SELECT nomeUsuario FROM story WHERE nomeArquivo = ?";
        return jdbcTemplate.queryForList(sql, nomeArquivo).get(0).get("nomeUsuario").toString();
    }

    public static PagPerfil getPerfil(String nomeUsuario, String usuarioAtual) {
        String sql = "SELECT nome, ? AS nomeUsuario, getFotoPerfil(?) AS nomeArquivoFotoPerfil, getNumFoto(?) AS numFoto,"
                + "getNumSeguidor(?) AS numSeguidor, getNumSeguindo(?) AS numSeguindo, checkSegue(?, ?) AS seguindo, "
                + "biografia, website FROM conta LEFT JOIN perfil ON conta.nomeUsuario = perfil.nomeUsuario "
                + "WHERE conta.nomeUsuario = ?";
        List<PagPerfil> result = jdbcTemplate.query(sql, new Object[] { nomeUsuario, nomeUsuario, nomeUsuario,
                nomeUsuario, nomeUsuario, usuarioAtual, nomeUsuario, nomeUsuario }, new PagPerfilMapper());
        if (result.size() == 1)
            return result.get(0);
        else
            return null;
    }

    public List<Foto> getFoto(String nomeUsuario, int inicio, int limite) {
        String sql = "SELECT idFoto, titulo, nomeArquivo, datahora, dataAtualizacao, local, nomeUsuario, "
                + "getFotoPerfil(nomeUsuario) AS getNomeArquivoFotoPerfil, getNumLike(idFoto) AS numLike, "
                + "getNumComentario(idFoto) AS numComentario, getNome(nomeUsuario) AS nome FROM foto "
                + "LEFT JOIN local ON foto.idLocal = local.idLocal "
                + "WHERE nomeUsuario = ? ORDER BY dataAtualizacao DESC LIMIT ?, ?";
        return jdbcTemplate.query(sql, new Object[] { nomeUsuario, inicio, limite }, new FotoMapper());
    }

    public int inserirFoto(Foto foto) {
        String sql = "INSERT INTO foto (titulo, nomeArquivo, idLocal, nomeUsuario) "
                + "VALUES (?, '0', getIdLocal(?), ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] { "idFoto" });
                ps.setString(1, foto.getTitulo());
                ps.setString(2, foto.getLocal());
                ps.setString(3, foto.getNomeUsuario());
                return ps;
            }

        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public void setNomeArquivoFoto(String nomeArquivo, int idFoto) {
        String sql = "UPDATE foto SET nomeArquivo = ? WHERE idFoto = ?";
        jdbcTemplate.update(sql, nomeArquivo, idFoto);
    }

    public List<Story> getStory(String nomeUsuario, int inicio, int limite) {
        String sql = "SELECT idStory, nomeArquivo, datahora, nomeUsuario, "
                + "getFotoPerfil(nomeUsuario) AS nomeArquivoFotoPerfil, getNome(nomeUsuario) AS nome FROM story "
                + "WHERE HOUR(NOW() - datahora) < 24 AND nomeUsuario IN (SELECT nomeUsuario2 FROM segue WHERE nomeUsuario1 = ?) "
                + "ORDER BY datahora DESC LIMIT ?, ?";
        return jdbcTemplate.query(sql, new Object[] { nomeUsuario, inicio, limite }, new StoryMapper());
    }
    
}