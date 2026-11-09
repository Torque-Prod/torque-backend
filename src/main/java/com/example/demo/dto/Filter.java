package com.example.demo.dto;


import lombok.Data;

import java.io.Serializable;

import static com.example.demo.utills.TableConstant.*;
import static com.example.demo.utills.CommonConstants.*;


@Data
public class Filter<T> implements Serializable {
    private T value;
    private String matchMode;

    public Object getFormattedValue() {
        if (null == value || value.equals(EMPTY_STRING)) {
            return null;
        } else if (STARTS_WITH.equalsIgnoreCase(matchMode)) {
            return value + SQL_PERCENTAGE_MARK;
        } else if (CONTAINS.equalsIgnoreCase(matchMode)) {
            return SQL_PERCENTAGE_MARK + value + SQL_PERCENTAGE_MARK;
        } else if (NOT_CONTAINS.equalsIgnoreCase(matchMode)) {
            return SQL_PERCENTAGE_MARK + value +SQL_PERCENTAGE_MARK;
        } else if (ENDS_WITH.equals(matchMode)) {
            return SQL_PERCENTAGE_MARK + value;
        } else {
            return this.value;
        }
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    public String getMatchMode() {
        return matchMode;
    }

    public void setMatchMode(String matchMode) {
        this.matchMode = matchMode;
    }
}
