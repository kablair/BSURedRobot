package exceptions;

public class ScanningDisabledException extends Exception {

	private static final long serialVersionUID = 7698263131260134453L;

	public ScanningDisabledException(){
		super("You have tried to use a method that was flagged as disabled");
	}
	
	public ScanningDisabledException(String message)
	{
		super(message);
	}
}
