import java.util.Scanner;

public class App {

    public static void main(String[] args) {
//        ArrayList<BodyMassIndex> bmiData = new ArrayList<BodyMassIndex>();

        while(moreInput()) {
            double height = getUserHeight();
            double weight = getUserWeight();

            BodyMassIndex bmi = new BodyMassIndex(height,weight);
//            bmiData.add(bmi);


            displayBmiInfo(bmi);
        }

//        displayBmiStatistics(bmiData);
    }



    public static boolean moreInput() {
        Scanner in = new Scanner(System.in);
        System.out.println("Would you like to add more data? (Y or N) ");
        String input = in.nextLine();

        if (input.equals("Y") || input.equals("y"))
            return true;
        return false;
    }

    public static void checkInputPositive(double input) {
        if (input <= 0) {
            System.out.println("Please enter a positive number");
            System.exit(0);
        }
    }

    public static double getUserHeight() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter your height (inches): ");
        double height = in.nextDouble();
        checkInputPositive(height);

        return height;
    }

    public static double getUserWeight() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter your weight (pounds): ");
        double weight = in.nextDouble();
        checkInputPositive(weight);

        return weight;
    }

    private static void displayBmiInfo(BodyMassIndex bmi) {
        System.out.println(bmi.bmiScore);
        System.out.println(bmi.bmiCategory);
    }







}
