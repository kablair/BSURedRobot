package main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import data_storage.ImageWriter;
import data_storage.TileReader;
import exceptions.GameScreenNotFoundException;
import exceptions.InvalidTileException;
import exceptions.ScanningDisabledException;
import frame.FrameMain;
import frame.TileDialog;
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
			BufferedImage image;
			try {
				image = ImageWriter.writeImage(TileReader.loadTile("oakhead"));
				TileDialog dialog = new TileDialog(frame, image);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				tiles = ScannerMain.getScreenTiles();
				
			} catch (InvalidTileException e) {
				e.printStackTrace();
			} catch (ScanningDisabledException e) {
				e.printStackTrace();
			}
		

	}
}
