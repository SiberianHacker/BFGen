package org.imp.core;
import org.imp.core.generator.CaptchaPainter;
import org.imp.core.generator.map.MapPalette;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Font;
import java.util.Random;

public final class CaptchaGeneration
{
    public static void generateImages() throws IOException {
        Random random = new Random();
        int sans = random.nextInt(5) + 62;
        
        ThreadLocal<Font[]> fonts = ThreadLocal.withInitial(() -> new Font[] { new Font("SansSerif", 0, sans), new Font("Serif", 0, sans), new Font("Monospaced", 1, sans) });
        MapPalette.prepareColors();
            Font[] curr = fonts.get();
            CaptchaPainter captchaPainter = new CaptchaPainter();
            BufferedImage image;
 
            		int random2 = random.nextInt(9999);
	                image = captchaPainter.draw(curr[random.nextInt(curr.length)], randomNotWhiteColor(random), String.valueOf(random2));
			        FileOutputStream fileOutputStream = new FileOutputStream("bffolder/" + random2 + "_" + random.nextInt(9999) + ".png");
			        ImageIO.write(image,"png",fileOutputStream);
			        System.out.println("Generated " + random2);
            	
            
     }
    public static void generateDataset() throws IOException {
        Random random = new Random();
        int sans = random.nextInt(5) + 62;
        
        ThreadLocal<Font[]> fonts = ThreadLocal.withInitial(() -> new Font[] { new Font("SansSerif", 0, sans), new Font("Serif", 0, sans), new Font("Monospaced", 1, sans) });
        MapPalette.prepareColors();
            Font[] curr = fonts.get();
            CaptchaPainter captchaPainter = new CaptchaPainter();
            BufferedImage image;
            	int random2 = random.nextInt(9);
                image = captchaPainter.draw(curr[random.nextInt(curr.length)], randomNotWhiteColor(random), String.valueOf(random2));
		        FileOutputStream fileOutputStream = new FileOutputStream(random2 + "/" + random2 + "_" + random.nextInt(9999) + ".png");
		        ImageIO.write(image,"png",fileOutputStream);
		        System.out.println("Generated " + random2);
     }
     
    
    private static Color randomNotWhiteColor(final Random random) {
        final Color color = MapPalette.colors[random.nextInt(MapPalette.colors.length)];
        if (color.getRed() == 255 && color.getGreen() == 255 && color.getBlue() == 255) {
            return randomNotWhiteColor(random);
        }
        return color;
    }
    
    private CaptchaGeneration() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static void main(String[] args) throws IOException {
    	if(args.length >= 1) {
    		for(int i = 0; i < 9;i++) {
    			File folder = new File("" + i);
    			if(!folder.exists()) {
    				folder.mkdir();
    			}
    		}
    		File bffolder = new File("bffolder");
    		if(!bffolder.exists()) {
    			bffolder.mkdir();
    		}
	    	if(args[0].contains("botfilter")) {
		    	while(true) {
		    		generateImages();
		    	}
	    	} else if(args[0].contains("dataset")){
	    		while(true) {
		    		generateDataset();
		    	}
	    	}
	    } else {
	    	System.out.println("arguments:\ndataset\nbotfilter");
	    }
    }
}
