package gui;

import utils.DataProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import config.ReadConfigUtils;

public class WavePanel extends JPanel {

    private StatisticsWrap statisticsWrap = null;

    private VirtualSample virtualSample = null;
    private boolean isVirtual = false;

    private int MAX_SHOW_COUNT = 110;
    private List<Integer> values = new ArrayList<>();
    private List<Integer> totalValues = new ArrayList<>();
    public int startPoint = 0;
    private int selectStartLine = -1;
    private int selectEndLine = -1;
    private int integrate = 0;

    WavePanel(StatisticsWrap statisticsWrap) {
        this.setPreferredSize(new Dimension(660, 140));
        this.virtualSample = new VirtualSample(values);
        this.statisticsWrap = statisticsWrap;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                switch (e.getButton()) {
                    case MouseEvent.BUTTON1:
                        if (e.getX() < selectEndLine || selectStartLine == -1) {
                            selectStartLine = e.getX();
                            if (selectStartLine == -1){
                                selectEndLine = selectStartLine + 1;
                            }
                        }
                        break;
                    case MouseEvent.BUTTON3:
                        if (e.getX() > selectStartLine || selectStartLine == -1) {
                            selectEndLine = e.getX();
                        }
                        break;
                }
                repaintWithNoValue();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    selectEndLine = -1;
                    selectStartLine = -1;
                    repaintWithNoValue();
                }
            }
        });
    }

    public void repaint(List<Integer> values) {
        ((WavePanelWrap) getParent()).slider.setMaximum(Math.max(values.size() - MAX_SHOW_COUNT, 0));
        this.totalValues = values;
        this.values = totalValues;
        super.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        int xDelta = w / MAX_SHOW_COUNT;
        int length = values.size();

        g2d.setColor(Color.decode(ReadConfigUtils.configList.getOrDefault("horizontalLineColor", "#ff00000")));
        g2d.drawLine(0, h / 2, w, h / 2);
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(1.50f));
        g2d.drawLine(selectStartLine, 10, selectStartLine, getHeight() - 10);
        g2d.drawLine(selectEndLine, 10, selectEndLine, getHeight() - 10);
        g2d.setColor(Color.decode(ReadConfigUtils.configList.getOrDefault("waveLineColor", "#000000")));
        g2d.setStroke(new BasicStroke(1.0f));


        int MAX_VALUE = 255;
        integrate = 0;
        if (isVirtual) {
            for (int i = 0; i < length - 1; ++i) {
                if ((MAX_SHOW_COUNT - length + i) * xDelta > selectStartLine && (MAX_SHOW_COUNT - length + i) * xDelta < selectEndLine) {
                    integrate += (values.get(i) + values.get(i + 1)) * xDelta / 2;
                    g2d.setColor(Color.GREEN);
                }
                g2d.drawLine(xDelta * (MAX_SHOW_COUNT - length + i), DataProcess.normalizeValueForYAxis(h, values.get(i), MAX_VALUE),
                        xDelta * (MAX_SHOW_COUNT - length + i + 1), DataProcess.normalizeValueForYAxis(h, values.get(i + 1), MAX_VALUE));
                g2d.setColor(Color.BLACK);
            }
        } else {
            for (int i = 0; i < length - 1; ++i) {
                if (i * xDelta > selectStartLine && i * xDelta < selectEndLine) {
                    integrate += (values.get(i) + values.get(i + 1)) * xDelta / 2;
                    g2d.setColor(Color.GREEN);
                }
                g2d.drawLine(xDelta * i, DataProcess.normalizeValueForYAxis(h, values.get(i), MAX_VALUE),
                        xDelta * (i + 1), DataProcess.normalizeValueForYAxis(h, values.get(i + 1), MAX_VALUE));
                g2d.setColor(Color.BLACK);
            }
        }

        statisticsWrap.setIntegrateValue(integrate);

    }

    public void repaintWithNoValue() {
        this.values = this.totalValues.stream().skip(startPoint).limit(MAX_SHOW_COUNT).collect(Collectors.toList());
        super.repaint();
    }

    public void toggleVirtual(){
        this.isVirtual = !isVirtual;
        this.virtualSample.toggleSampleState();
    }
}
