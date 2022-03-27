package com.dondu.myprisma;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.Iterator;
import au.com.bytecode.opencsv.CSVReader;

public class LibraryRepository {

	

	
	String USERFILEPATH = "./user.csv";
	String BOOKFILEPATH = "./books.csv";
	String BORROWEDFILEPATH = "./borrowed.csv";
	
	public List<User> getActiveUsers() throws IOException{

		List<User> users = parseUserCSVFileAsList();
		List<Borrowed> borrowedList = parseBorrowedCSVFileAsList();
		List<User> activeUsers = new ArrayList<User>();
		
		for (int i = 0; i < users.size(); i++) {
			String tempFullName = users.get(i).getName().trim() + "," + users.get(i).getFirstName().trim();
			for (int k = 0; k < borrowedList.size(); k++) {
				if(tempFullName.equalsIgnoreCase(borrowedList.get(k).getBorrower().trim())
						&& !activeUsers.contains(users.get(i))) {
					activeUsers.add(users.get(i));
					break;
				}
			}
		}
		
		return activeUsers;		
		
	}
	
	public List<User> getDeActiveUsers() throws IOException{
		
		List<User> users = parseUserCSVFileAsList();
		List<User> deActiveUsers = new ArrayList<User>();
		deActiveUsers.addAll(users);
		List<Borrowed> borrowedList = parseBorrowedCSVFileAsList();

		Boolean exist = false;
	
		
		for (int i = 0; i < users.size(); i++) {
			String tempFullName = users.get(i).getName().trim() + "," + users.get(i).getFirstName().trim();
			for (int k = 0; k < borrowedList.size(); k++) {
				if(tempFullName.equalsIgnoreCase(borrowedList.get(k).getBorrower().trim())
						&& deActiveUsers.contains(users.get(i))) {
																					deActiveUsers.remove(users.get(i));
					break;
				}
			}
		}
		
		return deActiveUsers;		
		
	}

	private List<User> parseUserCSVFileAsList() throws IOException {
		URL path = LibraryRepository.class.getResource("user.csv");
		//create CSVReader object
		CSVReader reader = new CSVReader(new FileReader(path.getPath()), ',');

		List<User> users = new ArrayList<User>();
		//read all lines at once
		List<String[]> records = reader.readAll();
		
		Iterator<String[]> iterator = records.iterator();
		//skip header row
		iterator.next();
		
		while(iterator.hasNext()){
			String[] record = iterator.next();
			if(record != null && record.length>1) {
				User user = new User();
				user.setName(record[0]);
				user.setFirstName(record[1]);
				user.setMemberSince(record[2]);
				user.setMemberTill(record[3]);
				user.setGender(record[4]);
				users.add(user);
			}

		}
		
		reader.close();
		
		return users;
	}
	
	private List<Borrowed> parseBorrowedCSVFileAsList() throws IOException {
		URL path = LibraryRepository.class.getResource("borrowed.csv");
		//create CSVReader object
		CSVReader reader = new CSVReader(new FileReader(path.getPath()), ',');

		List<Borrowed> borrowedList = new ArrayList<Borrowed>();
		//read all lines at once
		List<String[]> records = reader.readAll();
		
		Iterator<String[]> iterator = records.iterator();
		//skip header row
		iterator.next();
		
		while(iterator.hasNext()){
			String[] record = iterator.next();
			if(record != null && record.length>1) {
				Borrowed borrowedItem= new Borrowed();
				borrowedItem.setBorrower(record[0]);
				borrowedItem.setBook(record[1]);
				borrowedItem.setBorrowedFrom(record[2]);
				borrowedItem.setBorrowedTo(record[3]);
				borrowedList.add(borrowedItem);
			}
		}
		
		reader.close();
		
		return borrowedList;
	}
}
