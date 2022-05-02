package org.boothverse.foodpants.ui.controllers;

import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.*;

import javax.measure.Quantity;
import java.util.Date;
import java.util.List;

public class NutritionController {

    /**
     * Returns a list of all nutrition goals
     *
     * @return
     */
    public List<Goal> getGoals() {
        return Services.NUTRITION_SERVICE.getGoals();
    }

    /**
     * Adds a goal to the system
     *
     * @param goalType
     * @param quantity
     * @param nutritionType
     * @return
     */
    public Goal addGoal(GoalType goalType,
                        Quantity quantity, NutritionType nutritionType) {

        Goal goal = new Goal(Services.ID_SERVICE.getId(), goalType, quantity, nutritionType);
        Services.NUTRITION_SERVICE.addGoal(goal);

        return goal;
    }

    /**
     * Edits the specified goal with the given information
     *
     * @param id
     * @param goalType
     * @param quantity
     * @param nutritionType
     * @return
     * @throws PantsNotFoundException
     */
    public Goal editGoal(String id, GoalType goalType,
                         Quantity quantity, NutritionType nutritionType) throws PantsNotFoundException {

        Goal goal = new Goal(id, goalType, quantity, nutritionType);
        Services.NUTRITION_SERVICE.editGoal(goal);

        return goal;
    }

    /**
     * Removes the specified goal.
     *
     * @param id
     * @throws PantsNotFoundException
     */
    public void removeGoal(String id) throws PantsNotFoundException {
        Services.NUTRITION_SERVICE.removeGoal(id);
    }

    /**
     * Gets a list of all nutrition items
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<NutritionInstance> getItems(Date startDate, Date endDate) {
        return Services.NUTRITION_SERVICE.getItems(startDate, endDate);
    }

    /**
     * Adds a nutrition item to the system.
     *
     * @param foodId
     * @param quantity
     * @param consumedAt
     * @return
     */
    public NutritionInstance addItem(String foodId, Quantity quantity,
                                     Date consumedAt) {

        NutritionInstance nutInstance = new NutritionInstance(Services.ID_SERVICE.getId(),
            foodId, quantity, consumedAt);
        Services.NUTRITION_SERVICE.addItem(nutInstance);

        return nutInstance;
    }

    /**
     * Modifies the specified item with the given information.
     *
     * @param id
     * @param foodId
     * @param quantity
     * @param consumedAt
     * @return
     * @throws PantsNotFoundException
     */
    public NutritionInstance editItem(String id, String foodId, Quantity quantity, Date consumedAt) throws PantsNotFoundException {

        NutritionInstance nutInstance = new NutritionInstance(id, foodId, quantity, consumedAt);
        Services.NUTRITION_SERVICE.editItem(nutInstance);

        return nutInstance;
    }

    /**
     * Removes the specified item.
     *
     * @param id
     * @throws PantsNotFoundException
     */
    public void removeItem(String id) throws PantsNotFoundException {
        Services.NUTRITION_SERVICE.removeItem(id);
    }

    /**
     * Adds a report to the system
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public ReportPeriod addReport(Date startDate, Date endDate) {
        ReportPeriod reportPeriod = new ReportPeriod(Services.ID_SERVICE.getId(),
            startDate, endDate);
        Services.NUTRITION_SERVICE.addReport(reportPeriod);

        return reportPeriod;
    }

    /**
     * Modifies the specified report with the given info.
     *
     * @param id
     * @param startDate
     * @param endDate
     * @throws PantsNotFoundException
     */
    public void editReport(String id, Date startDate, Date endDate) throws PantsNotFoundException {
        ReportPeriod period = new ReportPeriod(id, startDate, endDate);
        Services.NUTRITION_SERVICE.editReport(period);
    }

    /**
     * Removes the specified report.
     *
     * @param id
     * @throws PantsNotFoundException
     */
    public void removeReport(String id) throws PantsNotFoundException {
        Services.NUTRITION_SERVICE.removeReport(id);
    }
}
