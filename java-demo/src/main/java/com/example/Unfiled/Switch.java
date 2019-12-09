package com.example.Unfiled;

import javax.swing.JOptionPane;

public class Switch {

	// private static Scanner in;

	public static void main(String[] args) {
		CalcDemo();
	}

	static void SwitchDemo1() {
		int read = 1;

		switch (read) {
		case 1:
			System.out.println("星期一");
			break;
		case 2:
			System.out.println("星期二");
			break;
		case 3:
			System.out.println("星期三");
			break;
		default:
			break;
		}
	}

	static void SwitchDemo2() {
		int i;
		for (i = 0; i <= 5; i++) {
			switch (i) {
			case 1:
			case 2:
			case 3:
				System.out.println("i is 1, 2 or 3");
				break;
			case 4:
				System.out.println("i is 4");
				break;
			}
			System.out.println();
		}
	}

	static void CalcDemo() {
		String flag = JOptionPane.showInputDialog("input the flag:");
		String numX = JOptionPane.showInputDialog("input the numX:");
		String numY = JOptionPane.showInputDialog("input the numY:");
		int x1 = Integer.parseInt(numX), y1 = Integer.parseInt(numY), numZ = 0;
		System.out.println(flag.getClass().toString());
 
		switch (flag) {
		case "+":
			numZ = x1 + y1;
			break;
		case "-":
			numZ = x1 - y1;
			break;
		case "*":
			numZ = x1 * y1;
			break;
		case "/":
			numZ = x1 / y1;
			break;
		default:
			System.out.println("输入的操作符号错误！");
		}

		System.out.printf("%d %s %d = %d\n", x1, flag, y1, numZ);
	}
}