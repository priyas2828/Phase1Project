package lockerApplication;

import java.io.File;
//import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import userHub.UserCredentials;
import userHub.Users;

public class Auth {
	
	
	private static Scanner texttype;
	private static Scanner input;
	private static Scanner lockerInput; 
	private static PrintWriter output;
	private static PrintWriter lockerOutput;
	private static Users users;
	private static UserCredentials userCredentials;
	
	
	public static void main(String[] args) {
		initApp();
		welcomeScreen();
		signInOptions();

	}
	public static void signInOptions() {
		
		System.out.println("1 . Registration ");
		System.out.println("2 . Login ");
		int option = texttype.nextInt();
		switch(option) {
			case 1 : 
				registerUser();
				break;
			case 2 :
				loginUser();
				break;
			default :
				System.out.println("Please select 1 or 2");
				break;
		}
		
		texttype.close();
		input.close();
	}
	
	public static void lockerOptions(String inpUsername) {
		System.out.println("1 . FETCH ALL STORED CREDENTIALS ");
		System.out.println("2 . STORED CREDENTIALS ");
		int option = texttype.nextInt();
		switch(option) {
			case 1 : 
				fetchCredentials(inpUsername);
				break;
			case 2 :
				storeCredentials(inpUsername);
				break;
			default :
				System.out.println("Please select 1 Or 2");
				break;
		}
		lockerInput.close();
	}
	
	public static void registerUser() {
		System.out.println("==========================================");
		System.out.println("*   WELCOME TO REGISTRATION PAGE	*");
		System.out.println("==========================================");
		
		System.out.println("Enter Username :");
		String username = texttype.next();
		users.setUsername(username);
		
		System.out.println("Enter Password :");
		String password = texttype.next();
		users.setPassword(password);
		
		output.println(users.getUsername());
		output.println(users.getPassword());
		
		System.out.println("User Registration Suscessful !");
		System.out.println("Welcome User :)");
		output.close();
		
	}
	public static void loginUser() {
		System.out.println("==========================================");
		System.out.println("*   Please Login Here	 *");
		System.out.println("==========================================");
		System.out.println("Enter Username :");
		String inpUsername = texttype.next();
		boolean found = false;
		while(input.hasNext() && !found) {
			if(input.next().equals(inpUsername)) {
				System.out.println("Enter Password :");
				String inpPassword = texttype.next();
				if(input.next().equals(inpPassword)) {
					System.out.println("Login Successful ! 200OK");
					found = true;
					lockerOptions(inpUsername);
					break;
				}
			}
		}
		if(!found) {
			System.out.println("User Not Found : Login Failure : 404");
		}
		
	}
	
	public static void welcomeScreen() {
		System.out.println("==========================================");
		System.out.println("*   Welcome To LockMe.com		*");
		System.out.println("*   Your Personal Digital Locker	*");
		System.out.println("==========================================");
		
	}
	
		
	
	public static void initApp() {

		File  dbFile = new File("DellEMC.txt");
		File  lockerFile = new File("storage.txt");
		
		try {
			//read data from db file
			input = new Scanner(dbFile);
			
			//red data from locker file
			lockerInput = new Scanner(lockerFile);
			
			//read data from keyboard
			texttype = new Scanner(System.in);
			
			//out put 
			output = new PrintWriter( new FileWriter(dbFile,true));
			lockerOutput = new PrintWriter( new FileWriter(lockerFile,true));
			
			users = new Users();
			userCredentials  = new UserCredentials();
			
			
		} catch (IOException e) {
			System.out.println("404 : File Not Found ");
		}
		
	}
	
	//store credentails
		public static void storeCredentials(String loggedInUser) {
			System.out.println("==========================================");
			System.out.println("*   Store your creds here :	 *");
			System.out.println("==========================================");
			
			userCredentials.setLoggedInUser(loggedInUser);
			
			System.out.println("Enter Site Name :");
			String siteName = texttype.next();
			userCredentials.setSiteName(siteName);
			
			System.out.println("Enter Username :");
			String username = texttype.next();
			userCredentials.setUsername(username);
			
			System.out.println("Enter Password :");
			String password = texttype.next();
			userCredentials.setPassword(password);
			
			lockerOutput.println(userCredentials.getLoggedInUser());
			lockerOutput.println(userCredentials.getSiteName());
			lockerOutput.println(userCredentials.getUsername());
			lockerOutput.println(userCredentials.getPassword());
			
			
			System.out.println("Your security is our responsiblility :)");
			lockerOutput.close();		
			
		}
		
		//fetch credentials
		public static void fetchCredentials(String inpUsername) {
			System.out.println("==========================================");
			System.out.println("*   Welcome to Digital locker 	 *");
			System.out.println("*   Your Creds are : 	 *");
			System.out.println("==========================================");
			System.out.println(inpUsername);
			
			
			while(lockerInput.hasNext()) {
//				System.out.println(lockerInput.hasNext());
				if(lockerInput.next().equals(inpUsername)) {
					System.out.println("Site Name: "+lockerInput.next());
					System.out.println("User Name: "+lockerInput.next());
					System.out.println("User Password: "+lockerInput.next());
					
				}
			}
}
}
