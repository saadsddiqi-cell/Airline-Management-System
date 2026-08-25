package airline;

import javax.swing.*;
import java.awt.*;

public class PassengerFrame extends JFrame {

    private JDesktopPane desktop;

    public PassengerFrame() { initUI(); }

    private void initUI() {
        setTitle("Airline Management — Passenger Dashboard");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(1100, 700));

        desktop = new JDesktopPane();
        desktop.setBackground(UITheme.BG_DARK);

        String pName = AppData.currentPassenger != null ? AppData.currentPassenger.getName() : "Passenger";

        JPanel banner = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 6));
        banner.setBackground(UITheme.BG_CARD);
        banner.setOpaque(true);
        JLabel logoSmall = UITheme.airplaneLogo(28);
        JLabel bannerText = new JLabel("Welcome, " + pName + "  \u2014  Passenger Portal");
        bannerText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bannerText.setForeground(UITheme.ACCENT_GREEN);
        banner.add(logoSmall);
        banner.add(bannerText);
        desktop.add(banner);
        banner.setBounds(0, 0, 1100, 42);

        getContentPane().setBackground(UITheme.BG_DARK);
        add(desktop, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(UITheme.BG_CARD);
        menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, UITheme.BORDER));

        JMenu menuTicket = styledMenu("\uD83C\uDFAB Tickets");
        menuTicket.add(styledItem("Book Ticket",   e -> openInternal(new BookTicket_GUI())));
        menuTicket.add(styledItem("Search Ticket", e -> openInternal(new TicketSearch_GUI())));
        menuTicket.add(new JSeparator());
        menuTicket.add(styledItem("Cancel Ticket", e -> openInternal(new TicketCancel_GUI())));
        menuBar.add(menuTicket);

        JMenu menuFlights = styledMenu("\u2708 Flights");
        menuFlights.add(styledItem("Search Flight", e -> openInternal(new SearchFlight_GUI())));
        menuBar.add(menuFlights);

        setJMenuBar(menuBar);
        pack();
    }

    private void openInternal(JInternalFrame frame) {
        frame.setVisible(true);
        desktop.add(frame);
        int count = desktop.getAllFrames().length;
        frame.setLocation(50 + (count % 4) * 30, 50 + (count % 4) * 30);
        try { frame.setSelected(true); } catch (Exception ignored) {}
    }

    private JMenu styledMenu(String text) {
        JMenu m = new JMenu(text);
        m.setForeground(UITheme.TEXT_PRIMARY);
        m.setFont(UITheme.FONT_LABEL);
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
