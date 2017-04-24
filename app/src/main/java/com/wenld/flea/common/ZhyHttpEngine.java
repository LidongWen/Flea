package com.wenld.flea.common;


import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import com.wenld.baselib.http.HttpUtils;
import com.wenld.baselib.http.builder.FileInput;
import com.wenld.baselib.http.callback.EngineCallBack;
import com.wenld.baselib.http.callback.FileCallBack;
import com.wenld.baselib.http.httpEngine.IHttpEngine;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.request.RequestCall;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * <p/>
 * Author: 温利东 on  2017/3/6 23:20
 * blog: http://blog.csdn.net/sinat_15877283
 * github: https://github.com/LidongWen
 *
 * @Description 基于 张鸿洋 okhttpUtils
 */


public class ZhyHttpEngine implements IHttpEngine {
    private OkHttpUtils instance = OkHttpUtils.getInstance();

    public ZhyHttpEngine(OkHttpClient okHttpClient) {

    }

    public ZhyHttpEngine() {
        this(null);
    }

    @Override
    public void postAPI(String url, Map<String, String> params, final EngineCallBack callback) {
        RequestCall requestCall = instance.post().url(url)
                .params(params)
                .build();
        if (FileCallBack.class.isInstance(callback)) {
            final FileCallBack finalCallBack = (FileCallBack) callback;
            requestCall.execute(getFileCallBack(finalCallBack));
        } else {
            requestCall.execute(getCallback(callback));
        }

    }

    @Override
    public void getAPI(String url, Map<String, String> params, final EngineCallBack callback) {
        RequestCall requestCall = instance.get()
                .url(url)
                .params(params)
                .build();
        if (FileCallBack.class.isInstance(callback)) {
            final FileCallBack finalCallBack = (FileCallBack) callback;
            requestCall.execute(getFileCallBack(finalCallBack));
        } else {
            requestCall.execute(getCallback(callback));
        }

    }

    @Override
    public void postAPI(String url, Map<String, String> params, Map<String, String> headers, final EngineCallBack callback) {
        if (headers == null) {
            headers = new ArrayMap<>();
        }
        if (params == null) {
            params = new ArrayMap<>();
        }
        RequestCall requestCall = instance.post().url(url)
                .headers(headers)
                .params(params)
                .build();
        if (FileCallBack.class.isInstance(callback)) {
            final FileCallBack finalCallBack = (FileCallBack) callback;
            requestCall.execute(getFileCallBack(finalCallBack));
        } else {
            requestCall.execute(getCallback(callback));
        }

    }


    @Override
    public void getAPI(String url, Map<String, String> params, Map<String, String> headers, final EngineCallBack callback) {
        instance.get()
                .url(url)
                .params(params)
                .headers(headers)
                .build()
                .execute(getCallback(callback));
    }

    @Override
    public void uploadAPI(String url, Map<String, String> params, Map<String, String> headers, List<FileInput> files, final EngineCallBack callback) {

        PostFormBuilder builder = instance.post().url(url);
        if (headers != null) {
            builder.headers(headers);
        }
        if (params != null) {
            builder.params(params);
        }
        if (files != null && files.size() > 0) {
            String key = files.get(0).key;
            Map<String, File> filess = new ArrayMap<>();
            for (FileInput fileInput : files) {
                filess.put(fileInput.filename, fileInput.file);
            }
            builder.files(key, filess);
        }
        RequestCall requestCall = builder.build();
        requestCall.execute(getCallback(callback));
    }

    @NonNull
    private com.zhy.http.okhttp.callback.FileCallBack getFileCallBack(final FileCallBack finalCallBack) {
        //自己处理存储 file
        {

        }
        // 使用 zhy 处理存储file
        return new com.zhy.http.okhttp.callback.FileCallBack(finalCallBack.destFileDir, finalCallBack.destFileName) {
//            @Override
//            public File parseNetworkResponse(Response response,final int id) throws Exception {
////                finalCallBack.parseNetworkResponse(response,id);      //这边
//                        final File file = saveFile((Response) response, id);
//        HttpUtils.getInstance().getDelivery().execute(new Runnable() {
//            @Override
//            public void run() {
//                onResponse(file, id);
//            }
//        });
//                return super.parseNetworkResponse(response, id);
//            }

            @Override
            public void onError(Call call, Exception e, int id) {
                finalCallBack.onError(e, id);
            }

            @Override
            public void onResponse(File response, int id) {
                finalCallBack.onResponse(response, id);
            }

            @Override
            public void inProgress(float progress, long total, int id) {
                finalCallBack.inProgress(progress, total, id);
            }
        };
    }

    @NonNull
    private Callback<String> getCallback(final EngineCallBack callback) {
        return new Callback<String>() {
            @Override
            public String parseNetworkResponse(Response response, final int id) throws Exception {
                final Object data;
                try {
                    data = callback.parseNetworkResponse(response, id);
                } catch (final Exception e) {
                    HttpUtils.getInstance().getDelivery().execute(new Runnable() {
                        @Override
                        public void run() {
                            callback.onError(e, id);
                        }
                    });
                    return null;
                }
                HttpUtils.getInstance().getDelivery().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(data, id);
                    }
                });
                return response.body().string();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                callback.onError(e, id);
            }

            @Override
            public void inProgress(float progress, long total, int id) {
                callback.inProgress(progress, total, id);
            }

            @Override
            public void onResponse(String response, int id) {

            }
        };
    }
}