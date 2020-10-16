package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Register {
	private ArrayList<Member> memList = new ArrayList<Member>();
	private RWFile rwFile = new RWFile();

	/*
	 * To read the file from the text file and copy to list
	 */
	public void readFile() throws IOException {
		memList = rwFile.textToList();
	}

	/*
	 * Add the member details to memlist
	 */
	public void regMem(int uniqueID, String name, String persNum) {
		this.memList.add(new Member(uniqueID, name, persNum));
	}

	public ArrayList<Member> getMemberList() {
		return new ArrayList<Member>(memList);
	}

	public void setMemberList(ArrayList<Member> memList) {
		this.memList = memList;
	}

	/*
	 * to update the information of member
	 */
	public void updateMem(Member m, String name, String persNum) {
		m.setName(name);
		m.setPN(persNum);
	}

	public void deleteMem(Member m) {
		this.memList.remove(m);
	}

	/*
	 * finding the member using unique id and returning the member
	 */
	public Member findMem(int uniqueID) {
		for (Member m : this.memList) {
			if (m.getUID() == uniqueID)
				return m;
		}
		return null;
	}

	/*
	 * Save the details
	 */
	public void save() throws IOException {
		rwFile.regMemberTxt(memList);
		rwFile.regBoatTxt(memList);
	}

	/*
	 * Clear data
	 */
	public void clearData() throws IOException {
		FileWriter clearMem = new FileWriter("Members.txt", false);
		FileWriter clearBoat = new FileWriter("Boats.txt", false);
		PrintWriter clearMem1 = new PrintWriter(clearMem, false);
		PrintWriter clearBoat1 = new PrintWriter(clearBoat, false);
		clearMem1.flush();
		clearMem1.close();
		clearMem.close();
		clearBoat1.flush();
		clearBoat1.close();
		clearBoat.close();
		memList.clear();
	}
}
