package com.carlosfilipe.zup.bootcamp.fase3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;

import com.carlosfilipe.zup.bootcamp.fase3.model.Foto;

public class FotoMapper implements RowMapper {

    public Foto mapRow(ResultSet rs, int rowNum) throws SQLException {
		Foto foto = new Foto();
		foto.setIdFoto(rs.getInt("idFoto"));
		foto.setTitulo(rs.getString("titulo"));
		foto.setNomeArquivo(rs.getString("nomeArquivo"));
		foto.setDatahora(rs.getString("dataHora"));
		foto.setDataAtualizacao(rs.getString("dataAtualizacao"));
		foto.setLocal(rs.getString("local"));
		foto.setNomeUsuario(rs.getString("nomeUsuario"));
		foto.setFotoPerfil(rs.getString("fotoPerfil"));
		foto.setNumLike(rs.getInt("numLike"));
		foto.setNumComentario(rs.getInt("numComentario"));
		foto.setNome(rs.getString("nome"));
		return foto;
	}

    @Override
    public int[] getRowsForPaths(TreePath[] path) {
        // TODO Auto-generated method stub
        return null;
    }
    
}