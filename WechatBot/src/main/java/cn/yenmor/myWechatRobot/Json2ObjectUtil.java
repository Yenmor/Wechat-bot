package cn.yenmor.myWechatRobot;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

public class Json2ObjectUtil {

    public static ArrayList<Reply> getRelies(JSONObject object){
        ArrayList<Reply> replies = new ArrayList<Reply>();
        int i = 1;
        String key = "key"+Integer.toString(i);
        while (object.containsKey(key)){
            System.out.println(key);

            JSONObject question = object.getJSONObject(key);
            int a = 1;
            String replyKey = "reply"+Integer.toString(a);
            while (question.containsKey(replyKey)){
                //System.out.println(replyKey);

                JSONObject reply = question.getJSONObject(replyKey);
                replies.add(new Reply(
                        question.getString("keyword"),
                        reply.getString("messageType"),
                        reply.getString("content"),
                        reply.getString("addition"),
                        false
                ));
                //System.out.println("添加了一个普通reply");
                a++;
                replyKey = "reply"+Integer.toString(a);
            }
            int b = 1;
            String overflowKey = "Overflow"+Integer.toString(b);
            while (question.containsKey(overflowKey)){

                JSONObject reply = question.getJSONObject(overflowKey);
                replies.add(new Reply(
                        question.getString("keyword"),
                        reply.getString("messageType"),
                        reply.getString("content"),
                        reply.getString("addition"),
                        true
                ));
                System.out.println("添加了一个overflow");
                b++;
                overflowKey = "Overflow"+Integer.toString(b);
            }

            i++;
            key = "key"+Integer.toString(i);

        }
        //System.out.println(replies);
        return replies;
    }

}
