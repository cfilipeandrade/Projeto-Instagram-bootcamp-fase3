package com.carlosfilipe.zup.bootcamp.fase3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;

import com.carlosfilipe.zup.bootcamp.fase3.model.Conta;

public class ContaMapper implements RowMapper{

    public Conta mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Conta conta = new Conta();
        conta.setNomeUsuario(rs.getString("nomeUsuario"));
        conta.setSenha(rs.getString("senha"));
        conta.setEmail(rs.getString("email"));
        conta.setAtivo(rs.getBoolean("active"));
        conta.setInativo(rs.getBoolean("lock"));
        conta.setDataAtualizacao(rs.getDate("dataAtualizacao"));
        conta.setDataCadastro(rs.getDate("dataCadastro"));
        conta.setNome(rs.getString("nome"));
        return conta;
    }

    @Override
    public int[] getRowsForPaths(final TreePath[] path) {
        // TODO Auto-generated method stub
        return null;
    }
    
}