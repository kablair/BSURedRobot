package exceptions;

public class InvalidTileException extends Exception {

	private static final long serialVersionUID = -3965650078437735393L;

	public InvalidTileException()
	{
		super("Tile is out of Bounds");
		
	}
}
