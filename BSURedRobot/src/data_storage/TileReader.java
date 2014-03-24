package data_storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import exceptions.InvalidTileException;
import scanning.ScreenTile;
import world_tiles.WorldTile;
import world_tiles.WorldTilesMain;

public class TileReader {
	
	 private static ArrayList<String> tileNameList = new ArrayList<String>();
	
	
	private static void loadTileNameList() throws IOException
	{
		String fileLocation= System.getProperty("user.dir")+"/tileData/tileList.txt";
		
		try {
			File file = new File(fileLocation);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String tileNameLine;
			while( (tileNameLine = br.readLine())!= null)
			{	
				tileNameList.add(tileNameLine);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	public static ArrayList<ScreenTile> loadAllTileData() throws IOException, InvalidTileException
	{
		loadTileNameList();
		ArrayList<ScreenTile> tileDataList = new ArrayList<ScreenTile>();
		for(int count =0;count<tileNameList.size(); count++)
		{
			int tileData[][] = loadTileData(tileNameList.get(count));
			ScreenTile tile = new ScreenTile(tileData);
			tileDataList.add(tile);
		}
		
		return tileDataList;
	}
	
	public static void loadWorldTileData() throws IOException, InvalidTileException
	{
		loadTileNameList();
		for(int count =0;count<tileNameList.size(); count++)
		{
			String name =tileNameList.get(count);
			int tileData[][] = loadTileData(name);
			ScreenTile tile = new ScreenTile(tileData);
			WorldTile worldTile =new WorldTile(tile);
			worldTile.identify(name);
			WorldTilesMain.worldTiles.add(worldTile);
		}
		
		
	}
	
	
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
