package com.carlosfilipe.zup.bootcamp.fase3.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;

import com.carlosfilipe.zup.bootcamp.fase3.model.Usuario;

public class UsuarioMapper implements RowMapper {

    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
        Usuario conta = new Usuario();
        conta.setNomeUsuario(rs.getString("nomeUsuario"));
        conta.setNome(rs.getString("nome"));
        conta.setEmail(rs.getString("email"));
        conta.setTelefone(rs.getString("telefone"));
        conta.setFotoPerfil(rs.getString("fotoPerfil"));
        return conta;

    }

    @Override
    public int[] getRowsForPaths(TreePath[] path) {
        // TODO Auto-generated method stub
        return null;
    }
}