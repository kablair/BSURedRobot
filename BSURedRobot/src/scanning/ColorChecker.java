package scanning;

import java.awt.Color;

public class ColorChecker {

	/**
	 * Sees if the color exists in the game. Used to check if a pixel is part of
	 * the game
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

}
