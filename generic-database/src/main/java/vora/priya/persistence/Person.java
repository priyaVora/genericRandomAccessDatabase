package vora.priya.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Person implements Storable {
	private String firstName;
	private String lastName;
	private String primaryEmailAddress;
	private String secondaryEmailAddress;
	private String primaryPhone;
	private String secondaryPhone;

	public Person(String firstName, String lastName, String primaryEmailAddress, String secondaryEmailAddress,
			String primaryPhoneNumber, String secondaryPhoneNumber) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setPrimaryEmailAddress(primaryEmailAddress);
		this.setSecondaryEmailAddress(secondaryEmailAddress);
		this.setPrimaryPhone(primaryPhoneNumber);
		this.setSecondaryPhone(secondaryPhoneNumber);
	}

	public Person() {

	}
	
	public Object deserialize(String theContactData) {

		String firstNameline = theContactData.substring(0, 255).trim().replace(" ", "");
		String lastNameLine = theContactData.substring(255, 510).trim().replace(" ", "");
		String primaryEmailLine = theContactData.substring(510, 765).trim().replace(" ", "");
		String secondaryEmailAddressLine = theContactData.substring(765, 1020).trim().replace(" ", "");
		String primaryPhoneLine = theContactData.substring(1020, 1032).trim().replace(" ", "");
		String secondaryPhoneLine = theContactData.substring(1032, 1044).trim().replace(" ", "");

		Person p = new Person(firstNameline, lastNameLine, primaryEmailLine, secondaryEmailAddressLine,
				primaryPhoneLine, secondaryPhoneLine);
		return p;
	}

	public int serializedSize() {
		int serializedS = 255 + 255 + 255 + 255 + 12 + 12;
		return serializedS;
	}

	public String serialize() {
		String formatted = String.format("%255s%255s%255s%255s%12s%12s", this.getFirstName(), this.getLastName(),
				this.getPrimaryEmailAddress(), this.getSecondaryEmailAddress(), this.getPrimaryPhone(),
				this.getSecondaryPhone());
		return formatted;
	}

	@Override
	public String toString() {
		return "Contact firstName =" + firstName + ", lastName =" + lastName + ", primaryEmailAddress ="
				+ primaryEmailAddress + ", secondaryEmailAddress =" + secondaryEmailAddress + ", primaryPhone ="
				+ primaryPhone + ", secondaryPhone =" + secondaryPhone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPrimaryEmailAddress() {
		return primaryEmailAddress;
	}

	public void setPrimaryEmailAddress(String primaryEmailAddress) {
		this.primaryEmailAddress = primaryEmailAddress;
	}

	public String getSecondaryEmailAddress() {
		return secondaryEmailAddress;
	}

	public void setSecondaryEmailAddress(String secondaryEmailAddress) {
		this.secondaryEmailAddress = secondaryEmailAddress;
	}

	public String getPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public String getSecondaryPhone() {
		return secondaryPhone;
	}

	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

}

//
//	public List<Integer> searchBy(String searchByAttribute, Map<String, List<Integer>>searchByHash) throws ClassNotFoundException, IOException { 
//
//		List<Integer> searchByIndecies = searchByHash.get(searchByAttribute);
//		List<Person> searchByResults = new ArrayList<>();
//		
//		
//		if (searchByIndecies == null) {
//			System.out.println("THIS WAS NULL");
//			return searchByResults;
//		}
//		for (Integer index : searchByIndecies) {
//			Person c = this.lookup(index); // look up method
//			searchByResults.add(c);
//		}
//		return searchByResults;
//	}