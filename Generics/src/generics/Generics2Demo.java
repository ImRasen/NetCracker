//Two types classes + generic methods
package generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Generics2Demo {
	public static void main(String[] args) {
		Storage2<String, Integer> storage = new Storage2<>(); // Optionally can write ..= new Storage<String>(); ->
																// Diamond
		storage.setObject1("Sweet home Alabama");
		storage.setObject2(Integer.valueOf(50));

		List<String> list = new ArrayList<>(); // Collection for generic method demo
		list.add("West Virginia");
		list.add(storage.getObject1());

		System.out.println(storage.getObject1());
		System.out.println(storage.getObject2());
		System.out.println(storage.<String>getFirst(list));
	}

}

class Storage2<T, S> {
	private T object1;
	private S object2;

	public T getObject1() {
		return object1;
	}

	public void setObject1(T object1) {
		this.object1 = object1;
	}

	public S getObject2() {
		return object2;
	}

	public void setObject2(S object2) {
		this.object2 = object2;
	}

	// Generic method
	public <C> C getFirst(Collection<C> col) {
		return col.iterator().next();
	}

}