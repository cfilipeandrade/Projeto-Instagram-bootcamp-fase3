package com.carlosfilipe.zup.bootcamp.fase3.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import com.carlosfilipe.zup.bootcamp.fase3.config.Config;
import com.carlosfilipe.zup.bootcamp.fase3.dao.ContaDAO;
import com.carlosfilipe.zup.bootcamp.fase3.dao.FotoDAO;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dowload")
public class DownloadController {

    static FotoDAO fotoDAO = Application.context.getBean("FotoDAO", FotoDAO.class);
    static ContaDAO contaDAO = Application.context.getBean("ContaDAO", ContaDAO.class);

    @GetMapping("/foto/{nomeArquivo}.{ext}")
    byte[] getFoto(final HttpServletResponse response, @PathVariable("nomeArquivo") String nomeArquivo,
            @PathVariable("ext") final String ext) throws IOException {
        nomeArquivo += "." + ext;
        response.setHeader("Content-Disposition", "attachment; nomeArquivo=\"" + nomeArquivo + ".jpg\"");
        final InputStream inputStream = new FileInputStream(
                Config.getConfig("foto.dir") + File.separator + nomeArquivo);
        final BufferedImage img = ImageIO.read(inputStream);
        final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", byteStream);
        return byteStream.toByteArray();

    }

    @GetMapping("/avatar/{filename}.{ext}")
    byte[] getAvatar(final HttpServletResponse response, @PathVariable("nomeArquivo") String nomeArquivo,
            @PathVariable("ext") final String ext) throws IOException {
        nomeArquivo += "." + ext;
        response.setHeader("Conteudo-Disposicao", "attachment; nomeArquivo=\"" + nomeArquivo + ".jpg\"");
        final InputStream inputStream = new FileInputStream(
                Config.getConfig("avatar.dir") + File.separator + nomeArquivo);
        final BufferedImage img = ImageIO.read(inputStream);
        final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", byteStream);
        return byteStream.toByteArray();
    }

    @GetMapping("/story/{filename}.{ext}")
    byte[] getStory(final HttpServletResponse response, @PathVariable("story_id") String nomeArquivo,
            @PathVariable("ext") final String ext) throws IOException {
        nomeArquivo += "." + ext;
        final String nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        if (contaDAO.checkPrivilegio(nomeUsuario, fotoDAO.getNomeUsuarioComNomeArquivoStory(nomeArquivo))) {
            response.setHeader("Content-Disposition", "attachment; nomeArquivo=\"" + nomeArquivo + ".jpg\"");
            final InputStream inputStream = new FileInputStream(
                    Config.getConfig("story.dir") + File.separator + nomeArquivo);
            final BufferedImageDevice img = ImageIO.read(inputStream);
            final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			ImageIO.write(img, "jpg", byteStream);
			return byteStream.toByteArray();
		} else
            return null;
            
    }
}
    
