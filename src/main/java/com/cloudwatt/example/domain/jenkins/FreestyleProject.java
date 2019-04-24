package com.cloudwatt.example.domain.jenkins;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class FreestyleProject implements Serializable {

    private static final long serialVersionUID= -1l;

    @JsonProperty("_class")
    private String myClass;

    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;

    @JsonProperty("color")
    private String color;

    public FreestyleProject() {
    }
}