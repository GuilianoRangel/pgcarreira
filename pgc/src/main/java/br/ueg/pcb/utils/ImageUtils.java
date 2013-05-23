package br.ueg.pcb.utils;
import java.awt.Graphics2D;

import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class ImageUtils {
	public static final String IMAGE_JPG = "jpg";
	public static InputStream scaleImage2(InputStream p_image, int p_width,
			int p_height) throws Exception {		
		ByteArrayInputStream bis = new ByteArrayInputStream(ImageUtils.scaleImage(p_image, p_width, p_height));
		return bis;
	}
	public static byte[] scaleImage(org.zkoss.image.Image p_image, int p_width, int p_height) throws Exception{
		InputStream ie = p_image.getStreamData();
		return ImageUtils.scaleImage(ie, p_width, p_height);
	}
	
	public static byte[] scaleImage(InputStream p_image, int p_width, int p_height) throws Exception{
		InputStream imageStream = new BufferedInputStream(p_image);
		Image image = (Image) ImageIO.read(imageStream);

		int thumbWidth = p_width;
		int thumbHeight = p_height;

		// Make sure the aspect ratio is maintained, so the image is not skewed
		double thumbRatio = (double) thumbWidth / (double) thumbHeight;
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);
		double imageRatio = (double) imageWidth / (double) imageHeight;
		if (thumbRatio < imageRatio) {
			thumbHeight = (int) (thumbWidth / imageRatio);
		} else {
			thumbWidth = (int) (thumbHeight * imageRatio);
		}

		// Draw the scaled image
		BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight,
				BufferedImage.TYPE_INT_RGB);
	
		Graphics2D graphics2D = thumbImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);

		// Write the scaled image to the outputstream
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		ImageIO.write(thumbImage, IMAGE_JPG, out);

		return out.toByteArray();
	}
}
