public class Contract {
//Год заключения 
    private int year;
//Бюджет контракта
    private double budgetMoney;
//Массив продуктов
    private Product[] products;
//Текущее количество добавленных
    private int count;

//Конструктор инициализации контракта
    public Contract(int year, double budgetMoney, int maxSize) {
        this.year = year;
        this.budgetMoney = budgetMoney;
        this.products = new Product[maxSize]; //активация массива заданного размера
        this.count = 0;
    }

//получение года 
    public int getYear() {
        return year;
    }

//получение количества продуктов
    public int getCount() {
        return count;
    }

//получение продукта по индексу
    public Product getProduct(int index) {
        return products[index];
    }

//Добавление продукта в контракт
    public void add(Product product) {
        if (count < products.length) { //проверка на наличие свободного места
            products[count] = product;
            count++;
        }
    }
}
