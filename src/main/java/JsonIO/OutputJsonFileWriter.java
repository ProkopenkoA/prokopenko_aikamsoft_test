package JsonIO;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class OutputJsonFileWriter {
    private final String fileName;

    public OutputJsonFileWriter(String fileName) {
        this.fileName = fileName;
    }

    public void writeJObjToFile(JSONObject jsonObject) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
