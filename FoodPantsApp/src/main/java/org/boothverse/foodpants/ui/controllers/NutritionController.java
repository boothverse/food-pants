package org.boothverse.foodpants.ui.controllers;

import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.*;

import javax.measure.Quantity;
import java.util.Date;
import java.util.List;

public class NutritionController {

    public List<Goal> getGoals() {
        return Services.NUTRITION_SERVICE.getGoals();
    }

    public Goal addGoal(GoalType goalType,
                        Quantity quantity, NutritionType nutritionType) {

        Goal goal = new Goal(Services.ID_SERVICE.getId(), goalType, quantity, nutritionType);
        Services.NUTRITION_SERVICE.addGoal(goal);

        return goal;
    }

    public Goal editGoal(String id, GoalType goalType,
                         Quantity quantity, NutritionType nutritionType) throws PantsNotFoundException {

        Goal goal = new Goal(id, goalType, quantity, nutritionType);
        Services.NUTRITION_SERVICE.editGoal(goal);

        return goal;
    }

    public void removeGoal(String id) throws PantsNotFoundException {
        Services.NUTRITION_SERVICE.removeGoal(id);
    }

    public List<NutritionInstance> getItems(Date startDate, Date endDate) {
        return Services.NUTRITION_SERVICE.getItems(startDate, endDate);
    }

    public NutritionInstance addItem(String foodId, Quantity quantity,
                                     Date consumedAt) {

        NutritionInstance nutInstance = new NutritionInstance(Services.ID_SERVICE.getId(),
            foodId, quantity, consumedAt);
        Services.NUTRITION_SERVICE.addItem(nutInstance);

        return nutInstance;
    }

    public NutritionInstance editItem(String id, String foodId, Quantity quantity, Date consumedAt) throws PantsNotFoundException {

        NutritionInstance nutInstance = new NutritionInstance(id, foodId, quantity, consumedAt);
        Services.NUTRITION_SERVICE.editItem(nutInstance);

        return nutInstance;
    }

    public void removeItem(String id) throws PantsNotFoundException {
        Services.NUTRITION_SERVICE.removeItem(id);
    }

    public ReportPeriod addReport(Date startDate, Date endDate) {
        ReportPeriod reportPeriod = new ReportPeriod(Services.ID_SERVICE.getId(),
            startDate, endDate);
        Services.NUTRITION_SERVICE.addReport(reportPeriod);

        return reportPeriod;
    }

    public void editReport(String id, Date startDate, Date endDate) throws PantsNotFoundException {
        ReportPeriod period = new ReportPeriod(id, startDate, endDate);
        Services.NUTRITION_SERVICE.editReport(period);
    }

    public void removeReport(String id) throws PantsNotFoundException {
        Services.NUTRITION_SERVICE.removeReport(id);
    }
}
