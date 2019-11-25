package listener;

import gui.ChannelChoosePanel;
import gui.MainFrame;
import gui.WavePanelWrap;
import utils.ReadFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFrameActionListener implements ActionListener {
    private JFileChooser fileChooser = new JFileChooser();
    private Thread randomThread = null;
    private Map<String, Integer> channelIndex = new HashMap<String, Integer>() {{
        put("第一通道", 0);
        put("第二通道", 1);
        put("第三通道", 2);
    }};
    private Map<String, Integer> zoomIndex = new HashMap<>() {{
        put("zoomUp", 0);
        put("zoomDown", 1);
    }};

    private List<WavePanelWrap> wavePanelWrapList = new ArrayList<>();

    public MainFrameActionListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        wavePanelWrapList.clear();
        wavePanelWrapList.add(MainFrame.wavePanelContainer.wavePanelWrapWrapTop);
        wavePanelWrapList.add(MainFrame.wavePanelContainer.wavePanelWrapWrapMid);
        wavePanelWrapList.add(MainFrame.wavePanelContainer.wavePanelWrapWrapBot);
        String command = e.getActionCommand();
        if (e.getSource().getClass().getName().toLowerCase().contains("jbutton")) {
            JButton button = ((JButton) e.getSource());
            String buttonType = button.getName();
            String buttonGroup = button.getParent().getName();
            int groupIndex = channelIndex.get(buttonGroup);
            int operateIndex = zoomIndex.get(buttonType);
            if (operateIndex == 0) {
                wavePanelWrapList.get(groupIndex).wavePanel.zoomUp();
            } else {
                wavePanelWrapList.get(groupIndex).wavePanel.zoomDown();
            }
            return;
        }
        switch (command) {
            case "从文件打开":
                fileChooser.setCurrentDirectory(new File("."));
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getPath();
                    ReadFile readFile = new ReadFile(filePath);
                    readFile.readData();

                    if (wavePanelWrapList.get(MainFrame.statisticsPanel.getChannelChoosePanel().getSelectedIndex()).wavePanel.isVirtual()) {
                        wavePanelWrapList.get(MainFrame.statisticsPanel.getChannelChoosePanel().getSelectedIndex()).wavePanel.toggleVirtual();
                        wavePanelWrapList.get(MainFrame.statisticsPanel.getChannelChoosePanel().getSelectedIndex()).slider.setVisible(true);
                    }
                    wavePanelWrapList.get(MainFrame.statisticsPanel.getChannelChoosePanel().getSelectedIndex()).wavePanel.repaint(readFile.getValues());
                }
                break;
            case "模拟实时采集":
                if (!wavePanelWrapList.get(MainFrame.statisticsPanel.getChannelChoosePanel().getSelectedIndex()).wavePanel.isVirtual()) {
                    wavePanelWrapList.get(MainFrame.statisticsPanel.getChannelChoosePanel().getSelectedIndex()).wavePanel.toggleVirtual();
                    wavePanelWrapList.get(MainFrame.statisticsPanel.getChannelChoosePanel().getSelectedIndex()).slider.setVisible(false);
                }
                break;
            case "comboBoxChanged":
                JComboBox<String> tempComboBox = ((JComboBox<String>) e.getSource());
                int seleceIndex = tempComboBox.getSelectedIndex();
                ((ChannelChoosePanel) tempComboBox.getParent()).setSelectedIndex(seleceIndex);
                break;
        }

    }


    private void addValue(List<Integer> list, int randomInt) {
        if (list.size() > 110) {
            list.remove(0);
        }
        list.add(randomInt);
    }
}
