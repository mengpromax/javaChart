package gui;

import listener.SliderChangeListener;

import javax.swing.*;
import java.awt.*;

public class WavePanelWrap extends JPanel {
    public WavePanel wavePanel = null;
    public JSlider slider = new JSlider();
    WavePanelWrap(StatisticsWrap statisticsWrap){
        wavePanel = new WavePanel(statisticsWrap);
        this.setPreferredSize(new Dimension(680,185));
        this.add(wavePanel,BorderLayout.NORTH);
        slider.setPreferredSize(new Dimension(650,10));
        slider.setValue(0);
        slider.setMaximum(0);
        slider.addChangeListener(new SliderChangeListener());
        this.add(slider,BorderLayout.SOUTH);
    }

    public WavePanel getWavePanel() {
        return wavePanel;
    }
}
