import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.xml.crypto.Data;

import org.junit.Test;

import vora.priya.persistence.Animal;
import vora.priya.persistence.Database;
import vora.priya.persistence.Person;

public class GenerticsTester {
	public int MAX_ARRAY_VALUE = 3000;
	Database<Person> cd;
	Database<Animal> animalDatabase;
	
	String personPathFile = "Person_DataBase";
	String animalPathFile = "Animal_DataBase";
	
	Random rand = new Random();

	String[] firstNamesArray = new String[3000];
	String[] lastNamesArray = new String[3000];
	String[] primaryEmailsArray = new String[3000];
	String[] secondaryEmailsArray = new String[3000];
	String[] primaryPhonesArray = new String[3000];
	String[] secondaryPhonesArray = new String[3000];

	public String[] nameMaker(String txtFileName) throws FileNotFoundException {
		File path = new File("C:\\Users\\Prvora89\\workspace_open_source\\generic-database\\" + txtFileName);

		Scanner file = new Scanner(path);
		String[] nameType = new String[100];
		for (int i = 0; i < nameType.length; i++) {
			nameType[i] = file.nextLine();
			nameType[i].trim().replaceAll(" ", "");
		}
		return nameType;
	}

	public String[] emailTypeMaker(String suffix) throws FileNotFoundException {

		String[] emailType = new String[100];

		for (int i = 0; i < emailType.length; i++) {
			emailType[i] = firstNamesArray[i] + lastNamesArray[i] + i + suffix;
		}
		return emailType;
	}

	public String[] phoneNumberGenerator() {
		Random rand = new Random();
		String[] numArray = new String[] { "234-234-345", "346-234-345", "423-923-434" };
		for (int i = 0; i < 3000; i++) {
			primaryPhonesArray[i] = numArray[rand.nextInt(3)];
		}
		// System.out.println("Generated Numbers is : " + number);
		return primaryPhonesArray;
	}

	public void setInformation() throws IOException, ClassNotFoundException {
		cd = new Database(personPathFile);

		firstNamesArray = nameMaker("firstnames.txt");
		lastNamesArray = nameMaker("lastnames.txt");

		primaryEmailsArray = emailTypeMaker("@gmail.com");
		secondaryEmailsArray = emailTypeMaker("@yahoo.com");

		primaryPhonesArray = phoneNumberGenerator();
		secondaryPhonesArray = phoneNumberGenerator();
	}

	public Database<Person> makeContactToDatabase() throws IOException, ClassNotFoundException {
		setInformation();
		cd = new Database<Person>(personPathFile);
		Person addContact = null;
		for (int i = 0; i < 2; i++) {
			int randomNum = rand.nextInt(99);
			int randomGen = rand.nextInt(99);
			addContact = new Person(firstNamesArray[randomNum], lastNamesArray[randomNum],
					primaryEmailsArray[randomGen], secondaryEmailsArray[randomGen], primaryPhonesArray[randomGen],
					secondaryPhonesArray[randomGen]);
			//System.out.println((i + 1) + addContact.toString());
			cd.insert(addContact);
		}
		return cd;
	}
	
	public Database<Animal> animalDatabase() throws IOException  {
		animalDatabase = new Database<>(animalPathFile);
		
		Animal a1 = new Animal("Dog","Black",true, true, true);
		Animal a2 = new Animal("Cat", "White", false, false, false);
		animalDatabase.insert(a1);
		animalDatabase.insert(a2);
		return animalDatabase;
	}

	
	@Test 
	public void addAnimal() throws IOException, ClassNotFoundException { 
		animalDatabase = animalDatabase();
		Animal thirdAnimal = new Animal("Mouse", "Grey", true, false, true);
		System.out.println("Created Animal: " + thirdAnimal);
		animalDatabase.insert(thirdAnimal);
	}
	
	@Test 
	public void addPerson() throws IOException, ClassNotFoundException { 
		cd = makeContactToDatabase();
		Person p = new Person("Priya", "Vora", "prvora89@gmail.com", "prvora89@yahoo.com", "14154104891", "14155745501");
		System.out.println("Created Person: " + p);
		cd.insert(p);
	}
	
	@Test
	public void lookupAnimal() throws IOException  {
		Database<Animal> ad = new Database<>(animalPathFile);
		Animal lookUpContact = new Animal();
		long startTime = System.currentTimeMillis();
		Animal returnedAnimal = (Animal) ad.lookup(2, lookUpContact);
		System.out.println("Returned Animal: " + returnedAnimal.toString());
		assertTrue(returnedAnimal.getAnimalType().equals("Mouse"));
		long endTime = System.currentTimeMillis();
		long elaspedTime = endTime - startTime;
		System.out.println("\nElasped Time: " + elaspedTime + " milliseconds\n");
	}
	
	
	
	
	@Test
	public void lookupPerson() throws IOException  {
		Database<Person> personDatabase = new Database<>(personPathFile);
		Person lookUpContact = new Person();
		long startTime = System.currentTimeMillis();
		Person returnedPerson = (Person) personDatabase.lookup(2, lookUpContact);
			System.out.println("Returned Person: " + returnedPerson.toString());
			assertTrue(returnedPerson.getFirstName().equals("Priya"));
		long endTime = System.currentTimeMillis();
		long elaspedTime = endTime - startTime;
		System.out.println("\nElasped Time: " + elaspedTime + " milliseconds\n");
	}
	
	@Test
	public void removeAnimal() throws IOException { 
		Database<Animal> ad = new Database<>(animalPathFile);
		Animal a1 = new Animal();
		System.out.println("Before Remove: ");
		System.out.println(ad.lookup(1, a1));
		ad.remove(1, a1);
		System.out.println("After Remove: ");
		System.out.println(ad.lookup(1, a1));
		
	}
	
	@Test
	public void removePerson() throws IOException { 
		Database<Person> personA = new Database<>(personPathFile);
		Person a1 = new Person();
		System.out.println("Before Remove: ");
		System.out.println(personA.lookup(1, a1));
		personA.remove(1, a1);
		System.out.println("After Remove: ");
		System.out.println(personA.lookup(1, a1));
		
	}

}
