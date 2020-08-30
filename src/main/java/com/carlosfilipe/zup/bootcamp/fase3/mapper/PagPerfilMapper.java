package com.carlosfilipe.zup.bootcamp.fase3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;

import com.carlosfilipe.zup.bootcamp.fase3.model.PagPerfil;

public class PagPerfilMapper implements RowMapper {

    public PagPerfil mapRow(ResultSet rs, int rowNum) throws SQLException {
		PagPerfil pagPerfil = new PagPerfil();
		pagPerfil.setNomeUsuario(rs.getString("nomeUsuario"));
		pagPerfil.setNome(rs.getString("nome"));
		pagPerfil.setFotoPerfil(rs.getString("fotoPerfil"));
		pagPerfil.setNumFoto(rs.getInt("photo_num"));
		pagPerfil.setNumSegidores(rs.getInt("seguidor"));
		pagPerfil.setNumSeguindo(rs.getInt("seguindo"));
		pagPerfil.setSegue(rs.getBoolean("segue"));
		pagPerfil.setBiografia(rs.getString("biografia"));
		pagPerfil.setWebsite(rs.getString("website"));
		return pagPerfil;
	}

    @Override
    public int[] getRowsForPaths(TreePath[] path) {
        // TODO Auto-generated method stub
        return null;
    }
    
}