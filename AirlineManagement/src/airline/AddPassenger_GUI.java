package airline;

import javax.swing.*;
import java.awt.*;

public class AddPassenger_GUI extends JInternalFrame {

    private JTextField txtName, txtUsername, txtCnic, txtPassport, txtAddress, txtContact;
    private JRadioButton rbMale, rbFemale;
    private JButton btnSubmit, btnCancel;

    public AddPassenger_GUI() {
        super("Add Passenger", true, true, true, true);
        initUI();
        setPreferredSize(new Dimension(500, 560));
        pack();
    }

    private void initUI() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(UITheme.BG_CARD);

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 16));
        header.setBackground(UITheme.ACCENT_GREEN);
        JLabel title = new JLabel("\uD83D\uDC64 Add New Passenger");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setForeground(Color.WHITE);
        header.add(title);
        root.add(header, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(UITheme.BG_CARD);
        form.setBorder(BorderFactory.createEmptyBorder(20, 32, 10, 32));
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridwidth = 1;

        txtName     = UITheme.styledField();
        txtUsername = UITheme.styledField();
        txtCnic     = UITheme.styledField();
        txtPassport = UITheme.styledField();
        txtAddress  = UITheme.styledField();
        txtContact  = UITheme.styledField();

        addRow(form, g, 0, "Full Name",   txtName);
        addRow(form, g, 1, "Username",    txtUsername);
        addRow(form, g, 2, "CNIC",        txtCnic);
        addRow(form, g, 3, "Passport ID", txtPassport);
        addRow(form, g, 4, "Address",     txtAddress);
        addRow(form, g, 5, "Contact No",  txtContact);

        g.gridx = 0; g.gridy = 12; g.insets = new Insets(10, 0, 2, 0);
        form.add(UITheme.fieldLabel("Gender"), g);

        ButtonGroup bg = new ButtonGroup();
        rbMale   = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        rbMale.setBackground(UITheme.BG_CARD);   rbFemale.setBackground(UITheme.BG_CARD);
        rbMale.setForeground(UITheme.TEXT_PRIMARY); rbFemale.setForeground(UITheme.TEXT_PRIMARY);
        rbMale.setFont(UITheme.FONT_LABEL);       rbFemale.setFont(UITheme.FONT_LABEL);
        rbMale.setSelected(true);
        bg.add(rbMale); bg.add(rbFemale);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 0));
        genderPanel.setBackground(UITheme.BG_CARD);
        genderPanel.add(rbMale); genderPanel.add(rbFemale);
        g.gridy = 13; g.insets = new Insets(0, 0, 4, 0);
        form.add(genderPanel, g);
        root.add(form, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 16, 16));
        btnPanel.setBackground(UITheme.BG_CARD);
        btnPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, UITheme.BORDER));
        btnCancel = UITheme.ghostButton("Cancel");
        btnSubmit = UITheme.primaryButton("Add Passenger");
        btnPanel.add(btnCancel); btnPanel.add(btnSubmit);
        root.add(btnPanel, BorderLayout.SOUTH);

        setContentPane(root);

        btnSubmit.addActionListener(e -> submit());
        btnCancel.addActionListener(e -> dispose());
        txtName.addActionListener(e -> txtUsername.requestFocus());
        txtUsername.addActionListener(e -> txtCnic.requestFocus());
        txtCnic.addActionListener(e -> txtPassport.requestFocus());
        txtPassport.addActionListener(e -> txtAddress.requestFocus());
        txtAddress.addActionListener(e -> txtContact.requestFocus());
        txtContact.addActionListener(e -> submit());
    }

    private void addRow(JPanel p, GridBagConstraints g, int row, String label, JTextField field) {
        g.gridx = 0; g.gridy = row * 2; g.insets = new Insets(8, 0, 2, 0);
        p.add(UITheme.fieldLabel(label), g);
        g.gridy = row * 2 + 1; g.insets = new Insets(0, 0, 2, 0);
        field.setPreferredSize(new Dimension(390, 36));
        p.add(field, g);
    }

    private void submit() {
        String name     = txtName.getText().trim();
        String username = txtUsername.getText().trim();
        String cnic     = txtCnic.getText().trim();
        String passport = txtPassport.getText().trim();
        String address  = txtAddress.getText().trim();
        String contact  = txtContact.getText().trim();
        String gender   = rbMale.isSelected() ? "Male" : "Female";

        if (name.isEmpty() || username.isEmpty() || cnic.isEmpty() ||
            passport.isEmpty() || address.isEmpty() || contact.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String passengerId = AppData.passengerData.generatePassengerId();
        Passenger p = new Passenger(name, username, passengerId, cnic, passport, address, gender, contact);
        AppData.passengerData.addPassenger(p);

        JOptionPane.showMessageDialog(this,
                "<html><b>Passenger Added!</b><br>Name: " + name + "<br>ID: <b>" + passengerId + "</b> (save this for login)</html>",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
