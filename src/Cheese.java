public class Cheese extends Product {
// Сорт
    private String sort;
//Процент жирности
    private int fatPercent;
//Тип упаковки
    private String packaging;

//Конструктор инициализации объекта
    public Cheese(double amount, String sort, int fatPercent, String packaging) {
        super("Сыр", amount); //передача в родительский класс
        this.sort = sort;
        this.fatPercent = fatPercent;
        this.packaging = packaging;
    }

//Получить сорт
    public String getSort() {
        return sort;
    }

//Получить процент жирности
    public int getFatPercent() {
        return fatPercent;
    }

//Получить тип упаковки
    public String getPackaging() {
        return packaging;
    }
}


