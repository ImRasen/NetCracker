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
		}

		int lastSignIndex; // firstSingIndex would always be 0

		lastSignIndex = value.lastIndexOf('+');
		if (value.lastIndexOf('-') > lastSignIndex) {
			lastSignIndex = value.lastIndexOf('-');
		}

		if (lastSignIndex == 0) {
			// 1 summand
			if (value.endsWith("i")) {
				// 1 imaginary summand
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
					return;
				}
			} else {
				// 1 real summand
				im = 0.0;
				re = Double.parseDouble(value);
				return;
			}
		} else {
			// 2 summands
			re = Double.parseDouble(value.substring(0, lastSignIndex));
			try {
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
				return;
			}
		}
	}

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

//	public static void main(String[] args) {
//		ComplexNumberImpl number = new ComplexNumberImpl();
//		number.set("i");
//		System.out.println("object re = " + number.re);
//		System.out.println("object im = " + number.im);
//		System.out.println(number.toString());
//	}
}
