package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import hust.soict.hedspi.aims.store.Store;

/**
 * Abstract parent screen for all "Add item to store" screens.
 * Provides the shared menu bar and the common layout structure.
 * Subclasses implement createFormPanel() to add their specific fields,
 * and handleAdd() to build and add the media object to the store.
 */
public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected StoreManagerScreen parentScreen;

    public AddItemToStoreScreen(Store store, StoreManagerScreen parentScreen, String title, int height) {
        this.store = store;
        this.parentScreen = parentScreen;

        setTitle(title);
        setSize(420, height);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());

        // Shared menu bar across all add screens
        setJMenuBar(createMenuBar());
        add(createFormPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ===================== SHARED MENU BAR =====================

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
            new AddBookToStoreScreen(store, parentScreen);
        });
        menuUpdate.add(itemAddBook);

        JMenuItem itemAddCD = new JMenuItem("Add CD");
        itemAddCD.addActionListener(e -> {
            dispose();
            new AddCompactDiscToStoreScreen(store, parentScreen);
        });
        menuUpdate.add(itemAddCD);

        JMenuItem itemAddDVD = new JMenuItem("Add DVD");
        itemAddDVD.addActionListener(e -> {
            dispose();
            new AddDigitalVideoDiscToStoreScreen(store, parentScreen);
        });
        menuUpdate.add(itemAddDVD);

        menuBar.add(menuUpdate);
        return menuBar;
    }

    // ===================== SHARED BUTTON PANEL =====================

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));

        JButton btnAdd = new JButton(getAddButtonLabel());
        btnAdd.addActionListener(e -> handleAdd());

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> goBackToStore());

        panel.add(btnAdd);
        panel.add(btnBack);
        return panel;
    }

    // ===================== NAVIGATION =====================

    protected void goBackToStore() {
        Store s = store;
        dispose();
        parentScreen.dispose();
        new StoreManagerScreen(s);
    }

    // ===================== ABSTRACT METHODS =====================

    /** Subclasses return a JPanel containing their specific input fields. */
    protected abstract JPanel createFormPanel();

    /** Subclasses implement the logic to read fields and add media to store. */
    protected abstract void handleAdd();

    /** Subclasses return the label for the Add button (e.g. "Add Book"). */
    protected abstract String getAddButtonLabel();
}
