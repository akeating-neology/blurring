/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.neology.tolling.dev.mantis;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pkumar
 */
public class MantisRequest {
    
    List<MantisRequestDetails> request_data = new ArrayList();

    public MantisRequest() {
    }

    public MantisRequest(List<MantisRequestDetails> request_data) {
        this.request_data = request_data;
    }

    public List<MantisRequestDetails> getRequest_data() {
        return request_data;
    }

    public void setRequest_data(List<MantisRequestDetails> request_data) {
        this.request_data = request_data;
    }
    
    public void addDetails(MantisRequestDetails details) {
        request_data.add(details);
    }

    @Override
    public String toString() {
        return "MantisRequest{" + "request_data=" + request_data + '}';
    }
    
}
