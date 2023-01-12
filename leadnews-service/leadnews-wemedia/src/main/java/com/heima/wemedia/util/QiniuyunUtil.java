package com.heima.wemedia.util;

import com.heima.common.Qiniuyun.ResponsePojo.Resopnse_Qiniuyun_JsonRootBean;
import com.heima.common.Qiniuyun.util.QiNuYun_util;
import com.qiniu.common.QiniuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QiniuyunUtil {
    @Autowired
    private QiNuYun_util qiNuYun_util;
    public Resopnse_Qiniuyun_JsonRootBean Text(String text) throws QiniuException {
        Resopnse_Qiniuyun_JsonRootBean resopnse_qiniuyun_jsonRootBean = qiNuYun_util.TextCensor(text);
        return resopnse_qiniuyun_jsonRootBean;
    }

    public Resopnse_Qiniuyun_JsonRootBean Image(String text) throws QiniuException {
        Resopnse_Qiniuyun_JsonRootBean resopnse_qiniuyun_jsonRootBean = qiNuYun_util.ImageCensor(text);
        return resopnse_qiniuyun_jsonRootBean;
    }

}
