public class Wheat extends Product {
//Твёрдость пшеницы
    private String hardness;
//Класс качества
    private String classGrade;

//Конструктор 
    public Wheat(double amount, String hardness, String classGrade) {
        super("Пшеница", amount); 
        this.hardness = hardness;
        this.classGrade = classGrade;
    }

//Получить твердость
    public String getHardness() {
        return hardness;
    }

//получить класс качества
    public String getClassGrade() {
        return classGrade;
    }
}
