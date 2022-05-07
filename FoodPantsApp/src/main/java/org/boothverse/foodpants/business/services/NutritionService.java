package org.boothverse.foodpants.business.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.boothverse.foodpants.business.dao.GoalDAO;
import org.boothverse.foodpants.business.dao.ListDAO;
import org.boothverse.foodpants.business.dao.NutritionInstanceDAO;
import org.boothverse.foodpants.business.dao.ReportDAO;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.business.services.util.EnumUtils;
import org.boothverse.foodpants.persistence.*;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.quantity.Dimensionless;
import java.util.*;

import static tech.units.indriya.AbstractUnit.ONE;

/**
 * service dealing with processing nutrition instances
 */
public class NutritionService {
    private static Logger logger = LogManager.getLogger(NutritionService.class);
    protected Map<String, NutritionInstance> items;
    protected Map<String, Goal> goals;
    protected Map<String, ReportPeriod> reportPeriods;
    protected final ListDAO<NutritionInstance> nutritionInstanceDAO = new NutritionInstanceDAO();
    protected final ListDAO<ReportPeriod> reportPeriodDAO = new ReportDAO();
    protected final ListDAO<Goal> goalDAO = new GoalDAO();

    private static final double AVG_CALORIE_MEN = 2500;
    private static final double AVG_CALORIE_WOMEN = 2000;
    private static final double CALORIE_GOAL_BUFFER = 500;

    /**
     * Creates the NutritionService and loads data stored in database
     */
    public NutritionService() {
        logger.info("Loading nutrition instances from database");
        items = nutritionInstanceDAO.load();
        logger.info("Loading goals from database");
        goals = goalDAO.load();
        logger.info("Loading report peiods from database");
        reportPeriods = reportPeriodDAO.load();
    }

    /**
     * Returns a list of NutritionInstance objects within the specified time range.
     *
     * @param startDate the start date of the time frame
     * @param endDate the end date of the time frame
     * @return a list of nutrition instances which fall within the time frame
     */
    public List<NutritionInstance> getItems(Date startDate, Date endDate) {
        logger.info("Getting nutritional instances between " + startDate + " and " + endDate);
        List<NutritionInstance> result = new ArrayList<>();
        for(NutritionInstance n: items.values()){
            if((n.getConsumedAt().after(startDate) || n.getConsumedAt().equals(startDate))
                    && n.getConsumedAt().before(endDate) ){
                logger.info("Adding nutritional instance with id " + n.getId() + " and date " + n.getConsumedAt() + " to return list");
                result.add(n);
            }
        }
        return result;
    }

    /**
     * Adds the item to the service and database.
     *
     * @param nutritionInstance the nutrition instance to be added
     */
    public void addItem(NutritionInstance nutritionInstance) {
        logger.info("Adding nutritional item with id " + nutritionInstance.getId());
        items.put(nutritionInstance.getId(), nutritionInstance);
        logger.info("Saving nutritional item with id " + nutritionInstance.getId() + " to database");
        nutritionInstanceDAO.save(nutritionInstance);
    }

    /**
     * Edits pre-existing item in service and database.
     *
     * @param nutritionInstance the nutrition instance to be modified
     */
    public void editItem(NutritionInstance nutritionInstance) throws PantsNotFoundException {
        if (!items.containsKey(nutritionInstance.getId())) {
            logger.warn("Trying to edit a nutrition instance that does not exist with id " + nutritionInstance.getId());
            throw new PantsNotFoundException("Failed to find specified NutritionInstance for modification.");
        }
        logger.info("Updating nutrition instance with id " + nutritionInstance.getId());
        items.replace(nutritionInstance.getId(), nutritionInstance);
        logger.info("Saving updated nutrition instance with id " + nutritionInstance.getId() + " in database");
        nutritionInstanceDAO.save(nutritionInstance);
    }

    /**
     * Removes the specified item from the service and database
     *
     * @param id the id of the instance to be removed
     */
    public void removeItem(String id) throws PantsNotFoundException {
        if (!items.containsKey(id)){
            logger.warn("Trying to remove a nutrition instance that does not exist with id " + id);
            throw new PantsNotFoundException("nutrition instance " + id + " not found");
        }
        logger.info("Removing nutrition instance with id " + id);
        items.remove(id);
        logger.info("Removing food with id " + id + " from database");
        nutritionInstanceDAO.remove(id);
    }

    /**
     * Returns the goals map
     *
     * @return the list of goals
     */
    public List<Goal> getGoals() {
        logger.info("Getting all goals as list");
        return new ArrayList<>(goals.values());
    }

