package org.example;


import org.example.model.Guitar;
import org.example.model.Singer;

/**
 * Created by iuliana.cosmina on 4/2/17.
 */
public class Guitarist implements Singer {

	@Override public void sing() {
		System.out.println("Dream of ways to throw it all away");
	}

	@AdviceRequired
	public void sing(Guitar guitar) {
		System.out.println("play: " + guitar.play());
	}

	public void rest(){
		System.out.println("zzz");
	}

}
