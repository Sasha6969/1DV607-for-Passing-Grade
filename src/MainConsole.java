

import java.io.IOException;

import View.ConsoleView;

public class MainConsole {

	public static void main(String[] args) throws IOException {
		ConsoleView start = new ConsoleView();
		Model.RWFile rwFile = new Model.RWFile();

		start.mainMenu(rwFile);
	}

}
