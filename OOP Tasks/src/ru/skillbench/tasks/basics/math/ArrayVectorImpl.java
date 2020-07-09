package ru.skillbench.tasks.basics.math;

import static java.lang.Math.sqrt;

public class ArrayVectorImpl implements ArrayVector {

	private int size;
	private double[] vector;

	@Override
	public void set(double... elements) {
		this.size = elements.length;
//		vector = new double[size];
//		for (int i = 0; i < size; i++) {
//			vector[i] = elements[i];
//		}
		this.vector = elements;
	}

	@Override
	public double[] get() {
		return vector;
	}

	@Override
	public ArrayVector clone() {
		ArrayVectorImpl clone = new ArrayVectorImpl();
		clone.vector = vector.clone();
		clone.size = size;
		return (ArrayVector) clone;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public void set(int index, double value) {
		if (index >= 0 && index < size) {
			this.vector[index] = value;
		} else if (index >= size) {
			double[] tmp = new double[index + 1];
			System.arraycopy(this.vector, 0, tmp, 0, size);
			this.vector = tmp;
//			this.vector = Arrays.copyOf(vector, size + 1);
			this.vector[index] = value;
			this.size = vector.length;
		}
		return;
	}

	@Override
	public double get(int index) throws ArrayIndexOutOfBoundsException {
		if (index >= 0 && index < size) {
			return vector[index];
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	@Override
	public double getMax() {
		double max = vector[0];
		for (double v : vector) {
			if (v > max) {
				max = v;
			}
		}
		return max;
	}

	@Override
	public double getMin() {
		double min = vector[0];
		for (double v : vector) {
			if (v < min) {
				min = v;
			}
		}
		return min;
	}

	// Find the smallest element in unsorted vector
	private int indexOfMin(double[] vector, int sortedEnd) {
		double min = vector[sortedEnd];
		int minIndex = sortedEnd;

		for (int i = sortedEnd + 1; i < size; i++) {
			if (vector[i] < min) {
				min = vector[i];
				minIndex = i;
			}
		}
		return minIndex;
	}

	// Choose sort
	@Override
	public void sortAscending() {
		int sortedEnd = 0;
		while (sortedEnd < size) {
			int nextIndex = indexOfMin(vector, sortedEnd);
			if (sortedEnd != nextIndex) {
				double tmp = vector[sortedEnd];
				this.vector[sortedEnd] = vector[nextIndex];
				this.vector[nextIndex] = tmp;
			}
			sortedEnd++;
		}
	}

	@Override
	public void mult(double factor) {
		for (int i = 0; i < size; i++) {
			this.vector[i] *= factor;
		}
	}

	@Override
	public ArrayVector sum(ArrayVector anotherVector) {
		int anotherSize = anotherVector.getSize();
		double[] anotherElements = anotherVector.get();
		int minSize;
		if (size < anotherSize) {
			minSize = size;
		} else {
			minSize = anotherSize;
		}
		for (int i = 0; i < minSize; i++) {
			this.vector[i] += anotherElements[i];
		}
		return (ArrayVector) this;
	}

	@Override
	public double scalarMult(ArrayVector anotherVector) {
		int anotherSize = anotherVector.getSize();
		double[] anotherElements = anotherVector.get();
		int minSize;
		if (size < anotherSize) {
			minSize = size;
		} else {
			minSize = anotherSize;
		}
		double scalar = 0.0;
		for (int i = 0; i < minSize; i++) {
			scalar += this.vector[i] * anotherElements[i];
		}
		return scalar;
	}

	@Override
	public double getNorm() {
		return sqrt(scalarMult((ArrayVector) this));
	}

}
