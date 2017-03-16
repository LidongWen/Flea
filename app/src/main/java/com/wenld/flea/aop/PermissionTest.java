package com.wenld.flea.aop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.wenld.flea.App;
import com.wenld.flea.ui.LoginActivity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * <p/>
 * Author: 温利东 on 2017/3/2 16:07.
 * blog: http://blog.csdn.net/sinat_15877283
 * github: https://github.com/LidongWen
 */
@Aspect
public class PermissionTest {
    private static final String TAG = "tag00";

    @Pointcut("execution(@com.wenld.flea.aop.LogonPermission  * *(..))")
    public void executionAspectJ() {

    }

    @Around("executionAspectJ()")
    public Object aroundAspectJ(ProceedingJoinPoint joinPoint) throws Throwable {
        // TODO: 2017/3/9  后面要改成 !=
        if (App.getInstance().user == null) {
            return joinPoint.proceed();
        }
        if (Fragment.class.isInstance(joinPoint.getTarget())) {
            startLoginActivity(((Fragment) joinPoint.getTarget()).getContext());
            return "";
        }
        if (Activity.class.isInstance(joinPoint.getTarget())) {
            startLoginActivity(((Activity) joinPoint.getTarget()).getBaseContext());
            return "";
        }
        if (AppCompatActivity.class.isInstance(joinPoint.getTarget())) {
            startLoginActivity(((AppCompatActivity) joinPoint.getTarget()).getBaseContext());
            return "";
        }
        return "";
    }

    private void startLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

}