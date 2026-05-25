import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Simulation extends Application {

    private Contract[] contractsStorage = new Contract[10];
    private int contractsCount = 0;
    private Delivery[] deliveriesStorage = new Delivery[10];
    private int deliveriesCount = 0;

    private TextArea outputArea;
    private TextField yearField;
    private TextField searchProductField;
    private ComboBox<String> sortCombo;
    private ToggleGroup reportTypeGroup;

    @Override
    public void start(Stage primaryStage) {
        initData();

        Label titleLabel = new Label("Государственное унитарное предприятие");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setStyle("-fx-text-fill: #2c3e50;");
        titleLabel.setPadding(new Insets(10, 0, 10, 0));

        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(true);
        outputArea.setStyle("-fx-font-size: 14px; -fx-background-color: #ffffff; -fx-control-inner-background: #ffffff;");
        VBox.setVgrow(outputArea, Priority.ALWAYS);

        yearField = new TextField();
        yearField.setPromptText("0 - все года");
        yearField.setPrefWidth(80);

        sortCombo = new ComboBox<>();
        sortCombo.getItems().addAll("По количеству", "По имени");
        sortCombo.setValue("По количеству");
        sortCombo.setPrefWidth(130);

        RadioButton radioDetailed = new RadioButton("Детально");
        RadioButton radioSummary = new RadioButton("Укрупненно");
        radioDetailed.setSelected(true);

        reportTypeGroup = new ToggleGroup();
        radioDetailed.setToggleGroup(reportTypeGroup);
        radioSummary.setToggleGroup(reportTypeGroup);

        HBox filterBox = new HBox(15);
        filterBox.setAlignment(Pos.CENTER_LEFT);
        filterBox.getChildren().addAll(
                new Label("Год:"), yearField,
                new Label("Сортировка:"), sortCombo,
                new Label("Вид:"), radioDetailed, radioSummary
        );

        Button btnFindWith = createStyledButton("5. Есть в поставках", "#DC143C");
        Button btnFindWithout = createStyledButton("6. Нет в поставках", "#00FA9A");

        searchProductField = new TextField();
        searchProductField.setPromptText("Например: Сыр или Пшеница");
        searchProductField.setPrefWidth(200);
        searchProductField.setStyle("-fx-border-color: #f39c12; -fx-border-width: 2;");

        Label searchLabel = new Label("Продукт:");
        searchLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #7B68EE;");

        HBox searchBox = new HBox(10);
        searchBox.setAlignment(Pos.CENTER_LEFT);
        searchBox.setPadding(new Insets(5, 0, 5, 0));
        searchBox.getChildren().addAll(searchLabel, searchProductField, btnFindWith, btnFindWithout);

        Button btnContracts = createStyledButton("1. Договоры", "#7B68EE");
        Button btnDeliveries = createStyledButton("2. Поставки", "#7B68EE");
        Button btnDeviation = createStyledButton("3. Отклонения", "#7B68EE");
        Button btnCheese = createStyledButton("4. Сыр", "#7B68EE");
        Button btnClear = createStyledButton("Очистить", "#7B68EE");

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10, 0, 10, 0));
        buttonBox.getChildren().addAll(btnContracts, btnDeliveries, btnDeviation, btnCheese, btnClear);

        Label authorLabel = new Label("Мартынов Артём. 2026");
        authorLabel.setStyle("-fx-text-fill: #7f8c8d; -fx-font-size: 10px;");
        authorLabel.setPadding(new Insets(5, 0, 5, 0));

        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: #FFFFFF;");

        root.getChildren().addAll(
                titleLabel,
                filterBox,
                searchBox,
                buttonBox,
                outputArea,
                authorLabel
        );

        btnContracts.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int year = getInt(yearField.getText());
                Product[] A = GUP.getContractProducts(contractsStorage, contractsCount, year);

                if (sortCombo.getValue().equals("По количеству")) {
                    GUP.sortByAmount(A);
                } else {
                    GUP.sortByName(A);
                }

                boolean isDetailed = radioDetailed.isSelected();

                if (isDetailed) {
                    outputArea.setText("Договоры (Детально)" + GUP.getListText(A));
                } else {
                    outputArea.setText("Договоры (Укрупненно)" + GUP.getReportText(A));
                }
            }
        });

        btnDeliveries.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int year = getInt(yearField.getText());
                Product[] A = GUP.getDeliveryProducts(deliveriesStorage, deliveriesCount, year);

                if (sortCombo.getValue().equals("По количеству")) {
                    GUP.sortByAmount(A);
                } else {
                    GUP.sortByName(A);
                }

                boolean isDetailed = radioDetailed.isSelected();

                if (isDetailed) {
                    outputArea.setText("Поставки (Детально)" + GUP.getListText(A));
                } else {
                    outputArea.setText("Поставки (Укрупненно)" + GUP.getReportText(A));
                }
            }
        });

        btnDeviation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int year = getInt(yearField.getText());
                outputArea.setText(GUP.getDeviationText(contractsStorage, contractsCount, deliveriesStorage, deliveriesCount, year));
            }
        });

        btnCheese.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Product[] A = GUP.getDeliveryProducts(deliveriesStorage, deliveriesCount, 0);
                outputArea.setText("Отчет по сыру" + GUP.getCheeseReportText(A));
            }
        });

        btnFindWith.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = searchProductField.getText().trim();
                if (name.isEmpty()) {
                    outputArea.setText("Ошибка: Введите название продукта");
                    return;
                }
                outputArea.setText("Поставки, где есть" + name + GUP.findWithText(deliveriesStorage, deliveriesCount, name));
            }
        });

        btnFindWithout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = searchProductField.getText().trim();
                if (name.isEmpty()) {
                    outputArea.setText("Ошибка: Введите название продукта");
                    return;
                }
                outputArea.setText("Поставки, где есть" + name + GUP.findWithoutText(deliveriesStorage, deliveriesCount, name));
            }
        });

        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                outputArea.clear();
                searchProductField.clear();
                yearField.clear();
            }
        });

        Scene scene = new Scene(root, 950, 650);
        primaryStage.setTitle("ГУП. Симуляция");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createStyledButton(String text, String bgColor) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: " + bgColor + "; -fx-text-fill: white; " +
                "-fx-font-size: 12px; -fx-font-weight: bold; -fx-background-radius: 5; -fx-padding: 5 10;");
        btn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn.setStyle("-fx-background-color: derive(" + bgColor + ", -20%); -fx-text-fill: white; " +
                        "-fx-font-size: 12px; -fx-font-weight: bold; -fx-background-radius: 5; -fx-padding: 5 10;");
            }
        });
        btn.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn.setStyle("-fx-background-color: " + bgColor + "; -fx-text-fill: white; " +
                        "-fx-font-size: 12px; -fx-font-weight: bold; -fx-background-radius: 5; -fx-padding: 5 10;");
            }
        });
        return btn;
    }

    private int getInt(String text) {
        int result = 0;
        try {
            result = Integer.parseInt(text);
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }

    private void initData() {
        Contract c1 = new Contract(2025, 1000000, 5);
        c1.add(new Wheat(500, "Твёрдая", "3 класс"));
        c1.add(new Cheese(200, "Швейцарский", 45, "Парафин"));
        contractsStorage[contractsCount] = c1;
        contractsCount = contractsCount + 1;

        Contract c2 = new Contract(2026, 1500000, 5);
        c2.add(new Wheat(800, "Мягкая", "4 класс"));
        c2.add(new Cheese(300, "Голландский", 50, "Плёнка"));
        c2.add(new Cheese(100, "Куяганский", 30, "Плёнка"));
        contractsStorage[contractsCount] = c2;
        contractsCount = contractsCount + 1;

        Delivery d0 = new Delivery(2025, "01.02.2025", "Глобус", 5);
        d0.add(new Wheat(100, "Твердая", "3 класс"));
        d0.add(new Cheese(50, "Алтайский", 45, "Парафин"));
        deliveriesStorage[deliveriesCount] = d0;
        deliveriesCount = deliveriesCount + 1;

        Delivery d1 = new Delivery(2025, "15.05.2025", "Глобус", 5);
        d1.add(new Wheat(200, "Твёрдая", "3 класс"));
        deliveriesStorage[deliveriesCount] = d1;
        deliveriesCount = deliveriesCount + 1;

        Delivery d2 = new Delivery(2026, "24.04.2026", "ООО Милки-вей", 5);
        d2.add(new Cheese(150, "Голландский", 50, "Плёнка"));
        deliveriesStorage[deliveriesCount] = d2;
        deliveriesCount = deliveriesCount + 1;

        Delivery d3 = new Delivery(2026, "05.05.2026", "ИП Мартынов", 5);
        d3.add(new Cheese(50, "Куяганский", 30, "Плёнка"));
        d3.add(new Cheese(50, "Куяганский", 30, "Парафин"));
        deliveriesStorage[deliveriesCount] = d3;
        deliveriesCount = deliveriesCount + 1;

        Delivery d4 = new Delivery(2026, "03.03.2026", "ООО Мельница", 5);
        d4.add(new Wheat(100, "Мягкая", "Нестандартная"));
        deliveriesStorage[deliveriesCount] = d4;
        deliveriesCount = deliveriesCount + 1;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
