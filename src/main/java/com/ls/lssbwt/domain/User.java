package com.ls.lssbwt.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lostsakura
 * @create 2020-01-07 17:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
public class User implements Serializable {
    @TableId(type = IdType.AUTO, value = "id")
    private Integer id;
    private String username;
    private String password;
    private String perms;
}
