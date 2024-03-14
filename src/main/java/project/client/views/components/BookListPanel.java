package project.client.views.components;

import project.utilities.referenceClasses.Book;
import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Button;
import project.utilities.viewComponents.Picture;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.LinkedList;

public class BookListPanel extends JPanel {

    private boolean isPending;
    public BookListPanel(LinkedList<Book> pendingBooks, boolean isPending){

        this.isPending = isPending;

        JPanel holder = new JPanel();
        GridLayout gridLayout = new GridLayout(0, 1);
        gridLayout.setVgap(20);
        holder.setLayout(gridLayout);

        for(Book book: pendingBooks) {
            holder.add(new BookCard(book));
        }

        JScrollPane scrollPane = new JScrollPane(holder);
        scrollPane.setPreferredSize(new Dimension(900, 400));

        add(scrollPane);

    }

    private  class BookCard extends JPanel {

        private BookCard(Book book) {

            setSize(new Dimension(300, 200));
            setLayout(new GridBagLayout());
            setBorder(new EmptyBorder(0, 10, 0, 20));

            Picture picture = new Picture(book.getImagePath(), 130, 200);
            JLabel bookName = new JLabel(book.getBookTitle());
            bookName.setFont(FontFactory.newPoppinsBold(18));
            JLabel author = new JLabel("by: " + book.getAuthor());
            author.setFont(FontFactory.newPoppinsDefault(12));

            Button button;
            if(isPending){
                button = new Button("Cancel", 100, 50, FontFactory.newPoppinsDefault(13));
            }else {
                button = new Button("Return", 100, 50, FontFactory.newPoppinsDefault(13));
            }

            button.setForeground(Color.white);
            button.setBackground(ColorFactory.red());

            JPanel bookInfoPanel = new JPanel();
            JPanel bookPanel = new JPanel();

            bookInfoPanel.setLayout(new BoxLayout(bookInfoPanel, BoxLayout.Y_AXIS));
            bookInfoPanel.add(bookName);
            bookInfoPanel.add(author);

            bookPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            bookPanel.add(picture);
            bookPanel.add(bookInfoPanel);

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.weightx = 2.0;
            constraints.fill = 2;

            add(bookPanel, constraints);

            constraints.gridx  =1;
            constraints.weightx = 0.0;
            constraints.fill = 0;
            add(button, constraints);
        }


    }


}
