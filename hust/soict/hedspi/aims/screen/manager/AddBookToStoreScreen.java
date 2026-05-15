package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import javax.swing.*;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;

/**
 * Screen for adding a new Book to the store.
 * Inherits shared menu bar and layout from AddItemToStoreScreen.
 */
public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfId       = new JTextField(15);
    private JTextField tfTitle    = new JTextField(15);
    private JTextField tfCategory = new JTextField(15);
    private JTextField tfCost     = new JTextField(15);
    private JTextField tfAuthors  = new JTextField(15); // comma-separated

    public AddBookToStoreScreen(Store store, StoreManagerScreen parentScreen) {
        super(store, parentScreen, "AIMS - Add Book", 320);
    }

    @Override
    protected String getAddButtonLabel() {
        return "Add Book";
    }

    @Override
    protected JPanel createFormPanel() {
        JPanel form = new JPanel(new GridLayout(5, 2, 5, 8));
        form.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));

        form.add(new JLabel("ID:"));              form.add(tfId);
        form.add(new JLabel("Title:"));           form.add(tfTitle);
        form.add(new JLabel("Category:"));        form.add(tfCategory);
        form.add(new JLabel("Cost:"));            form.add(tfCost);
        form.add(new JLabel("Authors (comma):")); form.add(tfAuthors);

        return form;
    }

    @Override
    protected void handleAdd() {
        try {
            int id       = Integer.parseInt(tfId.getText().trim());
            String title = tfTitle.getText().trim();
            String cat   = tfCategory.getText().trim();
            float cost   = Float.parseFloat(tfCost.getText().trim());

            Book book = new Book(id, title, cat, cost);

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
}
