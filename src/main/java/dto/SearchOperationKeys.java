package dto;

public enum SearchOperationKeys {
    LAST_NAME("lastName"),
    MIN_TIMES("minTimes"),
    PRODUCT_NAME("productName"),
    MIN_EXPENSES("minExpenses"),
    MAX_EXPENSES("maxExpenses"),
    BAD_CUSTOMERS("badCustomers");

    private String key;

    SearchOperationKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
