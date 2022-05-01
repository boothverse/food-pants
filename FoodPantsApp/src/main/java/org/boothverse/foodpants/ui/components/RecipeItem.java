package org.boothverse.foodpants.ui.components;

import org.boothverse.foodpants.persistence.*;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.standard.StandardButton;
import org.boothverse.foodpants.ui.components.standard.StandardGridBagPanel;
import org.boothverse.foodpants.ui.components.standard.StandardItem;
import org.boothverse.foodpants.ui.components.standard.StandardPanel;
import systems.uom.unicode.CLDR;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecipeItem extends StandardPanel {
    private static final String demoName = "Chocolate Cake";
    private static final String[] demoFoodNames = {"Bread Flour (lb)", "Vanilla Extract (oz)", "Eggs (unit)", "Sugar (cup)", "Cocoa Powder (g)"};
    private static final int[] demoQuantities = {10, 1, 4, 2, 15};
    private static final List<FoodInstance> demoIngredients = new ArrayList<>();
    static {
        for (int i = 0; i < demoFoodNames.length; i++) {
            demoIngredients.add(new Food(i + "", demoFoodNames[i], FoodGroup.DAIRY, null).createInstance(Quantities.getQuantity(demoQuantities[i], CLDR.POUND)));
        }
    }
    private static final String demoInstructions = """
        Preheat the oven to 350 degrees F.\s
        Coat two 9-inch-round cake pans with cooking spray and line the bottoms with parchment paper\s
        Whisk the cocoa powder and 1 1/2 cups boiling water in a medium bowl until smooth; set aside.\s
        Whisk the flour, sugar, baking powder, baking soda and salt in a large bowl until combined.\s
        Add the eggs, vegetable oil, sour cream and vanilla and beat with a mixer on medium speed until smooth,\s
        about 1 minute.\s
        Reduce the mixer speed to low; beat in the cocoa mixture in a steady stream until just combined,\s
        then finish mixing with a rubber spatula. (The batter will be thin.)\s
        Divide the batter between the prepared pans and tap the pans against the counter to help the batter settle.\s
        Bake until a toothpick inserted into the middle comes out clean, 30 to 40 minutes.\s
        Transfer to racks and let cool 10 minutes, then run a knife around the edge of the pans\s
        and turn the cakes out onto the racks to cool completely. Remove the parchment.\s
        Trim the tops of the cakes with a long serrated knife to make them level, if desired.""";

    private final Recipe demoRecipe = new Recipe("a123123123", "Chocolate Cake", FoodGroup.GRAIN, new NutritionDescriptor(null, Quantities.getQuantity(100, Units.GRAM)),
        demoInstructions, demoIngredients, 12.0);

    protected JLabel title;
    protected List<StandardItem> ingredientDisplays;

    protected StandardGridBagPanel contentPanel;
    protected JPanel wrapperPanel;
    protected JPanel ingredientPanel;
    protected JButton seeMoreButton;

    protected Recipe recipe;

    public RecipeItem(Recipe recipeItem) {
        super();
        recipe = Objects.requireNonNullElse(recipeItem, demoRecipe);

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

        ingredientPanel = new JPanel();
        ingredientPanel.setBackground(Style.TRANSPARENT);
        ingredientPanel.setLayout(new BoxLayout(ingredientPanel, BoxLayout.Y_AXIS));
        ingredientDisplays.stream().limit(3).forEach(display -> ingredientPanel.add(display));
        if (ingredientPanel.getComponentCount() < ingredientDisplays.size()) {
            JPanel seeMoreWrapper = new JPanel(new BorderLayout());
            seeMoreWrapper.setBackground(Style.TRANSPARENT);
            seeMoreWrapper.add(seeMoreButton, BorderLayout.EAST);
            ingredientPanel.add(seeMoreWrapper);
        }

        JLabel ingredientLabel = new JLabel("Ingredients");
        ingredientLabel.setFont(Style.headerStyle.deriveFont(13f));
        ingredientLabel.setVerticalAlignment(SwingConstants.BOTTOM);

        JLabel servingLabel = new JLabel();
        servingLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        servingLabel.setFont(Style.headerStyle.deriveFont(12f));
        servingLabel.setText("Servings: " + recipe.getServings() + " (" + recipe.getNutrition().getServingSize().toString() + "/each)");

        int i = 0;
        contentPanel.addRightComponent(servingLabel, i);
        contentPanel.addSeperator(++i);
        contentPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        contentPanel.addLeftComponent(ingredientLabel, ++i);
        contentPanel.addRightComponent(ingredientPanel, i);

        JPanel spacer = new JPanel();
        spacer.setBackground(Style.TRANSPARENT);
        contentPanel.addRightComponent(spacer, ++i);
        contentPanel.addSeperator(++i);
    }

    protected void initHeader(String header) {
        if (title == null) {
            title = new JLabel(header);
            title.setFont(Style.headerStyle.deriveFont(16f));
            contentPanel.addLeftComponent(title, 0);
        }
        else {
            title.setText(header);
        }

    }
}
