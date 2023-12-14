package com.demo.smartport.Util.Conf;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ImgBedGithubConfig {
    public static final String ACCESS_TOKEN = "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";
    public static final String OWNER = "CANDYFLOSSKKI";
    public static final String REPO_NAME = "smartport_photo";
    public static final String ADD_MESSAGE = "add img";
    public static final String PATH=" https://api.github.com/repos";

    public static String USER_URL(){
        return PATH +
                "/" + OWNER +
                "/" + REPO_NAME +
                "/contents/img/user/";
    }

    public static String LICENSE_URL(){
        return PATH+
                "/" + OWNER +
                "/" + REPO_NAME +
                "/contents/img/license/";
    }

    public static String ENTRUST_URL(){
        return PATH+
                "/" + OWNER +
                "/" + REPO_NAME +
                "/contents/img/entrust/";
    }

    public static String ORDER_URL(){
        return PATH+
                "/" + OWNER +
                "/" + REPO_NAME +
                "/contents/img/order/";
    }

}
