import java.io.IOException;


public class MainProgram {

	public static void main(String[] args) throws IOException {
		View.ConsoleView start = new View.ConsoleView();
		Controller.UserController ctrl = new Controller.UserController();
		Model.RWFile rwFile = new Model.RWFile();
		
		ctrl.startProgram(start, rwFile);
	}

}
