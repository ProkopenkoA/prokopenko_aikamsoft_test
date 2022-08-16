package argReader;

import dto.OperationType;

import java.util.stream.Stream;

public class ArgumentParser {
    private final String[] arguments;
    private OperationType operationType;
    private String inputFile;
    private String outputFile;

    public ArgumentParser(String[] arguments) {
        this.arguments = arguments;
    }

    public void check() {
        if (arguments.length != 3) {
            throw new IllegalArgumentException("Неверное кол-во аргументов");
        }
        operationType = Stream.of(OperationType.values()).filter(v -> v.getOperation().equals(arguments[0])).findAny().get();
        inputFile = arguments[1];
        outputFile = arguments[2];
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public String getInputFile() {
        return inputFile;
    }

    public String getOutputFile() {
        return outputFile;
    }
}

