package generics;

public class GenericsDemo {

	public static void main(String[] args) {
		Storage<String> storage = new Storage<>(); // Optionally can write ..= new Storage<String>(); -> Diamond
		storage.setObject("Sweet home Alabama");

		String str = storage.getObject();
		System.out.println(str);
	}

}

//Generics: T (Template) 
class Storage<T> {
	private T object;

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

}