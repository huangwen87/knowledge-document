package com.java.alogrithm;

import java.util.Calendar;
public class EightQueens {

	//统计解的个数
	int count ;
	//皇后数
	static int N = 8;
	//记录皇后的位置,x[i]表示皇后i放在棋盘的第i行的第x[i]列
	int [] X = new int[N];
	
	/**
	 * 测试皇后k在第k行第x[k]列时是否与前面已放置好的皇后相攻击.
	 * (X[j] == X[k]时，两皇后在同一列上，
	 * k-j == Math.abs(X[j] - X[k])时，两皇后在同一斜线上。
	 * @param k
	 * @return
	 */
	boolean check(int k) {
		for (int row = 0; row < k; row ++) {
			if ((X[row] == X[k] || k-row == Math.abs(X[row] - X[k]))) {
				return false ;
			}
		}
		return true;
	}
	
	/**
	 * 回溯求皇后的放置方案。
	 * 对于八皇后t的最大值为8.
	 * @param row row -> [0,1,2,3,4,5,6,7,8]
	 */
	void backtrack(int row) {
		//row == N 时，算法搜索至叶结点，得到一个新的N皇后互不攻击的放置方案
		if(row == N) {
			count++;
			printQueen();
		} else {
			for (int col = 0; col < N; col++) {
				X[row] = col;//第row行的皇后放在col列上
				if(check(row)) {//放置成功再放row+1行的
					backtrack(row+1);
				}
			}
		}
	}
	
	/**
	 * 打印皇后
	 */
	void printQueen() {
		System.out.println("==================第"+count+"种皇后图==================");
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (col == X[row]) {
					System.out.print("@ ");
				} else {
					System.out.print("* ");
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EightQueens queen = new EightQueens();
		long t1 = Calendar.getInstance().getTimeInMillis();
		//从0开始回溯
		queen.backtrack(0);
		long t2 = Calendar.getInstance().getTimeInMillis();
		//打印花费的时间。
		System.out.println("花费："+(t2-t1)+"ms");
		//打印方案总数
		System.out.println(queen.count);
	}

}

