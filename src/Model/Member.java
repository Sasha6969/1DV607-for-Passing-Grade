package Model;

import java.util.ArrayList;

public class Member {

	private int UID;
	private String PN;
	private String name;
	private ArrayList<Boat> boatList = new ArrayList<>();

	public int getUID() {
		return UID;
	}

	public void setUID(int UID) {
		this.UID = UID;
	}

	public String getPN() {
		return PN;
	}

	public void setPN(String PN) {
		this.PN = PN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNoOfBoats() {
		return boatList.size();
	}

	public ArrayList<Boat> getBoatList() {
		return new ArrayList<Boat>(boatList);
	}

	public void registerBoat(double length, String type) {
		this.boatList.add(new Boat(length, type));
	}

	public void updateBoat(double length, String type, Boat boat) {
		boat.setBoatLength(length);
		boat.setBoatType(type);
	}

	public void deleteBoat(Boat boat) {
		this.boatList.remove(boat);
	}

	public Member(int UID, String name, String PN) {
		this.UID = UID;
		this.PN = PN;
		this.name = name;

	}
}