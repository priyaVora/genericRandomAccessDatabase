package vora.priya.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;



//import com.neufree.contacts.collections.Contact;

public class Database<T extends Storable> { // T makes this generic
	//need a interface so everything that uses this method is forced to have those methods 
	private RandomAccessFile file;
	private int nextOffSet = 8;
	
	public Database(String path) throws IOException { 
		boolean readOffsetFromFile = false;
		if(new File(path).exists()) { 
			readOffsetFromFile = true;
		}
		
		
		file = new RandomAccessFile(path, "rw");
		if(readOffsetFromFile) { 
			nextOffSet = file.readInt();
		} else { 
			file.writeInt(nextOffSet);
		}
	}
	
	public void insert(T obj) throws IOException {		
		byte[] buffer = obj.serialize().getBytes();		
		if(buffer.length != obj.serializedSize()) { 
			throw new IllegalArgumentException("serialized size is not as promised. " + buffer.length + "!= " + obj.serialize());
		}
		file.seek(nextOffSet);
		file.write(buffer);
		nextOffSet += buffer.length;
		updateNextOffSet();
	}
	
	private void updateNextOffSet() throws IOException {
		file.seek(0);
		file.writeInt(nextOffSet);
		file.seek(nextOffSet);
	}
	
	public T lookup(int index, T type) throws IOException {//Instantiate the generic type
		///you need to know the bytes to serialize the file
		//index is given you need to read x number of bytes
		
		int totalSizeOfContact = type.serializedSize();
		int findingIndex = ((index * totalSizeOfContact) + 8);
		file.seek(findingIndex);
		byte[] getInfoArray = new byte[totalSizeOfContact];
		file.read(getInfoArray);

		String theContactData= new String(getInfoArray, "UTF-8");
		
	
		type = (T) type.deserialize(theContactData);
		return type;
	}
	
	public void remove(int index, T type) throws IOException {
		int totalSizeOfContact = type.serializedSize();
		int totalSizeofFile = (int) file.length();
		
		
		int findingIndex = ((index * totalSizeOfContact) + 8);
		int endOfContact = findingIndex + totalSizeOfContact;
		
		
		file.seek(endOfContact);
		byte[] getEndInfoArray = new byte[totalSizeofFile-endOfContact];
		file.read(getEndInfoArray);
	
		file.seek(findingIndex); // where c starts
		file.write(getEndInfoArray);
	}

}

//	public List<Person> searchBy() throws ClassNotFoundException, IOException { 
//		
//		
//		List<Integer> searchByIndecies
//		if (searchByIndecies == null) {
//			System.out.println("THIS WAS NULL");
//			return searchByResults;
//		}
//		for (Integer index : searchByIndecies) {
//			Person c = this.lookup(index); // look up method
//			searchByResults.add(c);
//		}
//	}