package ru.skillbench.tasks.basics.math;

import java.util.Arrays;
import java.util.Comparator;

public class ComplexNumberImpl implements ComplexNumber, Comparable<ComplexNumber> {
	private Double re, im;

	@Override
	public double getRe() {
		return re;
	}

	@Override
	public double getIm() {
		return im;
	}

	@Override
	public boolean isReal() {
		return im == 0.0;
	}

	@Override
	public void set(double re, double im) {
		this.re = re;
		this.im = im;
	}

	@Override
	public void set(String value) throws NumberFormatException {

		if (value.isEmpty()) {
			re = 0.0;
			im = 0.0;
			return;
		}

		if (!value.startsWith("+") && !value.startsWith("-")) {
			value = "+" + value;
			System.out.println("New value = " + value);
		}

		int lastSignIndex; // firstSingIndex would always be 0

		lastSignIndex = value.lastIndexOf('+');
		if (value.lastIndexOf('-') > lastSignIndex) {
			lastSignIndex = value.lastIndexOf('-');
		}

		System.out.println("last sign index = " + lastSignIndex);

		if (lastSignIndex == 0) {
			// 1 summand
			if (value.endsWith("i")) {
				// 1 imaginary summand
				System.out.println("ends with i");
				re = 0.0;
				try {
					im = Double.parseDouble(value.substring(0, value.length() - 1));
				} catch (NumberFormatException e) {
					// .. +i or .. -i
					switch (value.charAt(0)) {
					case '+':
						im = 1.0;
						break;
					case '-':
						im = -1.0;
						break;
					}
				}
				System.out.println("Set re = 0, im = double");
			} else {
				// 1 real summand
				System.out.println("does NOT end with i");
				im = 0.0;
				re = Double.parseDouble(value);
				System.out.println("Set re = double, im = 0");
			}
		} else {
			// 2 summands
			System.out.println("Re = parsing from " + value.substring(0, lastSignIndex));
			re = Double.parseDouble(value.substring(0, lastSignIndex));
			try {
				System.out.println("Im = parsing from " + value.substring(lastSignIndex, value.length() - 1));
				im = Double.parseDouble(value.substring(lastSignIndex, value.length() - 1));
			} catch (NumberFormatException e) {
				// .. +i or .. -i
				switch (value.charAt(lastSignIndex)) {
				case '+':
					im = 1.0;
					break;
				case '-':
					im = -1.0;
					break;
				}
			}
		}
	}
//		if (value.startsWith("i") || value.startsWith("+i")) {
//			re = 0.0;
//			im = 1.0;
//			return;
//		}
//
//		if (value.startsWith("-i")) {
//			re = 0.0;
//			im = -1.0;
//			return;
//		}

//		Double first = 0.0, second = 0.0;
//		int index = 0; // index of sign (if there is one)
//
//		try {
//			first = Double.valueOf(value);
//			System.out.println("first = " + first);
//			re = first;
//		} catch (NumberFormatException e) {
//			System.out.println("If here, then re = 0, im: +i -i or i");
//			switch (value.charAt(0)) {
//			case '+':
//			case 'i':
//				re = 0.0;
//				im = 1.0;
//				return;
//			case '-':
//				re = 0.0;
//				im = -1.0;
//				return;
//			}
//		}
//		System.out.println("After catch first");
//		try {
//			index = value.lastIndexOf('+');
//			if (value.lastIndexOf('-') > index) {
//				index = value.lastIndexOf('-');
//			}
//			System.out.println("last index of sign = " + index);
//			System.out.println("subString = " + value.subSequence(index, value.length()).toString());
//			if (index > 0) {
//				System.out.println("index > 0");
//				second = Double.valueOf(value.subSequence(index, value.length()).toString());
//				System.out.println("second = " + second);
//				im = second;
//			} else {
//				im = 0.0;
//			}
//		} catch (NumberFormatException e) {
//			// If here, then re = 0, im: +i -i or i
//			switch (value.charAt(index)) {
//			case '+':
//			case 'i':
//				System.out.println("cought re + i");
//				im = 1.0;
//				return;
//			case '-':
//				im = -1.0;
//				return;
//			}
//		}
//		System.out.println("After catch second");
//
//	}

//
//		int signMod[] = { 1, 1 };
//
//		if (value.startsWith("-")) {
//			signMod[0] = -1;
//		}
//
//		int countMinus = value.length() - value.replaceAll("-", "").length();
//
////		countMinus =	\	0	 1	 2
////		signMod[0] =  (+)1  1	-1	 -
////		signMod[1] = (-)-1	-	 1	-1
//
//		switch (countMinus) {
//		case 0:
//			signMod[1] = 1;
//			break;
//		case 1:
//			signMod[1] = -signMod[0];
//			break;
//		case 2:
//			signMod[1] = -1;
//			break;
//		}
//
//		String[] tokens = value.split("[\\+-]");
//		for (int i = 0; i < tokens.length; i++) {
//			System.out.println("token = " + tokens[i]);
//			if (tokens[i].equals("")) {
//				re = 0.0;
//				continue;
//			}
//			if (tokens[i].contains("i")) {
//				if (tokens[i].length() > 1) {
//					im = signMod[i] * Double.parseDouble(tokens[i]);
//				} else {
//					im = 1.0 * signMod[i];
//				}
//			} else {
//				re = signMod[i] * Double.parseDouble(tokens[i]);
//			}
//		}
//
//	}