    /**
     * Computes the recommended calorie goal based on the Harris-Benedict formula
     *
     * @return a recommended goal
     */
    public Goal<?> getRecommendedCalorieGoal() {
        UserService userService = Services.USER_SERVICE;
        double calories;
        double weight = userService.getBodyWeightKg();
        double height = userService.getHeightCm();
        int age = userService.getAge();
        String id = Services.ID_SERVICE.getId();

        // Harris-Benedict formula
        if (userService.userIsFemale()) {
            logger.info("Getting recommended calorie goal for female");
            calories = (655.1 + 9.6 * weight + 1.9 * height) / (4.7 * age);
        } else {
            logger.info("Getting recommended calorie goal for male");
            calories = (66.5 + 13.8 * weight + 5 * height) / (6.8 * age);
        }
        // TODO: low physical activity -> 1.2
        //       med                   -> 1.3
        //       high                  -> 1.4
        double multiplier = 1.3;
        calories *= multiplier;

        // Find goal type
        GoalType goalType;
        if (userService.userIsFemale()) {
            if (calories < AVG_CALORIE_WOMEN) {
                goalType = GoalType.MAXIMIZE;
                calories -= CALORIE_GOAL_BUFFER;
            } else {
                goalType = GoalType.MINIMIZE;
                calories += CALORIE_GOAL_BUFFER;
            }
        } else {
            if (calories < AVG_CALORIE_MEN) {
                goalType = GoalType.MAXIMIZE;
                calories -= CALORIE_GOAL_BUFFER;
            } else {
                goalType = GoalType.MINIMIZE;
                calories += CALORIE_GOAL_BUFFER;
            }
        }

        Quantity<Dimensionless> quantity = Quantities.getQuantity(calories, ONE);

        return new Goal<>(id, goalType, quantity, NutritionType.CALORIES);
    }

    /**
     * Adds a goal to the service and database
     *
     * @param goal the goal to be added
     */
    public void addGoal(Goal<?> goal) {
        logger.info("Adding goal with id " + goal.getId());
        goals.put(goal.getId(), goal);
        logger.info("Saving goal with id " + goal.getId() + " in database");
        goalDAO.save(goal);
    }

    /**
     * Changes the value of a goal in the service and database
     *
     * @param goal the goal to be modified
     */
    public void editGoal(Goal<?> goal) throws PantsNotFoundException {
        String id = goal.getId();
        if (!goals.containsKey(id)){
            logger.warn("Trying to edit a goal that does not exist with id " + id);
            throw new PantsNotFoundException("goal " + id + " not found");
        }
        logger.info("Updating goal with id " + id);
        goals.replace(id, goal);
        logger.info("Saving updated goal with id " + id + " in database");
        goalDAO.save(goal);
    }

    /**
     * Removes a goal from the service and database
     *
     * @param id the id of the goal top be removed
     */
    public void removeGoal(String id) throws PantsNotFoundException {
        if (!goals.containsKey(id)){
            logger.warn("Trying to remove a goal that does not exist with id " + id);
            throw new PantsNotFoundException("goal " + id + " not found");
        }
        logger.info("Removing goal with id " + id);
        goals.remove(id);
        logger.info("Removing goal with id " + id + " from database");
        goalDAO.remove(id);
    }

    public List<ReportPeriod> getReports() {
        logger.info("Getting all reports as list");
        return new ArrayList<>(reportPeriods.values());
    }

    /**
     * Adds a report to the service and database
     *
     * @param period the report to be added
     */
    public void addReport(ReportPeriod period) {
        logger.info("Adding report period with id " + period.getId());
        reportPeriods.put(period.getId(), period);
        logger.info("Saving report period with id " + period.getId() + " in database");
        reportPeriodDAO.save(period);
    }

    /**
     * Modifies an existing report in the service and database
     *
     * @param period the report to be modified
     */
    public void editReport(ReportPeriod period) throws PantsNotFoundException{
        String id = period.getId();
        if (!reportPeriods.containsKey(id)) {
            logger.warn("Trying to edit a report period that does not exist with id " + id);
            throw new PantsNotFoundException("report period " + id + " not found");
        }
        logger.info("Updating report period with id " + id);
        reportPeriods.replace(id, period);
        logger.info("Saving updated report period with id " + id + " in database");
        reportPeriodDAO.save(period);
    }

    /**
     * Removes a report from the service and database
     *
     * @param id the id of the report to be removed
     */
    public void removeReport(String id) throws PantsNotFoundException {
        if (!reportPeriods.containsKey(id)){
            logger.warn("Trying to remove a report period that does not exist with id " + id);
            throw new PantsNotFoundException("report period " + id + " not found");
        }

        logger.info("Removing report period with id " + id);
        reportPeriods.remove(id);
        logger.info("Removing report period with id " + id + " from database");
        reportPeriodDAO.remove(id);
    }

    /**
     * Return a list of nutrition types.
     *
     * @return a list of nutrition types
     */
    public String[] getNutritionTypes() {
        logger.info("Getting nutrition types as strings");
        return EnumUtils.getEnumOptions(NutritionType.class);
    }
}
