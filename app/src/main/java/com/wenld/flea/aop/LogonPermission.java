package com.wenld.flea.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p/>
 * Author: 温利东 on 2017/3/7 17:13.
 * blog: http://blog.csdn.net/sinat_15877283
 * github: https://github.com/LidongWen
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogonPermission {
}
