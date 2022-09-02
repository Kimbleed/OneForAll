package com.example.oneforall.utils;

import android.util.Log;

public class Logger {

    private static boolean logFlag = true;
    private static final int logLevel = Log.VERBOSE;
    private static final String mClassName = "CenDebug";
    private static final String TAG = "Logger_TAG";

    private static class LoggerHolder {
        private static Logger mInstance = new Logger();
    }

    public static Logger getInstance() {
        return LoggerHolder.mInstance;
    }

    /**
     * 获取运行方法的方法名
     *
     * @return
     */
    private String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return null;
        }
        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }
            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            if (st.getClassName().equals(this.getClass().getName())) {
                continue;
            }
            return mClassName + "[线程:" + Thread.currentThread().getName()
                    + " 文件名:" + st.getFileName()
                    + " 行号:" + st.getLineNumber()
                    + " 方法名:" + st.getMethodName() + " ]";
        }
        return null;
    }

    public void a(Object str, Object... strArr) {
        if (logFlag) {
            if (logLevel <= Log.ASSERT) {
                StringBuilder sb = new StringBuilder();
                for (Object obj : strArr) {
                    sb.append(obj.toString());
                    sb.append(" ");

                }
                Log.i(str.toString(), getFunctionName() + sb.toString());
            }
        }
    }

    public void e(Object str, Object... strArr) {
        if (logFlag) {
            if (logLevel <= Log.ERROR) {
                StringBuilder sb = new StringBuilder();
                for (Object obj : strArr) {
                    sb.append(obj.toString());
                    sb.append(" ");

                }
                Log.i(str.toString(), getFunctionName() + sb.toString());
            }
        }
    }

    public void w(Object str, Object... strArr) {
        if (logFlag) {
            if (logLevel <= Log.WARN) {
                StringBuilder sb = new StringBuilder();
                for (Object obj : strArr) {
                    sb.append(obj.toString());
                    sb.append(" ");

                }
                Log.i(str.toString(), getFunctionName() + sb.toString());
            }
        }
    }

    public void i(Object str, Object... strArr) {
        if (logFlag) {
            if (logLevel <= Log.INFO) {
                StringBuilder sb = new StringBuilder();
                for (Object obj : strArr) {
                    sb.append(obj.toString());
                    sb.append(" ");

                }
                Log.i(str.toString(), getFunctionName() + sb.toString());
            }
        }
    }

    public void d(Object str, Object... strArr) {
        if (logFlag) {
            if (logLevel <= Log.INFO) {
                StringBuilder sb = new StringBuilder();
                for (Object obj : strArr) {
                    sb.append(obj.toString());
                    sb.append(" ");

                }
                Log.i(str.toString(), getFunctionName() + sb.toString());
            }
        }
    }

    public void v(Object str, Object... strArr) {
        if (logFlag) {
            if (logLevel <= Log.INFO) {
                StringBuilder sb = new StringBuilder();
                for (Object obj : strArr) {
                    sb.append(obj.toString());
                    sb.append(" ");

                }
                Log.i(str.toString(), getFunctionName() + sb.toString());
            }
        }
    }


}
