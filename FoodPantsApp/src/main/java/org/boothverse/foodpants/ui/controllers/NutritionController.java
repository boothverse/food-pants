package org.boothverse.foodpants.ui.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.business.services.Services;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.*;

import javax.measure.Quantity;
import java.util.Date;
import java.util.List;

/**
 * A controller which handles user events related to nutritional info
 */
public class NutritionController {

    private static Logger logger = LogManager.getLogger(NutritionController.class);

    /**
     * Returns a list of all nutrition goals
     *
     * @return a list of all the goals
     */
    public List<Goal> getGoals() {
        logger.info("goals retrieved");
        return Services.NUTRITION_SERVICE.getGoals();
    }

    /**
     * Adds a goal to the system
     *
     * @param goalType the goals type
     * @param quantity the quantity the goal is centered around
     * @param nutritionType the nutritional type the goal measures
     * @return the newly create goal
     */
    public Goal addGoal(GoalType goalType,
                        Quantity quantity, NutritionType nutritionType) {

        Goal goal = new Goal(Services.ID_SERVICE.getId(), goalType, quantity, nutritionType);
        Services.NUTRITION_SERVICE.addGoal(goal);

        logger.info(goalType.name() + " goal added");

        return goal;
    }

    /**
     * Edits the specified goal with the given information
     *
     * @param id the id of the goal
     * @param goalType the goals type
     * @param quantity the quantity the goal is centered around
     * @param nutritionType the nutritional type the goal measures
     * @return the modified goal
     * @throws PantsNotFoundException
     */
    public Goal editGoal(String id, GoalType goalType,
                         Quantity quantity, NutritionType nutritionType) throws PantsNotFoundException {

        Goal goal = new Goal(id, goalType, quantity, nutritionType);
        Services.NUTRITION_SERVICE.editGoal(goal);

        logger.info(goal.getGoalType() + " goal modified");
        return goal;
    }

    /**
     * Removes the specified goal.
     *
     * @param id the id of the goal to be removed
     * @throws PantsNotFoundException
     */
    public void removeGoal(String id) throws PantsNotFoundException {
        Services.NUTRITION_SERVICE.removeGoal(id);
        logger.info(id + " goal removed");
    }

    /**
     * Gets a list of all nutrition items
     *
     * @param startDate the beginning date of the period
     * @param endDate the end date of the report period
     * @return
     */
    public List<NutritionInstance> getItems(Date startDate, Date endDate) {
        logger.debug("items retrieved between " + startDate.toString() + " and " + endDate.toString());
        return Services.NUTRITION_SERVICE.getItems(startDate, endDate);
    }

    /**
     * Adds a nutrition item to the system.
     *
     * @param foodId the name associated with the food
     * @param quantity the quantity of the food consumed
     * @param consumedAt the time at which the food is consumed
     * @return the newly created nutrition instance
     */
    public NutritionInstance addItem(String foodId, Quantity quantity,
                                     Date consumedAt) {

        NutritionInstance nutInstance = new NutritionInstance(Services.ID_SERVICE.getId(),
            foodId, quantity, consumedAt);
        Services.NUTRITION_SERVICE.addItem(nutInstance);
        logger.info(nutInstance.getId() + " added to nutrition");

        return nutInstance;
    }

    /**
     * Modifies the specified item with the given information.
     *
     * @param id the id of the item
     * @param foodId the name associated with the food
     * @param quantity the quantity of the food consumed
     * @param consumedAt the time at which the food is consumed
     * @return the modified nutrition instance
     * @throws PantsNotFoundException
     */
    public NutritionInstance editItem(String id, String foodId, Quantity quantity, Date consumedAt) throws PantsNotFoundException {

        NutritionInstance nutInstance = new NutritionInstance(id, foodId, quantity, consumedAt);
        Services.NUTRITION_SERVICE.editItem(nutInstance);

        logger.info(nutInstance.getId() + " nutrition item updated");

        return nutInstance;
    }

    /**
     * Removes the specified item.
     *
     * @param id the id of the item to be removed
     * @throws PantsNotFoundException
     */
    public void removeItem(String id) throws PantsNotFoundException {
        Services.NUTRITION_SERVICE.removeItem(id);
        logger.info(id + " nutrition removed");
    }

    public List<ReportPeriod> getReports() {
        logger.debug("retrieved list of reports");
        return Services.NUTRITION_SERVICE.getReports();
    }

    /**
     * Adds a report to the system
     *
     * @param startDate the beginning date for the report
     * @param endDate the end date for the report
     * @return the newly created report
     */
    public ReportPeriod addReport(Date startDate, Date endDate) {
        ReportPeriod reportPeriod = new ReportPeriod(Services.ID_SERVICE.getId(),
            startDate, endDate);
        Services.NUTRITION_SERVICE.addReport(reportPeriod);

        logger.info(reportPeriod.getId() + " report added");

        return reportPeriod;
    }

    /**
     * Modifies the specified report with the given info.
     *
     * @param id the id of the report
     * @param startDate the beginning date for the report
     * @param endDate the end date for the report
     * @throws PantsNotFoundException
     */
    public void editReport(String id, Date startDate, Date endDate) throws PantsNotFoundException {
        ReportPeriod period = new ReportPeriod(id, startDate, endDate);
        Services.NUTRITION_SERVICE.editReport(period);
        logger.info(id + " report updated");
    }

    /**
     * Removes the specified report.
     *
     * @param id the id of the report to be removed
     * @throws PantsNotFoundException
     */
    public void removeReport(String id) throws PantsNotFoundException {
        Services.NUTRITION_SERVICE.removeReport(id);
        logger.info(id + " report removed");
    }
}
