package com.example.helping.domain.exception;

public class NegocioException extends RuntimeException {
	
	private static long seriaVersionUID = 1L;
	
	public NegocioException(String message) {		
		super(message);
	}

}
