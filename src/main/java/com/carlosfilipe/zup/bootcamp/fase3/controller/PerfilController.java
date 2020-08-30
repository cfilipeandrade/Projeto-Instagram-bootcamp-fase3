package com.carlosfilipe.zup.bootcamp.fase3.controller;

import com.carlosfilipe.zup.bootcamp.fase3.dao.ContaDAO;
import com.carlosfilipe.zup.bootcamp.fase3.dao.FotoDAO;
import com.carlosfilipe.zup.bootcamp.fase3.model.PagPerfil;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PerfilController {

    static FotoDAO fotoDAO = Application.context.getBean("FotoDAO", FotoDAO.class);
	static ContaDAO contaDAO = Application.context.getBean("ContaDAO", ContaDAO.class);

	@GetMapping("/perfil/{nomeUsuario}")
	public PagPerfil getUserPage(@PathVariable("nomeUsuario") String nomeUsuario) {
		String usuarioAtual = SecurityContextHolder.getContext().getAuthentication().getName();
		if (contaDAO.checkBlock(nomeUsuario, usuarioAtual))
			return null;
		else {
			PagPerfil pagPerfil = FotoDAO.getPerfil(nomeUsuario, usuarioAtual);
			return pagPerfil;
		}
	}

	@GetMapping("/foto/{nomeUsuario}/{inicio}/{limite}")
	public Object getFoto(@PathVariable("nomeUsuario") String nomeUsuario, @PathVariable("inicio") int inicio, @PathVariable("limite") int limite) {
		String usuarioAtual = SecurityContextHolder.getContext().getAuthentication().getName();
		if (contaDAO.checkPrivilegio(usuarioAtual, nomeUsuario))
			return fotoDAO.getFoto(nomeUsuario, inicio, limite);
		else {
			return "Conta privada";
		}
	}
    
}