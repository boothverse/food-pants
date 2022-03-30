package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.ui.Style;

import javax.swing.*;
import java.awt.*;

public abstract class StandardForm extends JFrame {
  // Form panel
  JPanel boxPanel;

  public StandardForm(String header) {
    super();
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setLocationRelativeTo(null);
    setBackground(Style.PLATINUM);

    boxPanel = new JPanel(new GridBagLayout());

    // Flowlayout panel
    JPanel panel = new JPanel(new FlowLayout());

    // Form initialization
    initFormHeader(header, panel);
    initForm(panel);

    this.add(panel);
    setTitle(header);
    setSize(WIDTH, HEIGHT);
    if (getParent() != null) {
      setLocationRelativeTo(getParent());
    }
  }

  void initFormHeader(String header, final JPanel panel) {
    JLabel createHeader = new JLabel(header);
    createHeader.setFont(Style.headerStyle);
    panel.add(createHeader);
  }

  abstract void initForm(final JPanel panel);

  void addLeftComponent(Component c, int row) {
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.gridx = 0;
    gbc.gridy = row;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;

    boxPanel.add(c, gbc);
  }

  void addRightComponent(Component c, int row) {
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.gridx = 1;
    gbc.gridy = row;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;

    boxPanel.add(c, gbc);
  }
}
