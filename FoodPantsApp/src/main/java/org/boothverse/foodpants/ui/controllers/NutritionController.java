package org.boothverse.foodpants.ui.controllers;

import org.boothverse.foodpants.persistence.Goal;
import org.boothverse.foodpants.persistence.GoalType;
import org.boothverse.foodpants.persistence.NutritionInstance;
import org.boothverse.foodpants.persistence.NutritionType;

import javax.measure.Quantity;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class NutritionController {
    // TODO: someone else's problem lol
    public Map<String, Goal> getGoals() {
        // TODO: Nutrition Service getGoals
        return null;
    }

    public Goal addGoal(NutritionType nutritionType,
                        Quantity quantity, GoalType goalType) {
        return null;
    }

    public Goal editGoal(String id, NutritionType nutritionType,
                         Quantity quantity, GoalType goalType) {
        return null;
    }

    public void removeGoal(String id) {

    }

    public List<NutritionInstance> getItems(Date startDate, Date endDate) {
        return null;
    }

    public NutritionInstance addItem(String foodId, Quantity quantity,
                                     Date consumedAt) {
        return null;
    }

    public NutritionInstance editItem(String foodId, Quantity quantity,
                                      Date consumedAt) {
        return null;
    }

    public void removeItem(String foodId, Date consumedAt) {

    }

    public void addReport(Date startDate, Date endDate) {

    }

    public void editReport(String id, Date startDate, Date endDate) {

    }

    public void removeReport(String id) {

    }
}
