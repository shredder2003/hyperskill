package contacts;

import java.util.Scanner;

public class PersonContactFactory implements ContactFactory{


    @Override
    public Contact inputContact(Scanner scanner) {
        return Person.input(scanner);
    }
}
