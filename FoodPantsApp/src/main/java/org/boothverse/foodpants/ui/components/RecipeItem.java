package org.boothverse.foodpants.ui.components;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import org.boothverse.foodpants.business.services.util.UnitToString;
import org.boothverse.foodpants.persistence.*;
import org.boothverse.foodpants.ui.PageManager;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.standard.StandardButton;
import org.boothverse.foodpants.ui.components.standard.StandardGridBagPanel;
import org.boothverse.foodpants.ui.components.standard.StandardItem;
import org.boothverse.foodpants.ui.components.standard.StandardPanel;
import org.boothverse.foodpants.ui.forms.StandardForm;
import org.boothverse.foodpants.ui.forms.ViewRecipeForm;
import systems.uom.unicode.CLDR;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter @Setter
public class RecipeItem extends StandardPanel {
    protected JLabel title;
    protected List<StandardItem> ingredientDisplays;

    protected StandardGridBagPanel contentPanel;
    protected JPanel wrapperPanel;
    protected JPanel ingredientPanel;
    protected JButton seeMoreButton;
    protected JPanel seeMoreWrapper;

    protected Recipe recipe;

    public RecipeItem(@NonNull Recipe r) {
        super();
        recipe = Objects.requireNonNull(r);

        // Content goes in this panel, order with grid bag layout
        contentPanel = new StandardGridBagPanel();

        // Flowlayout panel (wrap around grid bag panel)
        wrapperPanel = new JPanel();
        wrapperPanel.setBackground(Style.TRANSPARENT);

        add(wrapperPanel);
        wrapperPanel.add(contentPanel);

        // init swing components
        initHeader(recipe.getName());
        initComponents();
    }

    private void initComponents() {
        ingredientDisplays = new ArrayList<>();
        recipe.getIngredients().forEach(ingredient -> ingredientDisplays.add(new StandardItem(ingredient)));

        seeMoreButton = new StandardButton("See more");
        seeMoreButton.addActionListener(e -> {
            StandardForm recipeForm = new ViewRecipeForm(recipe, PageManager.getActivePage());
            recipeForm.setLocationRelativeTo(PageManager.getActivePage());
            recipeForm.setVisible(true);
        });

        ingredientPanel = new JPanel();
        ingredientPanel.setBackground(Style.TRANSPARENT);
        ingredientPanel.setLayout(new BoxLayout(ingredientPanel, BoxLayout.Y_AXIS));
        ingredientDisplays.stream().limit(3).forEach(display -> ingredientPanel.add(display));

        seeMoreWrapper = new JPanel(new BorderLayout());
        seeMoreWrapper.setBackground(Style.TRANSPARENT);
        seeMoreWrapper.add(seeMoreButton, BorderLayout.EAST);

        JLabel ingredientLabel = new JLabel("Ingredients");
        ingredientLabel.setFont(Style.headerStyle.deriveFont(13f));
        ingredientLabel.setVerticalAlignment(SwingConstants.BOTTOM);

        JLabel servingLabel = new JLabel();
        servingLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        servingLabel.setFont(Style.headerStyle.deriveFont(12f));
        servingLabel.setText("Servings: " + recipe.getServings() + " (" + UnitToString.convertUnitToString(recipe.getNutrition().getServingSize().getUnit()) + "/each)");

        int i = 0;
        contentPanel.addRightComponent(servingLabel, i);

        contentPanel.addMiddleComponent(new JSeparator(SwingConstants.HORIZONTAL), ++i);

        contentPanel.addLeftComponent(ingredientLabel, ++i);
        contentPanel.addRightComponent(ingredientPanel, i);

        JPanel spacer = new JPanel();
        spacer.setBackground(Style.TRANSPARENT);
        contentPanel.addRightComponent(spacer, ++i);
        contentPanel.addMiddleComponent(new JSeparator(SwingConstants.HORIZONTAL), ++i);
        contentPanel.addRightComponent(seeMoreWrapper, ++i);
    }

    protected void initHeader(String header) {
        if (title == null) {
            title = new JLabel(header);
            title.setFont(Style.headerStyle.deriveFont(16f));
            contentPanel.addLeftComponent(title, 0);
        } else {
            title.setText(header);
        }
    }
}
