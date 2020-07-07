package collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RandomAccessDemo {

	public static void main(String[] args) {
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new LinkedList<String>();

		// Fill the collections
		// 10 100 1000 10000 100000
		for (int i = 0; i < 100000; i++) {
			list1.add("" + i);
			list2.add("" + i);
		}

		// Random Access Test
		long t0 = System.currentTimeMillis(); // get list1
		for (int i = 0, n = list1.size(); i < n; i++) {
			String s = list1.get(i);
		}

		System.out.println((System.currentTimeMillis() - t0));

		long t1 = System.currentTimeMillis();// get list2
		for (int i = 0, n = list2.size(); i < n; i++) {
			String s = list2.get(i);
		}

		System.out.println((System.currentTimeMillis() - t1));
		// Почему-то время одинаково
	}

}
