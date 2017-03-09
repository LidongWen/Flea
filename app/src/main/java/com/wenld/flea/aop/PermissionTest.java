package com.wenld.flea.aop;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.wenld.commontools.LogUtil;
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
        Log.i(TAG, "判断是否登录");
        if (App.getInstance().user != null) {
//            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//            Log.i(TAG, "aroundAspectJ(ProceedingJoinPoint joinPoint)");
//            LogonPermission aspectJAnnotation = methodSignature.getMethod().getAnnotation(LogonPermission.class);
//            String permission = aspectJAnnotation.value();
//            Log.i(TAG, "有权限："+permission);

            return joinPoint.proceed();
        }
//        Toast.makeText(App.getInstance(),""+joinPoint.getThis().toString(),Toast.LENGTH_LONG).show();
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        LogUtil.e(TAG,"joinPoint.getThis(): "+joinPoint.getThis().toString());
        LogUtil.e(TAG,"joinPoint.getTarget(): "+joinPoint.getTarget().toString());
        LogUtil.e(TAG,"joinPoint.getArgs(): "+joinPoint.getArgs().toString());
        LogUtil.e(TAG,"isFragment  "+joinPoint.getTarget().getClass().isInstance(Fragment.class));
//        Intent intent=new Intent(App.getInstance(),LoginActivity.class);
//        App.getInstance().startActivity(new Intent(App.getInstance(),LoginActivity.class));
        Intent intent = new Intent((Context) joinPoint.getThis(), LoginActivity.class);
        ((Context) joinPoint.getThis()).startActivity(intent);
        return "";
    }

}