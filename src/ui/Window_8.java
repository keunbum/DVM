package ui;

import domain.app.Controller;
import domain.product.Item;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import static domain.product.ItemManager.*;

public class Window_8 extends DvmWindow {
    //random 7 drink list
    private static final Item[] localItems = new Item[MAX_LOCAL_ITEM];

    private static final JButton btn1 = new JButton("LOGOUT");
    private static final JButton btn2 = new JButton("UPDATE");

    private static final JPanel itemLayout = new JPanel();
    private static final JPanel itemLayout2 = new JPanel();
    private static final JPanel itemLayout3 = new JPanel();

    private static final JTextField[] itemQty = new JTextField[MAX_LOCAL_ITEM];
    //padding for top, left, bottom, right
    private static final EmptyBorder eb = new EmptyBorder(new Insets(20, 10, 0, 10));
    private static final Border grayLine = BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1);

    private static final int btnWidth = 120;
    private static final int btnHeight = 30;
    private static final int drinkPanelWidth = 350;
    private static final int drinkPanelHeight = 400;
    private static final int drinkPanel2Width = 150;
    private static final int drinkPanel2Height = 250;
    private static final int drinkPanel3Width = 200;
    private static final int drinkPanel3Height = 250;

    public Window_8(Controller controller) {
        super(controller);
    }

    protected void init() {
        initLocalItems();

        //set drink list layout's size
        itemLayout.setPreferredSize(new Dimension(drinkPanelWidth, drinkPanelHeight));
        itemLayout2.setPreferredSize(new Dimension(drinkPanel2Width, drinkPanel2Height));
        itemLayout3.setPreferredSize(new Dimension(drinkPanel3Width, drinkPanel3Height));

        panel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        vmID.setBackground(Color.decode("#cfd0d1"));
        panel.setBackground(Color.decode("#dcebf7"));

        //set border + margin
        itemLayout.setBorder(BorderFactory.createCompoundBorder(grayLine, eb));

        //add panel to frame
        frame.add(panel);

        addJLable(vmID, 2, 10, 2, 2, true);

        addComponent(btn1, 10, 2, 2, 10, 4, 0, 0.5, GridBagConstraints.FIRST_LINE_END);
        addComponent(btn2, 10, 2, 2, 10, 4, 4, 0.5, GridBagConstraints.LINE_END);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        //remove border around text inside the button
        btn1.setFocusable(false);
        btn2.setFocusable(false);

        for (int i = 0; i < MAX_LOCAL_ITEM; i++) {
            itemQty[i] = new JTextField();
            itemQty[i].setPreferredSize(new Dimension(btnWidth + 50, btnHeight));
            itemQty[i].setDocument(new JTextFieldLimit(2));
            itemLayout3.add(itemQty[i], BorderLayout.CENTER);
        }

        for (int i = 0; i < MAX_LOCAL_ITEM; i++) {
            JLabel[] btn = new JLabel[MAX_LOCAL_ITEM];
            btn[i] = new JLabel(localItems[i].getItemName(), SwingConstants.CENTER);
            btn[i].setPreferredSize(new Dimension(btnWidth, btnHeight));
            btn[i].setOpaque(true);
            btn[i].setBackground(Color.WHITE);
            btn[i].setBorder(BorderFactory.createLineBorder(Color.decode("#cfd0d1"), 1));
            itemLayout2.add(btn[i], BorderLayout.CENTER);
        }

        addComponent(itemLayout3, 0, 15, 0, 0, 0, 0, 0, 0, GridBagConstraints.LINE_START);
        addComponent(itemLayout2, 0, 0, 0, 125, 0, 0, 0, 0, GridBagConstraints.LINE_START);
        addComponent(itemLayout, 0, 6, 0, 10, 0, 1, 3, 10, GridBagConstraints.LINE_START);
    }

    private void initLocalItems() {
        int cnt = 0;
        for (int i = 0; i < MAX_ITEM; i++) {
            if (items[i].getOnSale()) {
                localItems[cnt] = items[i];
                cnt += 1;
            }
        }
        assert cnt == MAX_LOCAL_ITEM;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("LOGOUT")) {
            //do nothing
            this.dispose();
            new Window_1(controller);
        } else if (e.getActionCommand().equals("UPDATE")) {
            boolean isValidInput = true;
            int[] inputQuantities = new int[MAX_LOCAL_ITEM];
            for (int i = 0; i < MAX_LOCAL_ITEM; i++) {
                String inputText = itemQty[i].getText();
                int inputNum;
                try {
                    inputNum = Integer.parseInt(inputText);
                    inputQuantities[i] = inputNum;
                } catch (NumberFormatException nfe) {
                    isValidInput = false;
                    break;
                }
                if (inputNum < 0 || inputNum > MAX_QUANTITY) {
                    isValidInput = false;
                    break;
                }
            }
            if (isValidInput) {
                for (int i = 0; i < MAX_LOCAL_ITEM; i++) {
                    controller.getItemManager().updateQuantity(localItems[i].getItemId(), inputQuantities[i]);
                    itemQty[i].setText("");
                }
                controller.getItemManager().showItemList();
                // show JDialog (successful update)
                // for 15 second
            } else {
                /* TODO: put some err dialog */
                System.out.println("invalid input quantity");
//				if there is a non-integer character,
//				show JDialog (Please input only integer);
            }
        }
    }
}
