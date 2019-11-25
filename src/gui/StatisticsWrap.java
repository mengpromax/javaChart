package gui;

import javax.swing.*;
import java.awt.*;

public class StatisticsWrap extends Panel {
    private JLabel integrate;
    private JLabel minimize;
    private int integrateValue = -1;
    private int meanValue = -1;
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

    void setIntegrateValue(int integrateValue) {
        this.integrateValue = integrateValue;
        this.integrate.setText("积分:" + integrateValue);
    }

    public int getMinimizeValue() {
        return meanValue;
    }

    void setMinimizeValue(int meanValue) {
        this.meanValue = meanValue;
        this.minimize.setText("平均值：" + this.meanValue);
    }
}
