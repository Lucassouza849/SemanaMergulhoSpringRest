package com.algaworks.algalog.domain.exception;

import lombok.AllArgsConstructor;


public class EntidadeNaoEncontradaException extends NegocioException{

    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }

}


