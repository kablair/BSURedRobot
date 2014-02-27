package main;

import java.io.IOException;

import data_storage.TileReader;
import data_storage.TileWriter;
import exceptions.InvalidTileException;
import exceptions.ScanningDisabledException;
import scanning.ScannerMain;
import scanning.ScreenTile;
import tile_processing.TileCompressor;

public class StartClass {

	public static void main(String args[])
	{
		ScannerMain.initialize();
		try {
			ScreenTile tile = ScannerMain.getScreenTile(5, 6);
			TileWriter.writeTile(tile, "sample");
			System.out.println(TileReader.loadTileData("sample"));
			TileCompressor.compress(tile);
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
