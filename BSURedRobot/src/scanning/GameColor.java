package scanning;

import java.awt.Color;

import exceptions.InvalidGameColorException;

/**
 * GameColors are the colors that appear in the game. All possible Colors are listed
 */
public enum GameColor {

	gameWhite(231, 214, 156), gameGrey(181, 165, 107), gameDarkGrey(123, 115,
			99), gameBlack(57, 57, 41);

	private final Color color;
	private final int rgb;
	private final byte id;
	
	private GameColor(int r, int g, int b) {
		this.color = new Color(r, g, b);
		this.rgb= this.color.getRGB();
		this.id=(byte) this.ordinal();
		System.out.println(this);
	}

	Color getColor() {
		return this.color;
	}
	
	public int getRGB()
	{
		return rgb;
	}
	
	public byte getId()
	{
		return id;
	}
	/**
	 * Sees if the color is the same color as a GameColor 
	 */
	public static boolean isGameColor(Color c) {
		boolean isGameColor = false;

		for (GameColor gameColor : GameColor.values()) {
			if (c.equals(gameColor.getColor())) // don't use ==
			{
				isGameColor = true;
			}
		}
		return isGameColor;
	}
	
	public static GameColor getGameColor(int rgb) throws InvalidGameColorException
	{
		for(GameColor gameColor:GameColor.values())
		{
			if(rgb==gameColor.getRGB())
			return gameColor;
		}
			throw new InvalidGameColorException();
	}
	
	public static GameColor getGameColor(Color color) throws InvalidGameColorException
	{
		for(GameColor gameColor:GameColor.values())
		{
			if(gameColor.equals(color))
			return gameColor;
		}
			throw new InvalidGameColorException();
	}
	

	/**
	 * You can use the equals method on a Color but you can not use the Color's
	 * equals method on a GameColor
	 */
	public boolean equals(Color color) {
		Color thisColor = this.getColor();
		if (color.equals(thisColor)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean equals(GameColor gameColor) {
		Color otherColor = gameColor.getColor();
		Color thisColor = this.getColor();

		if (otherColor.equals(thisColor)) {
			return true;
		} else {
			return false;
		}

	}
	
	@Override
	public String toString()
	{
		String string=id+": "+this.name()+" "+ this.getRGB();

		return string;
		
	}
}
