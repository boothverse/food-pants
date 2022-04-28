package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class NutritionService {
    protected Map<String, NutritionInstance> items;
    protected Map<String, Goal> goals;
    protected Map<String, ReportPeriod> reportPeriods;

    public NutritionService() {

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

    public void removeItem(String id) { items.remove(id); }

    public Map<String, Goal> getGoals() { return goals; }

    public Goal<?> getRecommendedGoal(User user) {
        return null;
    }

    public void addGoal(Goal<?> goal) {

    }

    public void removeGoal(String id) {

    }

    public void addReport(ReportPeriod period) {

    }

    public void removeReport(String id) {

    }

}
