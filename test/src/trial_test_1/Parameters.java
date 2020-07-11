package trial_test_1;

public class Parameters {
	public static void main(String[] args) {
		StringBuffer a = new StringBuffer("One");
		StringBuffer b = new StringBuffer("Two");
		swap(a, b);
//		a.append(" more");
//		b = a;
		System.out.println("a is " + a + "\nb is " + b);
	}

	// static method can be run without an existing object of a class
	static void swap(StringBuffer a, StringBuffer b) {
		a.append(" more"); // Value a is a reference, however, it cannot be changed itself:
		b = a; // Doesn't work because of pass-bu-value
		// https://stackoverflow.com/questions/40480/is-java-pass-by-reference-or-pass-by-value
	}
}