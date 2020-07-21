package ru.skillbench.tasks.text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.TimeZone;

public class ContactCardImpl implements ContactCard {

	private String fn, org;
	private Character gender;
	private Calendar bDay = Calendar.getInstance(TimeZone.getTimeZone("GMT+3"));
	private Map<String, String> tel = new HashMap<String, String>();

//	BEGIN:VCARD - vital, fixed position
//	FN:Forrest Gump - vital
//	ORG:Bubba Gump Shrimp Co. - vital
//	BDAY:06-06-1944
//	TEL;TYPE=WORK,VOICE:4951234567
//	TEL;TYPE=CELL,VOICE:9150123456
//	END:VCARD - vital, fixed position

	@Override
	public ContactCard getInstance(Scanner scanner) {

		ArrayList<String> lines = new ArrayList<String>();
		Set<String> lineTypes = new HashSet<>();

		scanner.useDelimiter("\r\n|\n");
		while (scanner.hasNext()) {
			lines.add(scanner.next());
		}

		if (!lines.get(0).equals("BEGIN:VCARD") || !lines.get(lines.size() - 1).equals("END:VCARD")) {
			throw new NoSuchElementException();
		}

		for (int i = 1; i < lines.size() - 1; i++) {

			String[] typeAndValue = lines.get(i).trim().split(":", 2);

			if (typeAndValue.length != 2) {
				throw new InputMismatchException();
			}

			lineTypes.add(typeAndValue[0]);

			switch (typeAndValue[0]) {
			case "FN":
				fn = new String(typeAndValue[1]);
				break;

			case "ORG":
				org = new String(typeAndValue[1]);
				break;

			case "GENDER":
				switch (typeAndValue[1]) {
				case "M":
					gender = 'M';
					break;
				case "F":
					gender = 'F';
					break;
				default:
					throw new InputMismatchException();
				}
				break;

			case "BDAY":
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				try {
					System.out.println(formatter.parse(typeAndValue[1]));
					bDay.setTime(formatter.parse(typeAndValue[1]));
				} catch (ParseException e) {
					throw new InputMismatchException();
				}
				break;

			default:
				// TEL or something else (add later or error for now)
				String telType = new String();
				if (typeAndValue[0].startsWith("TEL;TYPE=")) {
					telType = typeAndValue[0].substring(typeAndValue[0].indexOf('=') + 1);
				} else {
					throw new InputMismatchException();
				}
				if (typeAndValue[1].matches("\\d{10}")) {
					tel.put(telType, typeAndValue[1]);
				} else {
					throw new InputMismatchException();
				}
				break;
			}
		}

		if (!lineTypes.contains("FN") || !lineTypes.contains("ORG")) {
			throw new NoSuchElementException();
		}

		return this;
	}

	@Override
	public ContactCard getInstance(String data) {
		Scanner scanner = new Scanner(data);
		ContactCard vCard = getInstance(scanner);
		return vCard;
	}

	@Override
	public String getFullName() {
		return fn;
	}

	@Override
	public String getOrganization() {
		return org;
	}

	@Override
	public boolean isWoman() {
		if (gender != null) {
			return gender.equals('F');
		} else {
			return false;
		}
	}

	@Override
	public Calendar getBirthday() {
		if (bDay != null) {
			return bDay;
		} else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public Period getAge() {
		if (bDay != null) {
			LocalDate today = LocalDate.now();
			LocalDate bday = bDay.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			return Period.between(bday, today);
		} else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public int getAgeYears() {
		if (bDay != null) {
			return this.getAge().getYears();
		} else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public String getPhone(String type) {
//		"(123) 456-7890"
		try {

			StringBuffer rawNumber = new StringBuffer(tel.get(type));
			rawNumber.insert(0, '(');
			for (int i = 0; i < rawNumber.length(); i++) {
				if (i == 4) {
					rawNumber.insert(i, ") ");
				}
				if (i == 9) {
					rawNumber.insert(i, '-');
				}
			}
			return rawNumber.toString();

		} catch (NullPointerException e) {
			throw new NoSuchElementException();
		}
	}

	public static void main(String[] args) {

		String data = "BEGIN:VCARD\n" + "FN:Forrest Gump\n" + "ORG:Bubba Gump Shrimp Co.\n" + "BDAY:06-06-1944\n"
				+ "TEL;TYPE=WORK,VOICE:4951234567\n" + "TEL;TYPE=CELL,VOICE:9150123456\n" + "END:VCARD";

		ContactCard cc = new ContactCardImpl();
		cc.getInstance(data);

		System.out.println(cc.getAge());
		System.out.println(cc.getAgeYears());
		System.out.println(cc.getFullName());
		System.out.println(Long.parseLong("23476"));
		System.out.println(cc.getPhone("WORK,VOICE"));
		System.out.println(cc.isWoman());

	}
}
