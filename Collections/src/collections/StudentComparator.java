package collections;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class StudentComparator {

	private String surname;
	private String name;

	public StudentComparator() {
	}

	public StudentComparator(String surname, String name) {
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
		StudentComparator other = (StudentComparator) obj;
		return Objects.equals(name, other.name) && Objects.equals(surname, other.surname);
	}

	public static void main(String[] args) {
		StudentComparator s1 = new StudentComparator("Ivanov", "Ivan");
		StudentComparator s2 = new StudentComparator("Petrov", "Peter");
		StudentComparator s3 = new StudentComparator("Sidorova", "Olga");
		StudentComparator s4 = new StudentComparator("Ivanov", "Peter");
		StudentComparator s5 = new StudentComparator("Ivanov", "Alexandr");

		Set set = new TreeSet(StudentComparator.surnameNameComprator);
		// TreeSet has a constructor with Comparator parameter
		// It doesn't work without comparator (TreeSet)
		set.add(s1);
		set.add(s2);
		set.add(s3);
		set.add(s4);
		set.add(s5);

		Set set2 = new TreeSet(StudentComparator.nameSurnameComprator);
		set2.add(s1);
		set2.add(s2);
		set2.add(s3);
		set2.add(s4);
		set2.add(s5);

		System.out.println(set);
		System.out.println(set2);
	}

	// Compare Students
	// Anonymous classes
	public static Comparator<StudentComparator> surnameNameComprator = new Comparator<StudentComparator>() {
		@Override
		public int compare(StudentComparator s1, StudentComparator s2) {
			int result = s1.surname.compareTo(s2.surname);
			if (result != 0)
				return result;
			return s1.name.compareTo(s2.name);
		}
	};

	public static Comparator<StudentComparator> nameSurnameComprator = new Comparator<StudentComparator>() {
		@Override
		public int compare(StudentComparator s1, StudentComparator s2) {
			int result = s1.name.compareTo(s2.name);
			if (result != 0)
				return result;
			return s1.surname.compareTo(s2.surname);
		}
	};
}
