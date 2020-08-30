package com.carlosfilipe.zup.bootcamp.fase3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;

import com.carlosfilipe.zup.bootcamp.fase3.model.Comentario;

public class ComentarioMapper implements RowMapper {

    public Comentario mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comentario comentario = new Comentario(null);
        comentario.setIdComentario(rs.getInt("comentario"));
        comentario.setConteudo(rs.getString("conteudo"));
        comentario.setDataHora(rs.getString("dataHora"));
        comentario.setNomeUsuario(rs.getString("nomeUsuario"));
        comentario.setFotoPerfil(rs.getString("fotoPerfil"));
        comentario.setNumResposta(rs.getInt("numResposta"));
        return comentario;
    }

    @Override
    public int[] getRowsForPaths(TreePath[] path) {
        // TODO Auto-generated method stub
        return null;
    }
    
}