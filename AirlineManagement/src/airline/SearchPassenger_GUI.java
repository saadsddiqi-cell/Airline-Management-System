package airline;

import javax.swing.*;
import java.awt.*;

public class SearchPassenger_GUI extends JInternalFrame {

    private JTextField txtPassengerId;
    private JTextArea  txtResult;
    private JButton    btnSearch, btnClose;

    public SearchPassenger_GUI() {
        super("Search Passenger", true, true, true, true);
        initUI();
        setPreferredSize(new Dimension(460, 400));
        pack();
    }

    private void initUI() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(UITheme.BG_CARD);

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 14));
        header.setBackground(UITheme.ACCENT);
        JLabel title = new JLabel("\uD83D\uDD0D Search Passenger");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setForeground(Color.WHITE);
        header.add(title);
        root.add(header, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(UITheme.BG_CARD);
        form.setBorder(BorderFactory.createEmptyBorder(20, 28, 10, 28));
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;

        txtPassengerId = UITheme.styledField();
        txtPassengerId.setPreferredSize(new Dimension(360, 36));

        g.gridx = 0; g.gridy = 0; g.insets = new Insets(0, 0, 4, 0);
        form.add(UITheme.fieldLabel("Enter Passenger ID (e.g. PS001)"), g);
        g.gridy = 1; g.insets = new Insets(0, 0, 12, 0);
        form.add(txtPassengerId, g);

        btnSearch = UITheme.primaryButton("Search");
        g.gridy = 2; g.insets = new Insets(0, 0, 16, 0);
        form.add(btnSearch, g);

        g.gridy = 3; g.insets = new Insets(0, 0, 4, 0);
        form.add(UITheme.fieldLabel("Result"), g);

        txtResult = UITheme.styledTextArea();
        JScrollPane scroll = new JScrollPane(txtResult);
        UITheme.styleScrollPane(scroll);
        scroll.setPreferredSize(new Dimension(360, 160));
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
        txtPassengerId.addActionListener(e -> search());
    }

    private void search() {
        String id = txtPassengerId.getText().trim();
        if (id.isEmpty()) { txtResult.setText("Please enter a Passenger ID."); return; }
        Passenger p = AppData.passengerData.searchById(id);
        if (p == null) { txtResult.setText("No passenger found with ID: " + id); return; }
        txtResult.setText(
                "Passenger ID : " + p.getPassengerId()   + "\n" +
                "Name         : " + p.getName()           + "\n" +
                "Username     : " + p.getUserName()       + "\n" +
                "Gender       : " + p.getGender()         + "\n" +
                "Contact      : " + p.getContactNumber()
        );
    }
}
