package com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities;

import com.cardio.physician.domain.fitness.model.WeightModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class WeightLogs {

    @SerializedName("weight")
    @Expose
    private List<Weight> weight = new ArrayList<Weight>();

    /**
     * @return The weight
     */
    public List<Weight> getWeight() {
        return weight;
    }

    /**
     * @param weight The weight
     */
    public void setWeight(List<Weight> weight) {
        this.weight = weight;
    }

    @NotNull
    public List<WeightModel> toModel() {
        return toWeightModelList();
    }

    private List<WeightModel> toWeightModelList(){
        List<WeightModel>list=new ArrayList<>();
        for (Weight weight1 : weight) {
            list.add(weight1.toWeightModel());
        }
        return list;
    }
}
