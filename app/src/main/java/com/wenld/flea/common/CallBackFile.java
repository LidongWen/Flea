package com.wenld.flea.common;

import com.wenld.baselib.http.HttpUtils;
import com.wenld.baselib.http.callback.FileCallBack;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Response;

/**
 * <p/>
 * Author: 温利东 on  2017/3/6 23:06
 * blog: http://blog.csdn.net/sinat_15877283
 * github: https://github.com/LidongWen
 *
 * @Description http引擎回调
 */

public abstract class CallBackFile extends FileCallBack {


    public CallBackFile(String destFileDir, String destFileName) {
        super(destFileDir, destFileName);
    }

    @Override
    public File parseNetworkResponse(Object response, final int id) throws Exception {

        final File file = saveFile((Response) response, id);
        HttpUtils.getInstance().getDelivery().execute(new Runnable() {
            @Override
            public void run() {
                onResponse(file, id);
            }
        });
        return file;
    }


    public File saveFile(Response response, final int id) throws IOException {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
            is = response.body().byteStream();
            final long total = response.body().contentLength();

            long sum = 0;

            File dir = new File(destFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, destFileName);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                sum += len;
                fos.write(buf, 0, len);
                final long finalSum = sum;
                HttpUtils.getInstance().getDelivery().execute(new Runnable() {
                    @Override
                    public void run() {

                        inProgress(finalSum * 1.0f / total, total, id);
                    }
                });
            }
            fos.flush();

            return file;

        } finally {
            try {
                response.body().close();
                if (is != null) is.close();
            } catch (IOException e) {
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
            }
        }
    }

}
