package org.boothverse.foodpants.business.services;

import org.boothverse.foodpants.persistence.*;

import java.util.List;
import java.util.Map;

public class NutritionService {
    protected Map<String, NutritionInstance> items;
    protected Map<String, Goal> goals;
    protected Map<String, ReportPeriod> reportPeriods;

    public NutritionService() {

    }

    public List<NutritionInstance> getItems() {
        return null;
    }

    public void addItem(NutritionInstance nutritionInstance) {

    }

    public void editItem(NutritionInstance nutritionInstance) {

    }

    public void removeItem(String id) {

    }

    public List<Goal> getGoals() {
        return null;
    }

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
