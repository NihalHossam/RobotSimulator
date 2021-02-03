package main.java.softdesign;

import java.awt.Color;

import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import simbad.sim.Box;
import simbad.sim.EnvironmentDescription;
import simbad.sim.Wall;

public class Environment extends EnvironmentDescription {
    public static final int WORLDSIZE = 25;
    
    private static Environment instance = new Environment();

    public Environment() {
        // turn on the lights
        this.light1IsOn = true;
        this.light2IsOn = true;

        // enable the physics engine in order to have better physics effects on the objects
        this.setUsePhysics(true);

        // show the axes so that we know where things are
        this.showAxis(true);

        this.setWorldSize(WORLDSIZE);

        Wall outerWallWest = new Wall(new Vector3d(-12.5, 0, 0), 25, 2, this);
        outerWallWest.setColor(new Color3f(Color.GRAY));
        outerWallWest.rotate90(1);
        add(outerWallWest);

        Wall outerWallEast = new Wall(new Vector3d(12.5, 0, 0), 25, 2, this);
        outerWallEast.setColor(new Color3f(Color.GRAY));
        outerWallEast.rotate90(1);
        add(outerWallEast);

        Wall outerWallSouth = new Wall(new Vector3d(0, 0, 12.5), 25, 2, this);
        outerWallSouth.setColor(new Color3f(Color.GRAY));
        add(outerWallSouth);

        Wall outerWallNorth = new Wall(new Vector3d(0, 0, -12.5), 25, 2, this);
        outerWallNorth.setColor(new Color3f(Color.GRAY));
        add(outerWallNorth);

        Wall h1 = new Wall(new Vector3d(-8.5, 0, -8.5), 8, 1, this);
        h1.setColor(new Color3f(Color.GRAY));
        add(h1);

        Wall h2 = new Wall(new Vector3d(6.5, 0, -8.5), 6, 1, this);
        h2.setColor(new Color3f(Color.GRAY));
        add(h2);

        Wall h3 = new Wall(new Vector3d(-10, 0, -2.5), 5, 1, this);
        h3.setColor(new Color3f(Color.GRAY));
        add(h3);

        Wall h4 = new Wall(new Vector3d(-4.5, 0, -3.5), 2, 1, this);
        h4.setColor(new Color3f(Color.GRAY));
        add(h4);

        Wall h5 = new Wall(new Vector3d(2, 0, -3.5), 3, 1, this);
        h5.setColor(new Color3f(Color.GRAY));
        add(h5);

        Wall h6 = new Wall(new Vector3d(5.5, 0, -0.5), 4, 1, this);
        h6.setColor(new Color3f(Color.GRAY));
        add(h6);

        Wall h7 = new Wall(new Vector3d(10, 0, -5.5), 5, 1, this);
        h7.setColor(new Color3f(Color.GRAY));
        add(h7);

        Wall h8 = new Wall(new Vector3d(-10, 0, 2.5), 5, 1, this);
        h8.setColor(new Color3f(Color.GRAY));
        add(h8);

        Wall h9 = new Wall(new Vector3d(-2, 0, 3.5), 5, 1, this);
        h9.setColor(new Color3f(Color.GRAY));
        add(h9);

        Wall h10 = new Wall(new Vector3d(9, 0, 1.5), 3, 1, this);
        h10.setColor(new Color3f(Color.GRAY));
        add(h10);

        Wall h11 = new Wall(new Vector3d(-8.5, 0, 9.5), 2, 1, this);
        h11.setColor(new Color3f(Color.GRAY));
        add(h11);

        Wall h12 = new Wall(new Vector3d(-2, 0, 8.5), 5, 1, this);
        h12.setColor(new Color3f(Color.GRAY));
        add(h12);

        Wall h13 = new Wall(new Vector3d(5.5, 0, 9.5), 2, 1, this);
        h13.setColor(new Color3f(Color.GRAY));
        add(h13);

        Wall h14 = new Wall(new Vector3d(11.5, 0, 6.5), 2, 1, this);
        h14.setColor(new Color3f(Color.GRAY));
        add(h14);

        Wall v1 = new Wall(new Vector3d(-8.5, 0, -9.5), 2, 1, this);
        v1.setColor(new Color3f(Color.GRAY));
        v1.rotate90(1);
        add(v1);

        Wall v2 = new Wall(new Vector3d(-7.5, 0, -4.5), 4, 1, this);
        v2.setColor(new Color3f(Color.GRAY));
        v2.rotate90(1);
        add(v2);

        Wall v3 = new Wall(new Vector3d(-7.5, 0, 1), 3, 1, this);
        v3.setColor(new Color3f(Color.GRAY));
        v3.rotate90(1);
        add(v3);

        Wall v4 = new Wall(new Vector3d(-9.5, 0, 7), 5, 1, this);
        v4.setColor(new Color3f(Color.GRAY));
        v4.rotate90(1);
        add(v4);

        Wall v5 = new Wall(new Vector3d(-0.5, 0, -11.5), 2, 1, this);
        v5.setColor(new Color3f(Color.GRAY));
        v5.rotate90(1);
        add(v5);

        Wall v6 = new Wall(new Vector3d(3.5, 0, -2), 3, 1, this);
        v6.setColor(new Color3f(Color.GRAY));
        v6.rotate90(1);
        add(v6);

        Wall v7 = new Wall(new Vector3d(0.5, 0, 6), 5, 1, this);
        v7.setColor(new Color3f(Color.GRAY));
        v7.rotate90(1);
        add(v7);

        Wall v8 = new Wall(new Vector3d(9.5, 0, -10.5), 4, 1, this);
        v8.setColor(new Color3f(Color.GRAY));
        v8.rotate90(1);
        add(v8);

        Wall v9 = new Wall(new Vector3d(7.5, 0, -4.5), 2, 1, this);
        v9.setColor(new Color3f(Color.GRAY));
        v9.rotate90(1);
        add(v9);

        Wall v10 = new Wall(new Vector3d(10.5, 0, 0.5), 2, 1, this);
        v10.setColor(new Color3f(Color.GRAY));
        v10.rotate90(1);
        add(v10);

        Wall v11 = new Wall(new Vector3d(7.5, 0, 3.5), 4, 1, this);
        v11.setColor(new Color3f(Color.GRAY));
        v11.rotate90(1);
        add(v11);

        Wall v12 = new Wall(new Vector3d(8.5, 0, 10), 5, 1, this);
        v12.setColor(new Color3f(Color.GRAY));
        v12.rotate90(1);
        add(v12);

        Box box1 = new Box(new Vector3d(-11, 0, -11), new Vector3f(1, 1, 1), this);
        box1.setColor(new Color3f(Color.GREEN));
        add(box1);

        Box box2 = new Box(new Vector3d(-5, 0, -10), new Vector3f(1, 1, 1), this);
        box2.setColor(new Color3f(Color.BLUE));
        add(box2);

        Box box3 = new Box(new Vector3d(8, 0, -11), new Vector3f(1, 1, 1), this);
        box3.setColor(new Color3f(Color.GREEN));
        add(box3);

        Box box4 = new Box(new Vector3d(12, 0, -6), new Vector3f(1, 1, 1), this);
        box4.setColor(new Color3f(Color.BLUE));
        add(box4);

        Box box5 = new Box(new Vector3d(-12, 0, 2), new Vector3f(1, 1, 1), this);
        box5.setColor(new Color3f(Color.GREEN));
        add(box5);

        Box box6 = new Box(new Vector3d(-1, 0, -3), new Vector3f(1, 1, 1), this);
        box6.setColor(new Color3f(Color.BLUE));
        add(box6);

        Box box7 = new Box(new Vector3d(12, 0, -4), new Vector3f(1, 1, 1), this);
        box7.setColor(new Color3f(Color.GREEN));
        add(box7);

        Box box8 = new Box(new Vector3d(-12, 0, 11), new Vector3f(1, 1, 1), this);
        box8.setColor(new Color3f(Color.BLUE));
        add(box8);

        Box box9 = new Box(new Vector3d(-8, 0, 8), new Vector3f(1, 1, 1), this);
        box9.setColor(new Color3f(Color.GREEN));
        add(box9);

        Box box10 = new Box(new Vector3d(3, 0, 12), new Vector3f(1, 1, 1), this);
        box10.setColor(new Color3f(Color.BLUE));
        add(box10);

        Box box11 = new Box(new Vector3d(10, 0, 11), new Vector3f(1, 1, 1), this);
        box11.setColor(new Color3f(Color.GREEN));
        add(box11);

        Box box12 = new Box(new Vector3d(-1, 0, 5), new Vector3f(1, 1, 1), this);
        box12.setColor(new Color3f(Color.BLUE));
        add(box12);
    }
    
    public static Environment getInstance() {
		return instance;
	}
}
