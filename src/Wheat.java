public class Wheat extends Product {
    private String hardness;
    private String classGrade;

    public Wheat(double amount, String hardness, String classGrade) {
        super("Пшеница", amount);
        this.hardness = hardness;
        this.classGrade = classGrade;
    }

    public String getHardness() {
        return hardness;
    }

    public String getClassGrade() {
        return classGrade;
    }
}
