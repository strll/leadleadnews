import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.heima.common.Qiniuyun.RequestPojo.Data;
import com.heima.common.Qiniuyun.RequestPojo.Params;
import com.heima.common.Qiniuyun.RequestPojo.Request_Qiniuyun_JsonRootBean;
import com.heima.common.Qiniuyun.ResponsePojo.Resopnse_Qiniuyun_JsonRootBean;
import com.heima.common.Qiniuyun.util.getSign;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Testqiniuyun {

    @Test
public void test() throws IOException, NoSuchAlgorithmException, InvalidKeyException {
    getSign getSign = new getSign();


    // ���������ַ
    String url = "http://ai.qiniuapi.com/v3/text/censor";
    String s2 = getSign.getToken("helloworld"," jZb5Szh8BQT_0uMF1HfCx2J-lVClrNGMNFjL_-px","A8I9Yhkw0leFvPVY6Yn502ek40sVZ86K7-z-JNog");
    // ��������ͷ
    Map<String, String> headers = new HashMap<>();
    headers.put("Content-Type", "application/json");


    headers.put("Authorization", s2);

    Request_Qiniuyun_JsonRootBean request = new Request_Qiniuyun_JsonRootBean();
    Data data = new Data();
            data.setText("helloworld");
    request.setData(data);
    Params params = new Params();
    List<String> strings = new LinkedList();
    strings.add("antispam");
    params.setScenes(strings);
    request.setParams(params);

    Gson gson = new Gson();
    String s = gson.toJson(request);


    // ���� POST ����
    Connection.Response response = Jsoup.connect(url)
            .headers(headers)
            .method(Connection.Method.POST)
            .requestBody(s)
            .execute();

    // ��ȡ��Ӧ�ĵ�
    Document document = response.parse();

    // ��ȡ��Ӧ����
    String responseBody = document.body().text();

    // ����Ӧ����ת��Ϊ Java ����
    Resopnse_Qiniuyun_JsonRootBean my = JSON.parseObject(responseBody, Resopnse_Qiniuyun_JsonRootBean.class);
    System.out.println(my.toString());



}
}
