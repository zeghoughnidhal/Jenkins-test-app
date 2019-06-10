package com.cloudwatt.example.domain.jenkins;

public class HudsonNode extends HudsonCore {

    // add specific attributes
    private String env;
    private String folderName;
    private String viewName;

    public HudsonNode() {
        super();
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
}
