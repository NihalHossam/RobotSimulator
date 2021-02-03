

package main.java.robotsimulator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

/************************************************************/
/**
 * 
 */
public class Album {
	/**
	 * 
	 */
	private HashMap<Position, BufferedImage> photoCollection;

	/**
	 * 
	 */
	public Album() {
		photoCollection = new HashMap<Position, BufferedImage>();
	}

	/**
	 * 
	 * @param hashmap 
	 * This method go through the whole hashmap and export buffered image as jpg file, named by the Position, into a separate folder.
	 */
	public void exportPhotos() {
		for (Entry<Position, BufferedImage> entry : photoCollection.entrySet()) {
	    	BufferedImage bi = entry.getValue();
	        String pic = entry.getKey().getX()+","+entry.getKey().getY();
	        File outputfile = new File(pic+".jpg");
	        try {
	    		ImageIO.write(bi, "jpg", outputfile);
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}
		}
		return;
	}
	/**
	 * 
	 * @param Position 
	 * @param photo 
	 */
	public void addPhoto(Position coordinates, BufferedImage photo) {
		 photoCollection.put(coordinates, photo);
	}
	public int returnPhotoCount() {
		 return photoCollection.size();
	}
};
