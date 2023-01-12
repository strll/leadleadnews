package com.heima.wemedia.test;

import com.heima.common.Qiniuyun.ResponsePojo.Resopnse_Qiniuyun_JsonRootBean;
import com.heima.common.Qiniuyun.util.QiNuYun_util;
import com.heima.wemedia.WemediaApplication;
import com.qiniu.common.QiniuException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = WemediaApplication.class)
@RunWith(SpringRunner.class)
public class T1 {

    @Autowired
    private QiNuYun_util qiNuYun_util;

    @Test
    public void t1() throws QiniuException {
        Resopnse_Qiniuyun_JsonRootBean a = qiNuYun_util.TextCensor("2134567-12345请在这里输入正文");
        System.out.println(a);
        Resopnse_Qiniuyun_JsonRootBean resopnse_qiniuyun_jsonRootBean = qiNuYun_util.ImageCensor("http://43.142.125.197:9000/leadnews/2022/12/22/fa62bea8b45741ada92854c52b405b5c.png");
        System.out.println(resopnse_qiniuyun_jsonRootBean.toString());
    }


}
