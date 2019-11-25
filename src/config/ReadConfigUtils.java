package config;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReadConfigUtils {
    public static Map<String,String> configList = new HashMap<>();
    private static String ROOT = System.getProperty("user.dir");
    public static void initConfigList(){
        String fileName = "serverConfig.properties";
        String configFilePath = ROOT + "/src/" + fileName;
        try {
            InputStream inputStream = new FileInputStream(new File(configFilePath));
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while((line = br.readLine()) != null){
                if(line.contains("="))
                    configList.put(line.split("=")[0],line.split("=")[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
