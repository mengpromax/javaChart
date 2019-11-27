package listener;

import gui.MainFrame;
import gui.WavePanelWrap;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;

public class SliderChangeListener implements ChangeListener {
    private List<WavePanelWrap> wavePanelWrapList = new ArrayList<>();
    @Override
    public void stateChanged(ChangeEvent e) {
        wavePanelWrapList.clear();
        wavePanelWrapList.add(MainFrame.wavePanelContainer.wavePanelWrapWrapTop);
        wavePanelWrapList.add(MainFrame.wavePanelContainer.wavePanelWrapWrapMid);
        wavePanelWrapList.add(MainFrame.wavePanelContainer.wavePanelWrapWrapBot);
        String objectName = ((JSlider)e.getSource()).getName();
        switch (objectName) {
            case "topSlider":
                MainFrame.wavePanelContainer.wavePanelWrapWrapTop.getWavePanel().startPoint = ((JSlider) e.getSource()).getValue();
                MainFrame.wavePanelContainer.wavePanelWrapWrapTop.getWavePanel().repaintWithNoValue();
                break;
            case "midSlider":
                MainFrame.wavePanelContainer.wavePanelWrapWrapMid.getWavePanel().startPoint = ((JSlider) e.getSource()).getValue();
                MainFrame.wavePanelContainer.wavePanelWrapWrapMid.getWavePanel().repaintWithNoValue();
                break;
            case "botSlider":
                MainFrame.wavePanelContainer.wavePanelWrapWrapBot.getWavePanel().startPoint = ((JSlider) e.getSource()).getValue();
                MainFrame.wavePanelContainer.wavePanelWrapWrapBot.getWavePanel().repaintWithNoValue();
                break;
            case "zoomFactor":
                int value = ((JSlider) e.getSource()).getValue();
                wavePanelWrapList.get(MainFrame.statisticsPanel.getChannelChoosePanel().getSelectedIndex()).wavePanel.setZoomFactor(value);
                break;
        }
    }
}
