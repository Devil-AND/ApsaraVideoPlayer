package com.aliyun.alivcsolution;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import com.alivc.player.AliVcMediaPlayer;
import com.alivc.player.VcPlayerLog;
import com.aliyun.video.common.aliha.AliHaUtils;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Mulberry on 2018/2/24.
 */
public class MutiApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        VcPlayerLog.enableLog();
        initLeakcanary();//初始化内存检测
        //初始化阿⾥云移动高可⽤SDK接⼊——崩溃分析
        AliHaUtils.initHa(this,null);
        //初始化播放器
        AliVcMediaPlayer.init(getApplicationContext());

        ////设置保存密码。此密码如果更换，则之前保存的视频无法播放
        //AliyunDownloadConfig config = new AliyunDownloadConfig();
        //config.setSecretImagePath(Environment.getExternalStorageDirectory().getAbsolutePath()+"/aliyun/encryptedApp.dat");
        ////        config.setDownloadPassword("123456789");
        ////设置保存路径。请确保有SD卡访问权限。
        //config.setDownloadDir(Environment.getExternalStorageDirectory().getAbsolutePath()+"/test_save/");
        ////设置同时下载个数
        //config.setMaxNums(2);

        //AliyunDownloadManager.getInstance(this).setDownloadConfig(config);

    }

    private void initLeakcanary() {
        LeakCanary.install(this);
    }

}
