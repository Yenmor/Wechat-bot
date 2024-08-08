package cn.yenmor.myWechatRobot;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONWriter;

//import com.github.plexpt.chatgpt.Chatbot;
import io.uouo.wechatbot.client.WechatBotClient;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.nio.file.Files;

public class TestRobot {
    public  static  void  main(String[] args) throws URISyntaxException, FileNotFoundException, InterruptedException {

        String s = FileTools.readJsonFile("C:\\yenmor\\Robot\\document.json");
        JSONObject object = JSON.parseObject(s);
        Environment.replies = Json2ObjectUtil.getRelies(object);
        System.err.println(Environment.sessionToken);
        Thread.sleep(100);
        //Environment.chatbot  = new Chatbot(Environment.sessionToken,Environment.cf_clearance,Environment.ua);
        MyClient client = new MyClient("ws://localhost:5555");
        client.connect();
        Runnable ovflctl = new OverflowController();
        new Thread(ovflctl).start();

    }

}
