package exceptions;

public class IsDisabledException extends Exception {

	private static final long serialVersionUID = 7698263131260134453L;

	public IsDisabledException(){
		super("You have tried to use a method that was flagged as disabled");
	}
	
	public IsDisabledException(String message)
	{
		super(message);
	}
}
