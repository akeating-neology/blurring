/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.neology.tolling.dev.mantis;

/**
 *
 * @author pkumar
 */
public class MantisRequestDetails {

    private String image_name;

    private String image_data;

    public MantisRequestDetails() {
    }

    public MantisRequestDetails(String image_name, String image_data) {
        this.image_name = image_name;
        this.image_data = image_data;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getImage_data() {
        return image_data;
    }

    public void setImage_data(String image_data) {
        this.image_data = image_data;
    }

    @Override
    public String toString() {
        return "MantisRequestDetails{" + "image_name=" + image_name + '}';
    }
    
}
