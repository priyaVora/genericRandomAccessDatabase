package vora.priya.persistence;

public interface Storable {
	String serialize();
	Object deserialize(String data);
	int serializedSize();
	
	
}
