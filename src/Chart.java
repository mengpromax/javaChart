import config.ReadConfigUtils;
import gui.MainFrame;

class Chart {
    private Chart() {

    }

    public static void main(String[] args) {
        
        ReadConfigUtils.initConfigList();
        new MainFrame();
    }
}
