public class GUP {

    public static Product[] getContractProducts(Contract[] contractsStorage, int contractsCount, int selectedYear) {
        int totalProductsCount = 0;
        for (int i = 0; i < contractsCount; i++) {
            if (selectedYear == 0) {
                totalProductsCount += contractsStorage[i].getCount();
            } else {
                if (contractsStorage[i].getYear() == selectedYear) {
                    totalProductsCount += contractsStorage[i].getCount();
                }
            }
        }
        Product[] resultArray = new Product[totalProductsCount];
        int currentIndex = 0;
        for (int i = 0; i < contractsCount; i++) {
            boolean isMatchingYear = false;
            if (selectedYear == 0) {
                isMatchingYear = true;
            } else {
                if (contractsStorage[i].getYear() == selectedYear) {
                    isMatchingYear = true;
                }
            }
            if (isMatchingYear) {
                for (int j = 0; j < contractsStorage[i].getCount(); j++) {
                    resultArray[currentIndex] = contractsStorage[i].getProduct(j);
                    currentIndex++;
                }
            }
        }
        return resultArray;
    }

    public static Product[] getDeliveryProducts(Delivery[] deliveriesStorage, int deliveriesCount, int selectedYear) {
        int totalProductsCount = 0;
        for (int i = 0; i < deliveriesCount; i++) {
            if (selectedYear == 0) {
                totalProductsCount += deliveriesStorage[i].getCount();
            } else {
                if (deliveriesStorage[i].getYear() == selectedYear) {
                    totalProductsCount += deliveriesStorage[i].getCount();
                }
            }
        }
        Product[] resultArray = new Product[totalProductsCount];
        int currentIndex = 0;
        for (int i = 0; i < deliveriesCount; i++) {
            boolean isMatchingYear = false;
            if (selectedYear == 0) {                isMatchingYear = true;
            } else {
                if (deliveriesStorage[i].getYear() == selectedYear) {
                    isMatchingYear = true;
                }
            }
            if (isMatchingYear) {
                for (int j = 0; j < deliveriesStorage[i].getCount(); j++) {
                    resultArray[currentIndex] = deliveriesStorage[i].getProduct(j);
                    currentIndex++;
                }
            }
        }
        return resultArray;
    }

