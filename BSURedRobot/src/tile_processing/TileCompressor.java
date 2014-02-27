package tile_processing;

import java.io.IOException;

import data_storage.TileReader;
import exceptions.InvalidGameColorException;
import exceptions.InvalidTileException;
import scanning.GameColor;
import scanning.ScreenTile;

public class TileCompressor {

	
	
	public static void compress(ScreenTile tile) throws IOException, InvalidTileException
	
	{
		int[][] data = tile.getTileData();
		
		int colorInt=0;

		try {
			System.out.println(GameColor.getGameColor(data[5][5]));
		} catch (NumberFormatException | InvalidGameColorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public static int getGameColor(int[] hexRow)
//	{
//		
//	}
}
