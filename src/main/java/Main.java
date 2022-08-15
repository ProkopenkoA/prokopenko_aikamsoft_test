import JsonIO.InputJsonFileReader;
import JsonIO.OutputJsonFileWriter;
import argReader.ArgumentParser;
import database.DatabaseOperation;
import dto.OperationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            LOGGER.info("Программа запущена");
            ArgumentParser argumentParser = new ArgumentParser(args);
            argumentParser.check();
            OperationType operationType = argumentParser.getOperationType();
            InputJsonFileReader inputJsonFileReader = new InputJsonFileReader(argumentParser.getInputFile());
            OutputJsonFileWriter outputJsonFileWriter = new OutputJsonFileWriter(argumentParser.getOutputFile());
            inputJsonFileReader.checkOnJson();
            DatabaseOperation databaseOperation = new DatabaseOperation();
            databaseOperation.perform(operationType, inputJsonFileReader.getJsonObject());
            outputJsonFileWriter.writeJObjToFile(databaseOperation.getJObjResult());
            LOGGER.info("Программа выполнена");
        } catch (Exception e) {
            LOGGER.error("Error: ", e);
        }
    }
}
