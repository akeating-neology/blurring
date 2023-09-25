/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.neology.tolling.dev.mantis;

import java.util.Arrays;

/**
 *
 * @author pkumar
 */
public class MantisResponseData {
    
    private String status_code;
    
    private String status_message;
    
    private String image_name;
    
    private Float[] scores;
    
    private String[] tags;
    
    private MantisResponseDimension windshield;

    private MantisResponseDimension car;

    public MantisResponseData() {
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public Float[] getScores() {
        return scores;
    }

    public void setScores(Float[] scores) {
        this.scores = scores;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public MantisResponseDimension getWindshield() {
        return windshield;
    }

    public void setWindshield(MantisResponseDimension windshield) {
        this.windshield = windshield;
    }

    public MantisResponseDimension getCar() {
        return windshield;
    }

    public void setCar(MantisResponseDimension car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "MantisResponseData{" + "status_code=" + status_code + ", status_message=" + status_message + ", image_name=" + image_name + ", scores=" + Arrays.toString(scores) + ", tags=" + Arrays.toString(tags) + ", windshield=" + windshield + ", car=" + car + '}';
    }
    
}
