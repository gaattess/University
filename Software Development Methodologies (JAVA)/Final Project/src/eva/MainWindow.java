package eva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class MainWindow extends JFrame {
    private JLabel title, releaseLbl, durationLbl, artist;
    public JTextField songTitle, releaseTxt, durationTxt, artistName;
    // Genre
    private JComboBox genre;
    // Buttons
    private JButton save, statistics, about, close;
    // Menu
    private JMenuBar menuBar;
    private JMenu appMenu;
    private JMenuItem menuSave, menuStats, menuAbout, menuClose;

    public MainWindow() {
        Image icon = Toolkit.getDefaultToolkit().getImage("logo.png"); // Inserting icon to frame for our app
        this.setIconImage(icon);

        this.setSize(500, 450); // Window size
        this.setLocationRelativeTo(null); // Center position
        this.setTitle("Wnsᴉɔ ∀dd"); // App title
        this.setLayout(null); // Turn off Layout managers
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Pressing the close button on the frame does nothing
        this.addWindowListener(new WindowAdapter() { // Closes the app with pop-up window
            @Override
            public void windowClosing(WindowEvent e) {
                closeApp();
            }
        });

        this.getContentPane().setBackground(new Color(148, 208, 204)); // Background colour
        setResizable(false); // Not resizable
        // Calling component methods
        menu();
        labels();
        texts();
        drop();
        buttons();
        setVisible(true); // To show window
    }

    private void labels() {
        // Creating song title label
        title = new JLabel("Song Title");
        title.setForeground(Color.black); // Font color
        title.setFont(new Font("Serif", Font.PLAIN, 20)); // Font name and size
        title.setBounds(200, 0, 90, 30); // Setting label size and location on frame
        add(title);

        // Creating releaseDate label
        releaseLbl = new JLabel("Release year");
        releaseLbl.setForeground(Color.black); // Font color
        releaseLbl.setFont(new Font("Serif", Font.PLAIN, 20)); // Font name and size
        releaseLbl.setBounds(190, 65, 110, 30); // Setting label size and location on frame
        add(releaseLbl);

        // Creating duration label
        durationLbl = new JLabel("Song duration");
        durationLbl.setForeground(Color.black); // Font color
        durationLbl.setFont(new Font("Serif", Font.PLAIN, 20)); // Font name and size
        durationLbl.setBounds(185, 135, 115, 30); // Setting label size and location on frame
        add(durationLbl);

        // Creating artist label
        artist = new JLabel("Artist Name");
        artist.setForeground(Color.black); // Font color
        artist.setFont(new Font("Serif", Font.PLAIN, 20)); // Font name and size
        artist.setBounds(190, 205, 110, 30);
        add(artist);
    }

    private void texts() {
        // Creating song title text field
        songTitle = new JTextField(20);
        songTitle.setFont(new Font("Serif", Font.PLAIN, 20)); // Font name and size
        songTitle.setBounds(25, 30, 440, 30); // Setting text size and location on frame
        add(songTitle);

        // Creating releaseDate text field
        releaseTxt = new JTextField(20);
        releaseTxt.setFont(new Font("Serif", Font.PLAIN, 20)); // Font name and size
        releaseTxt.setBounds(25, 95, 440, 30); // Setting text size and location on frame
        add(releaseTxt);

        // Creating duration text field
        durationTxt = new JTextField(20);
        durationTxt.setFont(new Font("Serif", Font.PLAIN, 20)); // Font name and size
        durationTxt.setBounds(25, 165, 440, 30); // Setting text size and location on frame
        add(durationTxt);

        // Creating artist text field
        artistName = new JTextField(20);
        artistName.setFont(new Font("Serif", Font.PLAIN, 20)); // Font name and size
        artistName.setBounds(25, 235, 440, 30); // Setting text size and location on frame
        add(artistName);
    }

    private void buttons() {
        // Creating save button
        save = new JButton("Save");
        save.setBackground(new Color(242, 145, 145));
        save.setFont(new Font("Serif", Font.PLAIN, 20)); // Font name and size
        save.setBounds(25, 350, 80, 30); // Setting button size and location on frame
        save.setFocusPainted(false); // Removing lines when pressing button
        add(save);

        // Action listener for saving songs
        save.addActionListener(e -> {
            checkBlank();
        });


        // Creating statistics button
        statistics = new JButton("Statistics");
        statistics.setBackground(new Color(242, 145, 145));
        statistics.setFont(new Font("Serif", Font.PLAIN, 20)); // Font name and size
        statistics.setBounds(130, 350, 115, 30); // Setting button size and location on frame
        statistics.setFocusPainted(false); // Removing lines when pressing button
        add(statistics);

        statistics.addActionListener(e -> { // When pressing statistics button, open statistics in new frame

            Statistics tsistiks = new Statistics();
        });

        // Creating about button
        about = new JButton("About");
        about.setBackground(new Color(242, 145, 145));
        about.setFont(new Font("Serif", Font.PLAIN, 20)); // Font name and size
        about.setBounds(270, 350, 85, 30); // Setting button size and location on frame
        about.setFocusPainted(false); // Removing lines when pressing button
        add(about);

        // When pressing about button, opens another frame
        about.addActionListener(e -> {
            AboutFrame aboutMe = new AboutFrame();
        });

        // Creating close button
        close = new JButton("Close");
        close.setBackground(new Color(209, 217, 217));
        close.setFont(new Font("Serif", Font.PLAIN, 20)); // Font name and size
        close.setBounds(380, 350, 80, 30); // Setting button size and location on frame
        close.setFocusPainted(false); // Removing lines when pressing button
        add(close);


        // When pressing close button, pop-up
        close.addActionListener(e -> {
            closeApp();
        });
    }

    private void drop() {
        // Creating combo box for genre selection
        String[] genreOption = {"(Select genre of song)", "Pop", "Techno", "Psy-Trance", "K-pop", "Greek"};
        genre = new JComboBox(genreOption);
        genre.setForeground(Color.black);
        genre.setBackground(new Color(238, 196, 196));
        genre.setFont(new Font("Serif", Font.PLAIN, 20));
        genre.setBounds(130, 280, 210, 40); // Setting box size and location on frame
        genre.setFocusable(false);
        add(genre);
    }


    private void menu() {
        menuBar = new JMenuBar(); // Creating menu bar

        appMenu = new JMenu("App Menu"); // Creating menu category

        menuSave = new JMenuItem("Save"); // Creating save button
        menuStats = new JMenuItem("Statistics"); // Creating statistics button
        menuAbout = new JMenuItem("About app"); // Creating about button
        menuClose = new JMenuItem("Close app"); // Creating close button

        // Inserting buttons to category
        appMenu.add(menuSave);
        appMenu.add(menuStats);
        appMenu.add(menuAbout);
        appMenu.add(menuClose);

        menuBar.add(appMenu); // Inserting category to bar

        setJMenuBar(menuBar); // Inserting menu bar to frame

        menuSave.addActionListener(e -> {
            // If a text field is blank
            checkBlank();
        });

        menuStats.addActionListener(e -> {
            Statistics statistics1 = new Statistics();
        });

        menuAbout.addActionListener(e -> {
            AboutFrame aboutFrame = new AboutFrame();
        });


        menuClose.addActionListener(e -> {
            closeApp();
        });

    }

    private void closeApp() { // Method to close app with option pane
        int choice = JOptionPane.showConfirmDialog(MainWindow.this, "Are you sure you want to exit the app?"); // Creating a pop-up pane to ask the user if he really wants to close the app
        if (choice == JOptionPane.YES_OPTION) { // If yes, exit app
            boolean flag = checkIfSave();
            if (!flag)
                System.exit(0);

        }
    }

    private boolean checkIfSave() { // Method checking if fields are saved before closing
        boolean check = false;
        if (!((title.getText().isEmpty()) || (releaseLbl.getText().isEmpty()) || (durationLbl.getText().isEmpty()) || (artist.getText().isEmpty()) || (genre.getSelectedItem().equals("(Select genre of song)")))) {
            JOptionPane.showMessageDialog(
                    MainWindow.this, "Looks like you haven't saved your data", "Uh - oh!", JOptionPane.WARNING_MESSAGE);
            check = true;
        }
        return check;
    }

    private void checkBlank() { // Method to check if fields are filled
        // If a text field is blank
        if ((title.getText().isEmpty()) || (releaseLbl.getText().isEmpty()) || (durationLbl.getText().isEmpty()) || (artist.getText().isEmpty()) || (genre.getSelectedItem().equals("(Select genre of song)"))) {
            JOptionPane.showMessageDialog(
                    MainWindow.this, "Looks like you haven't filled all the fields", "Uh - oh!", JOptionPane.WARNING_MESSAGE);
        } else { // Save entry in file
            SaveButton save = new SaveButton();
        }
        // Clearing fields
        songTitle.setText("");
        releaseTxt.setText("");
        durationTxt.setText("");
        artistName.setText("");
        genre.setSelectedIndex(0);
    }

    class SaveButton {
        private String fileName = "songs.txt";

        public SaveButton() {
            Random rand = new Random();
            int songID = Math.abs(rand.nextInt());
            String songs = songTitle.getText();
            String names = artistName.getText();
            String genreChoice = (String) genre.getItemAt(genre.getSelectedIndex());
            try { // Checking if years are in correct format
                int years = Integer.parseInt(releaseTxt.getText());
                try { // Checking if duration is in correct format
                    double minutes = Double.parseDouble(durationTxt.getText());
                    // Reading file to check if entry is already there
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
                        String s;
                        boolean flag = false;
                        while ((s = bufferedReader.readLine()) != null) { // Splitting title from line
                            String[] str = s.split("\\|");
                            if (str.length > 5) {
                                String curTitle = str[1];
                                int curYear = Integer.parseInt(str[2]);
                                String curArtist = str[4];
                                System.out.println();
                                if (curTitle.equals(songs) && curYear == years && curArtist.equals(names)) { // Shows error message
                                    JOptionPane.showMessageDialog(MainWindow.this, "Song is already in file, try another one", "Error", JOptionPane.WARNING_MESSAGE);
                                    flag = true;
                                    break;
                                }
                            }
                        }

                        if (!flag) { // Writes new entry in file
                            BufferedWriter buffer = new BufferedWriter(new FileWriter(fileName, true));
                            buffer.append("\n" + songID + "|" + songs + "|" + years + "|" + minutes + "|" + names + "|" + genreChoice);
                            buffer.close();
                        }
                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(MainWindow.this, "Problem finding file.", "Aborting", JOptionPane.WARNING_MESSAGE);
                        e.printStackTrace();
                        System.exit(1);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(MainWindow.this, "Problem with file.", "Aborting", JOptionPane.WARNING_MESSAGE);
                        e.printStackTrace();
                        System.exit(1);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(MainWindow.this, "Minutes have to be in format: X.X. Please try again", "Field check", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(MainWindow.this, "Years have to be in format: XXXX. Please try again.", "Field Error", JOptionPane.WARNING_MESSAGE);
            }

        }

    }


}

