package project.client.views;

import com.formdev.flatlaf.FlatLightLaf;
import project.client.views.components.Header;
import project.client.views.components.Menu;

import javax.swing.*;
import java.awt.*;

public class ClientMainView extends JFrame {

    public static int FRAME_WIDTH = 1000;
    private Header header;
    private Menu menu;
    private JPanel contentHolder = new JPanel();



    public ClientMainView() {
        initializeFrame();

        header = new Header("Giovanni Leo");
        menu = new Menu();
        contentHolder.setPreferredSize(new Dimension(FRAME_WIDTH, 550));
        add(header);
        add(menu);
        add(contentHolder);
    }


    private void initializeFrame() {

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 10);
            UIManager.put("TextComponent.arc", 5);
            UIManager.put("ScrollBar.width", 10);
            UIManager.put("ScrollBar.thumbArc", 3);
            UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        setSize(new Dimension(FRAME_WIDTH, 800));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        setVisible(true);
    }

    public Header getHeader() {
        return header;
    }

    public Menu getMenu() {
        return menu;
    }
}
