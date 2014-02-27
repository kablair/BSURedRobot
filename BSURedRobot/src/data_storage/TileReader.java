package data_storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import exceptions.InvalidTileException;
import scanning.ScreenTile;

public class TileReader {
	
	public static int[][] loadTileData(String tileName) throws IOException
	{
		int[][] tileData = new int[ScreenTile.getArraysize()] [ScreenTile.getArraysize()];
		String fileLocation= System.getProperty("user.dir")+"/tileData/"+tileName+".txt";
		File file = new File(fileLocation);
		FileReader fr = new FileReader(file); 
		BufferedReader br = new BufferedReader(fr);
		
		
		for(int y=0; y<ScreenTile.getArraysize(); y++)
		{
			String tileLine = br.readLine();
			String[] values = tileLine.split("\\s");
			for(int x=0; x<ScreenTile.getArraysize(); x++)
			{
				tileData[y][x] =Integer.parseInt(values[x]);
			}
		}
		
	      br.close();
	      fr.close();
	     return tileData;
	      
	}
	
	public static ScreenTile loadTile(String tileName) throws IOException, InvalidTileException
	{
		int[][] tileData = loadTileData(tileName);
		ScreenTile tile = new ScreenTile(tileData);
		return tile;
	      
	}

}
