package contacts;

//import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

interface ContactFactory{
    Contact inputContact(Scanner scanner);
}

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String filename = "C:\\Users\\shred\\IdeaProjects\\Contacts (Java)\\Contacts (Java)\\task\\"+"phonebook.db";
        PhoneBook phoneBook;// = new PhoneBook();

        if(args.length==0 || args[0]==null || args[0].isEmpty() ){
            File file = new File(filename);
            file.delete();
        }else{
            filename = args[0];
        }

        try {
            phoneBook = (PhoneBook) SerializationUtils.deserialize(filename);
            System.out.println("open "+filename);
        }catch (IOException e){
            phoneBook = new PhoneBook();
        }

        Contact contact; // = new Contact();
        ContactFactory organizationContactFactory = new OrganizationContactFactory();
        ContactFactory personContactFactory = new PersonContactFactory();

        Scanner scanner = new Scanner(System.in);
        String action;
        do {
            System.out.println("[menu] Enter action (add, list, search, count, exit):");
            action = scanner.nextLine();
            switch (action.toLowerCase()){
                case "add" -> {
                    System.out.println("Enter the type (person, organization):");
                    action = scanner.nextLine();
                    if (action.equalsIgnoreCase("person")) {
                        contact = personContactFactory.inputContact(scanner);
                    } else {
                        contact = organizationContactFactory.inputContact(scanner);
                    }
                    System.out.println("The record added.");
                    phoneBook.addContact(contact);
                }
                case "list"   -> phoneBook.showList(scanner);
                case "search" -> phoneBook.search(scanner);
                //case "remove" -> phoneBook.remove(scanner);
                //case "edit"   -> phoneBook.edit(scanner);
                case "count"  -> phoneBook.count();
            }
            SerializationUtils.serialize(phoneBook, filename);
            System.out.println();
        } while(! action.equalsIgnoreCase("exit") );
    }
}
