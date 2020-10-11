package View;

import java.io.IOException;

public interface IView {
	
//Controller of the main menu with feature 
	void mainMenu() throws IOException;

//printing of main menu with all features
	int menu();
	
//displaying the registration of members
	void regMember();

//displaying the registration of boats
	void regBoat() throws IOException;

//displaying info for user to choose list or specific member info
	void showInfo();
	
//displays compact list
	void printCompactList();

//displays verbose list
	void printVerboseList();

//displays specific member's info
	void specMemInfo();

//displaying deletion of member of boats
	void deleteInfo() throws IOException;

//deletes member and it's boats
	void deleteMem() throws IOException;

//deletes member's boats
	void deleteBoat() throws IOException;

//displays update of member's info or boat
	void updateInfo() throws IOException;

//displays update members info
	void updateMemInfo() throws IOException;

//displays update boat info
	void updateBoat() throws IOException;
	
}
