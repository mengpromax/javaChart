package listener;

import gui.MainFrame;
import utils.ReadFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainFrameActionListener implements ActionListener {
    private JFileChooser fileChooser = new JFileChooser();
    private Thread randomThread = null;

    public MainFrameActionListener(){
        randomThread = new Thread(() -> {
            List<Integer> values = new ArrayList<>();
            Random rand = new Random();
            try {
                while (true) {
                    addValue(values, rand.nextInt(255));
                    MainFrame.wavePanelContainer.wavePanelWrapWrapTop.wavePanel.repaint(values);
                    MainFrame.wavePanelContainer.wavePanelWrapWrapMid.wavePanel.repaint(values);
                    MainFrame.wavePanelContainer.wavePanelWrapWrapBot.wavePanel.repaint(values);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        randomThread.start();
        randomThread.suspend();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("从文件打开")) {
            if (randomThread.isAlive()) {
                randomThread.suspend();
            }
            fileChooser.setCurrentDirectory(new File("."));
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getPath();
                ReadFile readFile = new ReadFile(filePath);
                readFile.readData();
                MainFrame.wavePanelContainer.wavePanelWrapWrapTop.wavePanel.toggleVirtual();
                MainFrame.wavePanelContainer.wavePanelWrapWrapMid.wavePanel.toggleVirtual();
                MainFrame.wavePanelContainer.wavePanelWrapWrapBot.wavePanel.toggleVirtual();
                MainFrame.wavePanelContainer.wavePanelWrapWrapTop.wavePanel.repaint(readFile.getValues());
                MainFrame.wavePanelContainer.wavePanelWrapWrapMid.wavePanel.repaint(readFile.getValues());
                MainFrame.wavePanelContainer.wavePanelWrapWrapBot.wavePanel.repaint(readFile.getValues());
                MainFrame.wavePanelContainer.wavePanelWrapWrapTop.slider.setVisible(true);
                MainFrame.wavePanelContainer.wavePanelWrapWrapMid.slider.setVisible(true);
                MainFrame.wavePanelContainer.wavePanelWrapWrapBot.slider.setVisible(true);
            }
        } else if (command.equals("模拟实时采集")) {
            MainFrame.wavePanelContainer.wavePanelWrapWrapTop.wavePanel.toggleVirtual();
            MainFrame.wavePanelContainer.wavePanelWrapWrapMid.wavePanel.toggleVirtual();
            MainFrame.wavePanelContainer.wavePanelWrapWrapBot.wavePanel.toggleVirtual();
            MainFrame.wavePanelContainer.wavePanelWrapWrapTop.slider.setVisible(false);
            MainFrame.wavePanelContainer.wavePanelWrapWrapMid.slider.setVisible(false);
            MainFrame.wavePanelContainer.wavePanelWrapWrapBot.slider.setVisible(false);
            randomThread.resume();

        }
    }

    private void addValue(List<Integer> list, int randomInt) {
        if (list.size() > 110) {
            list.remove(0);
        }
        list.add(randomInt);
    }
}
