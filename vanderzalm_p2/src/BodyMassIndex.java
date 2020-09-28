public class BodyMassIndex {

    double bmiScore;
    String bmiCategory;

    public BodyMassIndex(double height, double weight) {
        this.bmiScore = getBmiScore(height, weight);
        this.bmiCategory = getBmiCategory(height, weight);
    }

    public double getBmiScore(double height, double weight) {
        double score = 703 * (weight / (height * height));
        return Math.round(score * 10)/10.0;
    }

    public String getBmiCategory(double height, double weight) {
        String category = null;
        double score = getBmiScore(height,weight);
        
        if (score <= 18.5)
            category = "Underweight";
        else if (score > 18.5 && score <= 24.9)
            category = "Normal Weight";
        else if (score >= 25.0 && score <= 29.9)
            category = "Overweight";
        else if (score >= 30.0)
            category = "Obesity";
        
        return category;
    }
}
