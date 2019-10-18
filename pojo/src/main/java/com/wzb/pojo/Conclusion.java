package com.wzb.pojo;

public class Conclusion {
    private Integer id;

    private String projectName;

    private String plan;

    private Float priority;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan == null ? null : plan.trim();
    }

    public Float getPriority() {
        return priority;
    }

    public void setPriority(Float priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Conclusion{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", plan='" + plan + '\'' +
                ", priority=" + priority +
                '}';
    }
}