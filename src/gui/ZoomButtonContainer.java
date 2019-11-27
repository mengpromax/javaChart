package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ZoomButtonContainer extends JPanel {
    private ZoomButtonGroup leftZoomButtonGroup = null;
    private ZoomButtonGroup centerZoomButtonGroup = null;
    private ZoomButtonGroup rightZoomButtonGroup = null;

    ZoomButtonContainer(){

        this.setPreferredSize(new Dimension(190,140));
        this.setBorder(new TitledBorder("波形图压缩/拉伸"));

        leftZoomButtonGroup = new ZoomButtonGroup();
        leftZoomButtonGroup.setBorder(BorderFactory.createTitledBorder("一通道"));
        leftZoomButtonGroup.setName("第一通道");
        leftZoomButtonGroup.setPreferredSize(new Dimension(55,110));
        this.add(leftZoomButtonGroup,BorderLayout.WEST);

        centerZoomButtonGroup = new ZoomButtonGroup();
        centerZoomButtonGroup.setPreferredSize(new Dimension(55,110));
        centerZoomButtonGroup.setName("第二通道");
        centerZoomButtonGroup.setBorder(BorderFactory.createTitledBorder("二通道"));
        this.add(centerZoomButtonGroup,BorderLayout.CENTER);


        rightZoomButtonGroup = new ZoomButtonGroup();
        rightZoomButtonGroup.setPreferredSize(new Dimension(55,110));
        rightZoomButtonGroup.setName("第三通道");
        rightZoomButtonGroup.setBorder(BorderFactory.createTitledBorder("三通道"));
        this.add(rightZoomButtonGroup,BorderLayout.EAST);

    }
}
