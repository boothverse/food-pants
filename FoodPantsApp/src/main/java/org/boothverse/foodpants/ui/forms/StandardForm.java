package org.boothverse.foodpants.ui.forms;

import javax.swing.*;
import java.awt.*;

public abstract class StandardForm extends JFrame {

  private static final int DEFAULT_WIDTH = 500;
  private static final int DEFAULT_HEIGHT = 500;

  public StandardForm() {
    super();
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
  }

  abstract void initForm(final JPanel panel);

  void addLeftComponent(final JPanel panel, Component c, int row) {
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.gridx = 0;
    gbc.gridy = row;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;

    panel.add(c, gbc);
  }

  void addRightComponent(final JPanel panel, Component c, int row) {
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.gridx = 1;
    gbc.gridy = row;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;

    panel.add(c, gbc);
  }
}
