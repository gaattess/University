package eva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AboutFrame extends JFrame {
    JButton close;
    public AboutFrame(){
        window();
        labels();
        buttonClose();
    }

    private void window(){
        setSize(250, 380); // Frame size
        setResizable(false); // Non resizable
        setLocationRelativeTo(null); // Centered frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose this window on close
        setLayout(null); // No layout

        getContentPane().setBackground(new Color(255, 251, 231));

        Image icon = Toolkit.getDefaultToolkit().getImage("logo.png"); // Setting icon on this frame also
        setIconImage(icon);

        setVisible(true);
    }

    private void labels(){
        // App title
        JLabel appName = new JLabel("Wnsᴉɔ ∀dd (Music App upside down)");
        appName.setFont(new Font("Serif", Font.PLAIN, 15));
        appName.setBounds(5, 10, 230, 20);
        add(appName);

        // My info
        JLabel name = new JLabel("ΠΕΤΡΑΚΗ ΒΑΣΙΛΙΚΗ ΕΥΑΝΘΙΑ");
        name.setFont(new Font("Serif", Font.PLAIN, 15));
        name.setBounds(7, 40, 230, 20);
        add(name);

        JLabel am = new JLabel("19390193");
        am.setFont(new Font("Serif", Font.PLAIN, 15));
        am.setBounds(90, 70, 150, 20);
        add(am);

        // Time
        JLabel time = new JLabel("1/6/22 - 9/6/22");
        time.setFont(new Font("Serif", Font.PLAIN, 15));
        time.setBounds(75, 100, 150, 20);
        add(time);

        // Logo
        JLabel image = new JLabel();
        image.setIcon(new ImageIcon("logo.png"));
        Dimension size = image.getPreferredSize();
        image.setBounds(41, 130, size.width, size.height);
        add(image);
    }

    private void buttonClose(){
        close = new JButton("Close");
        close.setFont(new Font("Serif", Font.PLAIN, 15)); // Font name and size
        close.setBackground(new Color(64, 223, 239));
        close.setBounds(75, 300, 80, 30); // Setting button size and location on frame
        close.setFocusPainted(false); // Removing lines when pressing button
        add(close);

        close.addActionListener(e -> { // Dispose window on close button
            this.dispose();
        });
    }


}
