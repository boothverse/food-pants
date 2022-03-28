package org.boothverse.foodpants.ui.components.standard;

import org.boothverse.foodpants.ui.Style;

import javax.swing.*;

public abstract class StandardPanel extends JPanel {

  public StandardPanel() {
    super();
    setBackground(Style.WHITE);
    setForeground(Style.BLACK);
    setBorder(Style.BORDER_1);
  }
}
