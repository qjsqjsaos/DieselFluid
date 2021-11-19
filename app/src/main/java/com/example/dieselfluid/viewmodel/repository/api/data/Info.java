package com.example.dieselfluid.viewmodel.repository.api.data;

public class Info {
    private String description;

    private String title;

    private String version;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ClassPojo [description = " + description + ", title = " + title + ", version = " + version + "]";
    }
}