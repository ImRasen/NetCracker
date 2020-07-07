package collections;

import java.util.HashSet;
import java.util.Objects;

public class Student {

	private String surname;
	private String name;

	public Student() {
	}

	public Student(String surname, String name) {
		this.surname = surname;
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [surname=" + surname + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(name, other.name) && Objects.equals(surname, other.surname);
	}

	public static void main(String[] args) {
		Student s1 = new Student("Ivanov", "Ivan");
		Student s2 = new Student("Petrov", "Peter");
		Student s3 = new Student("Sidorova", "Olga");

		HashSet set = new HashSet();

		set.add(s1);
		set.add(s2);
		set.add(s3);
		set.add(s3);
		System.out.println(set);

		set.remove(s3); // Not imutable object
		s3.setSurname("Ivanova");
		set.add(s3);
		System.out.println(set);

	}

}
