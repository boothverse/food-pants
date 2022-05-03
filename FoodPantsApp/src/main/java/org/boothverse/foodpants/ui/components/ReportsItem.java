package org.boothverse.foodpants.ui.components;

import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.business.services.util.UnitToString;
import org.boothverse.foodpants.persistence.Food;
import org.boothverse.foodpants.persistence.NutritionInstance;
import org.boothverse.foodpants.persistence.NutritionType;
import org.boothverse.foodpants.ui.Style;
import org.boothverse.foodpants.ui.components.standard.StandardGridBagPanel;
import org.boothverse.foodpants.ui.components.standard.StandardPanel;
import org.boothverse.foodpants.ui.controllers.FoodController;
import org.boothverse.foodpants.ui.controllers.NutritionController;

import javax.measure.Quantity;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReportsItem extends StandardPanel {

    StandardGridBagPanel contentPanel;

    public ReportsItem(Date startDate, Date endDate) {
        contentPanel = new StandardGridBagPanel();
        initReport(startDate, endDate);
        add(contentPanel);
    }

    void initReport(Date startDate, Date endDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
        String start = dateFormat.format(startDate);
        String end = dateFormat.format(endDate);

        JLabel header = new JLabel(start + " - " + end);
        header.setFont(Style.headerStyle);

        NutritionController nutritionController = new NutritionController();
        List<NutritionInstance> items = nutritionController.getItems(startDate, endDate);

        if (items.size() == 0) {
            setVisible(false);
            return;
        }

        int i = 0;
        contentPanel.addMiddleComponent(header, ++i);

        FoodController foodController = new FoodController();

        Map<NutritionType, Quantity> nutritionalInfo = new HashMap<>();
        for (NutritionInstance item : items) {
            try {
                Food food = foodController.getFood(item.getFoodId());
                
                for (Map.Entry<NutritionType, Quantity<?>> entry : food.getNutrition().getNutritionInfo().entrySet()) {
                    if (nutritionalInfo.get(entry.getKey()) != null) {
                        nutritionalInfo.replace(entry.getKey(), nutritionalInfo.get(entry.getKey()).add(entry.getValue()));
                    } else {
                        nutritionalInfo.put(entry.getKey(), entry.getValue());
                    }
                }
            } catch (PantsNotFoundException e) {}
        }

        for (Map.Entry<NutritionType, Quantity> infoEntry : nutritionalInfo.entrySet()) {
            contentPanel.addLeftComponent(new JLabel(infoEntry.getKey().name()), ++i);

            contentPanel.addRightComponent(new JLabel("(" +
                UnitToString.convertUnitToString(infoEntry.getValue().getUnit()) + ") "
                    + infoEntry.getValue().getValue()), i++);
        }
    }
}
