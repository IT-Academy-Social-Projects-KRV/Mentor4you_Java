package com.mentor4you.model.DTO.coopDTO;

public class DTOstatusCoopMentee {
    private DTOforCopUser mentor;

    private CoopStatus coopStatus;

    public DTOstatusCoopMentee() {
    }

    public DTOstatusCoopMentee(DTOforCopUser mentor, CoopStatus coopStatus) {
        this.mentor = mentor;
        this.coopStatus = coopStatus;
    }

    public DTOforCopUser getMentor() {
        return mentor;
    }

    public void setMentor(DTOforCopUser mentor) {
        this.mentor = mentor;
    }

    public CoopStatus getCoopStatus() {
        return coopStatus;
    }

    public void setCoopStatus(CoopStatus coopStatus) {
        this.coopStatus = coopStatus;
    }
}
