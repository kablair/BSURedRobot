package data_storage;

import java.awt.image.BufferedImage;

public class ImageWriter {

	public static BufferedImage writeImage(int[][] pixels)
	{
		
		BufferedImage image = new BufferedImage(pixels.length, pixels[0].length, BufferedImage.TYPE_INT_RGB);
		for(int y=0; y<pixels[0].length; y++)
		{
			for(int x=0; x<pixels.length; x++)
			{
				//for(int deltaY=0; deltaY<4; deltaY++)
				{
					//for(int deltaX=0; deltaX<4; deltaX++)
					{
						image.setRGB(x, y, pixels[y][x]);
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
