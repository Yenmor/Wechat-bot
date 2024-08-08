package cn.yenmor.myWechatRobot;

public class Reply {
    String key;
    String messageType;
    String content;
    String addition;
    boolean isOverflow;
    public Reply(String key,String messageType,String content,String addition,boolean isOverflow){
        this.key = key;
        this.messageType = messageType;
        this.content = content;
        this.addition = addition;
        this.isOverflow = isOverflow;
    }
}
