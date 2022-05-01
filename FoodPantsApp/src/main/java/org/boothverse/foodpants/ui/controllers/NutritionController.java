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
                         Quantity quantity, NutritionType nutritionType) {

        Goal goal = new Goal(id, goalType, quantity, nutritionType);
        //TODO: Maybe error handling shoudln't be here
        try {
            Services.NUTRITION_SERVICE.editGoal(goal);
        } catch (PantsNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return goal;
    }

    public void removeGoal(String id) {
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

    public NutritionInstance editItem(String id, String foodId, Quantity quantity, Date consumedAt) {

        NutritionInstance nutInstance = new NutritionInstance(id, foodId, quantity, consumedAt);
        //TODO: Maybe error handling shoudln't be here
        try {
            Services.NUTRITION_SERVICE.editItem(nutInstance);
        } catch (PantsNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return nutInstance;
    }

    public void removeItem(String id) {
        Services.NUTRITION_SERVICE.removeItem(id);
    }

    public ReportPeriod addReport(Date startDate, Date endDate) {
        ReportPeriod reportPeriod = new ReportPeriod(Services.ID_SERVICE.getId(),
            startDate, endDate);
        Services.NUTRITION_SERVICE.addReport(reportPeriod);

        return reportPeriod;
    }

    public void editReport(String id, Date startDate, Date endDate) {
        ReportPeriod period = new ReportPeriod(id, startDate, endDate);
        //TODO: Maybe error handling shoudln't be here
        try {
            Services.NUTRITION_SERVICE.editReport(period);
        } catch (PantsNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeReport(String id) {
        Services.NUTRITION_SERVICE.removeReport(id);
    }
}
