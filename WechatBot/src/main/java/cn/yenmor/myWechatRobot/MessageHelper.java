package cn.yenmor.myWechatRobot;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.uouo.wechatbot.domain.WechatMsg;
import io.uouo.wechatbot.service.WechatBotService;
import io.uouo.wechatbot.service.impl.WechatBotServiceImpl;

import java.util.Map;
import java.util.Objects;
//import com.github.plexpt.chatgpt.*;
import com.plexpt.chatgpt.entity.chat.Message;
import com.plexpt.chatgpt.listener.ConsoleStreamListener;
import com.plexpt.chatgpt.util.Proxys;
import com.plexpt.chatgpt.*;
import com.plexpt.chatgpt.util.*;
import java.util.regex.Pattern;

public class MessageHelper {
    WechatBotServiceImpl service = new WechatBotServiceImpl();
    //Chatbot chatbot = new Chatbot(Environment.sessionToken);

    public MessageHelper(String s,MyClient client){
        service.wechatBotClient = client;

        for(String ss : Environment.REGISTED_GROUPS){
            if(s.contains(ss)){
                //System.out.println(s);
                JSONObject jsonObject = JSONObject.parseObject(s);
                //TestSendMessage(jsonObject);
                String msg = jsonObject.getString("content");
                Reply r = new RobotTalkingCore().GetReplyFromMessage(s);
                SendMessageFromReply(r,ss,msg);

            }
        }
    }
   // public JSONObject Message2Json(String s){
   //     JSONArray jsonArray = JSONArray.parseArray(s);


  //  }
    public void SendMessageFromReply(Reply reply,String wxid,String message){
        if(reply.addition!=null) {
            WechatMsg additionText = new WechatMsg();
            additionText.setContent(reply.addition);
            additionText.setWxid(wxid);
            service.sendTextMsg(additionText);
        }
        if(Objects.equals(reply.messageType, "Image")){
            WechatMsg msg = new WechatMsg();
            msg.setWxid(wxid);
            msg.setPath(reply.content);
            msg.setContent(reply.content);
            service.sendImgMsg(msg);
        }
        if(Objects.equals(reply.messageType, "Text")){
            WechatMsg msg = new WechatMsg();
            msg.setContent(reply.content);
            msg.setWxid(wxid);
            service.sendTextMsg(msg);
        }
        if(Objects.equals(reply.messageType, "function")){
            if(Objects.equals(reply.content, "gpt")){
                WechatMsg msg = new WechatMsg();
                System.out.println("收到一个GPT请求");

                msg.setContent(GetMessageFromGPT(message,wxid ));
                msg.setWxid(wxid);
                service.sendTextMsg(msg);
            }else {
                WechatMsg msg = new WechatMsg();
                msg.setContent("未完成的功能");
                msg.setWxid(wxid);
                service.sendTextMsg(msg);
            }
        }

    }
    private String GetMessageFromGPT(String s,String wxid){
        WechatMsg msg = new WechatMsg();
        msg.setContent("已经收到请求,稍等片刻");
        msg.setWxid(wxid);
        service.sendTextMsg(msg);
        String str = s.replaceFirst("/gpt","");
        String chatResponse = Environment.chatbot.chat(str);
        System.out.println(chatResponse);
        return chatResponse;

    }

    public void TestSendMessage(JSONObject message){
        WechatMsg msg = new WechatMsg();
        msg.setRoomid(message.getString("id2"));
        msg.setWxid(message.getString("id1"));
        msg.setContent("内容为:"+message.getString("content"));
        msg.setNickname("这里是nickNAME");
        service.sendATMsg(msg);
       // service.getWeChatUserList();
        System.out.println(message.getString("id1"));
        System.out.println(message.getString("id2"));
        System.out.println(message.getString("content"));
    }


}
