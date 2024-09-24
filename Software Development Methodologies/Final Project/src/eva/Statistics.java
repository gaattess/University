package eva;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Statistics extends JFrame {
    private String fileName = "songs.txt";
    private JLabel totalSongs, totalSongsNo, mostGenre, mostGenreNo, mostGenreName, maxDur, maxDurName, maxDurNo, minDur, minDurName, minDurNo;
    private JButton close;
    private String maxDurationName, minDurationName, mostMinutes, leastMinutes;
    private String maxGenreKey;
    private Double maxDurationNo, minDurationNo;


    public Statistics() {
        window();
        doMaths();
        buttonClose();
    }


    private long countLines() { // Counting lines in file so we can see how many songs we have inputted
        Path path = Paths.get(fileName);
        long count = 0;
        try {
            count = Files.lines(path).count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public void doMaths() {
        HashMap<String, Integer> genresCount = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String s;
            maxDurationName = "";
            minDurationName = "";
            maxDurationNo = 0.0;
            minDurationNo = Double.MAX_VALUE;
            while ((s = reader.readLine()) != null) { // Splitting title from line
                String[] str = s.split("\\|");
                double duration = Double.parseDouble(str[3]);
                String name = str[1];
                String genre = str[5];
                //Find max genre
                if (genresCount.containsKey(genre)) { // Count genre
                    genresCount.put(genre, genresCount.get(genre) + 1);
                } else {
                    genresCount.put(genre, 1);
                }

                if (duration > maxDurationNo) {
                    maxDurationName = name;
                    maxDurationNo = duration;
                }

                if (duration < minDurationNo) {
                    minDurationName = name;
                    minDurationNo = duration;
                }

            }
            mostMinutes = maxDurationNo + "";
            leastMinutes = minDurationNo + "";

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(Statistics.this, "Problem finding file.", "Aborting", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(Statistics.this, "Problem with file.", "Aborting", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
            System.exit(1);
        }

        //Get Genre (Key) that has the most songs
        maxGenreKey = Collections.max(genresCount.entrySet(), Map.Entry.comparingByValue())
                .getKey();
        String maxGenreName = genresCount.get(maxGenreKey) + "";

        /*--------------------------BEGIN LABEL CONFIGURATION---------------------------------------------------------*/
        // Songs
        totalSongs = new JLabel("Total number of songs:");
        totalSongs.setFont(new Font("Serif", Font.PLAIN, 15));
        totalSongs.setBounds(5, 5, 150, 20);
        add(totalSongs);

        totalSongsNo = new JLabel(String.valueOf(countLines()));
        totalSongsNo.setFont(new Font("Serif", Font.PLAIN, 15));
        totalSongsNo.setBounds(145, 5, 150, 20);
        add(totalSongsNo);

        // Genres
        mostGenre = new JLabel("Genre with most songs:");
        mostGenre.setFont(new Font("Serif", Font.PLAIN, 15));
        mostGenre.setBounds(5, 25, 150, 20);
        add(mostGenre);

        mostGenreName = new JLabel(maxGenreKey);
        mostGenreName.setFont(new Font("Serif", Font.PLAIN, 15));
        mostGenreName.setBounds(150, 25, 150, 20);
        add(mostGenreName);

        mostGenreNo = new JLabel(maxGenreName);
        mostGenreNo.setFont(new Font("Serif", Font.PLAIN, 15));
        mostGenreNo.setBounds(350, 25, 150, 20);
        add(mostGenreNo);


        // Max duration
        maxDur = new JLabel("Song with the most duration:");
        maxDur.setFont(new Font("Serif", Font.PLAIN, 15));
        maxDur.setBounds(5, 45, 190, 20);
        add(maxDur);

        maxDurName = new JLabel(maxDurationName + ",");
        maxDurName.setFont(new Font("Serif", Font.PLAIN, 15));
        maxDurName.setBounds(175, 45, 150, 20);
        add(maxDurName);

        maxDurNo = new JLabel(mostMinutes); // TODO fix this
        maxDurNo.setFont(new Font("Serif", Font.PLAIN, 15));
        maxDurNo.setBounds(345, 45, 50, 20);
        add(maxDurNo);

        // Min duration
        minDur = new JLabel("Song with the least duration:");
        minDur.setFont(new Font("Serif", Font.PLAIN, 15));
        minDur.setBounds(5, 65, 190, 20);
        add(minDur);

        minDurName = new JLabel(minDurationName + ",");
        minDurName.setFont(new Font("Serif", Font.PLAIN, 15));
        minDurName.setBounds(175, 65, 190, 20);
        add(minDurName);

        minDurNo = new JLabel(leastMinutes);
        minDurNo.setFont(new Font("Serif", Font.PLAIN, 15));
        minDurNo.setBounds(350, 65, 50, 20);
        add(minDurNo);

    }


    private void window() {
        setSize(400, 170); // Frame size
        setResizable(false); // Non resizable
        setLocationRelativeTo(null); // Centered frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose this window on close
        setLayout(null); // No layout

        getContentPane().setBackground(new Color(244, 191, 191));

        Image icon = Toolkit.getDefaultToolkit().getImage("logo.png"); // Setting icon on this frame also
        setIconImage(icon);

        setVisible(true);
    }


    private void buttonClose() {
        close = new JButton("Close");
        close.setFont(new Font("Serif", Font.PLAIN, 15)); // Font name and size
        close.setBackground(new Color(140, 192, 222));
        close.setBounds(160, 95, 70, 30); // Setting button size and location on frame
        close.setFocusPainted(false); // Removing lines when pressing button
        add(close);

        close.addActionListener(e -> { // Dispose window on close button
            this.dispose();
        });
    }
}
