package airline;

import javax.swing.*;
import java.awt.*;

public class TicketSearch_GUI extends JInternalFrame {

    private JTextField txtTicketId;
    private JTextArea  txtResult;
    private JButton    btnSearch, btnClose;

    public TicketSearch_GUI() {
        super("Search Ticket", true, true, true, true);
        initUI();
        setPreferredSize(new Dimension(460, 400));
        pack();
    }

    private void initUI() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(UITheme.BG_CARD);

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 14));
        header.setBackground(UITheme.ACCENT_AMBER);
        JLabel title = new JLabel("\uD83D\uDD0D Search Ticket");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setForeground(Color.WHITE);
        header.add(title);
        root.add(header, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(UITheme.BG_CARD);
        form.setBorder(BorderFactory.createEmptyBorder(20, 28, 10, 28));
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;

        txtTicketId = UITheme.styledField();
        txtTicketId.setPreferredSize(new Dimension(360, 36));

        g.gridx = 0; g.gridy = 0; g.insets = new Insets(0, 0, 4, 0);
        form.add(UITheme.fieldLabel("Enter Ticket ID (e.g. TK001)"), g);
        g.gridy = 1; g.insets = new Insets(0, 0, 12, 0);
        form.add(txtTicketId, g);

        btnSearch = UITheme.primaryButton("Search");
        g.gridy = 2; g.insets = new Insets(0, 0, 16, 0);
        form.add(btnSearch, g);

        g.gridy = 3; g.insets = new Insets(0, 0, 4, 0);
        form.add(UITheme.fieldLabel("Result"), g);

        txtResult = UITheme.styledTextArea();
        JScrollPane scroll = new JScrollPane(txtResult);
        UITheme.styleScrollPane(scroll);
        scroll.setPreferredSize(new Dimension(360, 180));
        g.gridy = 4; g.insets = new Insets(0, 0, 0, 0);
        form.add(scroll, g);
        root.add(form, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 16, 12));
        btnPanel.setBackground(UITheme.BG_CARD);
        btnPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, UITheme.BORDER));
        btnClose = UITheme.ghostButton("Close");
        btnPanel.add(btnClose);
        root.add(btnPanel, BorderLayout.SOUTH);

        setContentPane(root);
        btnSearch.addActionListener(e -> search());
        btnClose.addActionListener(e -> dispose());
        txtTicketId.addActionListener(e -> search());
    }

    private void search() {
        String id = txtTicketId.getText().trim();
        if (id.isEmpty()) { txtResult.setText("Please enter a Ticket ID."); return; }
        Ticket t = AppData.ticketData.searchById(id);
        if (t == null) { txtResult.setText("No ticket found with ID: " + id); return; }
        txtResult.setText(
                "Ticket ID    : " + t.getTicketId()                    + "\n" +
                "Passenger    : " + t.getPassenger().getName()          + "\n" +
                "Passenger ID : " + t.getPassenger().getPassengerId()   + "\n" +
                "Flight ID    : " + t.getFlight().getFlightId()         + "\n" +
                "Route        : " + t.getFlight().getSource() + " \u2192 " + t.getFlight().getDestination() + "\n" +
                "Seats Booked : " + t.getSeatsBooked()                  + "\n" +
                "Total (PKR)  : " + String.format("%.0f", t.getTotalAmount())
        );
    }
}
