import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        ArrayList<BodyMassIndex> bmiData = new ArrayList<BodyMassIndex>();

        while(moreInput()) {
            double height = getUserHeight;
            double weight = getUserWeight;

            BodyMassIndex bmi = new BodyMassIndex(height,weight);
            bmiData.add(bmi);

            displayBmiInfo(bmi);

            System.out.println("Hello");
        }
    }

    public static boolean moreInput() {
        Scanner in = new Scanner(System.in);
        System.out.println("Would you like to add more data? (Y or N) ");
        String input = in.nextLine();

        if (input.equals("Y") || input.equals("y"))
            return true;
        return false;
    }
    



}
