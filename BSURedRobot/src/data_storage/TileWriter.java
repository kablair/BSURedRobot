package data_storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import scanning.ScreenTile;

public class TileWriter {

	private static int count=0;
	private static final String location=System.getProperty("user.dir")+"/tileData/";
	private static final String listLocation= location+"tileList.txt";
	
	
	/**
	 * Sets up tileData folder. <p>
	 * If the tileData folder doesn't exist, creates an empty tileData folder. <p>
	 * If tileList.txt doesn't exist, creates tileList.txt.
	 */
	public static void initialize()
	{
		File file = new File(location);
		if(!file.exists())
			{
				file.mkdirs();
			}
		
		File listFile =new File(listLocation);
		if(!listFile.exists())
		{
			try {
				listFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	/**Writes a tile document for an unnamed tile*/
	public static void writeTile(ScreenTile tile, boolean menu) throws IOException
	{
		
		if(menu)
		{	/**Pops up a menu for the user to enter a name. The user can choose to not write the tile*/
			new TileDialog(tile);
		}
		
		else
		{	
			writeTile(tile, generateFileName());
		}
		
	}
	
	/**Writes a tile Document for a named tile*/
	public static void writeTile(ScreenTile tile, String tileName) throws IOException
	{
		String fileLocation=location+tileName+".txt";
		File file =new File(fileLocation);
		
		if(!file.exists()){
			file.createNewFile();
			recordTileName(file);
	    	recordTileData(file, tile);
		}
		else
			System.out.println("File already exists");
		
	}
	
	/**Records the tile data in a txt file
	 * @throws IOException */
	private static void recordTileData(File file, ScreenTile tile) throws IOException
	{
		//Here true is to append the content to file
    	FileWriter fw = new FileWriter(file,false);
    	//BufferedWriter writer gives better performance
    	BufferedWriter bw = new BufferedWriter(fw);
    	String content="";
    	int[][] tileData=tile.getTileData();
    	for(int y=0; y<tileData.length; y++)
    	{
    		for(int x=0; x<tileData[ScreenTile.getArraysize()-1].length; x++)
    		{
    			content+=tileData[y][x];
    			content+=" ";
    		}
    		content+="\n";
    		bw.write(content);
    		content="";
  
    	}
    	bw.close();
    	fw.close();
	}
	
	
	/**Adds the name of the tile to the tileList.txt
	 * @throws IOException*/
	private static void recordTileName(File file) throws IOException
	{
		File listFile = new File((TileWriter.listLocation));
		FileWriter fw = new FileWriter(listFile, true);
    	BufferedWriter bw = new BufferedWriter(fw);
    	bw.write(file.getName()+"\n");
    	bw.close();
    	fw.close();
	}
	
	/**
	 * @return Returns the name of a file that is not in use.<p> 
	 * This method will add the first available integer to the end of the base.<br>
	 * Integers start at 1.<p>
	 * Example: base1, base12, base100.
	 */
	private static String generateFileName()
	{
		File file;
		String name;
		String base="sample";
		do
		{
			count++;
			name=base+count;
			String fileLocation=System.getProperty("user.dir")+"/tileData/"+name+".txt";
			file =new File(fileLocation);
		}
		while(file.exists());
		
		return name;
	}
}