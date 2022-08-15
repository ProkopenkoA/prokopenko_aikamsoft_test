package dto;

public enum OperationType {
    SEARCH("search"),
    STAT("stat");

    private final String operation;

    OperationType(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}
