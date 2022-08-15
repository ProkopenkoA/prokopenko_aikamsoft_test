package JsonIO;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.stream.Collectors;

public class InputJsonFileReader {
    private String fileName;
    private JSONObject jsonObject;

    public InputJsonFileReader(String fileName) {
        this.fileName = fileName;
    }


    public void checkOnJson() throws Exception {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String content = bufferedReader.lines().collect(Collectors.joining());
            jsonObject = new JSONObject(content);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
