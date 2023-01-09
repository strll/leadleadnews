package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.WmNewsDto;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.model.wemedia.pojos.WmNews;

public interface WmNewsService extends IService<WmNews> {
    //��ѯ�����б�
public ResponseResult findList(WmNewsPageReqDto wmNewsPageReqDto);

//�������»��߱���Ϊ�ݸ�
public ResponseResult submitNews(WmNewsDto dto);

}
