package gui;

import listener.MainFrameActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ChannelChoosePanel extends JPanel {
    private JComboBox<String> comboBox = null;
    private JLabel label = null;
    private int selectedIndex = 0;
    private final String[] indexName = {"一","二","三"};
    ChannelChoosePanel(){
        this.setPreferredSize(new Dimension(200,50));
        this.setBorder(new TitledBorder("通道选择"));
        comboBox = new JComboBox<>();
        comboBox.setPreferredSize(new Dimension(80,20));
        comboBox.addItem("第一通道");
        comboBox.addItem("第二通道");
        comboBox.addItem("第三通道");
        comboBox.addActionListener(new MainFrameActionListener());
        this.add(comboBox,BorderLayout.WEST);

        label = new JLabel("选中通道：" + indexName[selectedIndex]);
        label.setPreferredSize(new Dimension(100,20));
        this.add(label,BorderLayout.EAST);
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        label.setText("选中通道：" + indexName[selectedIndex]);
    }
}
