package determinegamemode;

public enum Mode {
	introMenu, intro, naming, battleMenu, battleFight, battlePokemon, battleItems, world, 
	worldStart, worldPokemon, worldItems, worldTrainerCard, worldSave, worldOptions;
	
	private int leftLine =1;
	private int circleLocations=1;
	//use top left and bottom right
	//If running away would be an option, remember to check to see you can
	
	public static Mode findMode()
	{
		return world;
	}
}
