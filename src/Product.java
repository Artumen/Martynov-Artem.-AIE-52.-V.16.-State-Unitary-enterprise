public abstract class Product {
//Название 
    private String name;
//количество/вес продукта
    private double amount;

//конструктор 
    public Product(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

//Получить название
    public String getName() {
        return name;
    }

//Получить количество,вес
    public double getAmount() {
        return amount;
    }
}
