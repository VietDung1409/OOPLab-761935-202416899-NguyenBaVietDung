package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;

/**
 * GUI panel representing one Media item in the Store screen.
 */
public class MediaStore extends JPanel {
    private Media media;

    public MediaStore(Media media) {
        this.media = media;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(160, 120));

        // Title label (center)
        JLabel lblTitle = new JLabel("<html><center>" + media.getTitle() + "</center></html>",
                SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 13));
        add(lblTitle, BorderLayout.CENTER);

        // Cost label (south-west area)
        JLabel lblCost = new JLabel(String.format("%.2f $", media.getCost()),
                SwingConstants.CENTER);
        lblCost.setFont(new Font("Arial", Font.PLAIN, 11));

        // Button panel at the bottom
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 2));

        // Only show Play button if media implements Playable
        if (media instanceof Playable) {
            JButton btnPlay = new JButton("Play");
            btnPlay.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showPlayDialog();
                }
            });
            btnPanel.add(btnPlay);
        }

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(lblCost, BorderLayout.NORTH);
        southPanel.add(btnPanel, BorderLayout.SOUTH);

        add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * Show a JDialog with play information for the media.
     */
    private void showPlayDialog() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this),
                "Now Playing", true);
        dialog.setSize(300, 180);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));

        // Build play info text
        StringBuilder sb = new StringBuilder();
        sb.append("<html><center>");
        sb.append("<b>").append(media.getTitle()).append("</b><br/>");
        sb.append("Category: ").append(media.getCategory()).append("<br/>");
        sb.append("Cost: ").append(String.format("%.2f $", media.getCost())).append("<br/>");
        sb.append("</center></html>");

        JLabel lblInfo = new JLabel(sb.toString(), SwingConstants.CENTER);
        dialog.add(lblInfo, BorderLayout.CENTER);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(e -> dialog.dispose());
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnClose);
        dialog.add(btnPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }
}
