package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.business.dao.GoalDAO;
import org.boothverse.foodpants.business.dao.ListDAO;
import org.boothverse.foodpants.business.dao.NutritionInstanceDAO;
import org.boothverse.foodpants.business.dao.ReportDAO;
import org.boothverse.foodpants.business.services.exceptions.PantsNotFoundException;
import org.boothverse.foodpants.persistence.*;

import java.util.*;

public class NutritionService {
    // Map<id : String, i : NutritionInstance >
    protected Map<String, NutritionInstance> items;
    protected Map<String, Goal> goals;
    protected Map<String, ReportPeriod> reportPeriods;
    protected final ListDAO<NutritionInstance> nutritionInstanceDAO = new NutritionInstanceDAO();
    protected final ListDAO<ReportPeriod> reportPeriodDAO = new ReportDAO();
    protected final ListDAO<Goal> goalDAO = new GoalDAO();

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
        if (items.containsKey(nutritionInstance.getId())) { throw new PantsNotFoundException("Failed to find specified NutritionInstance for modification."); }
        items.replace(nutritionInstance.getId(), nutritionInstance);
        nutritionInstanceDAO.save(nutritionInstance);
    }

    /**
     * Removes the specified item from the service and database
     *
     * @param id
     */
    public void removeItem(String id) {
        items.remove(id);
        nutritionInstanceDAO.remove(id);
    }

    /**
     * Returns the goals map
     *
     * @return
     */
    public List<Goal> getGoals() { return new ArrayList<>(goals.values()); }

    //TODO
    /**
     *
     * @param user
     * @return
     */
    public Goal<?> getRecommendedGoal(User user) {
        return null;
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
        if (!goals.containsKey(goal.getId())) { throw new PantsNotFoundException("Failed to find specified NutritionInstance for modification."); }
        goals.replace(goal.getId(), goal);
        goalDAO.save(goal);
    }

    /**
     * Removes a goal from the service and database
     *
     * @param id
     */
    public void removeGoal(String id) {
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
        if (!reportPeriods.containsKey(period.getId())) { throw new PantsNotFoundException("Failed to find specified NutritionInstance for modification."); }
        reportPeriods.replace(period.getId(), period);
        reportPeriodDAO.save(period);
    }

    /**
     * Removes a report from the service and database
     *
     * @param id
     */
    public void removeReport(String id) {
        reportPeriods.remove(id);
        reportPeriodDAO.remove(id);
    }
}
