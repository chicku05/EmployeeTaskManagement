package com.employee.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TaskStatus {
    PENDING("PENDING"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED");

    private final String value;

    TaskStatus(String value) {
        this.value = value;
    }

    @JsonCreator
    public static TaskStatus fromString(String string) {
        for (TaskStatus task : TaskStatus.values()) {
            if (task.value.equalsIgnoreCase(string)) {
                return task;
            }
        }
        throw new IllegalArgumentException("Unknown task value: " + string);
    }
}
