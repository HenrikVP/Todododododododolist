package com.example.todododododododolist;


import java.io.Serializable;
import java.time.LocalDateTime;




public class TodoItem implements Serializable {

    public enum Priority {LOW, MEDIUM, HIGH, CRITICAL}
    public enum Status {TODO, DOING, DONE}
    public enum RepeatInterval {FIXED, DAILY, MONTHLY, YEARLY}

    private String name;
    private int point;
    private Priority priority;
    private Status status;
    private LocalDateTime created;
    private LocalDateTime completed;
    private LocalDateTime deadline;
    private boolean isRepeat;
    private RepeatInterval repeatType;
    private int repeatInterval;

    public TodoItem(){}
    public TodoItem(String name, int point, Priority priority, Status status, LocalDateTime created, LocalDateTime completed, LocalDateTime deadline, boolean isRepeat, RepeatInterval repeatType, int repeatInterval) {
        this.name = name;
        this.point = point;
        this.priority = priority;
        this.status = status;
        this.created = created;
        this.completed = completed;
        this.deadline = deadline;
        this.isRepeat = isRepeat;
        this.repeatType = repeatType;
        this.repeatInterval = repeatInterval;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getCompleted() {
        return completed;
    }

    public void setCompleted(LocalDateTime completed) {
        this.completed = completed;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public boolean isRepeat() {
        return isRepeat;
    }

    public void setRepeat(boolean repeat) {
        isRepeat = repeat;
    }

    public RepeatInterval getRepeatType() {
        return repeatType;
    }

    public void setRepeatType(RepeatInterval repeatType) {
        this.repeatType = repeatType;
    }

    public int getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(int repeatInterval) {
        this.repeatInterval = repeatInterval;
    }
}
