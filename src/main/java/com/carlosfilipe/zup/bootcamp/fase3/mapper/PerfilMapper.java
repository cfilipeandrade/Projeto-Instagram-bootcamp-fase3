package com.carlosfilipe.zup.bootcamp.fase3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;

import com.carlosfilipe.zup.bootcamp.fase3.model.Perfil;

public class PerfilMapper implements RowMapper {

    public Perfil mapRow(ResultSet rs, int rowNum) throws SQLException {
		Perfil perfil = new Perfil();
		perfil.setBiografia(rs.getString("biografia"));
		perfil.setTelefone(rs.getString("telefone"));
		perfil.setSexo(rs.getString("sexo"));
		perfil.setWebsite(rs.getString("website"));
		return perfil;
	}

    @Override
    public int[] getRowsForPaths(TreePath[] path) {
        // TODO Auto-generated method stub
        return null;
    }
    
}