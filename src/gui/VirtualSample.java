package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VirtualSample {
    private List<Integer> values = null;
    private int maxCapacity = 110;
    private final int MAX_VALUE = 255;
    private boolean isRunning = false;
    private Thread virtualDataThread = null;

    public VirtualSample(List<Integer> inputValue){
        this.values = inputValue;
        virtualDataThread = new Thread(() -> {
            Random rand = new Random();
            try {
                while (true) {
                    addValue(rand.nextInt(MAX_VALUE));
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        virtualDataThread.start();
        virtualDataThread.suspend();
        isRunning = false;
    }

    private void addValue(int value) {

        if (values.size() > maxCapacity) {
            values.remove(0);
        }
        values.add(value);
    }

    public void toggleSampleState(){
        if (isRunning){
            virtualDataThread.suspend();
        }else{
            virtualDataThread.resume();
        }
        isRunning = !isRunning;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }
}
