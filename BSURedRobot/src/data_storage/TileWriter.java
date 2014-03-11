package data_storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import scanning.ScreenTile;

public class TileWriter {

	private static int count=0;

	public static void init()
	{
		String fileLocation=System.getProperty("user.dir")+"/tileData/";
		File file = new File(fileLocation);
		if(!file.exists())
			{
				file.mkdirs();
			}
	}
	
	
	
	public static void writeTile(ScreenTile tile, boolean menu) throws IOException
	{
		String name="";
		if(menu)
		{
			new TileDialog(tile.getImage());
			
		}
	
		else
		{	
			String base ="sample";
			name = base;
			File file;
			do
			{
				count++;
				name=base+count;
				String fileLocation=System.getProperty("user.dir")+"/tileData/"+name+".txt";
				file =new File(fileLocation);
			}
			while(file.exists());
			
			writeTile(tile, name);

		}
		
	}
	
	
	public static void writeTile(ScreenTile tile, String tileName) throws IOException
	{
		
		String fileLocation=System.getProperty("user.dir")+"/tileData/"+tileName+".txt";
		String tileListLocation=System.getProperty("user.dir")+"/tileData/tileList.txt";
		int[][] tileData=tile.getTileData();
		File file =new File(fileLocation);
		File listFile =new File(tileListLocation);
		if(!listFile.exists())
		{
			listFile.createNewFile();
		}
		if(!file.exists()){
			file.createNewFile();
			//Here true is to append the content to file
	    	FileWriter fw = new FileWriter(file,false);
	    	//BufferedWriter writer give better performance
	    	BufferedWriter bw = new BufferedWriter(fw);
	    	String content="";
	    	
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
	    	
	    	FileWriter fw2 = new FileWriter(listFile, true);
	    	BufferedWriter bw2 = new BufferedWriter(fw2);
	    	bw2.write(tileName+"\n");
	    	bw2.close();
	    	fw2.close();
		}
		else
			System.out.println("File already exists");
		
	}

}