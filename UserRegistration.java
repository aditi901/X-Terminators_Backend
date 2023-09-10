package userRegistration;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserRegistration {
	private static Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerUser(Scanner scanner) {
        System.out.print("Enter Email: ");
        String email = scanner.next();
        System.out.print("Enter Role: ");
        String role = scanner.next();
        System.out.print("Enter Password: ");
        String password = scanner.next();
        
        
		if (!users.containsKey(email)) {
            User user = new User(email, password, role);
            users.put((String) email, user);
            System.out.println("Registration successful!");
        } else {
            System.out.println("This user already exists. Please choose a different username.");
        }
    }
}

