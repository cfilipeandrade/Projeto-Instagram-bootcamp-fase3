package com.carlosfilipe.zup.bootcamp.fase3.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.json.JsonException;

import com.carlosfilipe.zup.bootcamp.fase3.config.Config;
import com.carlosfilipe.zup.bootcamp.fase3.dao.FotoDAO;
import com.carlosfilipe.zup.bootcamp.fase3.model.Foto;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    static FotoDAO fotoDAO = Application.context.getBean("FotoDAO", FotoDAO.class);

    @PostMapping("/foto")
    String foto(@RequestParam("arquivo") MultipartFile arquivo, @RequestParam("titulo") String titulo,
            @RequestParam("local") String local) throws JsonException {
		String nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
		Foto foto = new Foto();
		foto.setNomeUsuario(nomeUsuario);
		foto.setTitulo(titulo);
		foto.setLocal(local);

		int idFoto = fotoDAO.inserirFoto(foto);
		String nomeArquivo = String.valueOf(idFoto+ 100) + ".jpg";
		try {
			byte[] bytes = arquivo.getBytes();
			File dir = new File(Config.getConfig("foto.dir"));
			if (!dir.exists())
				dir.mkdirs();
			File serverFile = new File(dir.getAbsolutePath() + File.separator + nomeArquivo);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
			fotoDAO.setNomeArquivoFoto(nomeArquivo, idFoto);
			System.out.println("[ITitan] Upload de foto com sucesso: " + serverFile.getAbsolutePath());
			return "{\"resultado\":\"sucesso\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"resultado\":\"erro\"}";
		}
	}

	@PostMapping("/fotoPerfil")
	String fotoPerfil(@RequestParam("arquivo") MultipartFile file) throws JsonException {
		String nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

		String nomeArquivo = "test.jpg";
		try {
			byte[] bytes = file.getBytes();
			File dir = new File(Config.getConfig("fotoPerfil.dir"));
			if (!dir.exists())
				dir.mkdirs();
			File serverFile = new File(dir.getAbsolutePath() + File.separator + nomeArquivo);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
			System.out.println("[ITitan] Upload de foto do perfil com sucesso: " + serverFile.getAbsolutePath());
			return "{\"resultado\":\"sucesso\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"resultado\":\"erro\"}";
		}
	}

	@PostMapping("/story")
	String story(@RequestParam("arquivo") MultipartFile file) throws JsonException {
		String nomeArquivo = "test.jpg";
		try {
			byte[] bytes = file.getBytes();
			File dir = new File(Config.getConfig("story.dir"));
			if (!dir.exists())
				dir.mkdirs();
			File serverFile = new File(dir.getAbsolutePath() + File.separator + nomeArquivo);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
			System.out.println("[ITitan] Upload de STORY com SUCESSO: " + serverFile.getAbsolutePath());
			return "{\"resultADO\":\"sucesso\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"resultado\":\"erro\"}";
		}
	}

}