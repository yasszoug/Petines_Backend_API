package com.petines.RESTApi.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tag_id")
    private Integer tid;

     @Column(name="tag")
    private String tag;



    public Tag() {
    }

    public Tag(String tag) {
        this.tag = tag;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
