//package com.cloudwatt.example.entity;
//
//import javax.persistence.*;
//import java.sql.Clob;
//
//@Entity
//@Table(name = "BUILD")
//public class BuildEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private String buildId;
//
//    // A voir le format du message de build ? Blob ?
//    private Clob detail;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getBuildId() {
//        return buildId;
//    }
//
//    public void setBuildId(String buildId) {
//        this.buildId = buildId;
//    }
//
//    public Clob getDetail() {
//        return detail;
//    }
//
//    public void setDetail(Clob detail) {
//        this.detail = detail;
//    }
//}
