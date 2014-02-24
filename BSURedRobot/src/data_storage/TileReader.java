package data_storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import scanning.ScreenTile;

public class TileReader {
	
	public static String[][] loadTile(String tileName) throws IOException
	{
		String[][] tileData = new String[ScreenTile.getTilesize()] [ScreenTile.getTilesize()];
		String fileLocation= System.getProperty("user.dir")+"/tileData/"+tileName+".txt";
		File file = new File(fileLocation);
		FileReader fr = new FileReader(file); 
		BufferedReader br = new BufferedReader(fr);
		
		
		for(int y=0; y<ScreenTile.getTilesize(); y++)
		{
			String tileLine = br.readLine();
			String[] values = tileLine.split("\\s");
			for(int x=0; x<ScreenTile.getTilesize(); x++)
			{
				tileData[y][x] =values[x];
			}
		}
		
	      br.close();
	      fr.close();
	     return tileData;
	      
	}

}
