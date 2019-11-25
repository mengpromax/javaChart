package gui;

import javax.swing.*;
import java.awt.*;

public class StatisticsWrap extends Panel {
    private JLabel integrate;
    private JLabel minimize;
    private int integrateValue = -1;
    private int minimizeValue = -1;
    StatisticsWrap(){
        this.setPreferredSize(new Dimension(180,25));
        integrate = new JLabel();
        integrate.setPreferredSize(new Dimension(80,25));
        integrate.setText("积分:-1");
        minimize = new JLabel();
        minimize.setPreferredSize(new Dimension(80,25));
        minimize.setText("最小值:-1");
        GridLayout layout = new GridLayout(1,2);
        this.add(integrate,layout);
        this.add(minimize,layout);
    }

    public int getIntegrateValue() {
        return integrateValue;
    }

    public void setIntegrateValue(int integrateValue) {
        this.integrateValue = integrateValue;
        this.integrate.setText("积分:" + this.integrateValue);
    }

    public int getMinimizeValue() {
        return minimizeValue;
    }

    public void setMinimizeValue(int minimizeValue) {
        this.minimizeValue = minimizeValue;
        this.minimize.setText("最小值：" + this.minimizeValue);
    }
}
