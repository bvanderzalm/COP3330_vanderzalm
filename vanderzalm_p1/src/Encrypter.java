import java.util.Arrays;

public class Encrypter
{
    public String encrypt(String stringInput) {
        int [] unencryptedArray = stringToArrayofIntegers(stringInput);

        int [] tempArray = plusSevenRemainder(unencryptedArray);

        int [] encryptedArray = swapDigits(tempArray);

//        System.out.println(Arrays.toString(encryptedArray));

//        String encryptedOutput = integerArrayToString(encryptedArray);

        return "0189";
    }

    public int [] stringToArrayofIntegers(String stringInput) {
        int size = stringInput.length();
        int [] unencryptedArray = new int [size];

        for (int i = 0; i < size; i++)
            unencryptedArray[i] = stringInput.charAt(i) - '0';

        return unencryptedArray;
    }

    public int [] plusSevenRemainder(int [] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] <= 2) {
                array[i] += 7;
            }
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

//    public String convertArrayToString(int [] encryptedArray) {
//
//
//        return encryptedString;
//    }

}
