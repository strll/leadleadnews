package com.heima.wemedia.test;


import com.heima.common.Qiniuyun.ResponsePojo.Resopnse_Qiniuyun_JsonRootBean;
import com.heima.common.Qiniuyun.util.QiNuYun_util;
import com.heima.common.aliyun.GreenImageScan;
import com.heima.common.aliyun.GreenTextScan;
import com.heima.file.service.FileStorageService;
import com.heima.wemedia.WemediaApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SpringBootTest(classes = WemediaApplication.class)
@RunWith(SpringRunner.class)
public class AliyunTest {

    @Autowired
    private GreenTextScan greenTextScan;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private GreenImageScan greenImageScan;

    @Autowired
    private QiNuYun_util qiNuYun_util;

    /**
     * 测试文本内容审核
     */
    @Test
    public void testScanText() throws Exception {

        Resopnse_Qiniuyun_JsonRootBean helloworld = qiNuYun_util.TextCensor("helloworld");
        String s = helloworld.returnMessage_Mean();
        System.out.println(s);
        System.out.println(helloworld.toString());
        Resopnse_Qiniuyun_JsonRootBean resopnse_qiniuyun_jsonRootBean = qiNuYun_util.ImageCensor("http://43.142.125.197:9000/bot/2023/01/09/67482f88c7db44d98b42ef7365e41abf");
        System.out.println(resopnse_qiniuyun_jsonRootBean.returnMessage_Mean());


    }

    /**
     * 测试图片审核
     */
    @Test
    public void testScanImage() throws Exception {

        byte[] bytes = fileStorageService.downLoadFile("http://192.168.200.130:9000/leadnews/2021/04/26/07caf2be1520457e9fe59f2969eebf65.jpg");

        List<byte []> list = new ArrayList<>();
        list.add(bytes);

        Map map = greenImageScan.imageScan(list);
        System.out.println(map);

    }



    @Test
    public void test() throws Exception {


           URL url = new URL("http://gchat.qpic.cn/gchatpic_new/1290366985/3915150599-2735225766-DE6C9474F27D4423B08EF35021995805/0?term&#61;2&amp;is_origin&#61;0");

        InputStream inputStream = url.openStream();
        FileOutputStream outputStream = new FileOutputStream("1.jpg");
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();

        File file = new File("1.jpg");
        InputStream inputStream1 = new FileInputStream(file);


        String replace = UUID.randomUUID().toString().replace("-", "");
        String originalFilename = "123";
       // String postfix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileId = fileStorageService.uploadImgFile("", "1234",  inputStream1 );
        System.out.println(fileId);
        file.delete();
        inputStream1.close();

    }

}
