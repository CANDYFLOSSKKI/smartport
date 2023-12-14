package com.demo.smartport.Util.Handler;

import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.demo.smartport.Entity.ImgUploadReq;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Util.Conf.ImgBedGithubConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@Component
public class ImgUploadHandler {
    @Autowired
    private HttpRequestHandler httpRequestHandler;

    public FlagResp uploadUserImg(MultipartFile file) throws Exception {
        String name=file.getOriginalFilename();
        assert name!=null;
        if(!(name.endsWith("jpg")||name.endsWith("jpeg")||name.endsWith("png"))){
            return FlagResp.OFF().MES("不支持该图片类型");
        }
        HashMap<String,String> header=new HashMap<>();
        header.put("Accept","application/vnd.github+json");
        header.put("Authorization","Bearer "+ImgBedGithubConfig.ACCESS_TOKEN);
        header.put("X-Github-Api-Version","2022-11-28");
        String params=JSON.toJSONString(new ImgUploadReq(ImgBedGithubConfig.ADD_MESSAGE, Base64.encode(file.getBytes())));
        String url= ImgBedGithubConfig.USER_URL()+ ImgParamHandler.getUUID(file.getOriginalFilename());
        String resp= httpRequestHandler.putJson(url.trim(),params,header);
        String html_url=JSONUtil.parseObj(JSONUtil.parseObj(resp).getObj("content")).getObj("html_url").toString();
        return FlagResp.ON().MES(ImgParamHandler.getHtmlURL(html_url));
    }

    public FlagResp uploadLicenseImg(MultipartFile file) throws Exception {
        String name=file.getOriginalFilename();
        assert name!=null;
        if(!(name.endsWith("jpg")||name.endsWith("jpeg")||name.endsWith("png"))){
            return FlagResp.OFF().MES("不支持该图片类型");
        }
        HashMap<String,String> header=new HashMap<>();
        header.put("Accept","application/vnd.github+json");
        header.put("Authorization","Bearer "+ImgBedGithubConfig.ACCESS_TOKEN);
        header.put("X-Github-Api-Version","2022-11-28");
        String params=JSON.toJSONString(new ImgUploadReq(ImgBedGithubConfig.ADD_MESSAGE, Base64.encode(file.getBytes())));
        String url= ImgBedGithubConfig.LICENSE_URL()+ ImgParamHandler.getUUID(file.getOriginalFilename());
        String resp= httpRequestHandler.putJson(url.trim(),params,header);
        String html_url=JSONUtil.parseObj(JSONUtil.parseObj(resp).getObj("content")).getObj("html_url").toString();
        return FlagResp.ON().MES(ImgParamHandler.getHtmlURL(html_url));
    }

    public FlagResp uploadEntrustFile(MultipartFile file) throws Exception {
        String name=file.getOriginalFilename();
        assert name!=null;
        HashMap<String,String> header=new HashMap<>();
        header.put("Accept","application/vnd.github+json");
        header.put("Authorization","Bearer "+ImgBedGithubConfig.ACCESS_TOKEN);
        header.put("X-Github-Api-Version","2022-11-28");
        String params=JSON.toJSONString(new ImgUploadReq(ImgBedGithubConfig.ADD_MESSAGE, Base64.encode(file.getBytes())));
        String url= ImgBedGithubConfig.ENTRUST_URL()+ ImgParamHandler.getUUID(file.getOriginalFilename());
        String resp= httpRequestHandler.putJson(url.trim(),params,header);
        String html_url=JSONUtil.parseObj(JSONUtil.parseObj(resp).getObj("content")).getObj("html_url").toString();
        return FlagResp.ON().MES(ImgParamHandler.getHtmlURL(html_url)+"###"+file.getOriginalFilename());
    }

    public FlagResp uploadOrderFile(MultipartFile file) throws Exception {
        String name=file.getOriginalFilename();
        assert name!=null;
        HashMap<String,String> header=new HashMap<>();
        header.put("Accept","application/vnd.github+json");
        header.put("Authorization","Bearer "+ImgBedGithubConfig.ACCESS_TOKEN);
        header.put("X-Github-Api-Version","2022-11-28");
        String params=JSON.toJSONString(new ImgUploadReq(ImgBedGithubConfig.ADD_MESSAGE, Base64.encode(file.getBytes())));
        String url= ImgBedGithubConfig.ORDER_URL()+ ImgParamHandler.getUUID(file.getOriginalFilename());
        String resp= httpRequestHandler.putJson(url.trim(),params,header);
        String html_url=JSONUtil.parseObj(JSONUtil.parseObj(resp).getObj("content")).getObj("html_url").toString();
        return FlagResp.ON().MES(ImgParamHandler.getHtmlURL(html_url));
    }
}
