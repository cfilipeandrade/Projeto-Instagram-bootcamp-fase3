package com.carlosfilipe.zup.bootcamp.fase3.dao;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.security.auth.callback.PasswordCallback;
import javax.sql.DataSource;

import com.carlosfilipe.zup.bootcamp.fase3.mapper.ContaMapper;
import com.carlosfilipe.zup.bootcamp.fase3.config.Config;
import com.carlosfilipe.zup.bootcamp.fase3.mapper.UsuarioMapper;
import com.carlosfilipe.zup.bootcamp.fase3.model.Configuracao;
import com.carlosfilipe.zup.bootcamp.fase3.model.Conta;
import com.carlosfilipe.zup.bootcamp.fase3.model.Usuario;

import org.apache.catalina.startup.PasswdUserDatabase;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jndi.JndiTemplate;

import sun.security.util.Password;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class ContaDAO {

	@SuppressWarnings("naousado")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Conta> getTudo() {
		String query = "SELECT * from conta";
		List<Conta> empList = jdbcTemplate.query(query, new ContaMapper());
		return empList;

	}

	public int insert(String nomeUsuario, String senha, String email, String nome) {
		String sql = "INSERT INTO account VALUES (?, ?, ?, 0, 0, NOW(), NOW(), ?, ?)";
		return jdbcTemplate.update(sql, nomeUsuario, senha, email, nome);
	}

	public Conta getConta(String nomeUsuario) {
		String sql = "SELECT * FROM conta WHERE nomeUsuario = ?";
		return jdbcTemplate.query(sql, new Object[] { nomeUsuario }, new ContaMapper()).get(0);
	}

	public int atualizarInfo(String nomeUsuario, Conta conta) {
		String sql = "UPDATE conta SET nomeUsuario = ?, email = ?, dataAtualizacao = NOW(), nome = ? "
				+ "WHERE nomeUsuario = ?";
		return jdbcTemplate.update(sql, conta.getNomeUsuario(), conta.getEmail(), conta.getNome(), nomeUsuario);
	}

	public int atualizarSenha(String nomeUsuario, String senha) {
		String sql = "UPDATE conta SET senha = ? WHERE nomeUsuario = ?";
		return jdbcTemplate.update(sql, senha, nomeUsuario);
	}

	public boolean checkPrivilegio(String nomeUsuario1, String nomeUsuario2) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("checkFotoUsuario");
		SqlParameterSource in = new MapSqlParameterSource().addValue("nomeUsuario1", nomeUsuario1).addValue("nomeUsuario2", nomeUsuario2);
		Map<String, Object> out = jdbcCall.execute(in);
		return Boolean.valueOf(out.get("output").toString());
	}

	public boolean checkBlock(String nomeUsuario1, String nomeUsuario2) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("checkBlock");
		SqlParameterSource in = new MapSqlParameterSource().addValue("nomeUsuario1", nomeUsuario1).addValue("nomeUsuario2", nomeUsuario2);
		Map<String, Object> out = jdbcCall.execute(in);
		return Boolean.valueOf(out.get("output").toString());
	}

	public String checkAtivo(String nomeUsuario) {
		String sql = "SELECT ativo FROM conta WHERE nomeUsuario = ?";
		return jdbcTemplate.queryForList(sql, nomeUsuario).get(0).get("ativo").toString();
	}

	public boolean checkInativo(String nomeUsuario) {
		String sql = "SELECT `inativo` FROM conta WHERE nomeUsuario = ?";
		return Boolean.valueOf(jdbcTemplate.queryForList(sql, nomeUsuario).get(0).get("inativo").toString());
	}

	public String verificar(String nomeUsuario, String hash) {
		String sql = "SELECT COUNT(*) AS cont FROM conta WHERE nomeUsuario = ? AND verificar = ?";
		int cont = Integer.valueOf(jdbcTemplate.queryForList(sql, nomeUsuario, hash).get(0).get("cont").toString());
		if (cont == 1) {
			String sql2 = "UPDATE conta SET ativo = 1 WHERE nomeUsuario = ?";
			int row = jdbcTemplate.update(sql2, nomeUsuario);
			if (row == 1)
				return "Verificação feita";
		}
		return "Verificação não feita";
	}

	public String getLinkVerificação(String nomeUsuario) {
		String sql = "SELECT verify-code FROM conta WHERE username = ?";
		Map<String, Object> result = jdbcTemplate.queryForList(sql, nomeUsuario).get(0);
		return Configuracao.getConfiguracao("hostname") + "/verificar/" + nomeUsuario + "/" + result.get("verify-code");
	}

	public String getEmail(String nomeUsuario) {
		String sql = "SELECT email FROM conta WHERE nomeUsuario = ?";
		return jdbcTemplate.queryForList(sql, nomeUsuario).get(0).get("email").toString();
	}

	public List<Usuario> buscarUsuario(String palavraChave, String usuarioAtual) {
		String sql = "SELECT conta.nomeUsuario, email, nome, "
				+ "getPerfil(conta.nomeUsuario) AS fotoPerfil, telefone "
				+ "FROM conta LEFT JOIN perfil ON conta.nomeUsuario = perfil.nomeUsuario "
				+ "WHERE (NOT checkInativo(conta.nomeUsuario, ?)) AND (conta.nomeUsuario LIKE '%" + palavraChave + "%' OR "
				+ "email LIKE '%" + palavraChave + "%' OR " + "telefone LIKE '%" + palavraChave + "%' OR nome LIKE '%"
				+ palavraChave + "%') LIMIT 0, 20";
		return jdbcTemplate.query(sql, new Object[] { usuarioAtual }, new UsuarioMapper());
	}

	public Boolean segue(String usuarioAtual, String nomeUsuario) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("definirSeguir");
		SqlParameterSource in = new MapSqlParameterSource().addValue("usuario1", usuarioAtual).addValue("usuario2", nomeUsuario);
		Map<String, Object> out = jdbcCall.execute(in);
		return Boolean.valueOf(out.get("output").toString());
	}


    
}