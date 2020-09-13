import java.util.Arrays;

public class Encrypter
{
    public String encrypt(String stringInput)
    {
        int [] fourDigitArray = StringToSequenceofIntegers(stringInput);

        












        String encryptedOutput = new String("0189");
        return encryptedOutput;
    }

    public int [] StringToSequenceofIntegers(String stringInput)
    {
        int size = stringInput.length();
        int [] fourDigitArray = new int [size];

        for (int i = 0; i < size; i++)
            fourDigitArray[i] = stringInput.charAt(i) - '0';

        return fourDigitArray;
    }

}
