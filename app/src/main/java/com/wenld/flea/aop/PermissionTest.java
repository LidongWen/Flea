package com.wenld.flea.aop;

/**
 * <p/>
 * Author: 温利东 on 2017/3/2 16:07.
 * blog: http://blog.csdn.net/sinat_15877283
 * github: https://github.com/LidongWen
 */
//@Aspect
//public class PermissionTest {
//    private static final String TAG = "tag00";
//
//    @Pointcut("execution(@com.wenld.flea.aop.Permission  * *(..))")
//    public void executionAspectJ() {
//
//    }
//
//    @Around("executionAspectJ()")
//    public Object aroundAspectJ(ProceedingJoinPoint joinPoint) throws Throwable {
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        Log.i(TAG, "aroundAspectJ(ProceedingJoinPoint joinPoint)");
//        AspectJAnnotation aspectJAnnotation = methodSignature.getMethod().getAnnotation(AspectJAnnotation.class);
//        String permission = aspectJAnnotation.value();
//         Log.i(TAG, "有权限："+permission);
//        return "ds";
//    }
//
//}