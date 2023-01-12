package com.heima.common.Qiniuyun.util;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.heima.common.Qiniuyun.RequestPojo.Data;
import com.heima.common.Qiniuyun.RequestPojo.Params;
import com.heima.common.Qiniuyun.RequestPojo.Request_Qiniuyun_JsonRootBean;
import com.heima.common.Qiniuyun.ResponsePojo.Resopnse_Qiniuyun_JsonRootBean;
import com.heima.common.Qiniuyun.util.getSign;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
@Component
@Getter
@Setter

public class QiNuYun_util {



    //设置好账号的ACCESS_KEY和SECRET_KEY
    @Value("${qiniuyu_common.AccessKey}")
    private  String ACCESS_KEY="jZb5Szh8BQT_0uMF1HfCx2J-lVClrNGMNFjL_-px";
    @Value("${qiniuyu_common.SecretKey}")
    private  String SECRET_KEY="A8I9Yhkw0leFvPVY6Yn502ek40sVZ86K7-z-JNog";
    
    private final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    
    private final Client client = new Client();


    public Resopnse_Qiniuyun_JsonRootBean TextCensor(String text) throws QiniuException {
        // 构造post请求body
        Gson gson = new Gson();

        Map<String, Object> uri = new HashMap<>();
        uri.put("text", text);

        Map<String, Object> scenes = new HashMap<>();
        //pulp 黄  terror 恐  politician 敏感人物
        String[] types = {"antispam"};
        scenes.put("scenes", types);

        Map params = new HashMap();
        params.put("data", uri);
        params.put("params", scenes);

        String paraR = gson.toJson(params);
        byte[] bodyByte = new byte[0];
        bodyByte = paraR.getBytes(StandardCharsets.UTF_8);

        // 接口请求地址//http://ai.qiniuapi.com/v3/text/censor
        String url = "http://ai.qiniuapi.com/v3/text/censor";

        String post = post(url, bodyByte);
        Gson gson1 = new Gson();
        Resopnse_Qiniuyun_JsonRootBean person = gson.fromJson(post, Resopnse_Qiniuyun_JsonRootBean.class);

        return person;
    }


    //参考api文档 https://developer.qiniu.com/dora/manual/4252/image-review
    //图片审核
    public Resopnse_Qiniuyun_JsonRootBean ImageCensor(String url) throws QiniuException {
        // 构造post请求body
        Gson gson = new Gson();

        Map<String, Object> uri = new HashMap<>();
        uri.put("uri", url);

        Map<String, Object> scenes = new HashMap<>();
        //pulp 黄  terror 恐  politician 敏感人物
        String[] types = {"pulp", "terror", "politician", "ads"};
        scenes.put("scenes", types);

        Map params = new HashMap();
        params.put("data", uri);
        params.put("params", scenes);

        String paraR = gson.toJson(params);
        byte[] bodyByte = new byte[0];
        bodyByte = paraR.getBytes(StandardCharsets.UTF_8);

        // 接口请求地址
        String url1 = "http://ai.qiniuapi.com/v3/image/censor";

        String post = post(url1, bodyByte);

        Gson gson1 = new Gson();
        Resopnse_Qiniuyun_JsonRootBean person = gson.fromJson(post, Resopnse_Qiniuyun_JsonRootBean.class);

        return person;
    }

    //参考api文档 https://developer.qiniu.com/dora/manual/4258/video-pulp
    //视频审核
    public Resopnse_Qiniuyun_JsonRootBean VideoCensor(String url) throws QiniuException {
        // 构造post请求body
        Gson gson = new Gson();

        Map bodyData = new HashMap();

        Map<String, Object> uri = new HashMap<>();
        uri.put("uri", url);

        Map<String, Object> params = new HashMap<>();

        Map<String, Object> scenes = new HashMap<>();
        //pulp 黄  terror 恐  politician 敏感人物
        String[] types = {"pulp", "terror", "politician"};

        Map<String, Object> cut_param = new HashMap<>();
        cut_param.put("interval_msecs", 500);

        params.put("scenes", types);
        params.put("cut_param", cut_param);

        bodyData.put("data", uri);
        bodyData.put("params", params);
        String paraR = gson.toJson(bodyData);
        byte[] bodyByte = paraR.getBytes();

        // 接口请求地址
        String url1 = "http://ai.qiniuapi.com/v3/video/censor";
        String post = post(url1, bodyByte);
        Gson gson1 = new Gson();
        Resopnse_Qiniuyun_JsonRootBean person = gson.fromJson(post, Resopnse_Qiniuyun_JsonRootBean.class);

        return  person;
    }

    /**
     * 查询视频审核内容结果
     * 参考
     * https://developer.qiniu.com/censor/api/5620/video-censor#4
     * @param ID : 视频审核返回的 job ID
     *
     */
    public String getVideoCensorResultByJobID(String ID){
        String url = "http://ai.qiniuapi.com/v3/jobs/video/".concat(ID);
        String accessToken = (String) auth.authorizationV2(url).get("Authorization");
        StringMap headers = new StringMap();
        headers.put("Authorization", accessToken);

        try {
            com.qiniu.http.Response resp = client.get(url,headers);
            return resp.bodyString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String post(String url, byte[] body) throws QiniuException {
        com.qiniu.http.Response resp = client.post(url, body, auth.authorizationV2(url, "POST", body, "application/json"), Client.JsonMime);
        return resp.bodyString();
    }



}
