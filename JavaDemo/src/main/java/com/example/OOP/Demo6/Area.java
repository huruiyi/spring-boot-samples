package com.example.OOP.Demo6;

import java.util.Scanner;

public class Area {
	public static int Width;
	public static int Height;
	private static Scanner s;

	Area(int width, int height) {
		Area.Width = width;
		Area.Height = height;
	}

	public static void main(String[] args) {
		ICaclRectArea mianji01 = new Rect();
		s = new Scanner(System.in);
		System.out.println("input the Width ");
		Width = s.nextInt();
		System.out.println("input the Height ");
		Height = s.nextInt();
		System.out.println("----------------------");
		System.out.println("the mianji = " + mianji01.GetArea(Width, Height));
		System.out.println("----------------------");

		ICaclTriangleArea mianji02 = new Triangle();
		System.out.println("input the Width ");
		Width = s.nextInt();
		System.out.println("input the Height ");
		Height = s.nextInt();
		System.out.println("the sanjiaomianji = " + mianji02.GetArea(Width, Height));
		System.out.println("----------------------");
	}
}

interface ICaclRectArea {
	public int GetArea(int l, int w);

}

class Rect implements ICaclRectArea {

	public int GetArea(int l, int w) {
		return (l * w);
	}
}

interface ICaclTriangleArea {
	public float GetArea(float i, float j);
}

class Triangle implements ICaclTriangleArea {
	public float GetArea(float i, float j) {
		return (i * j / 2);
	}
}