	@Override
	public ComplexNumber copy() {
		ComplexNumber temp = new ComplexNumberImpl();
		temp.set(re, im);
		return temp;
	}

	public ComplexNumber clone() throws CloneNotSupportedException {
		ComplexNumber temp = new ComplexNumberImpl();
		temp.set(re, im);
		return (ComplexNumber) temp;
	}

	@Override
	public String toString() {
		if (re == 0.0 && im == 0.0) {
			return "";
		}
		if (re == 0.0) {
			return (String.valueOf(im) + "i");
		} else if (im == 0.0) {
			return (String.valueOf(re));
		} else {
			if (im < 0) {
				return (String.valueOf(re) + String.valueOf(im) + "i");
			} else {
				return (String.valueOf(re) + "+" + String.valueOf(im) + "i");
			}
		}
	}

	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}

		if (!(other instanceof ComplexNumber)) {
			return false;
		}

		ComplexNumber temp = (ComplexNumber) other;
		return (re.equals(temp.getRe()) && im.equals(temp.getIm()));
	}

	@Override
	public int compareTo(ComplexNumber other) throws NullPointerException {
		if (other == null) {
			throw new NullPointerException();
		}
		if (this == other) {
			return 0;
		}
		return (int) (re * re + im * im - other.getRe() * other.getRe() - other.getIm() * other.getIm());
	}

	public static Comparator<ComplexNumber> ComplexComparator = new Comparator<ComplexNumber>() {

		@Override
		public int compare(ComplexNumber o1, ComplexNumber o2) {
			return o1.compareTo(o2);
		}

	};

	@Override
	public void sort(ComplexNumber[] array) {

		Arrays.sort(array, ComplexComparator);
	}

	@Override
	public ComplexNumber negate() {
		set(-re, -im);
		return this;
	}

	@Override
	public ComplexNumber add(ComplexNumber arg2) {
		set(re + arg2.getRe(), im + arg2.getIm());
		return this;
	}

	// a+bi * c+di = (a*c-b*d)+(b*c+a*d)i
	@Override
	public ComplexNumber multiply(ComplexNumber arg2) {
		set(re * arg2.getRe() - im * arg2.getIm(), im * arg2.getRe() + re * arg2.getIm());
		return this;
	}

//	@Override
//	protected Object clone() throws CloneNotSupportedException {
//		// TODO Auto-generated method stub
//		return super.clone();
//	}

	public static void main(String[] args) {
		ComplexNumberImpl number = new ComplexNumberImpl();
		number.set("");
		System.out.println("object set");
		System.out.println("object re = " + number.re);
		System.out.println("object im = " + number.im);
		System.out.println(number.toString());
	}
}
