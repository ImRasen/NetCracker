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

//	// 1 - done, 1.2 - done, 1.3i - done, 1.3+i, 1.3+2.1i, i - done
//	// -1, -1.2, -1.3i, -1.3+i
//	static enum State {
//		START, RE, RE_PLUS, RE_MINUS, IM_PLUS, IM_MINUS;
//	}
//
//	static enum Tokens {
//		NUM, PLUS, MINUS, I, DOT;
//	}
//
//	public boolean isSign(char c) {
//		return (c == '+' || c == '-');
//	}
//
//	public boolean isNumber(char c) {
//		return (c >= '0' && c <= '9');
//	}

	@Override
	public void set(String value) throws NumberFormatException {

		if (value.isEmpty()) {
			throw new NumberFormatException();
		}

		if (value.charAt(0) == 'i') {
			re = 0.0;
			im = 1.0;
		}

		int countPlus = value.length() - value.replaceAll("+", "").length();
		int countMinus = value.length() - value.replaceAll("-", "").length();

		switch (countPlus + countMinus) {

		case 0: // re | imi
			if (value.endsWith("i")) {
				re = 0.0;
				im = Double.parseDouble(value);
			} else {
				re = Double.parseDouble(value);
				im = 0.0;
			}
			break;

		case 1: // +/-re | re +/- imi | re +/- i
			re = Double.parseDouble(value);
			if (value.endsWith("i")) {
				if (!value.contains("+i") && value.contains("+")) {
					im = Double.parseDouble(value.substring(value.indexOf("+"), value.length()));
				} else if (!value.contains("-i") && value.contains("-")) {
					im = Double.parseDouble(value.substring(value.indexOf("-"), value.length()));
				} else // re +/- i
				if (value.contains("+i")) {
					im = 1.0;
				} else
					im = -1.0;
			}
			break;

		default: // +/- re +/- im i | +/- re +/- i
			int lastSign = value.lastIndexOf('+') > value.lastIndexOf('-') ? value.lastIndexOf('+')
					: value.lastIndexOf('-');
			if (!value.contains("+i") && !value.contains("-i")) {
				re = Double.parseDouble(value);
				im = Double.parseDouble(value.substring(lastSign, value.length()));
			} else {
				im = value.charAt(lastSign) == '+' ? 1.0 : -1.0;
			}
		}
	}

	{
//		Double[] values = new Double[2];
//		Double reValue, imValue, tempValue;
//		State current = State.START;
//		for (int i = 0; i < value.length(); i++) {
//			char c = value.charAt(i);
//			if(isSign(c) && current==State.START) {
//				switch(c) {
//				case '+':
//					current = State.RE_PLUS;
//					break;
//				case '-':
//					current = State.RE_MINUS;
//					break;
//				}
//			}
//			if(isSign(c) && (current==State.RE ||current==State.RE_PLUS || current==State.RE_MINUS)){
//				switch(c) {
//				case '+':
//					current = State.IM_PLUS;
//					break;
//				case '-':
//					current = State.IM_MINUS;
//					break;
//				}
//			}
//			if (current==State.START) {
//				tempValue = 
//			}
//		}
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
			return (String.valueOf(re) + "+" + String.valueOf(im) + "i");
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

}
