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
    private int maxValue = 255;
    private int minValue = 0;


    private int maxShowCount = 110;
    private List<Integer> values = new ArrayList<>();
    private List<Integer> totalValues = new ArrayList<>();
    public int startPoint = 0;
    private int selectStartLine = -1;
    private int selectEndLine = -1;
    private double zoomFactor = 0;//缩放比例


    WavePanel(StatisticsWrap statisticsWrap) {
        this.setPreferredSize(new Dimension(660, 140));
        this.virtualSample = new VirtualSample(this);
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
        ((WavePanelWrap) getParent()).slider.setMaximum(Math.max(values.size() - maxShowCount, 0));
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
        int xDelta = w / maxShowCount;
        int length = values.size();
        int fontWidth = 0;

        g2d.setColor(Color.decode(ReadConfigUtils.configList.getOrDefault("horizontalLineColor", "#00ff00")));
        fontWidth = SwingUtilities.computeStringWidth(g2d.getFontMetrics(), String.valueOf(0.5 * (maxValue - minValue)));
        g2d.drawLine(fontWidth + 5, h / 2, w, h / 2);
        g2d.drawString(String.valueOf(0.5 * (maxValue - minValue)),0,(int)(0.5 * h) + g2d.getFontMetrics().getHeight() / 4);
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(1.50f));
        g2d.drawLine(selectStartLine, 10, selectStartLine, getHeight() - 10);
        g2d.drawLine(selectEndLine, 10, selectEndLine, getHeight() - 10);
        g2d.setColor(Color.decode(ReadConfigUtils.configList.getOrDefault("waveLineColor", "#000000")));
        g2d.setStroke(new BasicStroke(1.0f));


        if (values.size() != 0) {
            maxValue = (int) (values.stream().max(Integer::compare).get() + zoomFactor * (values.stream().max(Integer::compare).get() - values.stream().min(Integer::compare).get()) / 16);
            minValue = (int) (values.stream().min(Integer::compare).get() - zoomFactor * (values.stream().max(Integer::compare).get() - values.stream().min(Integer::compare).get()) / 16);
        }
        int integrate = 0;
        int total = 0;
        g2d.setStroke(new BasicStroke(0.2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[]{3.0f, 3.0f}, 5.0f));
        g2d.setFont(new Font("Arial", Font.BOLD, 10));
        for (int i = 0;i < maxShowCount;i++){
            g2d.drawLine(xDelta * i, 0, xDelta * i,h);
            if (i % 20 == 0){
                fontWidth = SwingUtilities.computeStringWidth(g2d.getFontMetrics(), String.valueOf(i));
                g2d.drawString(String.valueOf(i),xDelta * i + fontWidth / 2,h);
            }
        }
        g2d.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[]{3.0f, 10.0f}, 1.0f));
        g2d.setFont(new Font("Arial", Font.BOLD, 10));
        g2d.drawString(String.valueOf(0.75 * (maxValue - minValue)),0,(int)(0.25 * h) + g2d.getFontMetrics().getHeight() / 4);
        fontWidth = SwingUtilities.computeStringWidth(g2d.getFontMetrics(), String.valueOf(0.75 * (maxValue - minValue)));
        g2d.drawLine(xDelta + fontWidth + 5,(int)(0.75 * h),w - xDelta,(int)(0.75 * h));
        fontWidth = SwingUtilities.computeStringWidth(g2d.getFontMetrics(), String.valueOf(0.25 * (maxValue - minValue)));
        g2d.drawString(String.valueOf(0.25 * (maxValue - minValue)),0,(int)(0.75 * h) + g2d.getFontMetrics().getHeight() / 4);
        g2d.drawLine(xDelta + fontWidth + 5,(int)(0.25 * h),w - xDelta,(int)(0.25 * h));
        g2d.setStroke(new BasicStroke(1.0f));
        if (isVirtual) {
            for (int i = 0; i < length - 1; ++i) {
                if ((maxShowCount - length + i) * xDelta > selectStartLine && (maxShowCount - length + i) * xDelta < selectEndLine) {
                    integrate += (values.get(i) + values.get(i + 1)) * xDelta / 2;
                    total += values.get(i);
                    g2d.setColor(Color.decode(ReadConfigUtils.configList.getOrDefault("selectedLineColor", "#00ff00")));
                }
                g2d.drawLine(xDelta * (maxShowCount - length + i), DataProcess.normalizeValueForYAxis(h, values.get(i), maxValue, minValue),
                        xDelta * (maxShowCount - length + i + 1), DataProcess.normalizeValueForYAxis(h, values.get(i + 1), maxValue, minValue));
                g2d.setColor(Color.decode(ReadConfigUtils.configList.getOrDefault("waveLineColor", "#000000")));
            }
        } else {
            for (int i = 0; i < length - 1; ++i) {
                if (i * xDelta > selectStartLine && i * xDelta < selectEndLine) {
                    integrate += (values.get(i) + values.get(i + 1)) * xDelta / 2;
                    total += values.get(i);
                    g2d.setColor(Color.GREEN);
                    g2d.setColor(Color.decode(ReadConfigUtils.configList.getOrDefault("selectedLineColor", "#00ff00")));
                }
                g2d.drawLine(xDelta * i, DataProcess.normalizeValueForYAxis(h, values.get(i), maxValue, minValue),
                        xDelta * (i + 1), DataProcess.normalizeValueForYAxis(h, values.get(i + 1), maxValue, minValue));
                g2d.setColor(Color.decode(ReadConfigUtils.configList.getOrDefault("waveLineColor", "#000000")));
            }
        }

        statisticsWrap.setIntegrateValue(integrate);
        statisticsWrap.setMinimizeValue(selectEndLine - selectStartLine == 0? -1 : total / (selectEndLine - selectStartLine) * xDelta);

    }

    public void repaintWithNoValue() {
        this.values = this.totalValues.stream().skip(startPoint).limit(maxShowCount).collect(Collectors.toList());
        super.repaint();
    }

    public void toggleVirtual(){
        this.isVirtual = !isVirtual;
        this.virtualSample.toggleSampleState();
    }

    public boolean isVirtual() {
        return isVirtual;
    }

    public void setVirtual(boolean virtual) {
        isVirtual = virtual;
    }

    public int getMaxShowCount() {
        return maxShowCount;
    }

    public void setMaxShowCount(int maxShowCount) {
        this.maxShowCount = maxShowCount;
        virtualSample.setMaxCapacity(maxShowCount);
    }

    public void zoomUp(){
        virtualSample.setMaxCapacity(maxShowCount);
        this.maxShowCount = maxShowCount / 2;
        this.repaintWithNoValue();
    }

    public void zoomDown(){
        virtualSample.setMaxCapacity(maxShowCount);
        this.maxShowCount = maxShowCount * 2;
        this.repaintWithNoValue();
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }

    public double getZoomFactor() {
        return zoomFactor;
    }

    public void setZoomFactor(double zoomFactor) {
        this.zoomFactor = zoomFactor;
        this.repaintWithNoValue();
    }
}
