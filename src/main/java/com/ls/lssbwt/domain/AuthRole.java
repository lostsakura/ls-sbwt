package com.ls.lssbwt.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lostsakura
 * @create 2020-01-12 15:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("auth_role")
public class AuthRole implements Serializable, Cloneable {
    @TableId(type = IdType.AUTO, value = "id")
    private Long id ;
    /** 角色代码 */
    private String code ;
    /** 角色名称 */
    private String name ;
    /** 角色描述 */
    private String description ;
    /** 角色状态 */
    private String status ;
    /** 创建时间 */
    private Date createTime ;
    /** 更新时间 */
    private Date updateTime ;
}
