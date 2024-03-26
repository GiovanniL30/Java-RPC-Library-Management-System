package project.server.views.components;

import project.server.controller.ServerObserver;
import project.server.views.utility.ServerPanels;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class SubHeader extends JPanel {

    private final LinkedList<ClickableText> clickableTexts = new LinkedList<>();
    private final ServerSearchBar searchBar = new ServerSearchBar(new Dimension(500, 55));
    private final ClickableText button1;
    private final ClickableText button2;
    private final ClickableText button3;
    private ClickableText currentButton;
    private final JLabel errorMessage;

    public SubHeader(ClickableText button1, ClickableText button2, ClickableText button3, ServerObserver serverObserver) {
        this.errorMessage = new JLabel();
        this.button1 = button1;
        this.button2 = button2;
        this.button3 = button3;

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.gridx = 0;
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(900, 55));
        setBackground(Color.white);

        clickableTexts.add(this.button1);
        clickableTexts.add(this.button2);
        clickableTexts.add(this.button3);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.white);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        buttonsPanel.add(button1);
        buttonsPanel.add(button2);
        buttonsPanel.add(button3);

        add(buttonsPanel, constraints);

        constraints.gridx = 1;
        constraints.fill = 2;
        constraints.weightx = 2.0;
        add(searchBar, constraints);
        setCurrentClickableText(button1);

        constraints.gridy = 1;
        errorMessage.setForeground(Color.RED);
        errorMessage.setVisible(false);
        add(errorMessage, constraints);

        this.button1.addActionListener(e -> {

            if (button1.getText().equals(ServerPanels.All_BOOKS_PANEL.getDisplayName())) {
                serverObserver.changeFrame(ServerPanels.All_BOOKS_PANEL);
            } else if (button1.getText().equals(ServerPanels.PENDING_BORROW_PANEL.getDisplayName())) {
                serverObserver.changeFrame(ServerPanels.PENDING_BORROW_PANEL);
            }

        });

        this.button2.addActionListener(e -> {

            if (button2.getText().equals(ServerPanels.AVAILABLE_BOOKS_PANEL.getDisplayName())) {
                serverObserver.changeFrame(ServerPanels.AVAILABLE_BOOKS_PANEL);
            } else if (button2.getText().equals(ServerPanels.PENDING_RETURN_PANEL.getDisplayName())) {
                serverObserver.changeFrame(ServerPanels.PENDING_RETURN_PANEL);
            }

        });
        this.button3.addActionListener(e -> {
            if (button3.getText().equals(ServerPanels.UNAVAILABLE_BOOKS_PANEL.getDisplayName())) {
                serverObserver.changeFrame(ServerPanels.UNAVAILABLE_BOOKS_PANEL);
            }else if(button3.getText().equals(ServerPanels.BORROWED_PANEL.getDisplayName())) {
                serverObserver.changeFrame(ServerPanels.BORROWED_PANEL);
            }

        });
    }
    public void enableError(String message) {

        errorMessage.setText(message);
        errorMessage.setVisible(true);

    } // end of enableError method
    public void setCurrentClickableText(ClickableText currentText) {

        currentButton = currentText;
        currentText.setEnabled(false);

        for (ClickableText clickableText : clickableTexts) {

            if (!clickableText.equals(currentText)) {
                clickableText.setEnabled(true);
            }

        }
    }

    public ClickableText getButton1() {
        return button1;
    }

    public ClickableText getButton2() {
        return button2;
    }

    public ClickableText getButton3() {
        return button3;
    }

    public ClickableText getCurrentButton() {
        return currentButton;
    }

    public ServerSearchBar getSearchBar() {
        return searchBar;
    }

}
