package contacts;

import java.time.LocalDate;
import java.util.Scanner;

public class Person extends Contact {
    private String surname;
    private LocalDate birthDate;
    private String gender;

    public Person(){
        //name, surname, birth, gender, number
        this.setEditableFields( new String[] {"name", "surname", "birth", "gender", "number"} );
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F") ) {
            this.gender = gender;
        }else{
            System.out.println("Bad gender!");
        }
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        if(birthDate != null && ! birthDate.isEmpty()) {
            this.birthDate = LocalDate.parse(birthDate);
        }
        if(this.getBirthDate()==null){
            System.out.println("Bad birth date!");
        }
    }


    public void setSurname(String surname) {
        this.surname = surname;
    }


    public String getSurname() {
        return surname;
    }

    static public Person input(Scanner scanner){
        String name;
        String surname;
        String birthDate;
        String gender;
        String phoneNumber;
        Person person = new Person();
        person.setPerson(true);
        System.out.println("Enter the name:");
        name = scanner.nextLine();
        person.setName(name);
        System.out.println("Enter the surname:");
        surname = scanner.nextLine();
        person.setSurname(surname);
        System.out.println("Enter the birth date:");
        birthDate = scanner.nextLine();
        person.setBirthDate(birthDate);
        System.out.println("Enter the gender (M, F):");
        gender = scanner.nextLine();
        person.setGender(gender);
        System.out.println("Enter the number:");
        phoneNumber = scanner.nextLine();
        person.setPhoneNumber(phoneNumber);
        return person;
    }

    @Override
    public String toString() {
        return String.format("""
Name: %s
Surname: %s
Birth date: %s
Gender: %s
""", this.getName(), this.getSurname()
, this.getBirthDate()==null?"[no data]":this.getBirthDate()
, this.getGender()==null?"[no data]":this.getGender()
        )+super.toString();
    }

    public String toSearchString() {
        return this.getName()+" "+this.getSurname()+" "+this.getBirthDate()+" "+super.toSearchString();
    }

    public void updateEditableField(String fieldName, String value){
        switch (fieldName.toLowerCase()){
            case "name" -> this.setName(value);
            case "surname" -> this.setSurname(value);
            case "birth" -> this.setBirthDate(value);
            case "gender" -> this.setGender(value);
            case "number" -> this.setPhoneNumber(value);
        }
    }

}
