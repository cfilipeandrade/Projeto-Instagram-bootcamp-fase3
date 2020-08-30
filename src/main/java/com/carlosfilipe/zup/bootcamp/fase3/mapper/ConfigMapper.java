package com.carlosfilipe.zup.bootcamp.fase3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;

import com.carlosfilipe.zup.bootcamp.fase3.model.Configuracao;

public class ConfigMapper implements RowMapper {
    public Configuracao mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Configuracao config = new Configuracao();
		config.setLingua(rs.getString("lingua"));
		config.setContaPrivada(rs.getBoolean("contaPrivada"));
		return config;
	}

    @Override
    public int[] getRowsForPaths(TreePath[] path) {
        // TODO Auto-generated method stub
        return null;
    }
    
}