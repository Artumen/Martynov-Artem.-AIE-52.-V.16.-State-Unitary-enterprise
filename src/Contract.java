public class Contract {
    private int year;
    private double budgetMoney;
    private Product[] products;
    private int count;

    public Contract(int year, double budgetMoney, int maxSize) {
        this.year = year;
        this.budgetMoney = budgetMoney;
        this.products = new Product[maxSize];
        this.count = 0;
    }

    public int getYear() {
        return year;
    }

    public int getCount() {
        return count;
    }

    public Product getProduct(int index) {
        return products[index];
    }

    public void add(Product product) {
        if (count < products.length) {
            products[count] = product;
            count++;
        }
    }
}
