package com.carlosfilipe.zup.bootcamp.fase3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;

import com.carlosfilipe.zup.bootcamp.fase3.model.Resposta;

public class RespostaMapper implements RowMapper {

    public Resposta mapRow(ResultSet rs, int rowNum) throws SQLException {
		Resposta resposta = new Resposta();
	    resposta.setIdResposta(rs.getString("idResposta"));
		resposta.setConteudo(rs.getString("conteudo"));
		resposta.setDataHora(rs.getString("dataHora"));
		resposta.setNomeUsuario(rs.getString("nomeUsuario"));
		resposta.setFotoPerfil(rs.getString("fotoPerfil"));
		return resposta;
	}


    @Override
    public int[] getRowsForPaths(TreePath[] path) {
        // TODO Auto-generated method stub
        return null;
    }


    
}