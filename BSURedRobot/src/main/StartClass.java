package main;

import exceptions.GameScreenNotFoundException;
import exceptions.InvalidTileException;
import exceptions.ScanningDisabledException;
import frame.FrameMain;
import scanning.ScannerMain;
import scanning.ScreenTile;

public class StartClass {

	public static void main(String args[])
	{
		ScreenTile[][] tiles;
			ScannerMain.initialize();
			FrameMain frame = new FrameMain();
			frame.addTextLine("hi");
			frame.addTextLine("hi");
			frame.addTextLine("hi");
			frame.addTextLine("hi");
			frame.addTextLine("hi");
			frame.addTextLine("hi");
			frame.addTextLine("hi");
			frame.addTextLine("hi");
			frame.addTextLine("hi");
			frame.addTextLine("hi");
			frame.addTextLine("hi");
			try {
				tiles = ScannerMain.getScreenTiles();
			} catch (InvalidTileException e) {
				e.printStackTrace();
			} catch (ScanningDisabledException e) {
				e.printStackTrace();
			}
		

	}
}
