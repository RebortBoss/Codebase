package com.ganxin.codebase.utils.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Html;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * Description : 系统跳转帮助类  <br/>
 * author : WangGanxin <br/>
 * date : 2016/9/5 <br/>
 * email : ganxinvip@163.com <br/>
 */
public class SystemHelper {

    private SystemHelper() {}

    /**
     * 调用系统拔号
     *
     * @param context
     * @param phone
     */
    public static void SystemPhone(Context context, String phone) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phone + ""));
        context.startActivity(intent);
    }

    /**
     * 调用系统短信
     *
     * @param context
     * @param address
     * @param sms_body
     */
    public static void SystemSms(Context context, String address, String sms_body) {
        Intent mIntent = new Intent(Intent.ACTION_VIEW);
        mIntent.putExtra("address", address);   //发送号码
        mIntent.putExtra("sms_body", sms_body);   //短信内容
        mIntent.setType("vnd.android-dir/mms-sms");
        context.startActivity(mIntent);
    }

    /**
     * 调用系统浏览器
     *
     * @param context
     * @param url
     */
    public static void SystemWebView(Context context, String url) {
        Uri u = Uri.parse(url);
        Intent it = new Intent(Intent.ACTION_VIEW, u);
        context.startActivity(it);
    }

    /**
     * 调用系统相机
     *
     * @param activity
     */
    public static void SystemCamera(Activity activity) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyyMMddhhmmss");
        Date date = new Date();
        String file_name = simpleDateFormat.format(date) + ".jpg";

        Uri imageUri = Uri.fromFile(new File(Environment
                .getExternalStorageDirectory(), file_name));
        // 指定照片保存路径（SD卡）
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intent, 1);
    }

    /**
     * 调用系统相册
     *
     * @param activity
     */
    public static void SystemMediaPhoto(Activity activity) {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(i, 1);
    }

    /**
     * 调用系统音乐播放器
     *
     * @param context
     * @param path
     */
    public static void SystemPlayAudio(Context context, String path) {
        Intent it = new Intent(Intent.ACTION_VIEW);
        it.setDataAndType(Uri.parse(path), "audio/mp3");
        context.startActivity(it);
    }

    /**
     * 调用系统视频播放器
     *
     * @param context
     * @param path
     */
    public static void SystemPlayMp4(Context context, String path) {
        Intent it = new Intent(Intent.ACTION_VIEW);
        it.setDataAndType(Uri.parse(path), "video/mp4");
        context.startActivity(it);
    }

    /**
     * 调用系统自带下载
     *
     * @param context
     * @param url
     */
    public void download(Context context, String url) {
        if (url != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri data = Uri.parse(Html.fromHtml(url).toString());
            intent.setData(data);
            intent.setPackage("com.google.android.browser");
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setComponent(new ComponentName("com.android.browser",
                    "com.android.browser.BrowserActivity"));
            context.startActivity(intent);
        }
    }
}
