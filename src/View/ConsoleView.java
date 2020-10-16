package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import Model.Boat;
import Model.Member;
import Model.Register;

public class ConsoleView implements IView {
	Scanner sc = new Scanner(System.in);
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Register register = new Register();

	public void mainMenu(Model.RWFile rwFile) throws IOException {
		register.readFile();
		int input = 0;
		while (input != 7) {
			input = menu();
			MenuOption menuOption1 = MenuOption.ADD_MEMBER;
			if (menuOption1.getCode() == input)
				regMember();
			MenuOption menuOption2 = MenuOption.ADD_BOAT;
			if (menuOption2.getCode() == input)
				regBoat();
			MenuOption menuOption3 = MenuOption.SHOW_INFO;
			if (menuOption3.getCode() == input)
				showInfo();
			MenuOption menuOption4 = MenuOption.UPDATE_INFO;
			if (menuOption4.getCode() == input)
				updateInfo();
			MenuOption menuOption5 = MenuOption.DELETE_INFO;
			if (menuOption5.getCode() == input)
				deleteInfo();
			MenuOption menuOption6 = MenuOption.CLEAR_INFO;
			if (menuOption6.getCode() == input)
				register.clearData();
			MenuOption menuOption7 = MenuOption.SAVE_INFO;
			if (menuOption7.getCode() == input) {
				register.save();
				System.out.println(" Saving and Exiting...");
				System.exit(0);

			}
		}
	}

	public int menu() {

		Scanner sc = new Scanner(System.in);
		int menuChoice = 0;
		while (true) {
			try {
				System.out.println("|-----------------------------------|");
				System.out.println("|      Welcome to the Yacht club    |");
				System.out.println("|---------------MENU----------------|");
				System.out.println("| Choose an option from below:      |");
				System.out.println("| 1. Create a Member                |");
				System.out.println("| 2. Register Boat             	    |");
				System.out.println("| 3. Show Information               |");
				System.out.println("| 4. Update Information	 	    |");
				System.out.println("| 5. Delete Information	 	    |");
				System.out.println("| 6. *CLEAR ALL DATA*	      	    |");
				System.out.println("| 7. Save and Exit		    |");
				System.out.println("|-----------------------------------|");
				System.out.print("Enter a number: ");
				menuChoice = sc.nextInt();
			} catch (InputMismatchException ex) {
				System.out.println("Error, Please Enter a Valid Number: ");
				sc.next();
			}
			return menuChoice;
		}
	}

	public void regMember() throws IOException {
		int uniqueId = (int) (System.currentTimeMillis() & 0xffffff);// generate unique id
		register.regMem(uniqueId, userEntersName(), userEntersPersNum());
		System.out.println("Your Unique ID (UID): " + uniqueId);
		System.out.println("Member Registered Successfully");
	}


public void regBoat() throws IOException {// Registering boats
        System.out.println("\n\t--Register a Boat--");
        int id = userEntersUniqueId();
        if (register.findMem(id) != null) {// Checking it with UID in the member list
            Member t = register.findMem(id);
            System.out.print("How Many Boats Would Like to Register: ");
            int boats = sc.nextInt();
            if(boats>0) {
                addBoats(boats, t);
            }else {
                System.out.println("Error, You have to register at least 1 boat");
                System.out.print("How Many Boats Would Like to Register: ");
                boats = sc.nextInt();
                addBoats(boats, t);
            }
        }
    }

    public void addBoats(int boats, Member t) {

        for (int j = 0; j < boats; j++) {
            System.out.print("\nChoose a Boat Type (1-4): ");
            System.out.println("\n1. Sailboat" + "\n2. Motorsailer" + "\n3. Kayak" + "\n4. Other");
            System.out.print("Enter Number: ");
            int type = sc.nextInt();
            System.out.print("Enter Boat Length in Meters: ");
            double length = sc.nextDouble();
            if (type == 1) {

                t.registerBoat(length, "SailBoat");
            }
            if (type == 2) {
                t.registerBoat(length, "MotorSailer");
            }
            if (type == 3) {
                t.registerBoat(length, "Kayak");

            }
            if (type == 4) {
                t.registerBoat(length, "Other");
            }
        }
    }

	public void showInfo() throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("\t--Show Information--\n");
		System.out.println("Choose an option from below");
		System.out.println("  1. Show Members List");
		System.out.println("  2. Show Members Info");
		System.out.print("\nEnter a number: ");

		int choice3 = sc.nextInt();

