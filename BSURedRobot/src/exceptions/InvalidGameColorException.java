package exceptions;

public class InvalidGameColorException extends Exception {

	private static final long serialVersionUID = -203865673040612562L;

	public InvalidGameColorException()
	{
		super("Invalid game color");
	}
	
	public InvalidGameColorException(String message)
	{
		super(message);
	}
}
