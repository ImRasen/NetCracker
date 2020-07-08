package generics;

public class Generics2Demo {
	public static void main(String[] args) {
		Storage2<String, Integer> storage = new Storage2<>(); // Optionally can write ..= new Storage<String>(); ->
																// Diamond
		storage.setObject1("Sweet home Alabama");
		storage.setObject2(Integer.valueOf(23));

		System.out.println(storage.getObject1());
		System.out.println(storage.getObject2());
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

}