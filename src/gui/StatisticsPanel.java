package gui;

import javax.swing.*;
import java.awt.*;

public class StatisticsPanel extends JPanel {
    private ZoomButtonContainer zoomButtonContainer = null;
    private StatisticsDashboard statisticsDashboard = null;
    private ChannelChoosePanel channelChoosePanel = null;
    StatisticsPanel(){
        this.setBorder(BorderFactory.createTitledBorder("数据显示"));
        this.setPreferredSize(new Dimension(200,600));
        zoomButtonContainer = new ZoomButtonContainer();
        this.add(zoomButtonContainer,BorderLayout.NORTH);
        statisticsDashboard = new StatisticsDashboard();
        this.add(statisticsDashboard,BorderLayout.NORTH);
        channelChoosePanel = new ChannelChoosePanel();
        this.add(channelChoosePanel,BorderLayout.NORTH);

    }

    StatisticsDashboard getStatisticsDashboard() {
        return statisticsDashboard;
    }

    public void setStatisticsDashboard(StatisticsDashboard statisticsDashboard) {
        this.statisticsDashboard = statisticsDashboard;
    }

    public ZoomButtonContainer getZoomButtonContainer() {
        return zoomButtonContainer;
    }

    public void setZoomButtonContainer(ZoomButtonContainer zoomButtonContainer) {
        this.zoomButtonContainer = zoomButtonContainer;
    }

    public ChannelChoosePanel getChannelChoosePanel() {
        return channelChoosePanel;
    }

    public void setChannelChoosePanel(ChannelChoosePanel channelChoosePanel) {
        this.channelChoosePanel = channelChoosePanel;
    }
}
