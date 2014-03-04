package data_storage;

import java.awt.image.BufferedImage;

import scanning.ScreenTile;

public class ImageWriter {

	public static BufferedImage writeImage(int[][] pixels, boolean lifeSize)
	{
		BufferedImage image;
		if(lifeSize)
		{
			image= writeBigImage(pixels);
		}
		else 
		{
			image=writeSmallImage(pixels);
		}
		return image;
	}
	
	private static BufferedImage writeSmallImage(int[][] pixels)
	{
		BufferedImage image = new BufferedImage(pixels.length, pixels[0].length, BufferedImage.TYPE_INT_RGB);
		for(int y=0; y<pixels[0].length; y++)
		{
			for(int x=0; x<pixels.length; x++)
			{
						image.setRGB(x, y, pixels[y][x]);
			}
		}
		
		return image;
	}
	
	private static BufferedImage writeBigImage(int[][] pixels)
	{
		int tileMultiplier = ScreenTile.getTilesize()/ScreenTile.getArraysize();
		BufferedImage image = new BufferedImage(pixels.length*tileMultiplier, pixels[0].length*tileMultiplier, BufferedImage.TYPE_INT_RGB);
		for(int y=0; y<pixels[0].length; y++)
		{
			for(int x=0; x<pixels.length; x++)
			{
				for(byte deltaY=0; deltaY<tileMultiplier; deltaY++)
				{
					for(byte deltaX=0; deltaX<tileMultiplier; deltaX++)
					{
						image.setRGB(tileMultiplier*x+deltaX, tileMultiplier*y+deltaY, pixels[y][x]);
					}
				}
						
			}
		}
		
		return image;
	}
	
//	private static Color turnStringToColor(String string)
//	{
//		String hexString= String.format("%06d",string);
//		Color color=Color.decode(hexString);
//		return color;
//	}
}
