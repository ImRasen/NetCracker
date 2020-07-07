package collections;

import java.util.ArrayList;
import java.util.List;

public class ArrayListDemo {

	public static void main(String[] args) {
		List list = new ArrayList();
		list.add(new Integer(1));
		list.add(null);
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");
		list.add("one");
		list.add("two");

		System.out.println(list);

	}

}
