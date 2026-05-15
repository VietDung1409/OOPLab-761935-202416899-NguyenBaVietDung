package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.Store;

/**
 * Main screen for the Store Manager role.
 * Shows all items in the store and provides menu to update the store.
 */
public class StoreManagerScreen extends JFrame {
    private Store store;

    public StoreManagerScreen(Store store) {
        this.store = store;

        setTitle("AIMS - Store Manager");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createNorth(), BorderLayout.NORTH);
        add(createCenter(), BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ===================== NORTH =====================

    private JPanel createNorth() {
        JPanel north = new JPanel(new BorderLayout());
        north.add(createMenuBar(), BorderLayout.NORTH);
        north.add(createHeader(), BorderLayout.CENTER);
        return north;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // "View Store" menu item (single item, acts as button)
        JMenu menuView = new JMenu("View Store");
        menuView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                reloadStoreView();
            }
        });
        menuBar.add(menuView);

        // "Update Store" menu with sub-items
        JMenu menuUpdate = new JMenu("Update Store");

        JMenuItem itemAddBook = new JMenuItem("Add Book");
        itemAddBook.addActionListener(e -> openAddBookScreen());
        menuUpdate.add(itemAddBook);

        JMenuItem itemAddCD = new JMenuItem("Add CD");
        itemAddCD.addActionListener(e -> openAddCDScreen());
        menuUpdate.add(itemAddCD);

        JMenuItem itemAddDVD = new JMenuItem("Add DVD");
        itemAddDVD.addActionListener(e -> openAddDVDScreen());
        menuUpdate.add(itemAddDVD);

        menuBar.add(menuUpdate);

        return menuBar;
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new GridLayout(1, 3));

        JLabel lblStore = new JLabel("Store", SwingConstants.LEFT);
        lblStore.setFont(new Font("Arial", Font.ITALIC, 14));

        JLabel lblTitle = new JLabel("AIMS", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel lblCart = new JLabel("Cart", SwingConstants.RIGHT);
        lblCart.setFont(new Font("Arial", Font.ITALIC, 14));

        header.add(lblStore);
        header.add(lblTitle);
        header.add(lblCart);
        header.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        return header;
    }

    // ===================== CENTER =====================

    private JPanel createCenter() {
        java.util.ArrayList<Media> items = store.getItems();

        // Use GridLayout: 3 columns, rows auto-calculated
        int cols = 3;
        int rows = (int) Math.ceil((double) items.size() / cols);
        if (rows == 0) rows = 1;

        JPanel center = new JPanel(new GridLayout(rows, cols, 5, 5));
        center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (Media m : items) {
            center.add(new MediaStore(m));
        }

        // Wrap in scroll pane in case store grows large
        JScrollPane scrollPane = new JScrollPane(center);
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(scrollPane, BorderLayout.CENTER);
        return wrapper;
    }

    // ===================== NAVIGATION =====================

    /** Reload the store view (refresh CENTER panel). */
    private void reloadStoreView() {
        getContentPane().removeAll();
        add(createNorth(), BorderLayout.NORTH);
        add(createCenter(), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void openAddBookScreen() {
        new AddBookToStoreScreen(store, this);
        setVisible(false);
    }

    private void openAddCDScreen() {
        new AddCompactDiscToStoreScreen(store, this);
        setVisible(false);
    }

    private void openAddDVDScreen() {
        new AddDigitalVideoDiscToStoreScreen(store, this);
        setVisible(false);
    }

    // ===================== MAIN =====================

    public static void main(String[] args) {
        // Sample data
        Store store = new Store();
        store.addMedia(new DigitalVideoDisc(1, "Inception", "Sci-Fi", "Nolan", 148, 19.9f));
        store.addMedia(new DigitalVideoDisc(2, "Avatar", "Action", "Cameron", 160, 25.0f));
        store.addMedia(new CompactDisc(3, "Thriller", "Music", 15.0f, "Michael Jackson"));
        store.addMedia(new Book(4, "Clean Code", "Programming", 29.9f));
        store.addMedia(new Book(5, "Effective Java", "Programming", 35.0f));

        SwingUtilities.invokeLater(() -> new StoreManagerScreen(store));
    }
}
