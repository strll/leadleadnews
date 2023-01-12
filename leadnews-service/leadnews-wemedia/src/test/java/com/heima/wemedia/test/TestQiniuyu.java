package com.heima.wemedia.test;

import com.heima.common.Qiniuyun.ResponsePojo.Resopnse_Qiniuyun_JsonRootBean;
import com.heima.common.Qiniuyun.util.QiNuYun_util;
import com.heima.wemedia.WemediaApplication;
import com.heima.wemedia.util.QiniuyunUtil;
import com.qiniu.common.QiniuException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = WemediaApplication.class)
@RunWith(SpringRunner.class)
public class TestQiniuyu {
    @Autowired
    private QiNuYun_util qiNuYun_util;
    @Test
    public void test() throws QiniuException {
        Resopnse_Qiniuyun_JsonRootBean a = qiNuYun_util.TextCensor("请在这里输入正文");
        System.out.println(a);
    }
    @Autowired
    private QiniuyunUtil   q;
    @Test
    public void test1() throws QiniuException {
        Resopnse_Qiniuyun_JsonRootBean text = q.Text("2134-56712345请在这里输入正文");
        System.out.println(text.toString());
    }

}
