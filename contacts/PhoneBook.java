package contacts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PhoneBook implements Serializable {
    private static final long serialVersionUID = 7L;
    ArrayList<Contact> contacts;

    public PhoneBook(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public PhoneBook() {
        this.contacts = new ArrayList<Contact>() {
        };
    }

    public void addContact(Contact contact){
        this.contacts.add(contact);
    }

    public ArrayList<Contact> printList(String searchText){
        int i=0;
        String info;
        Boolean showContact = true;
        List<Contact> contacts;
        if( searchText==null || searchText.isEmpty() ) {
            contacts = this.contacts;
        }else{
            contacts = this.contacts.stream().filter(
                    contact -> {
                        return contact.toSearchString().toLowerCase().matches(".*"+searchText.toLowerCase()+".*");
                    }).collect(Collectors.toList()
            );
            System.out.printf("Found %d results:\n", contacts.size());
        }
        for (Contact contact: contacts
        ) {
            i++;
            info = contact.getName();
            //System.out.println("class="+contact.getClass().getName());
            if(contact.getClass().getName().toLowerCase().contains("person") ) {
                info += " " + ((Person) contact).getSurname();
            }
            System.out.printf("%d. %s, %s\n",i, info, contact.getPhoneNumber());
        }
        return (ArrayList)contacts;
    }

    /*public void info(Scanner scanner){
        printList(null);
        int recNum;
        String field;
        System.out.println("Enter index to show info:");
        recNum = Integer.parseInt(scanner.nextLine());
        Contact contact = this.contacts.get(recNum-1);
        System.out.println(contact);
    }*/

    public void editContact(Scanner scanner, Contact contact){
        String field;
        System.out.println("Select a field ("+contact.editableFields()+"):");
        field = scanner.nextLine();
        for (String fieldNameI: contact.getEditableFields()
        ) {
            if (field.equalsIgnoreCase(fieldNameI)) {
                System.out.printf("Enter %s\n", field);
                String newValue = scanner.nextLine();
                contact.updateEditableField(fieldNameI,newValue);
                System.out.println("The record updated!");
                field = "exit";
                break;
            }
        }
    }

    public void deleteContact(Contact contact) {
        this.contacts.remove(contact);
        System.out.println("The record removed!");
    }

    public void showContact(Scanner scanner, ArrayList<Contact> contacts){
        String action;
        String field;
        action = scanner.nextLine();
        int recNum;
        switch (action.toLowerCase()) {
            case "back" -> { return; }
            case "again" -> search(scanner);
            default -> {
                recNum = Integer.parseInt(action);
                Contact contact = contacts.get(recNum-1);
                System.out.println(contact);
                //System.out.println();
                System.out.println("[record] Enter action (edit, delete, menu):");
                action = scanner.nextLine();
                switch (action.toLowerCase()) {
                    case "edit" -> editContact(scanner, contact);
                    case "delete" -> deleteContact(contact);
                    case "menu" -> {return;}
                }
            }
        }
    }


    public void search(Scanner scanner){
        System.out.println("Enter search query:");
        String searchText = scanner.nextLine();
        ArrayList<Contact> contacts = printList(searchText);
        //int recNum;
        String field;
        //String action;
        //do {
            /*System.out.println("[search] Enter action ([number], back, again):");
            action = scanner.nextLine();
            switch (action.toLowerCase()) {
                case "back" -> { return; }
                case "again" -> search(scanner);
                default -> {
                    recNum = Integer.parseInt(scanner.nextLine());
                    Contact contact = contacts.get(recNum-1);
                    System.out.println(contact);
                }
            }*/
        //} while(! action.equalsIgnoreCase("back") );
        if(contacts.size()>0) {
            System.out.println("[search] Enter action ([number], back, again):");
            showContact(scanner, contacts);
        }
    }

    public void showList(Scanner scanner){
        ArrayList<Contact> contacts = printList(null);
        //int recNum;
        String field;
        //String action;
        //do {
        System.out.println();
        System.out.println("[list] Enter action ([number], back):");
        /*action = scanner.nextLine();
        switch (action.toLowerCase()) {
            case "back" -> { return; }
            default -> {
                recNum = Integer.parseInt(scanner.nextLine());
                Contact contact = contacts.get(recNum-1);
                System.out.println(contact);
            }
        }*/
        //} while(! action.equalsIgnoreCase("back") );
        showContact(scanner, contacts);
    }

    public void count(){
        System.out.printf("The Phone Book has %d records.\n",this.contacts.size());
    }

    /*
    public void edit(Scanner scanner){
        if(this.contacts.size()==0){
            System.out.println("No records to edit!");
        }else {
            this.printList(null);
            int recNum;
            String field;
            System.out.println("Select a record:");
            recNum = Integer.parseInt(scanner.nextLine());
            Contact contact = this.contacts.get(recNum-1);
            do {
                System.out.println("Select a field ("+contact.editableFields()+"):");
                field = scanner.nextLine();
                for (String fieldNameI: contact.getEditableFields()
                     ) {
                    if (field.equalsIgnoreCase(fieldNameI)) {
                        System.out.printf("Enter %s\n", field);
                        String newValue = scanner.nextLine();
                        contact.updateEditableField(fieldNameI,newValue);
                        System.out.println("The record updated!");
                        field = "exit";
                        break;
                    }
                }
            } while(! field.equalsIgnoreCase("exit") );
        }
    }


    public void remove(Scanner scanner) {
        if (this.contacts.size() == 0) {
            System.out.println("No records to remove!");
        } else {
            this.printList(null);
            int recNum;
            System.out.println("Select a record:");
            recNum = scanner.nextInt();
            this.contacts.remove(recNum-1);
            System.out.println("The record removed!");
        }
    }
*/


}
