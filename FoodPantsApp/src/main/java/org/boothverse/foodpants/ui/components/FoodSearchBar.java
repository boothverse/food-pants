package org.boothverse.foodpants.ui.components;

import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.ui.controllers.FoodController;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FoodSearchBar extends JComboBox<Food> {
    protected DefaultComboBoxModel<Food> model;
    private final FoodController foodController = new FoodController();

    public FoodSearchBar() {
        super();
        model = new DefaultComboBoxModel<>();
        setModel(model);
        AutoCompleteDecorator.decorate(this, new ObjectToStringConverter() {
            @Override
            public String getPreferredStringForItem(Object o) {
                if (o != null) {
                    return ((Food)o).getName();
                }
                else {
                    return "";
                }
            }
        });

        ComboBoxRenderer renderer = new ComboBoxRenderer();
        setRenderer(renderer);
        setMaximumRowCount(10);
        model.addAll(foodController.getFoods());
    }

    public void update(Food newFood) {
        model.addElement(newFood);
    }

    static class ComboBoxRenderer extends JLabel implements ListCellRenderer<Food> {
        public ComboBoxRenderer() {
            setOpaque(true);
            setHorizontalAlignment(LEFT);
            setVerticalAlignment(CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Food> list, Food value, int index, boolean isSelected, boolean cellHasFocus) {
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            setText(value.getName());
            return this;

        }
    }
}
