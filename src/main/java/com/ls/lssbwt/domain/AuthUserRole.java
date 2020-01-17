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
 * @create 2020-01-12 15:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("auth_user_role")
public class AuthUserRole implements Serializable, Cloneable {
    @TableId(type = IdType.AUTO, value = "id")
    private Long id ;
    /** 用户代码 */
    private String userCode ;
    /** 角色代码 */
    private String roleCode ;
}
