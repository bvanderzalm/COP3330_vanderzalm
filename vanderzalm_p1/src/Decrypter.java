public class Decrypter {

    public String decrypt(String stringInput) {
        int [] encryptedArray = stringToArrayofIntegers(stringInput);
        int [] tempArray = subtractSevenOrAddThree(encryptedArray);
        int [] decryptedArray = swapDigits(tempArray);
        String decryptedString = integerArrayToString(decryptedArray);

        return decryptedString;
    }

    public int [] stringToArrayofIntegers(String str) {
        int size = str.length();
        int [] array = new int[size];

        for (int i = 0; i < size; i++)
            array[i] = str.charAt(i) - '0';

        return array;
    }

    public int [] subtractSevenOrAddThree(int [] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= 7)
                array[i] -= 7;
            else if (array[i] < 7)
                array[i] += 3;
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

        String decryptedString = stringArrayToStringNoBrackets(strArray);
        return decryptedString;
    }

    public String stringArrayToStringNoBrackets(String [] strArray) {
        StringBuilder tempBuffer = new StringBuilder();
        for (String str : strArray)
            tempBuffer.append(str);
        return tempBuffer.toString();
    }
}
