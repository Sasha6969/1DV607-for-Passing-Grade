package Model;

public class Boat {

	private double boatLength;
	private String boatType;

	public double getBoatLength() {
		return boatLength;
	}

	public void setBoatLength(double boatLength) {
		this.boatLength = boatLength;
	}

	public String getBoatType() {
		return boatType;
	}

	public void setBoatType(String boatType) {
		this.boatType = boatType;
	}

	public Boat(double boatLength, String boatType) {
		this.boatLength = boatLength;
		this.boatType = boatType;
	}
}