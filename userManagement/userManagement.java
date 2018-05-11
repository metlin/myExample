import java.util.Scanner;
import java.util.ArrayList;

class User {
	String name;
	String surname;
	String dateOfBirth;
	String login;
	String password;
	String aboutOneself;
	String adress;

	public static ArrayList<User> userList = new ArrayList<User>();

	void createUser() {
		System.out.print("Enter user name: ");
		Scanner scName = new Scanner(System.in);
		name = scName.nextLine();

		System.out.print("Enter user surname: ");
		Scanner scSurname = new Scanner(System.in);
		surname = scSurname.nextLine();

		System.out.print("Enter user dateOfBirth: ");
		Scanner scDateOfBirth = new Scanner(System.in);
		dateOfBirth = scDateOfBirth.nextLine();

		System.out.print("Enter user login: ");
		Scanner scLogin = new Scanner(System.in);
		login = scLogin.nextLine();

		System.out.print("Enter user password: ");
		Scanner scPassword = new Scanner(System.in);
		password = scPassword.nextLine();

		System.out.print("Enter aboutOneself: ");
		Scanner scAboutOneself = new Scanner(System.in);
		aboutOneself = scAboutOneself.nextLine();

		System.out.print("Enter user adress: ");
		Scanner scAdress = new Scanner(System.in);
		adress = scAdress.nextLine();
	
		userList.add(this);
	}

	String printUser() {
		String str = (name + " " + surname + " " + dateOfBirth + " " + login + " " + password + " "
		+ aboutOneself +  " " + adress);
		return str;
	}

	static void viewlist() {
		for (int i = 0; i < userList.size(); i++) {
			System.out.println(userList.get(i).printUser());
		}	
	}

	void deleteUser(int index) {
		userList.remove(index);
	} 

	int searchUser(String str) {
		for (int i = 0; i < userList.size(); i++) {
			if (str.compareTo(userList.get(i).name) == 0) {
				return i;
			}
		}
		return -1;
	}
}

class userManagement {
	public static void main(String[] args) {
		int i = 0;
		while (i != 1) { //тут можно по - другому придумать
		
			String action = new String();
			while (action.compareTo("createUser") != 0 && action.compareTo("viewlist") != 0
			&& action.compareTo("editUser") != 0 && action.compareTo("deleteUser") != 0) { 
				System.out.print("Enter the action: (createUser, viewlist, editUser, deleteUser) - ");
				Scanner sc = new Scanner(System.in);
				action = sc.nextLine();
			}

			switch(action) {
				case "createUser":
					User user = new User();
					user.createUser();
					break;

				case "viewlist":
					User.viewlist();
					break;

				case "editUser":
					User.viewlist();
					User editUser = new User(); 
					String strEdit = new String();
					
					System.out.println("Enter edit user name");
					Scanner scann = new Scanner(System.in);
					strEdit = scann.nextLine();

					int indexEdit = editUser.searchUser(strEdit);
					if (indexEdit >= 0) {
						editUser.deleteUser(indexEdit);
						editUser.createUser();
					}
					else {
						System.out.println("Such user is not in the list");
					}
					break;

				case "deleteUser":
					User.viewlist();
					User delUser = new User();
					String str = new String();
					
					System.out.println("Enter delete user name");
					Scanner scan = new Scanner(System.in);
					str = scan.nextLine();

					int index = delUser.searchUser(str);
					if (index >= 0) {
						delUser.deleteUser(index);
					}
					else {
						System.out.println("Such user is not in the list");
					}
					break;
			}
		}
	}
}