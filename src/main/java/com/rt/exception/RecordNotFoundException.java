package com.rt.exception;

public class RecordNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public RecordNotFoundException(Long id){
        super("Nao encontrado cliente id: " + id);
    }
}
