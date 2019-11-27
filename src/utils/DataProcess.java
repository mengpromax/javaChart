package utils;

import java.util.ArrayList;
import java.util.List;

public class DataProcess {
    public static int normalizeValueForYAxis(int height,int value,int maxValue, int minValue){
        return (int) ((double) height / (maxValue - minValue) * (value - minValue));
    }

    public static List<Integer> differentialValue(List<Integer> values){
        List<Integer> differentialValues = new ArrayList<>();
        if (values.size() == 0){
            return values;
        }
        for (int i = 1;i < values.size();i++){
            differentialValues.add(values.get(i) - values.get(i - 1));
        }
        return differentialValues;
    }

}
