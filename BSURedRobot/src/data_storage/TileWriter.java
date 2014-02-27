package data_storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import scanning.ScreenTile;

public class TileWriter {

	public static void writeTile(ScreenTile tile, String tileName) throws IOException
	{
		
		String fileLocation=System.getProperty("user.dir")+"/tileData/"+tileName+".txt";
		int[][] tileData=tile.getTileData();
		File file =new File(fileLocation);
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
		}
		else
			System.out.println("File already exists");
		
	}

}