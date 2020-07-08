package ru.skillbench.tasks.basics.control;

import static java.lang.Math.sin;

public class ControlFlowStatements1Impl implements ControlFlowStatements1 {

	@Override
	public float getFunctionValue(float x) {
		if (x > 0) {
			return (float) (2 * sin(x));
		} else {
			return 6 - x;
		}
	}

	@Override
	public String decodeWeekday(int weekday) {
		switch (weekday) {
		case 1:
			return ("Monday");
		case 2:
			return ("Tuesday");
		case 3:
			return ("Wednesday");
		case 4:
			return ("Thursday");
		case 5:
			return ("Friday");
		case 6:
			return ("Saturday");
		case 7:
			return ("Sunday");
		default:
			return null;
		}
	}

	@Override
	public int[][] initArray() {
		int[][] array = new int[8][5];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				array[i][j] = i * j;
			}
		}
		return array;
	}

	@Override
	public int getMinValue(int[][] array) {
		int min = array[0][0];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				if (array[i][j] < min) {
					min = array[i][j];
				}
			}
		}
		return min;
	}

	@Override
	public BankDeposit calculateBankDeposit(double P) {
		// $1000, P - %. Goal: amount > $5000
		BankDeposit deposit = new BankDeposit();

		double amount = 1000;
		int year = 0;
		// wait for goal to be reached
		while (amount < 5000) {
			year++;
			amount = (1 + P / 100) * amount;
		}

		deposit.years = year;
		deposit.amount = amount;

		return deposit;
	}

	// public static void main(String[] args) {
	// System.out.println(new
	// ControlFlowStatements1Impl().calculateBankDeposit(12.5).toString());
	// }

}
