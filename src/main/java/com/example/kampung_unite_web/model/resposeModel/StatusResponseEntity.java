package com.example.kampung_unite_web.model.resposeModel;

public class StatusResponseEntity<T> {
    Boolean status;
    String message;
    T entity;

    public StatusResponseEntity(Boolean status, String message, T entity) {
        this.status = status;
        this.message = message;
        this.entity = entity;
    }
}