		if (choice3 == 1) {
			System.out.println("\nChoose a List");
			System.out.println("1. Compact List");
			System.out.println("2. Verbose List");
			System.out.print("\nEnter a number: ");
			int list = sc.nextInt();

			if (list == 1) {
				printCompactList();
			}
			if (list == 2) {
				printVerboseList();
			}
		}
		if (choice3 == 2) {
			specMemInfo();
		}
		return;
	}

	public void printCompactList() {
		System.out.println("\n||   UID  ||   Name   ||  No. Of Boats  ||");
		System.out.println("------------------------------------------");
		for (Member m : register.getMemberList()) {
			System.out.println("||" + Integer.toString(m.getUID()) + "||" + m.getName() + "||"
					+ Integer.toString(m.getNoOfBoats()) + "||");
		}
	}

	public void printVerboseList() {

		System.out.println("\n---------------------------------------------");
		for (Member m : register.getMemberList()) {
			System.out.println("||   UID  ||   Name   ||   Personal Number  ||");
			System.out.println();
			System.out.println("||" + Integer.toString(m.getUID()) + "||" + m.getName() + "||" + m.getPN() + "||");
			System.out.println("   Boat Length(m)   --   Boat Type   ");
			for (Boat b : m.getBoatList()) {
				System.out.println(
						"     	 " + Double.toString(b.getBoatLength()) + "	    ||   " + b.getBoatType() + "      ");
			}
			System.out.println("----------------------------------------\n");
		}
		System.out.println();
	}

	public void specMemInfo() throws NumberFormatException, IOException {
		int id = userEntersUniqueId();
		System.out.println("\n||   UID  ||   Personal Number   ||   Name  ||");
		Member t = register.findMem(id);
		System.out.println(t.getUID() + "  " + t.getName() + "  " + t.getPN() + "  " + t.getNoOfBoats());
		System.out.println("   Boat Length(m)   --   Boat Type   ");
		for (Boat b : t.getBoatList()) {
			System.out.println("       " + b.getBoatLength() + "              " + b.getBoatType());
		}
		System.out.println();
	}

	public void deleteInfo() throws IOException {

		Scanner sc = new Scanner(System.in);
		System.out.println("\t--Delete Information--\n");
		System.out.println("1. Delete Member");
		System.out.println("2. Delete Boat");
		System.out.println("Enter Number: ");
		int di = sc.nextInt();
		if (di == 1) {
			deleteMem();
		}
		if (di == 2) {
			deleteBoat();
		}
		return;
	}

	public void deleteMem() throws IOException {

		if (register.findMem(userEntersUniqueId()) != null) {
			register.deleteMem(register.findMem(userEntersUniqueId()));
			System.out.println("Member deleted successfully");

		}

	}

	public void deleteBoat() throws IOException {
		int id = userEntersUniqueId();
		Member t = register.findMem(id);
		if (register.findMem(id) != null) {
			System.out.println("Which boat do you want to remove? ");
			String bt = sc.next();
			for (Boat b : t.getBoatList()) {
				if (bt.equalsIgnoreCase(b.getBoatType()))

				{
					t.deleteBoat(b);
					System.out.println("Boat successfully deleted");
					break;
				}
			}

		}
	}

	public void updateInfo() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("\t--update Information--\n");
		System.out.println("Choose an option from below");
		System.out.println("  1. Update Member");
		System.out.println("  2. Update Boat");
		System.out.print("\nEnter a number: ");
		int info = sc.nextInt();

		if (info == 1) {
			updateMemInfo();
		}
		if (info == 2) {
			updateBoat();
		}
	}

	public void updateMemInfo() throws IOException {
		if (register.findMem(userEntersUniqueId()) != null) {
			register.updateMem(register.findMem(userEntersUniqueId()), userEntersName(), userEntersPersNum());
		}

	}

	public void updateBoat() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.print("What is your UID : ");
		int id = sc.nextInt();
		for (Member m : register.getMemberList()) {// Checking through the member
			if (id == m.getUID()) {
				System.out.println("Which boat details do you want to update? ");
				String bt = sc.next();
				for (Boat b : m.getBoatList()) {
					if (bt.equalsIgnoreCase(b.getBoatType())) {
						String btName = sc.next();
						System.out.println("Length of Boat: ");
						Double btLength = sc.nextDouble();
						m.updateBoat(btLength, btName, b);
						System.out.println("Boat successfully updated");
						break;
					}
				}

				break;
			}
		}

	}

	// XXXXX----INPUT-----XXXXX

	public int userEntersUniqueId() throws NumberFormatException, IOException

	{
		boolean error = true;
		int id=0;
		do {
			try {
				System.out.println("What is your UID : ");
				id = Integer.parseInt(br.readLine());
				error = false;
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid Number try Again");
			}
		}while (error);
		return id;
	}

	public String userEntersName() throws IOException {
		System.out.print("\nEnter Full Name: ");
		String name = br.readLine();
		return name;

	}

	public String userEntersPersNum() throws IOException {
		int intPNum = 0;
		boolean error = true;
		do {
			try {
				System.out.print("Enter the Personal Number (YYMMDDXXXX): ");
				intPNum = Integer.parseInt(br.readLine());
				error = false;
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid Number try Again:");
			}
		}while (error);
		return Integer.toString(intPNum);
	}

}