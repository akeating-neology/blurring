/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.neology.tolling.dev.mantis;

import java.util.List;

/**
 *
 * @author pkumar
 */
public class MantisResponse {
    
    private int http_status_code;
    
    private String status_message;
    
    private List<MantisResponseData> data;

    public MantisResponse() {
    }

    public int getHttp_status_code() {
        return http_status_code;
    }

    public void setHttp_status_code(int http_status_code) {
        this.http_status_code = http_status_code;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public List<MantisResponseData> getData() {
        return data;
    }

    public void setData(List<MantisResponseData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MantisResponse{" + "http_status_code=" + http_status_code + ", status_message=" + status_message + ", data=" + data + '}';
    }
    
}
