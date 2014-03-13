package tests;

import java.awt.image.BufferedImage;
import java.io.IOException;

import scanning.ScannerMain;
import scanning.ScreenTile;
import data_storage.TileDialog;
import data_storage.TileReader;
import data_storage.TileWriter;
import exceptions.InvalidTileException;
import exceptions.ScanningDisabledException;

public class ScreenTileTest {

	
	public static void testTiles() throws IOException, InvalidTileException, ScanningDisabledException
	{
		ScreenTile tile = ScannerMain.getScreenTile(4, 4);
		ScreenTile[][] tiles = ScannerMain.getScreenTiles();
		System.out.println("Tile Data scanning and conversion is working: " +tiles[4][4].equals(tile));
		TileWriter.writeTile(tile,"testTile");
		ScreenTile tile2 =TileReader.loadTile("testTile");
		System.out.println("Tile Reading and writing is working: "+ tile2.equals(tile));
		ScreenTile tile3= ScannerMain.getScreenTile(0, 0);
		System.out.println("The equals method gives false positives: " + tile3.equals(tile));
		TileDialog dialog =new TileDialog(TileReader.loadTile("testTile"));
		//TileDialog dialog2 =new TileDialog(ScannerMain.getScreenTile(4, 4));
	}

	
}
