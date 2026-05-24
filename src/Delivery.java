public class Delivery {
    private int year;
    private String date;
    private String supplier;
    private Product[] products;
    private int count;

    public Delivery(int year, String date, String supplier, int maxSize) {
        this.year = year;
        this.date = date;
        this.supplier = supplier;
        this.products = new Product[maxSize];
        this.count = 0;
    }

    public int getYear() {
        return year;
    }

    public String getDate() {
        return date;
    }

    public String getSupplier() {
        return supplier;
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
