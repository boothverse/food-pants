package org.boothverse.foodpants.ui.forms;

import org.boothverse.foodpants.ui.components.standard.StandardLabel;

import javax.swing.*;
import java.awt.*;

public class RecipeForm extends StandardForm {
  protected final int WIDTH = 640;
  protected final int HEIGHT = 480;
  protected final int TXT_FIELD_WIDTH = 25;

  public RecipeForm() {
    super();
    setSize(WIDTH, HEIGHT);

    this.setTitle("Create Recipe");
  }

  @Override
  void initForm(final JPanel panel) {
    String[] textLabels = new String[]{"Name", "Servings"};
    initFormHeader("Create Recipe", panel);

    int i;
    for (i = 0; i < textLabels.length; i++) {
      JLabel lbl = new StandardLabel(textLabels[i]);
      JTextField txtField = new JTextField(TXT_FIELD_WIDTH);

      addLeftComponent(lbl, i + 1);
      addRightComponent(txtField, i + 1);
    }

    JLabel ingredients = new StandardLabel("Ingredients");
    TextArea ingredArea = new TextArea();

    addLeftComponent(ingredients, ++i);
    addRightComponent(ingredArea, ++i);

    JButton submitBtn = new JButton("Submit");
    submitBtn.addActionListener(e -> this.dispose());

    addRightComponent(submitBtn, ++i);

    panel.add(boxPanel, BorderLayout.CENTER);
  }
}
