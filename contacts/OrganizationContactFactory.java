package contacts;

import java.util.Scanner;

public class OrganizationContactFactory implements ContactFactory{
    @Override
    public Contact inputContact(Scanner scanner) {
        return Organization.input(scanner);
    }
}
