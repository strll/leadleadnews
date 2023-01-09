import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.util.Auth;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import java.util.HashMap;
import java.util.Map;

public class Test1 {


    //设置好账号的ACCESS_KEY和SECRET_KEY
    private static final String ACCESS_KEY = "jZb5Szh8BQT_0uMF1HfCx2J-lVClrNGMNFjL_-px";
    private static final String SECRET_KEY = "A8I9Yhkw0leFvPVY6Yn502ek40sVZ86K7-z-JNog";
    private final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    private final Client client = new Client();
    @Test
    public void test(){
        Test1 resourcesCensor = new Test1();


        try {
            String s = resourcesCensor.TextCensor();
            String s1 = resourcesCensor.ImageCensor();

            System.out.println(s);
            System.out.println(s1);
        } catch (QiniuException e) {
            e.printStackTrace();
        }

    }

    public String TextCensor() throws QiniuException {
        // 构造post请求body
        Gson gson = new Gson();

        Map<String, Object> uri = new HashMap<>();
        uri.put("text", "asfgWGfSDGFSDf");

        Map<String, Object> scenes = new HashMap<>();
        //pulp 黄  terror 恐  politician 敏感人物
        String[] types = {"antispam"};
        scenes.put("scenes", types);

        Map params = new HashMap();
        params.put("data", uri);
        params.put("params", scenes);

        String paraR = gson.toJson(params);
        byte[] bodyByte = paraR.getBytes();

        // 接口请求地址//http://ai.qiniuapi.com/v3/text/censor
        String url = "http://ai.qiniuapi.com/v3/text/censor";

        return post(url, bodyByte);
    }


    //参考api文档 https://developer.qiniu.com/dora/manual/4252/image-review
    //图片审核
    public String ImageCensor() throws QiniuException {
        // 构造post请求body
        Gson gson = new Gson();

        Map<String, Object> uri = new HashMap<>();
        uri.put("uri", "http://oayjpradp.bkt.clouddn.com/Audrey_Hepburn.jpg");

        Map<String, Object> scenes = new HashMap<>();
        //pulp 黄  terror 恐  politician 敏感人物
        String[] types = {"pulp", "terror", "politician", "ads"};
        scenes.put("scenes", types);

        Map params = new HashMap();
        params.put("data", uri);
        params.put("params", scenes);

        String paraR = gson.toJson(params);
        byte[] bodyByte = paraR.getBytes();

        // 接口请求地址
        String url = "http://ai.qiniuapi.com/v3/image/censor";

        return post(url, bodyByte);
    }

    //参考api文档 https://developer.qiniu.com/dora/manual/4258/video-pulp
    //视频审核
    public String VideoCensor() throws QiniuException {
        // 构造post请求body
        Gson gson = new Gson();

        Map bodyData = new HashMap();

        Map<String, Object> uri = new HashMap<>();
        uri.put("uri", "https://mars-assets.qnssl.com/scene.mp4");

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
        String url = "http://ai.qiniuapi.com/v3/video/censor";
        return post(url, bodyByte);
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