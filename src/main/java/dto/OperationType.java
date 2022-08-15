package dto;

public enum OperationType {
    SEARCH("search"),
    STAT("stat");

    private String operation;

    OperationType(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}
