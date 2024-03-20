package project.server.views.components.manageBookPanel;

import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.FieldInput;
import project.utilities.viewComponents.Button;


import javax.swing.*;
import java.awt.*;

public class ManageBooksSearch extends JPanel {
    private final FieldInput input;
    private final Button cancelSearch;

    public ManageBooksSearch(Dimension dimension) {
        setPreferredSize(dimension);
        setLayout(new GridBagLayout());
        setBackground(Color.white);

        GridBagConstraints layout = new GridBagConstraints();
        layout.anchor = GridBagConstraints.WEST;
        layout.gridx = 0;
        layout.gridy = 1;
        layout.weightx = 2.0;
        layout.insets = new Insets(0, 70, 0, 0);
        input = new FieldInput("", new Dimension((int) (dimension.getWidth() / 1.5), (int) (dimension.getHeight() - 50)), 30, 0, false);
        input.setBackground(Color.white);
        add(input, layout);

        cancelSearch = new Button("X", 50, 50, FontFactory.newPoppinsDefault(13));
        layout.gridx = 2;
        layout.weightx = 0;
        add(cancelSearch, layout);
    }
    public String getSearch() {
        return input.getInput();
    }

    public Button getCancelButton() {
        return cancelSearch;
    }
    public FieldInput getInput() {
        return input;
    }
}
