package org.boothverse.foodpants.ui.components.standard;

import org.boothverse.foodpants.ui.Style;

import javax.swing.*;
import java.awt.*;

public class PantryPanel extends StandardPanel {

  protected JLabel itemName;
  protected JLabel quantity;

  protected int WIDTH = 250;
  protected int HEIGHT = 50;

  public PantryPanel(String name, int amt) {
    super();

    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    initChildren(name, amt);
  }

  private void initChildren(String name, int amt) {
    JPanel panel = new JPanel();
    panel.setBackground(Style.WHITE);

    itemName = new StandardLabel(name);
    quantity = new StandardLabel(amt + "");

    JButton editBtn = new StandardButton("Edit");
    editBtn.setFont(Style.bodyStyle);

    JButton deleteBtn = new StandardButton("Delete");
    editBtn.setFont(Style.bodyStyle);

    panel.add(quantity);
    panel.add(editBtn);
    panel.add(deleteBtn);

    add(itemName, BorderLayout.WEST);
    add(panel, BorderLayout.EAST);
  }
}