    public static void sortByAmount(Product[] A) {
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = 0; j < A.length - i - 1; j++) {
                if (A[j].getAmount() < A[j + 1].getAmount()) {
                    Product temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                }
            }
        }
    }

    public static void sortByName(Product[] A) {
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = 0; j < A.length - i - 1; j++) {
                if (A[j].getName().compareTo(A[j + 1].getName()) > 0) {
                    Product temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                }
            }
        }
    }

    public static String getListText(Product[] A) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < A.length; i++) {
            Product currentProduct = A[i];
            String detailsInfo = "";
            if (currentProduct instanceof Wheat) {
                Wheat wheatItem = (Wheat) currentProduct;
                detailsInfo = " Твердость: " + wheatItem.getHardness() + " Класс: " + wheatItem.getClassGrade();
            }
            if (currentProduct instanceof Cheese) {                Cheese cheeseItem = (Cheese) currentProduct;
                detailsInfo = " Сорт: " + cheeseItem.getSort() + " Жир: " + cheeseItem.getFatPercent() + "% Уп: " + cheeseItem.getPackaging();
            }
            sb.append(currentProduct.getName()).append(": ").append(currentProduct.getAmount()).append(detailsInfo).append("\n");
        }
        return sb.toString();
    }

    public static String getReportText(Product[] A) {
        double totalWheat = 0;
        double totalCheese = 0;
        for (int i = 0; i < A.length; i++) {
            Product currentProduct = A[i];
            if (currentProduct.getName().equals("Пшеница")) {
                totalWheat += currentProduct.getAmount();
            }
            if (currentProduct.getName().equals("Сыр")) {
                totalCheese += currentProduct.getAmount();
            }
        }
        return "Пшеница: " + totalWheat + "\nСыр: " + totalCheese;
    }

    public static String getDeviationText(Contract[] contractsStorage, int contractsCount, Delivery[] deliveriesStorage, int deliveriesCount, int selectedYear) {
        Product[] plannedData = getContractProducts(contractsStorage, contractsCount, selectedYear);
        Product[] deliveredData = getDeliveryProducts(deliveriesStorage, deliveriesCount, selectedYear);

        double planWheat = 0;
        double planCheese = 0;
        double factWheat = 0;
        double factCheese = 0;

        for (int i = 0; i < plannedData.length; i++) {
            Product currentProduct = plannedData[i];
            if (currentProduct.getName().equals("Пшеница")) {
                planWheat += currentProduct.getAmount();
            }
            if (currentProduct.getName().equals("Сыр")) {
                planCheese += currentProduct.getAmount();
            }
        }

        for (int i = 0; i < deliveredData.length; i++) {
            Product currentProduct = deliveredData[i];
            if (currentProduct.getName().equals("Пшеница")) {
                factWheat += currentProduct.getAmount();
            }
            if (currentProduct.getName().equals("Сыр")) {
                factCheese += currentProduct.getAmount();
            }        }

        String periodLabel = "Все годы";
        if (selectedYear != 0) {
            periodLabel = String.valueOf(selectedYear) + " год";
        }

        return "Отклонение за " + periodLabel +
                "Пшеница: План " + planWheat + " Факт " + factWheat + " Отклонение " + (planWheat - factWheat) + "\n" +
                "Сыр: План " + planCheese + " Факт " + factCheese + " Отклонение " + (planCheese - factCheese);
    }

    public static String getCheeseReportText(Product[] A) {
        StringBuilder sb = new StringBuilder();
        double total = 0;
        double s30 = 0;
        double s40 = 0;
        double s50 = 0;

        for (int i = 0; i < A.length; i++) {
            Product currentProduct = A[i];
            if (currentProduct instanceof Cheese) {
                Cheese cheeseItem = (Cheese) currentProduct;
                total += cheeseItem.getAmount();
                sb.append(cheeseItem.getSort()).append(" ").append(cheeseItem.getFatPercent()).append("% ")
                        .append(cheeseItem.getPackaging()).append(" Кол-во: ").append(cheeseItem.getAmount()).append("\n");
                if (cheeseItem.getFatPercent() == 30) {
                    s30 += cheeseItem.getAmount();
                }
                if (cheeseItem.getFatPercent() == 40) {
                    s40 += cheeseItem.getAmount();
                }
                if (cheeseItem.getFatPercent() == 50) {
                    s50 += cheeseItem.getAmount();
                }
            }
        }
        sb.append("Итого сыра: ").append(total).append("\n")
                .append("30%: ").append(s30).append(" 40%: ").append(s40).append(" 50%: ").append(s50);
        return sb.toString();
    }

    public static String findWithText(Delivery[] deliveriesStorage, int deliveriesCount, String targetProductName) {
        StringBuilder sb = new StringBuilder();
        boolean isFound = false;
        for (int i = 0; i < deliveriesCount; i++) {
            boolean containsProduct = false;
            for (int j = 0; j < deliveriesStorage[i].getCount(); j++) {
                if (deliveriesStorage[i].getProduct(j).getName().equals(targetProductName)) {
                    containsProduct = true;                    break;
                }
            }
            if (containsProduct) {
                sb.append(deliveriesStorage[i].getDate()).append(" Поставщик: ").append(deliveriesStorage[i].getSupplier()).append("\n");
                isFound = true;
            }
        }
        if (!isFound) {
            sb.append("Поставок нет");
        }
        return sb.toString();
    }

    public static String findWithoutText(Delivery[] deliveriesStorage, int deliveriesCount, String targetProductName) {
        StringBuilder sb = new StringBuilder();
        boolean isFound = false;
        for (int i = 0; i < deliveriesCount; i++) {
            boolean containsProduct = false;
            for (int j = 0; j < deliveriesStorage[i].getCount(); j++) {
                if (deliveriesStorage[i].getProduct(j).getName().equals(targetProductName)) {
                    containsProduct = true;
                    break;
                }
            }
            if (!containsProduct) {
                sb.append(deliveriesStorage[i].getDate()).append(" Поставщик: ").append(deliveriesStorage[i].getSupplier()).append("\n");
                isFound = true;
            }
        }
        if (!isFound) {
            sb.append("Поставок нет");
        }
        return sb.toString();
    }
}

