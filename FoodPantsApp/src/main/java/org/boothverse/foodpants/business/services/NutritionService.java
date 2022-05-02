package org.boothverse.foodpants.business.services;

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

public class NutritionService {
    // Map<id : String, i : NutritionInstance >
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
        items = nutritionInstanceDAO.load();
        goals = goalDAO.load();
        reportPeriods = reportPeriodDAO.load();
    }

    /**
     * Returns a list of NutritionInstance objects within the specified time range.
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<NutritionInstance> getItems(Date startDate, Date endDate) {
        List<NutritionInstance> result = new ArrayList<>();
        for(NutritionInstance n: items.values()){
            if(n.getConsumedAt().after(startDate) && n.getConsumedAt().before(endDate) ){
                result.add(n);
            }
        }
        return result;
    }

    /**
     * Adds the item to the service and database.
     *
     * @param nutritionInstance
     */
    public void addItem(NutritionInstance nutritionInstance) {
        items.put(nutritionInstance.getId(), nutritionInstance);
        nutritionInstanceDAO.save(nutritionInstance);
    }

    /**
     * Edits pr-existing item in service and database.
     *
     * @param nutritionInstance
     */
    public void editItem(NutritionInstance nutritionInstance) throws PantsNotFoundException {
        if (!items.containsKey(nutritionInstance.getId())) { throw new PantsNotFoundException("Failed to find specified NutritionInstance for modification."); }
        items.replace(nutritionInstance.getId(), nutritionInstance);
        nutritionInstanceDAO.save(nutritionInstance);
    }

    /**
     * Removes the specified item from the service and database
     *
     * @param id
     */
    public void removeItem(String id) throws PantsNotFoundException {
        if (!items.containsKey(id)) throw new PantsNotFoundException("nutrition instance " + id + " not found");
        items.remove(id);
        nutritionInstanceDAO.remove(id);
    }

    /**
     * Returns the goals map
     *
     * @return
     */
    public List<Goal> getGoals() { return new ArrayList<>(goals.values()); }

    /**
     * Computes the recommended calorie goal based on the Harris-Benedict formula
     *
     * @return
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
            calories = (655.1 + 9.6 * weight + 1.9 * height) / (4.7 * age);
        } else {
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
     * @param goal
     */
    public void addGoal(Goal<?> goal) {
        goals.put(goal.getId(), goal);
        goalDAO.save(goal);
    }

    /**
     * Changes the value of a goal in the service and database
     *
     * @param goal
     */
    public void editGoal(Goal<?> goal) throws PantsNotFoundException {
        String id = goal.getId();
        if (!goals.containsKey(id)) throw new PantsNotFoundException("goal " + id + " not found");
        goals.replace(id, goal);
        goalDAO.save(goal);
    }

    /**
     * Removes a goal from the service and database
     *
     * @param id
     */
    public void removeGoal(String id) throws PantsNotFoundException {
        if (!goals.containsKey(id)) throw new PantsNotFoundException("goal " + id + " not found");
        goals.remove(id);
        goalDAO.remove(id);
    }

    /**
     * Adds a report to the service and database
     *
     * @param period
     */
    public void addReport(ReportPeriod period) {
        reportPeriods.put(period.getId(), period);
        reportPeriodDAO.save(period);
    }

    /**
     * Modifies an existing report in the service and database
     *
     * @param period
     */
    public void editReport(ReportPeriod period) throws PantsNotFoundException{
        String id = period.getId();
        if (!reportPeriods.containsKey(id)) { throw new PantsNotFoundException("report period " + id + " not found"); }
        reportPeriods.replace(id, period);
        reportPeriodDAO.save(period);
    }

    /**
     * Removes a report from the service and database
     *
     * @param id
     */
    public void removeReport(String id) throws PantsNotFoundException {
        if (!reportPeriods.containsKey(id)) throw new PantsNotFoundException("report period " + id + " not found");
        reportPeriods.remove(id);
        reportPeriodDAO.remove(id);
    }

    /**
     * Return a list of nutrition types.
     *
     * @return
     */
    public String[] getNutritionTypes() {
        return EnumUtils.getEnumOptions(NutritionType.class);
    }
}
