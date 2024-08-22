package UI;

import javax.swing.*;

public class Window {
    public static void init(String windowName, int width, int height){
        JFrame frame = new JFrame(windowName);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Interface.UI(frame);

        frame.setVisible(true);
    }
}
