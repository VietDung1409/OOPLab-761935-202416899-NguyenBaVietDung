package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;

/**
 * Screen for adding a new Book to the store.
 */
public class AddBookScreen extends JFrame {
    private Store store;
    private StoreManagerScreen parentScreen;

    private JTextField tfId       = new JTextField(15);
    private JTextField tfTitle    = new JTextField(15);
    private JTextField tfCategory = new JTextField(15);
    private JTextField tfCost     = new JTextField(15);
    private JTextField tfAuthors  = new JTextField(15); // comma-separated

    public AddBookScreen(Store store, StoreManagerScreen parentScreen) {
        this.store = store;
        this.parentScreen = parentScreen;

        setTitle("AIMS - Add Book");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createMenuBar(), BorderLayout.NORTH);
        add(createForm(), BorderLayout.CENTER);
        add(createButtons(), BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuView = new JMenu("View Store");
        menuView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                goBackToStore();
            }
        });
        menuBar.add(menuView);

        JMenu menuUpdate = new JMenu("Update Store");

        JMenuItem itemAddBook = new JMenuItem("Add Book");
        itemAddBook.addActionListener(e -> {
            dispose();
            new AddBookScreen(store, parentScreen);
        });
        menuUpdate.add(itemAddBook);

        JMenuItem itemAddCD = new JMenuItem("Add CD");
        itemAddCD.addActionListener(e -> {
            dispose();
            new AddCDScreen(store, parentScreen);
        });
        menuUpdate.add(itemAddCD);

        JMenuItem itemAddDVD = new JMenuItem("Add DVD");
        itemAddDVD.addActionListener(e -> {
            dispose();
            new AddDVDScreen(store, parentScreen);
        });
        menuUpdate.add(itemAddDVD);

        menuBar.add(menuUpdate);
        return menuBar;
    }

    private JPanel createForm() {
        JPanel form = new JPanel(new GridLayout(5, 2, 5, 8));
        form.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));

        form.add(new JLabel("ID:"));           form.add(tfId);
        form.add(new JLabel("Title:"));        form.add(tfTitle);
        form.add(new JLabel("Category:"));     form.add(tfCategory);
        form.add(new JLabel("Cost:"));         form.add(tfCost);
        form.add(new JLabel("Authors (,):"));  form.add(tfAuthors);

        return form;
    }

    private JPanel createButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));

        JButton btnAdd = new JButton("Add Book");
        btnAdd.addActionListener(e -> addBook());

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> goBackToStore());

        panel.add(btnAdd);
        panel.add(btnBack);
        return panel;
    }

    private void addBook() {
        try {
            int id         = Integer.parseInt(tfId.getText().trim());
            String title   = tfTitle.getText().trim();
            String cat     = tfCategory.getText().trim();
            float cost     = Float.parseFloat(tfCost.getText().trim());

            Book book = new Book(id, title, cat, cost);

            // Add authors (comma-separated, no validation needed per lab)
            String authorsRaw = tfAuthors.getText().trim();
            if (!authorsRaw.isEmpty()) {
                for (String author : authorsRaw.split(",")) {
                    book.addAuthor(author.trim());
                }
            }

            store.addMedia(book);
            JOptionPane.showMessageDialog(this,
                    "Book \"" + title + "\" added successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            goBackToStore();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numbers for ID and Cost.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goBackToStore() {
        dispose();
        parentScreen.setVisible(true);
        // Refresh the store view
        parentScreen.getContentPane().removeAll();
        parentScreen.getContentPane().setLayout(new BorderLayout());
        // Trigger reload via revalidate trick — delegate to parent
        parentScreen.dispatchEvent(new WindowEvent(parentScreen, WindowEvent.WINDOW_ACTIVATED));
        parentScreen.revalidate();
        parentScreen.repaint();
        // Re-open fresh
        Store s = store;
        parentScreen.dispose();
        new StoreManagerScreen(s);
    }
}
