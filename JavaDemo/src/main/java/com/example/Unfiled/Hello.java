package com.example.Unfiled;

import java.util.Scanner;
/*
 * 
 * Eclipse 快捷键
 * */
// Ctrl + F11 			 	运行
// Ctrl + D  				删除一行
// Ctrl + /					注释或取消注释
// Ctrl + Alt + ↑或↓			复制当前行向上或向下
// Ctrl + Shift + L  		查看所有注释,查看引用,查看文件内容
// Ctrl + Shift + X  		选中单词 转换 大写
// Ctrl + Shift + R  		查看文件
// Ctrl + Shift + Y  		选中单词 转换 小写
// Ctrl + Shift + A  		块编辑
// Ctrl + Shift + F  		代码格式化
// Ctrl + Shift + O  		导入jar包
// Ctrl + Shift + Enter 	跳转到上一行
// Shift + Enter   			 跳转到下一行
// Alt + /					智能提示
// Alt + ↑或↓				向上或向下移动当前行
// Ctrl + 2 + L				选中方法,再按L 自动声明变量
// Ctrl + 1					导入包
// Ctrl + O					预览方法签名
// Ctrl + /					代码折叠或展开(小键盘上的 / )
// Alt  + Shift +J 			代码参数说明

public class Hello {
	private static Scanner input;

	public static void main(String[] args) {
		
		PerfectNumber();
	}

	static void PerfectNumber() {
		int i, m = 0, s;
		for (m = 1; m < 10000; m++) {
			s = 0;
			for (i = 1; i < m; i++) {
				if (m % i == 0) {
					s += i;
				}
			}

			if (s == m) {
				System.out.print(m + "是完数，其因子是：");
				for (i = 1; i < m; i++) {
					if (m % i == 0) {
						System.out.print(i + ",");
					}
				}
				System.out.println();
			}
		}
	}

	static boolean IsPrimer(int n) {
		int m = (int) Math.sqrt(n);
		for (int i = 2; i <= m; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	// 普通年（不能被100整除的年份）能被4整除的为闰年。（如2004年就是闰年,1999年不是闰年）；
	// 世纪年（能被100整除的年份）能被400整除的是闰年。(如2000年是闰年，1900年不是闰年)；
	static void LeapYear() {
		input = new Scanner(System.in);
		System.out.print("请输入年份：");
		int year = input.nextInt();

		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			System.out.print(year + "年是闰年。");
		} else {
			System.out.print(year + "年不是闰年。");
		}
	}
}