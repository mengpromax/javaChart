package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class StatisticsDashboard extends JPanel {
    private List<StatisticsWrap> statisticsWraps = new ArrayList<>();
    StatisticsDashboard(){
        this.setPreferredSize(new Dimension(190,120));
        for (int i = 0;i < 3;i++)
            statisticsWraps.add(new StatisticsWrap());
        this.setBorder(new TitledBorder("数据面板"));
        this.add(statisticsWraps.get(0),BorderLayout.NORTH);
        this.add(statisticsWraps.get(1),BorderLayout.CENTER);
        this.add(statisticsWraps.get(2),BorderLayout.SOUTH);
    }

    List<StatisticsWrap> getStatisticsWraps() {
        return statisticsWraps;
    }

}
