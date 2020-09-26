public class BodyMassIndex {

    public double bmiScore;
    public String bmiCategory;

    public BodyMassIndex(double height, double weight) {
        this.bmiScore = getBmiScore(height, weight);
        this.bmiCategory = getBmiCategory(bmiScore);
    }

    public double getBmiScore(double height, double weight) {
//        System.out.println("nice");
        return height;
        
    }

    public String getBmiCategory(double bmiScore) {
        return "Your fat";
    }
}
