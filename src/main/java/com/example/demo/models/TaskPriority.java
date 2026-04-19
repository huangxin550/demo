package com.example.demo.models;

/**
 * 任务优先级枚举
 */
public enum TaskPriority {
    LOW("low", "低"),
    MEDIUM("medium", "中"),
    HIGH("high", "高");

    private final String code;
    private final String description;

    TaskPriority(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static TaskPriority fromCode(String code) {
        for (TaskPriority priority : values()) {
            if (priority.getCode().equals(code)) {
                return priority;
            }
        }
        throw new IllegalArgumentException("Invalid task priority: " + code);
    }
}
