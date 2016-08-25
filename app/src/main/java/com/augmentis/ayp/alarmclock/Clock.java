package com.augmentis.ayp.alarmclock;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Wilailux on 8/24/2016.
 */
public class Clock {

    private UUID id;
    public Date alarmDate;
    public int hours;
    public int minutes;
    public boolean status;

    public Clock(){
        this(UUID.randomUUID());
    }

    public Clock(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getAlarmDate() {
        return alarmDate;
    }

    public void setAlarmDate(Date alarmDate) {
        this.alarmDate = alarmDate;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UUID=").append(id);
        builder.append(",Hours=").append(hours);
        builder.append(",Minutes=").append(minutes);
        builder.append(",Status=").append(status);
        return builder.toString();
    }
}
