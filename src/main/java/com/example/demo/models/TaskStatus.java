package com.example.demo.models;

/**
 * 任务状态枚举
 */
public enum TaskStatus {
    PENDING("pending", "待处理"),
    IN_PROGRESS("in_progress", "进行中"),
    COMPLETED("completed", "已完成");

    private final String code;
    private final String description;

    TaskStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static TaskStatus fromCode(String code) {
        for (TaskStatus status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid task status: " + code);
    }
}
