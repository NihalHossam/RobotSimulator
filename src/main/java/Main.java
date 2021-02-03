package main.java.softdesign;

import java.awt.Color;
import java.util.ArrayList;
import simbad.gui.Simbad;
import main.java.softdesign.Environment;

/**
 *
 * Simbad Source Code
 * - https://github.com/jimmikaelkael/simbad/tree/master/src
 * Avoidance Examples
 * - https://www.ibm.com/developerworks/java/library/j-robots/
 *
 */

public class Main {

    public static void main(String[] args) {
    	
        /*setting up system's properties*/
        System.setProperty("j3d.implicitAntialiasing", "true");
        
        /*creation of the environment containing all obstacles and robots
         * and starting the search mission with two target colors: blue and green*/
        Environment environment = Environment.getInstance();
        CentralStation cs = CentralStation.getInstance();
        cs.init();
        ArrayList<Color> boxColors = new ArrayList<Color>();
        boxColors.add(Color.BLUE);
        boxColors.add(Color.GREEN);
        cs.startSearch(boxColors);
		
        /*creating an instance of the whole Simbad simulator and assigning the newly created environment*/     
        Simbad frame = new Simbad(environment, false);
        frame.update(frame.getGraphics());
    }
}
