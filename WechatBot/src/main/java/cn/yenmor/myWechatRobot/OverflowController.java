package cn.yenmor.myWechatRobot;

import io.uouo.wechatbot.domain.WechatMsg;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class OverflowController implements Runnable{
    public  void  run(){
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ArrayList<WechatMsg> msgs = Environment.overflowRecorder;
            if(determineEqal(msgs,6)){
                Environment.isOverflow=true;
                Environment.overflowLevel=3;

            }else
            if(determineEqal(msgs,5)){
                Environment.isOverflow=true;
                Environment.overflowLevel=2;
            }else
            if(determineEqal(msgs,4)){
                Environment.isOverflow=true;
                Environment.overflowLevel=1;
            }else {
                Environment.isOverflow=false;
                Environment.overflowLevel=0;
            }
        }

    }
    public boolean determineEqal(ArrayList<WechatMsg> msgs,int count){
        //int c = 0;
        int i = msgs.size()-1;
       if(count<msgs.size()){
           for(int b = 0;b<count-1;b++){
               if(!Objects.equals(msgs.get(i - b).getContent(), msgs.get(i - b - 1).getContent())){
                   return false;
               }
           }
           return true;
       }else {
           return false;
       }
    }

}
