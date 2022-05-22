package ui;

import domain.app.Controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Window_3_1 extends DvmWindow {
    private static final JButton btn1 = new JButton("PAY");
    private static final JButton btn2 = new JButton("BACK");
    private static final GridBagConstraints c = new GridBagConstraints();

    private static final JLabel totalPrice = new JLabel("<html>Total price:<br><center>" + Window_2.getTotalPrice() + "</center></html>", SwingConstants.CENTER);

    private static final int btnTotalPriceWidth = 100;
    private static final int btnTotalPriceHeight = 70;

    public Window_3_1(Controller controller) {
        super(controller);
    }

    protected void init() {
        panel = new JPanel(new GridBagLayout());
        frame.add(panel);
        vmID.setBackground(Color.decode("#cfd0d1"));
        panel.setBackground(Color.decode("#dcebf7"));

        //padding for top, left, bottom, right
        c.insets = new Insets(2, 10, 2, 2);
        vmID.setOpaque(true);
        panel.add(vmID, c);

        totalPrice.setPreferredSize(new Dimension(btnTotalPriceWidth, btnTotalPriceHeight));
        totalPrice.setOpaque(true);
        totalPrice.setBorder(BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1));

        c.insets = new Insets(10, 2, 2, 10);
        c.anchor = GridBagConstraints.FIRST_LINE_END; //top corner right
        c.weightx = 0.5;
        c.gridx = 4;
        c.gridy = 0;
        panel.add(btn2, c);

        c.insets = new Insets(0, 50, 120, 0);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER; //center
        c.weighty = 0.5;
        panel.add(totalPrice, c);

        c.insets = new Insets(0, 55, 0, 0);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER; //center
        c.weighty = 0.5;
        panel.add(btn1, c);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        btn1.setFocusable(false);
        btn2.setFocusable(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("PAY")) {
            dispose();
            new Window_4(controller);
        } else if (e.getActionCommand().equals("BACK")) {
            dispose();
            new Window_2(controller);
        }
    }

}

