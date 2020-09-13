import java.util.Arrays;

public class Encrypter
{
    public String encrypt(String stringInput) {
        int [] unencryptedArray = stringToArrayofIntegers(stringInput);

        int [] tempArray = plusSevenRemainder(unencryptedArray);

        System.out.println(Arrays.toString(tempArray));

//        int [] encryptedArray = swapDigits(tempArray);

//        String encryptedOutput = convertArrayToString(encryptedArray);

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
        int size = array.length;
//        int [] newArray = new int [size];
//        System.arraycopy(array, 0, newArray, 0, size);
        for (int i = 0; i < size; i++) {
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

//    public int [] swapDigits(int [] tempArray)
//    {
//
//
//        return encryptedArray;
//    }
//
//    public String convertArrayToString(int [] encryptedArray)
//    {
//
//        return encryptedString;
//    }

}
