package data_storage;

import java.awt.image.BufferedImage;

public class ImageWriter {

	public static BufferedImage writeImage(String[][] pixels)
	{
		
		BufferedImage image = new BufferedImage(pixels.length, pixels[0].length, BufferedImage.TYPE_INT_RGB);
		for(int y=0; y<pixels[0].length; y++)
		{
			for(int x=0; x<pixels.length; x++)
			{
				image.setRGB(x, y, Integer.parseInt(pixels[y][x]));
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
