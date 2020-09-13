public class Encrypter {

    public String encrypt(String stringInput) {
        int [] unencryptedArray = stringToArrayofIntegers(stringInput);
        int [] tempArray = plusSevenRemainder(unencryptedArray);
        int [] encryptedArray = swapDigits(tempArray);
        String encryptedString = integerArrayToString(encryptedArray);

        return encryptedString;
    }

    public int [] stringToArrayofIntegers(String str) {
        int size = str.length();
        int [] array = new int [size];

        for (int i = 0; i < size; i++)
            array[i] = str.charAt(i) - '0';

        return array;
    }

    public int [] plusSevenRemainder(int [] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] <= 2)
                array[i] += 7;
            else {
                array[i] += 7;
                array[i] = (array[i] % 10);
            }
        }
        return array;
    }

    public int [] swapDigits(int [] array) {
        int temp = array[0];
        array[0] = array[2];
        array[2] = temp;

        temp = array[1];
        array[1] = array[3];
        array[3] = temp;
        return array;
    }

    public String integerArrayToString(int [] intArray) {
        String [] strArray = new String[intArray.length];
        for (int i = 0; i < intArray.length; i++)
            strArray[i] = String.valueOf(intArray[i]);

        String encryptedString = stringArrayToStringNoBrackets(strArray);
        return encryptedString;
    }

    public String stringArrayToStringNoBrackets(String [] strArray) {
        StringBuilder tempBuffer = new StringBuilder();
        for (String str : strArray)
            tempBuffer.append(str);
        return tempBuffer.toString();
    }
}
