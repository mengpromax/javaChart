package gui;

import listener.SliderChangeListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ZoomFactorPanel extends JPanel {
    private JSlider slider = null;
    public ZoomFactorPanel(){
        this.setPreferredSize(new Dimension(190,80));
        this.setBorder(new TitledBorder("通道缩放"));
        slider = new JSlider();
        slider.setName("zoomFactor");
        slider.setMinimum(-7);
        slider.setMaximum(7);
        slider.setPaintTrack(false);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setValue(0);
        slider.setSnapToTicks(true);
        slider.setMajorTickSpacing(2);
        slider.setPreferredSize(new Dimension(160,50));
        slider.addChangeListener(new SliderChangeListener());
        this.add(slider,BorderLayout.NORTH);
    }
    public void reset(){
        this.slider.setValue(0);
    }

    public JSlider getSlider() {
        return slider;
    }

    public void setSlider(JSlider slider) {
        this.slider = slider;
    }
}
