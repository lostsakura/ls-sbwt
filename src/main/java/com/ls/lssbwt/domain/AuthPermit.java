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
 * @create 2020-01-12 15:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("auth_permit")
public class AuthPermit implements Serializable, Cloneable {
    @TableId(type = IdType.AUTO, value = "id")
    private Long id ;
    /** 权限代码 */
    private String code ;
    /** 权限名称 */
    private String name ;
    /** 权限标识 */
    private String perms ;
    /** 权限类型 */
    private Integer type ;
    /** 放行资源 */
    private String url ;
    /** 父节点 */
    private Long pid ;
    /** 权限描述 */
    private String description ;
    /** 权限状态 */
    private String status ;
    /** 创建时间 */
    private Date createTime ;
    /** 更新时间 */
    private Date updateTime ;

}
