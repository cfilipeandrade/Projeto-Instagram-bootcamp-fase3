
package com.carlosfilipe.zup.bootcamp.fase3.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carlosfilipe.zup.bootcamp.fase3.config.Config;
import com.carlosfilipe.zup.bootcamp.fase3.dao.ContaDAO;
import com.carlosfilipe.zup.bootcamp.fase3.model.Conta;
import com.carlosfilipe.zup.bootcamp.fase3.model.Usuario;
import com.jayway.jsonpath.JsonPathException;

import org.springframework.boot.json.JsonParseException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;

@RestController
public class ContaController {

    static ContaDAO contaDAO = Application.context.getBean("ContaDAO", ContaDAO.class);

    @GetMapping("/")
    public String root() {
        return "Bem vindo ao Instagram API";
    }

    @PostMapping("/inscrever-se")
    public Object inscreverSe(HttpServletRequest request, HttpServletResponse response, @RequestBody String body)
            throws IOException, JsonPathException {
        JSONObject json = new JSONObject();
        response.setContentType("application/json");
        int result = 0;
        try {

            result = contaDAO.insert(json.getAsString("nomeUsuario"), json.getAsString("senha"),
                    json.getAsString("email"), json.getAsString("nome"));
        } catch (DuplicateKeyException e) {
            return "Usuario existe";
        }
        if (result == 1) {
            String body1 = new String(Files.readAllBytes(Paths.get(Config.getConfig("mail.verify.path1"))),
                    StandardCharsets.UTF_8);
            String body2 = new String(Files.readAllBytes(Paths.get(Config.getConfig("mail.verify.path2"))),
                    StandardCharsets.UTF_8);
            String verify = contaDAO.getLinkVerificação(json.getAsString("nomeUsuario"));
            String email = contaDAO.getEmail(json.getAsString("nomeUsuario"));
            return "true";
        }

        else
            return "Falha no registro";
    }

    @GetMapping("/verificar/reenviar/{nomeUsuario}")
    public String reenviar(@PathVariable("nomeUsuario") String nomeUsuario) throws IOException {
        String body1 = new String(Files.readAllBytes(Paths.get(Config.getConfig("mail.verify.path1"))),
                StandardCharsets.UTF_8);
        String body2 = new String(Files.readAllBytes(Paths.get(Config.getConfig("mail.verify.path2"))),
                StandardCharsets.UTF_8);
        String verify = contaDAO.getLinkVerificação(nomeUsuario);
        String email = contaDAO.getEmail(nomeUsuario);
        return "Feito";
    }

    @GetMapping("/verificar/{nomeUsuario}/{hash}")
    public String verificar(@PathVariable("nomeUsuario") String nomeUsuario, @PathVariable("hash") String hash) {
        return contaDAO.verificar(nomeUsuario, hash);
    }

    @GetMapping("/conta")
    public Conta conta() {
        String nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
		return contaDAO.getConta(nomeUsuario);
	}

	@PutMapping("/conta/atualizarInfo")
	public String atualizarInfo(HttpServletRequest request, @RequestBody String body) throws IOException, 
            JsonParseException {
		String nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
		JSONObject json = new JSONObject();
		Conta conta = new Conta();
		conta.setNomeUsuario(json.getAsString("nomeUsuario"));
		conta.setEmail(json.getAsString("email"));
		conta.setNome(json.getAsString("nome"));

		if (contaDAO.atualizarInfo(nomeUsuario, conta) == 1)
			return "{\"resultado\":\"sucesso\"}";
		else
			return "{\"resultado\":\"erro\"}";
	}

	@PutMapping("/conta/atualizarSenha")
	public String atualizarSenha(HttpServletRequest request) throws JsonParseException, IOException {
		String nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
		StringBuffer buffer = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		while ((line = reader.readLine()) != null)
			buffer.append(line);
		JSONObject json = new JSONObject();
		if (contaDAO.atualizarSenha(nomeUsuario, json.getAsString("senha")) == 1)
			return "{\"resultado\":\"sucesso\"}";
		else
			return "{\"resultado\":\"erro\"}";
	}


	@GetMapping(value = "/buscar/usuario", params= "buscar")
	public List<Usuario> buscarUsuario(@RequestParam("buscar") String palavraChave) {
		String usuarioAtual = SecurityContextHolder.getContext().getAuthentication().getName();
		return contaDAO.buscarUsuario(palavraChave, usuarioAtual);
	}
	
	@GetMapping("/segue/{username}")
	public String segue(@PathVariable("username") String username) {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		return "{\"follow_status\":\"" + contaDAO.segue(currentUser, username) + "\"}";
	}

    

}