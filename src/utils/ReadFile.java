package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    private final String filePath;
    private InputStream inputStream = null;
    private List<Integer> values = new ArrayList<>();

    private void openFileStream(){
        File file = new File(filePath);
        try {
            this.inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private int readNBits(){
        try {
            return this.inputStream.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public ReadFile(String filePath){
        this.filePath = filePath;
    }

    public void readData(){
        openFileStream();
        int readValue;
        while ((readValue = readNBits()) != -1){
            values.add(readValue);
        }
    }

    public List<Integer> getValues() {
        return values;
    }
}
