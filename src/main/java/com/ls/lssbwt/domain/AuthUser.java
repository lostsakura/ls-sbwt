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
 * @create 2020-01-12 15:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("auth_user")
public class AuthUser implements Serializable, Cloneable {
    @TableId(type = IdType.AUTO, value = "id")
    private Long id ;
    /** 用户代码 */
    private String code ;
    /** 用户名 */
    private String name ;
    /** 密码 */
    private String password ;
    /** 密码盐值 */
    private String salt ;
    /** 性别 */
    private Integer gender ;
    /** 出生日期 */
    private Date birthday ;
    /** 电子邮件 */
    private String email ;
    /** 手机号 */
    private String phone ;
    /** 创建时间 */
    private Date createTime ;
    /** 创建IP */
    private String createIp ;
    /** 更新时间 */
    private Date updatedTime ;
    /** 最近登陆IP */
    private String lastLoginIp ;
    /** 最近登陆时间 */
    private Date lastLoginTime ;
    /** 用户状态 */
    private Integer status ;
}
