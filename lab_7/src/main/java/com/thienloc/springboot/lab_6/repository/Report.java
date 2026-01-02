package com.thienloc.springboot.lab_6.repository;

import java.io.Serializable;

public interface Report {
    Serializable getGroup();
    Double getSum();
    Long getCount();
}
