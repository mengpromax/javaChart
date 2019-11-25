package listener;

import gui.MainFrame;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderChangeListener implements ChangeListener {
    @Override
    public void stateChanged(ChangeEvent e) {
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
        }
    }
}
