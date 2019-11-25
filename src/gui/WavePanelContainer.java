package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class WavePanelContainer extends JPanel {
    public WavePanelWrap wavePanelWrapWrapTop = null;
    public WavePanelWrap wavePanelWrapWrapMid = null;
    public WavePanelWrap wavePanelWrapWrapBot = null;

    WavePanelContainer(List<StatisticsWrap> list){

        this.wavePanelWrapWrapTop = new WavePanelWrap(list.get(0));
        this.wavePanelWrapWrapMid = new WavePanelWrap(list.get(1));
        this.wavePanelWrapWrapBot = new WavePanelWrap(list.get(2));

        this.setBorder(BorderFactory.createTitledBorder("数据面板"));
        this.setPreferredSize(new Dimension(700,600));
        wavePanelWrapWrapTop.setBorder(BorderFactory.createTitledBorder("第一通道"));
        wavePanelWrapWrapTop.slider.setName("topSlider");
        this.add(wavePanelWrapWrapTop, BorderLayout.NORTH);
        wavePanelWrapWrapMid.setBorder(BorderFactory.createTitledBorder("第二通道"));
        wavePanelWrapWrapMid.slider.setName("midSlider");
        this.add(wavePanelWrapWrapMid,BorderLayout.CENTER);
        wavePanelWrapWrapBot.setBorder(BorderFactory.createTitledBorder("第三通道"));
        wavePanelWrapWrapBot.slider.setName("botSlider");
        this.add(wavePanelWrapWrapBot,BorderLayout.SOUTH);
    }

}
