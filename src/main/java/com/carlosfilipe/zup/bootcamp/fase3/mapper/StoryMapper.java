package com.carlosfilipe.zup.bootcamp.fase3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;

import com.carlosfilipe.zup.bootcamp.fase3.model.Story;

public class StoryMapper implements RowMapper {

    public Story mapRow(ResultSet rs, int rowNum) throws SQLException {
		Story story = new Story();
		story.setIdStory(rs.getString("idStory"));
		story.setNomeUsuario(rs.getString("nomeUsuario"));
		story.setFotoPerfil(rs.getString("fotoPerfil"));
		story.setDataHora(rs.getString("dataHora"));
		story.setNomeArquivo(rs.getString("nomeArquivo"));
		story.setNome(rs.getString("nome"));
        return story;
    }

    @Override
    public int[] getRowsForPaths(TreePath[] path) {
        // TODO Auto-generated method stub
        return null;
    }


    
}