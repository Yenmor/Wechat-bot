package cn.yenmor.myWechatRobot;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.uouo.wechatbot.domain.WechatMsg;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.*;
//import com.github.plexpt.chatgpt.*;

public class RobotTalkingCore {
    //JSONArray array;
    JSONObject message;
    String content;
    ArrayList<Reply> replies;

    RobotTalkingCore() {
        replies = Environment.replies;


    }


    public Reply GetReplyFromMessage(String s){
        message = JSONObject.parseObject(s);
        content = message.getString("content");
        ArrayList<Reply> pickedReplies = new ArrayList<Reply>();
        for (Reply reply:replies) {
           if(Pattern.matches(reply.key,content)){
               pickedReplies.add(reply);
           }
        }
        if(pickedReplies.isEmpty()){
            return new Reply(null,null,null,null,false);
        }
        return ChooseReply(pickedReplies);

    }
    private Reply ChooseReply(ArrayList<Reply> replies){
        ArrayList<Reply> rpls = new ArrayList<Reply>();
        for(Reply rpl:replies){
            //System.out.println(rpl.isOverflow);
            if(rpl.isOverflow==Environment.isOverflow){
                rpls.add(rpl);
            }

        }
        //System.out.println((int)Math.random()*rpls.size());
        if(!Environment.isOverflow) {
            return rpls.get((int) (Math.random() * rpls.size()));
        }else {
            return rpls.get(Environment.overflowLevel-1);
        }


   }
}
