import com.heima.common.Qiniuyun.ResponsePojo.Resopnse_Qiniuyun_JsonRootBean;
import com.heima.common.Qiniuyun.util.QiNuYun_util;
import com.qiniu.common.QiniuException;
import org.junit.Test;

public class Test2 {
    @Test
    public void test() throws QiniuException {
        QiNuYun_util qiNuYun_util = new QiNuYun_util();
        Resopnse_Qiniuyun_JsonRootBean helloworld = qiNuYun_util.TextCensor("helloworld");
        System.out.println(helloworld.toString());
        Resopnse_Qiniuyun_JsonRootBean resopnse_qiniuyun_jsonRootBean =
                qiNuYun_util.ImageCensor("http://gchat.qpic.cn/gchatpic_new/3591066892/0-0-146D472A86B57CD3785FB7602ECF1722/0?term=2");
        System.out.println(resopnse_qiniuyun_jsonRootBean.returnMessage());
    }
}
