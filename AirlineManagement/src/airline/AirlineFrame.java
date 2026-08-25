package airline;

import javax.swing.*;
import java.awt.*;

public class AirlineFrame extends JFrame {

    private JDesktopPane desktop;

    public AirlineFrame() { initUI(); }

    private void initUI() {
        setTitle("Airline Management — Admin Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1280, 760));

        desktop = new JDesktopPane();
        desktop.setBackground(UITheme.BG_DARK);

        // Banner with logo
        JPanel banner = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 6));
        banner.setBackground(UITheme.BG_CARD);
        banner.setOpaque(true);
        JLabel logoSmall = UITheme.airplaneLogo(28);
        JLabel bannerText = new JLabel("Airline Management  \u2014  Admin Dashboard");
        bannerText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bannerText.setForeground(UITheme.TEXT_PRIMARY);
        banner.add(logoSmall);
        banner.add(bannerText);
        desktop.add(banner);
        banner.setBounds(0, 0, 1280, 42);

        getContentPane().setBackground(UITheme.BG_DARK);
        add(desktop, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(UITheme.BG_CARD);
        menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, UITheme.BORDER));

        JMenu menuPassenger = styledMenu("\uD83D\uDC64 Passenger");
        menuPassenger.add(styledItem("Add Passenger",   e -> openInternal(new AddPassenger_GUI())));
        menuPassenger.add(styledItem("Show Passenger",  e -> openInternal(new SearchPassenger_GUI())));
        menuPassenger.add(new JSeparator());
        menuPassenger.add(styledItem("Passenger Login", e -> openInternal(new PassengerLogin_GUI())));
        menuBar.add(menuPassenger);

        JMenu menuFlights = styledMenu("\u2708 Flights");
        menuFlights.add(styledItem("Add Flight",    e -> openInternal(new AddFlight_GUI())));
        menuFlights.add(styledItem("Search Flight", e -> openInternal(new SearchFlight_GUI())));
        menuBar.add(menuFlights);

        JMenu menuAbout = styledMenu("\u2139 About");
        menuAbout.add(styledItem("Version 2.0 \u2014 Fixed Edition", e -> {}));
        menuBar.add(menuAbout);

        setJMenuBar(menuBar);
        pack();
        setLocationRelativeTo(null);
    }

    private void openInternal(JInternalFrame frame) {
        frame.setVisible(true);
        desktop.add(frame);
        int count = desktop.getAllFrames().length;
        frame.setLocation(50 + (count % 5) * 30, 55 + (count % 5) * 30);
        try { frame.setSelected(true); } catch (Exception ignored) {}
    }

    private JMenu styledMenu(String text) {
        JMenu m = new JMenu(text);
        m.setForeground(UITheme.TEXT_PRIMARY);
        m.setFont(UITheme.FONT_LABEL);
        m.setBackground(UITheme.BG_CARD);
        return m;
    }

    private JMenuItem styledItem(String text, java.awt.event.ActionListener al) {
        JMenuItem item = new JMenuItem(text);
        item.setBackground(UITheme.BG_CARD);
        item.setForeground(UITheme.TEXT_PRIMARY);
        item.setFont(UITheme.FONT_LABEL);
        item.addActionListener(al);
        return item;
    }
}
