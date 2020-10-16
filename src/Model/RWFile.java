package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class RWFile {
	
	//protected ArrayList<Member> memList = new ArrayList<>();
	private File memFile = new File("Members.txt");
	private File boatFile = new File("Boats.txt");
	

	public void regMemberTxt(ArrayList<Member> memList) throws IOException {// Adding the members to text file
		if (!memFile.exists()) {
			memFile.createNewFile();
			try {
				FileWriter fileWriter = new FileWriter(memFile);
				BufferedWriter output = new BufferedWriter(fileWriter);
				for (Member m : memList) {
					output.write(m.getUID() + ";" + m.getName() + ";" + m.getPN() + "\n");

				}
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				FileWriter fileWriter = new FileWriter(memFile);
				BufferedWriter output = new BufferedWriter(fileWriter);
				for (Member m : memList) {
					output.write(m.getUID() + ";" + m.getName() + ";" + m.getPN() + "\n");
				}
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void regBoatTxt(ArrayList<Member> memList) throws IOException {// Adding boats to text

		if (!boatFile.exists()) {
			boatFile.createNewFile();
			try {
				FileWriter myWriter = new FileWriter(boatFile.getPath());
				Writer output = new BufferedWriter(myWriter);
				for (Member m : memList) {
					if (m.getNoOfBoats() != 0) {// To store only if the member has boats
						for (Boat b : m.getBoatList())
							output.write(Integer.toString(m.getUID()) + ";" + b.getBoatType() + ";"
									+ Double.toString(b.getBoatLength()) + "\n");
					}
				}

				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				FileWriter myWriter = new FileWriter(boatFile.getPath());
				Writer output = new BufferedWriter(myWriter);
				for (Member m : memList) {
					if (m.getNoOfBoats() != 0) {// To store only if the member has boats
						for (Boat b : m.getBoatList())
							output.write(Integer.toString(m.getUID()) + ";" + b.getBoatType() + ";"
									+ Double.toString(b.getBoatLength()) + "\n");
					}
				}

				output.close();
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
	}
	
	public ArrayList<Member> textToList() throws IOException {
		
		textFilesExists();
		ArrayList<Member> memList=new ArrayList<Member>();

				String line = "";
				FileInputStream file = new FileInputStream("Members.txt");
				Scanner scan = new Scanner(file);
				while (scan.hasNextLine()) {// reading the members file

					line = scan.nextLine() + ";";
					String[] part = line.split(";");// parting for members text file
					memList.add(new Member(Integer.parseInt(part[0]), part[1], part[2]));

				}
				for (Member m : memList) {
					String boatLine = "";
					FileInputStream boatFile = new FileInputStream("Boats.txt");
					Scanner boatScan = new Scanner(boatFile);
					while (boatScan.hasNextLine()) {
						boatLine = boatScan.nextLine() + ";";
						String[] partBoat = boatLine.split(";");// parting for boats text file

						if (Integer.parseInt(partBoat[0]) == m.getUID()) {
							m.registerBoat(Double.parseDouble(partBoat[2]), partBoat[1]);

						}
					}boatScan.close();
				}
				scan.close();
				
				return memList;
			}
		
	
	
	public void textFilesExists() throws IOException {
		
		if (!memFile.exists()) {
			memFile.createNewFile();
		}
		
		if (!boatFile.exists()) {
			boatFile.createNewFile();
		}		
		
	}
		
}
