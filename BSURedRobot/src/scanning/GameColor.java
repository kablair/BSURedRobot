package scanning;

import java.awt.Color;

/**
 * GameColors are the colors that appear in the game. All possible Colors are listed
 */
public enum GameColor {

	gameWhite(231, 214, 156), gameGrey(181, 165, 107), gameDarkGrey(123, 115,
			99), gameBlack(57, 57, 41);

	private final Color color;

	private GameColor(int r, int g, int b) {
		this.color = new Color(r, g, b);
	}

	Color getColor() {
		return this.color;
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
}
