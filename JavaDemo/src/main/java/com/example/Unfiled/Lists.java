package com.example.Unfiled;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Lists {
	public static void main(String[] args) {
		System.out.println("第1次从1~36中随机选出的7个数：");
		List<Integer> list1 = selectNums(7);
		System.out.println("\t" + list1.toString());
		System.out.println("第2次从1~36中随机选出的7个数：");
		List<Integer> list2 = selectNums(7);
		Iterator<Integer> it = list2.iterator();
		while (it.hasNext()) {
			System.out.print("\t" + it.next());
		}

		System.out.println("\n第3次从1~36中随机选出的7个数：");
		List<Integer> list3 = selectNums(7);
		Object obj[] = list3.toArray();
		Arrays.sort(obj);
		for (int i = 0; i < obj.length; i++) {
			System.out.print("\t" + obj[i]);
		}
	}

	// 自定义方法：从1~36个数中随机选出k个数，模拟抽奖
	public static ArrayList<Integer> selectNums(int k) {
		Random rd = new Random(); 
		ArrayList<Integer> allNum = new ArrayList<Integer>(); // 创建数组列表对象，存放1~36个整数
		ArrayList<Integer> select = new ArrayList<Integer>(); // 创建数组列表对象，存放从allNum选出的7个整数
		for (int i = 0; i < 36; i++) {
			allNum.add(i + 1); 
		}
		int x;  
		for (int i = 0; i < k; i++) { 
			x = rd.nextInt(36 - i); 
			select.add(allNum.get(x)); 
			allNum.remove(x); 
		}
		return select; 
	}
}
