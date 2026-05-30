public class Delivery {
//Год 
    private int year;
//Дата 
    private String date;
//Поставщик
    private String supplier;
//Массив продуктов в доставке
    private Product[] products;
//текущее количество добавленных продуктов
    private int count;

//конструктор для инициализации доставки
    public Delivery(int year, String date, String supplier, int maxSize) {
        this.year = year;
        this.date = date;
        this.supplier = supplier;
        this.products = new Product[maxSize]; 
        this.count = 0;
    }

//Получение года
    public int getYear() {
        return year;
    }

//Получение даты
    public String getDate() {
        return date;
    }

//получение поставщика
    public String getSupplier() {
        return supplier;
    }

//Получение текущего количества продуктов
    public int getCount() {
        return count;
    }

//Получение продукта по индексу
    public Product getProduct(int index) {
        return products[index];
    }

//Добавление продукта в доставку
    public void add(Product product) {
        if (count < products.length) { // Проверка на свободное место
            products[count] = product;
            count++;
        }
    }
}
