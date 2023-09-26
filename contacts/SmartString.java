package contacts;

public class SmartString {

    static public int substrCount(String mainString, String subString){
        //return mainString.split(subString, -1).length-1;
        int index = 0, count = 0, length = subString.length();
        while( (index = mainString.indexOf(subString, index)) != -1 ) {
            index += length; count++;
        }
        return count;
    }
}
