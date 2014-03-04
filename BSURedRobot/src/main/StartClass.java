package main;

import java.io.IOException;

import maze.MazeFrame;
import maze.MazeMain;
import data_storage.TileReader;
import data_storage.TileWriter;
import exceptions.InvalidGameColorException;
import exceptions.InvalidTileException;
import exceptions.ScanningDisabledException;
import frame.FrameMain;
import scanning.ScannerMain;
import scanning.ScreenTile;
import tests.ScreenTileTest;
import tile_processing.TileCompressor;
import tile_processing.TileProcessingMain;

public class StartClass {

	
	public static void main(String args[])
	{
		ScannerMain.initialize();

		try {
			ScreenTile tile = ScannerMain.getScreenTile(5, 5);
			TileWriter.writeTile(tile);
			ScreenTileTest.testTiles();
			System.out.println(TileReader.loadTileData("sample1"));
			tile = new ScreenTile(TileReader.loadTileData("sample1"));
			//FrameMain frame = new FrameMain();
			//MazeMain.openMazeFrame();
			TileProcessingMain.checkTile();
	
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
