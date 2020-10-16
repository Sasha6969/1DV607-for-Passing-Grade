package View;

public enum MenuOption {
	ADD_MEMBER(1, "Create a Member"), ADD_BOAT(2, "Register Boat"), SHOW_INFO(3, "Show Information"),
	UPDATE_INFO(4, "Update Information"), DELETE_INFO(5, "Delete Information"), CLEAR_INFO(6, "Clear Information"),
	SAVE_INFO(7, "Save and Exit");

	private int code;
	private String message;

	MenuOption(int i, String s) {
		this.code = i;
		this.message = s;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}