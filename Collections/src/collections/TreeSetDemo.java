package collections;

import java.util.*;

public class TreeSetDemo {
	
	@SuppressWarnings({"unchecked", "rawtypes" })
	public static void main(String[] args) {
		
		Set set = new TreeSet();
		
		set.add("one");
		set.add("two");
		set.add("three");
		set.add("four");
		set.add("one");
		set.add("four");
		
		//set.add(new Integer(1)); //Exception. String cannot be compared with Integer
		//set.add(null); //NullPointerException
		//set.add(new Object()); //Object cannot be cast to Comparable
		
		System.out.println(set);
	}
}
