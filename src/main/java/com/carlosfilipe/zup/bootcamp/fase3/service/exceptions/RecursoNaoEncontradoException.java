package com.carlosfilipe.zup.bootcamp.fase3.service.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RecursoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public RecursoNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
    
}