package gui;

import listener.MainFrameActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.metal.MetalScrollButton;
import java.awt.*;

public class ZoomButtonGroup extends JPanel {
    ZoomButtonGroup(){
        this.setPreferredSize(new Dimension(60,150));
        this.setBorder(new TitledBorder("一"));
        ImageIcon icon;
        JButton button;
        icon = new ImageIcon("zoomUp.png");
        icon=new ImageIcon(icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        button = new JButton(icon);
        button.setName("zoomUp");
        button.addActionListener(new MainFrameActionListener());
        button.setPreferredSize(new Dimension(35,35));
        this.add(button,BorderLayout.NORTH);
        icon = new ImageIcon("zoomDown.png");
        icon=new ImageIcon(icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        button = new JButton(icon);
        button.setName("zoomDown");
        button.addActionListener(new MainFrameActionListener());
        button.setPreferredSize(new Dimension(35,35));
        this.add(button,BorderLayout.SOUTH);
    }
}
