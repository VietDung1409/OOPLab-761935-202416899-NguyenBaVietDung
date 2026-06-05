package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import javax.swing.*;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.store.Store;

/**
 * Screen for adding a new CompactDisc to the store.
 * Inherits shared menu bar and layout from AddItemToStoreScreen.
 */
public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfTitle    = new JTextField(15);
    private JTextField tfCategory = new JTextField(15);
    private JTextField tfCost     = new JTextField(15);
    private JTextField tfArtist   = new JTextField(15);

    public AddCompactDiscToStoreScreen(Store store, StoreManagerScreen parentScreen) {
        super(store, parentScreen, "AIMS - Add CD", 300);
        init(); // must be called after fields are initialized
    }

    @Override
    protected String getAddButtonLabel() {
        return "Add CD";
    }

    @Override
    protected JPanel createFormPanel() {
        JPanel form = new JPanel(new GridLayout(4, 2, 5, 8));
        form.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));

        form.add(new JLabel("Title:"));    form.add(tfTitle);
        form.add(new JLabel("Category:")); form.add(tfCategory);
        form.add(new JLabel("Cost:"));     form.add(tfCost);
        form.add(new JLabel("Artist:"));   form.add(tfArtist);

        return form;
    }

    @Override
    protected void handleAdd() {
        try {
            int id        = store.getItems().size() + 1;
            String title  = tfTitle.getText().trim();
            String cat    = tfCategory.getText().trim();
            float cost    = Float.parseFloat(tfCost.getText().trim());
            String artist = tfArtist.getText().trim();

            CompactDisc cd = new CompactDisc(id, title, cat, cost, artist);
            store.addMedia(cd);

            JOptionPane.showMessageDialog(this,
                    "CD \"" + title + "\" added successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            goBackToStore();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numbers for ID and Cost.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
