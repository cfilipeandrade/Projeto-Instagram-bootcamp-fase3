package com.carlosfilipe.zup.bootcamp.fase3.controller;

import java.util.List;

import com.carlosfilipe.zup.bootcamp.fase3.dao.ComentarioDAO;
import com.carlosfilipe.zup.bootcamp.fase3.dao.ContaDAO;
import com.carlosfilipe.zup.bootcamp.fase3.dao.FotoDAO;
import com.carlosfilipe.zup.bootcamp.fase3.dao.RespostaDAO;
import com.carlosfilipe.zup.bootcamp.fase3.model.Comentario;
import com.carlosfilipe.zup.bootcamp.fase3.model.Foto;
import com.carlosfilipe.zup.bootcamp.fase3.model.Resposta;
import com.carlosfilipe.zup.bootcamp.fase3.model.Story;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FotoController {

    static FotoDAO fotoDAO = Application.context.getBean("FotoDAO", FotoDAO.class);
	static ComentarioDAO comentarioDAO = Application.context.getBean("ComentarioDAO", ComentarioDAO.class);
	static RespostaDAO respostaDAO = Application.context.getBean("RespostaDAO", RespostaDAO.class);
	static ContaDAO contaDAO = Application.context.getBean("ContaDAO", ContaDAO.class);

    @GetMapping("/novofeed/{inicio}/{limite}")
	public List<Foto> getNovoFeed(@PathVariable("inicio") final int inicio, @PathVariable("limite") final int limite) {
        final String nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        final List<Foto> foto = fotoDAO.getNovoFeed(nomeUsuario, inicio, limite);
        for (final Foto f : foto) {
            final List<Comentario> comentario = comentarioDAO.getComentario(f.getIdFoto(), 0, 5);
            f.setComentario(comentario);
        }
        return foto;
    }

    @GetMapping("/novofeed/tudo")
    public List<Foto> getTudo() {
        final String nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        final List<Foto> foto = fotoDAO.getNovoFeed(nomeUsuario, 0, 1000);
        for (final Foto f : foto) {
            final List<Comentario> comentario = comentarioDAO.getComentario(f.getIdFoto(), 0, 1000);
            f.setComentario(comentario);
            for (final Comentario c : comentario) {
                c.setResposta(respostaDAO.getResposta(c.getIdComentario(), 0, 1000));
            }
        }
        return foto;
    }

    @GetMapping("/comentario/{idFoto}/{inicio}/{limite}")
    public List<Comentario> getComentario(@PathVariable("idFoto") final int idFoto,
            @PathVariable("inicio") final int inicio, @PathVariable("limite") final int limite) {
        final String nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        if (contaDAO.checkPrivilegio(nomeUsuario, fotoDAO.getNomeUsuarioComIdFoto(idFoto))) {
			List<Comentario> comentario = comentarioDAO.getComentario(idFoto, inicio, limite);
			for (Comentario c : comentario) {
				List<Resposta> resposta = respostaDAO.getResposta(c.getIdComentario(), 0, 5);
				c.setResposta(resposta);
			}
            return comentario;
        } else
            return null;
    }

    @GetMapping("/resposta/{idComentario}/{inicio}/{limite}")
    public List<Resposta> getResposta(@PathVariable("idComentario") final int idComentario,
            @PathVariable("inicio") final int inicio, @PathVariable("limite") final int limite) {
        final String nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        if (contaDAO.checkPrivilegio(nomeUsuario, fotoDAO.getNomeUsuarioComIdComentario(idComentario)))
            return respostaDAO.getResposta(idComentario, inicio, limite);
        else
            return null;
    }

    @GetMapping("/like/{idFoto}")
    public List<String> getLikecomNomeUsuario(@PathVariable("IdFoto") final int IdFoto) {
        final String nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        if (contaDAO.checkPrivilegio(nomeUsuario, fotoDAO.getNomeUsuarioComIdFoto(IdFoto)))
            return fotoDAO.getLikeComNomeUsuario(IdFoto);
        else
            return null;
    }

    @GetMapping("/story/{inicio}/{limite}")
    public List<Story> getStory(@PathVariable("inicio") final int inicio, @PathVariable("limite") final int limite) {
        final String nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
		return fotoDAO.getStory(nomeUsuario, inicio, limite);
	}
    
}