package fr.eni.dal;

public class DALException extends Exception{

	private String message;

	public DALException(String message) {
		super(message);
		this.message = message;
	}
	
	
	
}
