package tile_processing;

import java.io.IOException;
import java.util.BitSet;

import data_storage.TileReader;
import exceptions.InvalidGameColorException;
import exceptions.InvalidTileException;
import scanning.GameColor;
import scanning.ScreenTile;

public class TileCompressor {

	
	
	public static void compress(ScreenTile tile) throws IOException, InvalidTileException, InvalidGameColorException
	
	{
		int[][] data = tile.getTileData();
		
		BitSet colorBits;

		//
//		for(int n=0; n<data[5].length; n++)
//		{
//			colorBits.
//			colorInt+=GameColor.getGameColor(data[5][n]).getId();
//			colorInt*=4;
//			System.out.println(colorInt);
//		}
//		colorInt=4^16;
//		System.out.println(colorInt);
//			
		
	}
	//0000
	//0001
	//0010
	//0011
	
	//01
	//02
	//03
//	public static int getGameColor(int[] hexRow)
//	{
//		
//	}
}
