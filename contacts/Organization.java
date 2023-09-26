package contacts;

import java.util.Scanner;

public class Organization extends Contact{

    String address;

    public Organization(){
        this.setEditableFields( new String[] {"name", "address", "number"} );
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    static public Organization input(Scanner scanner){
        String name;
        String address;
        String phoneNumber;
        Organization organization = new Organization();
        organization.setPerson(false);
        System.out.println("Enter the organization name:");
        name = scanner.nextLine();
        organization.setName(name);
        System.out.println("Enter the address:");
        address = scanner.nextLine();
        organization.setAddress(address);
        System.out.println("Enter the number:");
        phoneNumber = scanner.nextLine();
        organization.setPhoneNumber(phoneNumber);
        return organization;
    }

    @Override
    public String toString() {
        return String.format("""
Organization name: %s
Address: %s
""", this.getName(), this.getAddress()
        )+super.toString();
    }

    public String toSearchString() {
        return this.getName()+" "+this.getAddress()+" "+super.toSearchString();
    }

    public void updateEditableField(String fieldName, String value){
        switch (fieldName.toLowerCase()){
            case "name" -> this.setName(value);
            case "address" -> this.setAddress(value);
            case "number" -> this.setPhoneNumber(value);
        }
    }


}
