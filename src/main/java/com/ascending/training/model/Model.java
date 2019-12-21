/*
 *  Copyright 2019, Liwei Wang <daveywang@live.com>.
 *  All rights reserved.
 *  Author: Liwei Wang
 *  Date: 06/2019
 */

package com.ascending.training.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;

@MappedSuperclass
public abstract class Model {
    @Id
    //@SequenceGenerator(name = "department_id_generator", sequenceName = "department_id_seq", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_id_generator")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        String str = null;

        try {
            str = objectMapper.writeValueAsString(this);
        }
        catch(JsonProcessingException jpe) {
            jpe.printStackTrace();
        }

        return str;
    }
}