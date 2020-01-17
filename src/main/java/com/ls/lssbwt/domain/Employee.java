package com.ls.lssbwt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lostsakura
 * @create 2020-01-06 14:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private Integer gender;
    private Integer age;
}
