package com.wzb.pojo;

public class ProjectInformation {
    private Integer id;

    private String projectName;

    private Integer layer;

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

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    @Override
    public String toString() {
        return "ProjectInformation{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", layer=" + layer +
                '}';
    }
}