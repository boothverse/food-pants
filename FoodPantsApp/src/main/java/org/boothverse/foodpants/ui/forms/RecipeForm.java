package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.StandardLabel;

import javax.swing.*;
import java.awt.*;

public class RecipeForm extends StandardForm {
  private static final int WIDTH = 640;
  private static final int HEIGHT = 480;
  private static final int TXT_FIELD_WIDTH = 25;

  private String[] fields = new String[]{"Name", "Servings"};

  public RecipeForm() {
    super();
    this.setSize(WIDTH, HEIGHT);
    JPanel panel = new JPanel(new FlowLayout());
    initForm(panel);

    this.add(panel);
    this.setTitle("Create Recipe");
  }

  @Override
  void initForm(final JPanel panel) {
    JPanel boxPanel = new JPanel(new GridBagLayout());

    JLabel createHeader = new JLabel("Create Recipe");
    createHeader.setFont(Style.headerStyle);
    panel.add(createHeader);

    int i;
    for (i = 0; i < fields.length; i++) {
      JLabel lbl = new StandardLabel(fields[i]);
      JTextField txtField = new JTextField(TXT_FIELD_WIDTH);

      addLeftComponent(boxPanel, lbl, i + 1);
      addRightComponent(boxPanel, txtField, i + 1);
    }

    JLabel ingredients = new StandardLabel("Ingredients");
    TextArea ingredArea = new TextArea();

    addLeftComponent(boxPanel, ingredients, i + 1);
    i++;
    addRightComponent(boxPanel, ingredArea, i + 1);
    i++;

    JButton submitBtn = new JButton("Submit");
    submitBtn.addActionListener(e -> this.dispose());

    addRightComponent(boxPanel, submitBtn, i + 1);

    panel.add(boxPanel, BorderLayout.CENTER);
  }
}
