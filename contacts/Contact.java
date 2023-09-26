package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Scanner;

public abstract class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private boolean isPerson;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;
    private String[] editableFields;

    public String[] getEditableFields() {
        return editableFields;
    }

    protected void setEditableFields(String[] editableFields) {
        this.editableFields = editableFields;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public boolean isPerson() {
        return isPerson;
    }

    public void setPerson(boolean person) {
        isPerson = person;
    }

    static private boolean checkPhoneNumber(String phoneNumber){
        boolean error = false;
        if(phoneNumber.length()==0){
            error = true;
        }else {
            //if (clearedPhoneNumber.charAt(0) == '+') {
            //    clearedPhoneNumber = clearedPhoneNumber.substring(1);
            //}
            if (SmartString.substrCount(phoneNumber, "(") <= 1
                    && SmartString.substrCount(phoneNumber, ")") <= 1
            ) {
                int i = 0;
                for (String group : phoneNumber.split("[ -]")
                ) {
                    i++;
                    System.out.println("i=" + i + " group " + group);
                    if (i > 1 && group.length() < 2) {
                        error = true;
                    }
                    if (i > 2 && (group.contains("(") || group.contains(")"))) {
                        error = true;
                    }
                    if (i == 1) {
                        if (!group.matches("\\+?\\(?[0-9a-zA-Z]+\\)?")) {
                            error = true;
                        }
                        if(SmartString.substrCount(group, "(") != SmartString.substrCount(group, ")") ){
                            error = true;
                        }
                    }else if (i == 2) {
                        if (!group.matches("\\(?[0-9a-zA-Z]+\\)?")) {
                            error = true;
                        }
                    } else {
                        if (!group.matches("[0-9a-zA-Z]+")) {
                            error = true;
                        }
                    }
                    if(error) break;
                }
            } else {
                error = true;
            }
        }
        if(error){
            return false;
        }else{
            return true;
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        if(checkPhoneNumber(phoneNumber)){
            this.phoneNumber = phoneNumber;
        }else{
            this.phoneNumber = "[no number]";
            System.out.println("Wrong number format!");
        }
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String toString() {
        return String.format("""
Number: %s
Time created: %s
Time last edit: %s        
""", this.getPhoneNumber(),this.getCreationDate(), this.getLastUpdateDate()
        );
    }

    public String toSearchString() {
        return this.getPhoneNumber();
    }

    public String editableFields(){
        return String.join(",", getEditableFields());
    };

    public abstract void updateEditableField(String fieldName, String value);

}




