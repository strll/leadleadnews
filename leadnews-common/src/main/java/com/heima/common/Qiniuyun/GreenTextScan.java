package com.heima.common.Qiniuyun;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "qiniuyun")
public class GreenTextScan {
    private String  SecretKey;
    private String AccessKey;

}
