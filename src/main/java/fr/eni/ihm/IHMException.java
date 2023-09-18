package fr.eni.ihm;

public class IHMException extends Exception {

	private String message;

	public IHMException(String message) {
		super(message);
		this.message = message;
	}
	
	
}
