package cn.yenmor.myWechatRobot;
import com.alibaba.fastjson.JSON;
import io.uouo.wechatbot.client.WechatBotClient;
import io.uouo.wechatbot.domain.WechatMsg;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;


public class MyClient extends WechatBotClient{
    public MyClient(String url) throws URISyntaxException {
        super(url);
    }
    @Override
    public void onMessage(String s){
        System.out.println("微信中收到了消息:" + s);
        new MessageHelper(s,this);
        System.out.println(JSON.parseObject(s).getString("id1"));
        if(JSON.parseObject(s).getString("id1")!=null) {
            WechatMsg msg = new WechatMsg();
            msg.setWxid(JSON.parseObject(s).getString("id1"));
            msg.setContent(JSON.parseObject(s).getString("content"));
            Environment.overflowRecorder.add(msg);
            System.out.println("添加了一个有效信息进入overflowRCD");

        }
    }
}
