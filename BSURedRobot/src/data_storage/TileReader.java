package data_storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import exceptions.InvalidTileException;
import scanning.ScreenTile;

public class TileReader {
	
	public static ArrayList<String> tileNameList = new ArrayList<String>();
	
	
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
	
	public static int[][][] loadAllTileData() throws IOException
	{
		loadTileNameList();
		int[][][] tileDataList = new int[tileNameList.size()][ScreenTile.getArraysize()] [ScreenTile.getArraysize()];
		int count =0;
		while(count<tileNameList.size())
		{
			tileDataList[count] = loadTileData(tileNameList.get(count));
		}
		
		return tileDataList;
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
