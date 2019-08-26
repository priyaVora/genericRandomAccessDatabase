package vora.priya.persistence;

public class Animal implements Storable{
	private String animalType;
	private String colorType;
	private boolean canFly;
	private boolean hasTail;
	private boolean isWild;
	
	
	public Animal(String animalType, String colorType, boolean canFly, boolean hasTail, boolean isWild) {
		this.animalType = animalType;
		this.colorType = colorType;
		this.canFly = canFly;
		this.hasTail = hasTail;
		this.isWild = isWild; 
	}
	
	public Animal() { 
		
	}

	public Animal deserialize(String theContactData) {
		String animalTypeLine = theContactData.substring(0, 255).trim().replace(" ", "");
		String colorTypeLine = theContactData.substring(255, 510).trim().replace(" ", "");
		String canFlyLine = theContactData.substring(510, 515).trim().replace(" ", "");
		String hasTailLine = theContactData.substring(515, 520).trim().replace(" ", "");
		String isWildLine = theContactData.substring(520, 525).trim().replace(" ", "");
		

		if(canFlyLine.equals("true")) { 
			canFly = true;
		} else { 
			canFly = false;
		}
		
		if(hasTailLine == "true") { 
			hasTail = true;
		} else { 
			hasTail = false;
		}
		
		if(isWildLine == "true") { 
			isWild = true;
		} else { 
			isWild = false;
		}
		
		
		Animal animal = new Animal(animalTypeLine, colorTypeLine, canFly, hasTail, isWild);
		return animal;
	}

	public int serializedSize() {
		int sizeS = 255+255+5+5+5;
		return sizeS;
	}
	
	public String serialize() { 
		String formatted =  String.format("%255s%255s%5b%5b%5b", this.animalType, this.colorType, this.canFly, this.hasTail, this.isWild);
		return formatted;
	}
	
	@Override
	public String toString() {
		return "Animal animalType =" + animalType + ", colorType =" + colorType + ", canFly =" + canFly + ", hasTail =" + hasTail + ", isWild =" + isWild;
	}

	public String getAnimalType() {
		return animalType;
	}

	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}

	public String getColorType() {
		return colorType;
	}

	public void setColorType(String colorType) {
		this.colorType = colorType;
	}

	public boolean isCanFly() {
		return canFly;
	}

	public void setCanFly(boolean canFly) {
		this.canFly = canFly;
	}

	public boolean isHasTail() {
		return hasTail;
	}

	public void setHasTail(boolean hasTail) {
		this.hasTail = hasTail;
	}

	public boolean isWild() {
		return isWild;
	}

	public void setWild(boolean isWild) {
		this.isWild = isWild;
	}

	

	

	
	}
	
	