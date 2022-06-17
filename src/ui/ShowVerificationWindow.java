package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static ui.DvmWindow.*;

// Window5
public class ShowVerificationWindow extends DvmPanel {

    private JButton btn1;
    private JButton btn2;
    private static final JLabel verCode = new JLabel("<html><center>Verification code:<br>1234567890</html>", SwingConstants.CENTER);
    JPanel panel;

    private static final JLabel loc = new JLabel("Location: (x,y)", SwingConstants.CENTER);
    private static final JLabel distance = new JLabel("Distance: m", SwingConstants.CENTER);

    protected void init() {
        super.init();
        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        CARD.add(panel);

        VM_ID.setBackground(Color.decode("#cfd0d1"));
        panel.setBackground(Color.decode("#dcebf7"));

        addJLabel(panel);
        setJLabel(verCode,120, 70, Color.decode("#cfd0d1"));
        setJLabel(loc,100, 70, Color.decode("#cfd0d1"));
        setJLabel(distance,100, 70, Color.decode("#cfd0d1"));

        btn1 = new JButton("HOME");
        btn2 = new JButton("BACK");
        addComponent(panel,btn2, 10, 0, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
        addComponent(panel,loc, 0, 0, 300, 150, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel,distance, 0, 150, 300, 0, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel,verCode, 0, 0, 120, 0, 1, 1, 0.5, GridBagConstraints.CENTER);
        addComponent(panel,btn1, 0, 0, 0, 0, 1, 1, 0.5, GridBagConstraints.CENTER);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        btn1.setFocusable(false);
        btn2.setFocusable(false);
    }

    public void actionPerformed(ActionEvent e) {
        resetCard();
        if (e.getActionCommand().equals("HOME")) {
            CARD.add(new HomeWindow());
        } else if (e.getActionCommand().equals("BACK")) {
            prevPanel.init();
            CARD.add(prevPanel);
        }
    }
}