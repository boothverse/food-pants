package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.persistence.*;

import java.util.*;

public class NutritionService {
    // Map<id : String, i : NutritionInstance >
    protected Map<String, NutritionInstance> items;
    protected Map<String, Goal> goals;
    protected Map<String, ReportPeriod> reportPeriods;

    public NutritionService() {
        // TODO load from database?
        items = new HashMap<>();
        goals = new HashMap<>();
        reportPeriods = new HashMap<>();
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
        //TODO Write to database
        //FileListDAO<NutritionInstance> dao = new FileListDAO<NutritionInstance>(NutritionInstance.class, "User.json");
    }

    /**
     * Edits pr-existing item in service and database.
     *
     * @param nutritionInstance
     */
    public void editItem(NutritionInstance nutritionInstance) {
        //TODO: Should I make sure the operation is successful?
        items.replace(nutritionInstance.getId(), nutritionInstance);
        //TODO Write to database
    }

    /**
     *
     * @param id
     */
    public void removeItem(String id) { items.remove(id); }

    /**
     *
     * @return
     */
    public Map<String, Goal> getGoals() { return goals; }

    /**
     *
     * @param user
     * @return
     */
    public Goal<?> getRecommendedGoal(User user) {
        return null;
    }

    /**
     *
     * @param goal
     */
    public void addGoal(Goal<?> goal) {
        goals.put(goal.getId(), goal);
    }

    public void editGoal(Goal<?> goal) {
        goals.replace(goal.getId(), goal);
    }

    /**
     *
     * @param id
     */
    public void removeGoal(String id) {
        goals.remove(id);
    }

    /**
     *
     * @param period
     */
    public void addReport(ReportPeriod period) {
        reportPeriods.put(period.getId(), period);
    }

    public void editReport(ReportPeriod period) {
        reportPeriods.replace(period.getId(), period);
    }

    /**
     *
     * @param id
     */
    public void removeReport(String id) {
        reportPeriods.remove(id);
    }
}
