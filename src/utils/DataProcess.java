package utils;

public class DataProcess {
    public static int normalizeValueForYAxis(int height,int value,int maxValue){
        return (int) ((double) height / maxValue * value);
    }

    public static int binaryToDecimal(String numStr){
        return Integer.parseInt(numStr,2);
    }



}
