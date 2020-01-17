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
 * @create 2020-01-12 16:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("auth_role_permit")
public class AuthRolePermit implements Serializable, Cloneable {
    @TableId(type = IdType.AUTO, value = "id")
    private Long id ;
    /** 角色代码 */
    private String roleCode ;
    /** 权限代码 */
    private String permitCode ;
}
