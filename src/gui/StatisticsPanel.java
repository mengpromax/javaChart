package gui;

import javax.swing.*;
import java.awt.*;

public class StatisticsPanel extends JPanel {
    private ZoomButtonContainer zoomButtonContainer = null;
    public StatisticsDashboard statisticsDashboard = null;
    StatisticsPanel(){
        this.setBorder(BorderFactory.createTitledBorder("数据显示"));
        this.setPreferredSize(new Dimension(200,600));
        zoomButtonContainer = new ZoomButtonContainer();
        this.add(zoomButtonContainer,BorderLayout.NORTH);
        statisticsDashboard = new StatisticsDashboard();
        this.add(statisticsDashboard,BorderLayout.SOUTH);
    }

    public StatisticsDashboard getStatisticsDashboard() {
        return statisticsDashboard;
    }

    public void setStatisticsDashboard(StatisticsDashboard statisticsDashboard) {
        this.statisticsDashboard = statisticsDashboard;
    }
}
