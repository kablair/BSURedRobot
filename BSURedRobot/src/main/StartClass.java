package main;

import java.io.IOException;

import maze.MazeFrame;
import maze.MazeMain;
import data_storage.TileDialog;
import data_storage.TileReader;
import data_storage.TileWriter;
import exceptions.InvalidGameColorException;
import exceptions.InvalidTileException;
import exceptions.ScanningDisabledException;
import frame.FrameMain;
import scanning.ScannerMain;
import scanning.ScreenReader;
import scanning.ScreenTile;
import tests.ScreenTileTest;
import tile_processing.TileLoader;
import tile_processing.TileProcessingMain;
import world_tiles.WorldTilesMain;

public class StartClass {

	
	private static void initialize()
	{
		TileWriter.initialize();
		ScannerMain.initialize();
		WorldTilesMain.initialize();
		MazeMain.initialize();
	}
	
	
	
	
	public static void main(String args[])
	{
		StartClass.initialize();

		try {
			FrameMain frame = new FrameMain();
			MazeMain.openMazeFrame();
			//TileDialog dialog = new TileDialog(TileReader.loadTile("grass"));
			//TileLoader.loadScreen();
			ScreenTile tile = ScannerMain.getScreenTile(1, 1);
			ScreenTile tile2 = ScannerMain.getScreenTile(2, 3);
			//TileWriter.writeTile(tile,true);
			//TileWriter.writeTile(tile2,true);
			WorldTilesMain.addNewTiles();
			//FrameMain frame = new FrameMain();
			//MazeMain.openMazeFrame();
			//for(int y=0; y<ScannerMain.gameHeight; y++)
			//{
			//	for(int x=0; x<ScannerMain.gameWidth; x++)
				//{
					//ScreenTile tile = ScannerMain.getScreenTile(1, 5);
					//TileWriter.writeTile(tile, false);
					//ScreenTile tile2 = ScannerMain.getScreenTile(1, 6);
					//TileWriter.writeTile(tile2, true);
				//	WorldTilesMain.addNewTile(tile);
			//	}
			//}
			
			TileProcessingMain.checkTile(tile2);
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidTileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ScanningDisabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


	}
}
