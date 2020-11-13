package de.sarah.elements;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;

public class DifficultyBar {

    private JPanel difficultyBar;

    private final int BAR_WIDTH = 500;
    private final int BAR_HEIGHT = 160;

    private final ImageIcon X = new ImageIcon("src/images/X.png");
    private final ImageIcon O = new ImageIcon("src/images/O.png");

    private JButton userDifficulty;
    private JButton easyDifficulty;
    private JButton mediumDifficulty;
    private JButton hardDifficulty;

    public DifficultyBar() {
        difficultyBar = new JPanel();
        //difficultyBar.setPreferredSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));
        difficultyBar.setBackground(new Color(50, 50, 50));
        difficultyBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        difficultyBar.setBorder(new EmptyBorder(new Insets(50, 25, 50, 25)));

        userDifficulty = new JButton("User");
        easyDifficulty = new JButton("Easy");
        mediumDifficulty = new JButton("Medium");
        hardDifficulty = new JButton("Hard");

        Dimension buttonSize = new Dimension(100, 60);
        userDifficulty.setPreferredSize(buttonSize);
        easyDifficulty.setPreferredSize(buttonSize);
        mediumDifficulty.setPreferredSize(buttonSize);
        hardDifficulty.setPreferredSize(buttonSize);


        difficultyBar.add(userDifficulty);
        difficultyBar.add(easyDifficulty);
        difficultyBar.add(mediumDifficulty);
        difficultyBar.add(hardDifficulty);
        difficultyBar.add(Box.createRigidArea(new Dimension(500, 150)));

    }

    // Getter and Setter

    public JPanel getDifficultyBar() {
        return difficultyBar;
    }

    public void setDifficultyBar(JPanel difficultyBar) {
        this.difficultyBar = difficultyBar;
    }

    public JButton getUserDifficulty() { return userDifficulty; }

    public void setUserDifficulty(JButton userDifficulty) { this.userDifficulty = userDifficulty; }

    public JButton getEasyDifficulty() { return easyDifficulty; }

    public void setEasyDifficulty(JButton easyDifficulty) { this.easyDifficulty = easyDifficulty; }

    public JButton getMediumDifficulty() { return mediumDifficulty; }

    public void setMediumDifficulty(JButton mediumDifficulty) { this.mediumDifficulty = mediumDifficulty; }

    public JButton getHardDifficulty() { return hardDifficulty; }

    public void setHardDifficulty(JButton hardDifficulty) { this.hardDifficulty = hardDifficulty; }
}
