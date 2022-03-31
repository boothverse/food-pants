package org.boothverse.foodpants.ui.components.standard;

import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.PantryItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeItem extends StandardPanel {
    private static final String demoName = "Chocolate Cake";
    private static final String[] demoIngredients = {"Bread Flour (lb)", "Vanilla Extract (oz)", "Eggs (unit)", "Sugar (cup)", "Cocoa Powder (g)"};
    private static final int[] demoQuantities = {10,1,4,2,15};
    private static final String[] demoInstructions = {"Preheat the oven to 350 degrees F.", "Coat two 9-inch-round cake pans with cooking spray and line the bottoms with parchment paper.",
    "Whisk the cocoa powder and 1 1/2 cups boiling water in a medium bowl until smooth; set aside.","Whisk the flour, sugar, baking powder, baking soda and salt in a large bowl until combined.","Add the eggs, vegetable oil, sour cream and vanilla and beat with a mixer on medium speed until smooth, about 1 minute."," Reduce the mixer speed to low; beat in the cocoa mixture in a steady stream until just combined, then finish mixing with a rubber spatula. (The batter will be thin.)",
            "Divide the batter between the prepared pans and tap the pans against the counter to help the batter settle.","Bake until a toothpick inserted into the middle comes out clean, 30 to 40 minutes. ","Transfer to racks and let cool 10 minutes, then run a knife around the edge of the pans and turn the cakes out onto the racks to cool completely."," Remove the parchment. Trim the tops of the cakes with a long serrated knife to make them level, if desired."};


    protected String name;
    protected List<PantryItem> ingredients;

    protected JPanel ingredientDisplay;
    protected JPanel instructionDisplay;

    public RecipeItem() {
        super();
        setLayout(new GridBagLayout());

        // Init fields
        this.name = demoName;
        ingredients = new ArrayList<>();

        // init swing components
        ingredientDisplay = new JPanel(new GridLayout(demoIngredients.length,0));
        instructionDisplay = new JPanel(new GridLayout(demoInstructions.length,0));
        initDemoValues();
        initComponents();
    }

    private void initComponents() {
        GridBagConstraints c = new GridBagConstraints();
        JLabel label;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = .2f;
        label = new JLabel("Instructions");
        label.setFont(Style.headerStyle.deriveFont(20f));
        label.setBorder(Style.BORDER_1);
        add(label, c);

        c.gridy = 2;
        label = new JLabel("Ingredients");
        label.setFont(Style.headerStyle.deriveFont(20f));
        label.setBorder(Style.BORDER_1);
        add(label, c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = .8f;
        c.weighty = .3f;
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(Style.headerStyle);
        add(nameLabel, c);

        c.gridy = 1;
        c.weighty = .4f;
        instructionDisplay.setBorder(Style.BORDER_1);
        add(instructionDisplay, c);

        c.gridy = 2;
        c.weighty = .7f;
        add(ingredientDisplay, c);

    }

    private void initDemoValues() {
        for (int i = 0; i < demoIngredients.length; i++) {
            ingredients.add(new PantryItem(demoIngredients[i], demoQuantities[i]));
        }
        for (PantryItem p : ingredients) {
            ingredientDisplay.add(p);
        }

        for (String s: demoInstructions) {
            instructionDisplay.add(new JLabel(s));
        }
    }
}
