
public class Cheese extends Product {
    private String sort;
    private int fatPercent;
    private String packaging;

    public Cheese(double amount, String sort, int fatPercent, String packaging) {
        super("Сыр", amount);
        this.sort = sort;
        this.fatPercent = fatPercent;
        this.packaging = packaging;
    }

    public String getSort() {
        return sort;
    }

    public int getFatPercent() {
        return fatPercent;
    }

    public String getPackaging() {
        return packaging;
    }
}


