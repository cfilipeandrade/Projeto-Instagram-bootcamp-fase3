package com.carlosfilipe.zup.bootcamp.fase3.service;

import java.util.List;
import java.util.Map;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import com.carlosfilipe.zup.bootcamp.fase3.config.Config;
import com.carlosfilipe.zup.bootcamp.fase3.controller.Application;
import com.carlosfilipe.zup.bootcamp.fase3.dao.ContaDAO;
import com.carlosfilipe.zup.bootcamp.fase3.dao.FotoDAO;
import com.carlosfilipe.zup.bootcamp.fase3.model.Conta;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public class teste {

    static ContaDAO contaDAO = Application.context.getBean("ContaDAO", ContaDAO.class);
	static FotoDAO fotoDAO = Application.context.getBean("FotoDAO", FotoDAO.class);
	
	@GetMapping("/userlist")
	public List<Conta> test() {
		return contaDAO.getTudo();
	}

	@GetMapping("/atual")
	public String atual() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	@PostMapping("/teste/{nomeUsuario1}/{nomeUsuario2}")
	public Map<String, Object> request(@PathVariable("nomeUsuario1") String nomeUsuario1, @PathVariable("nomeUsuario2") String nomeUsuario2) {
		return null;
	}

	@PostMapping("/upload")
	Object uploadFileHandler(@RequestParam("file") MultipartFile file) {
		String filename = "test.jpg";
		try {
			byte[] bytes = file.getBytes();
			File dir = new File(Config.getConfig("foto.dir"));
			if (!dir.exists())
				dir.mkdirs();
			File serverFile = new File(dir.getAbsolutePath() + File.separator + filename);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
			System.out.println("[ITitan] Upload de fot com sucesso: " + serverFile.getAbsolutePath());
			return "{\"resultado\":\"sucesso\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"resultado\":\"erro\"}";
		}
	}
}