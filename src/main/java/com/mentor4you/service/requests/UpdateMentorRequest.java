package com.mentor4you.service.requests;



public class UpdateMentorRequest {

    private String description;
    private boolean showable_status;
    private boolean is_online;
    private boolean is_offline_in;
    private boolean is_offline_out;


    public UpdateMentorRequest(String description,
                               boolean showable_status,
                               boolean is_online,
                               boolean is_offline_in,
                               boolean is_offline_out) {
        this.description = description;
        this.showable_status = showable_status;
        this.is_online = is_online;
        this.is_offline_in = is_offline_in;
        this.is_offline_out = is_offline_out;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isShowable_status() {
        return showable_status;
    }

    public void setShowable_status(boolean showable_status) {
        this.showable_status = showable_status;
    }

    public boolean isIs_online() {
        return is_online;
    }

    public void setIs_online(boolean is_online) {
        this.is_online = is_online;
    }

    public boolean isIs_offline_in() {
        return is_offline_in;
    }

    public void setIs_offline_in(boolean is_offline_in) {
        this.is_offline_in = is_offline_in;
    }

    public boolean isIs_offline_out() {
        return is_offline_out;
    }

    public void setIs_offline_out(boolean is_offline_out) {
        this.is_offline_out = is_offline_out;
    }
}
