package gui;


import listener.MainFrameActionListener;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    static StatisticsPanel statisticsPanel = null;
    public static WavePanelContainer wavePanelContainer = null;

    public MainFrame(){
        initMenu();
        //窗口的设置
        this.setSize(900,600);
        this.setResizable(false);
        this.setTitle("[万恶之原]-信号数据处理系统 v1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("icon.png");
        this.setIconImage(icon.getImage());

        /*左边的数据显示面板*/
        statisticsPanel = new StatisticsPanel();
        add(statisticsPanel,BorderLayout.WEST);


        /*右边的通道采集面板*/
        wavePanelContainer = new WavePanelContainer(statisticsPanel.getStatisticsDashboard().getStatisticsWraps());
        add(wavePanelContainer,BorderLayout.EAST);


        this.pack();
        this.setVisible(true);
    }

    /**
     * description:菜单栏的初始化
     */
    private void initMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu open = new JMenu("打开");
        JMenu statistics = new JMenu("统计数据");
        JMenuItem fromFile = new JMenuItem("从文件打开");
        JMenuItem fromVirtualMachine = new JMenuItem("模拟实时采集");
        MainFrameActionListener listener = new MainFrameActionListener();
        fromFile.addActionListener(listener);
        fromVirtualMachine.addActionListener(listener);
        open.add(fromFile);
        open.add(fromVirtualMachine);
        menuBar.add(open);
        menuBar.add(statistics);
        this.setJMenuBar(menuBar);
    }
}
