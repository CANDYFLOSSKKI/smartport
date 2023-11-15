package com.demo.smartport.Util.Handler;

import cn.hutool.json.JSONUtil;
import com.demo.smartport.Entity.MapCoordResp;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class MapCoordHandler {
    @Autowired
    private HttpRequestHandler httpRequestHandler;
    private static final String AK="mQDnwQAKwwiouNbV3N43IkufShnksqaO";
    public static MapCoordResp getOriginCoord(String longitude, String latitude){
        return new MapCoordResp(parseCoord(longitude),parseCoord(latitude));
    }

    public static float parseCoord(String str){
        int i=str.indexOf("-");
        int j=str.indexOf(".");
        int a=Integer.parseInt(str.substring(0,i));
        int b=Integer.parseInt(str.substring(i+1,j));
        int c=Integer.parseInt(str.substring(j+1,str.length()-1));
        return a+(float)b/60+(float)c/3600;
    }

    public MapCoordResp getCoordResp(MapCoordResp coord){
        String url="https://api.map.baidu.com/geoconv/v1/?coords="
                +coord.getX()+","+coord.getY()
                +"&from=1&to=5&ak="
                +AK;
        String resp=httpRequestHandler.getString(url);
        MapCoordResp resp_xy=new MapCoordResp();
        Object resp_coord=JSONUtil.parseArray(JSONUtil.parseObj(resp).getObj("result")).get(0);
        resp_xy.setX(Float.parseFloat(JSONUtil.parseObj(resp_coord).getObj("x").toString()));
        resp_xy.setY(Float.parseFloat(JSONUtil.parseObj(resp_coord).getObj("y").toString()));
        return resp_xy;
    }
}
