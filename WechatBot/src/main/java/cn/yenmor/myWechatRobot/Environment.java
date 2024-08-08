package cn.yenmor.myWechatRobot;

import com.alibaba.fastjson.JSONArray;
//import com.plexpt.chatgpt.Chatbot;

import com.plexpt.chatgpt.entity.chat.Message;
import com.plexpt.chatgpt.listener.ConsoleStreamListener;
import com.plexpt.chatgpt.util.Proxys;
import com.plexpt.chatgpt.*;
import com.plexpt.chatgpt.util.*;
import io.uouo.wechatbot.domain.WechatMsg;

import java.net.Proxy;
import java.util.ArrayList;

public class Environment {

    public static final String commandBegin = "/m\\d";
    public static final String[] REGISTED_GROUPS = {"17753401261@chatroom","44473596994@chatroom"};

    public static JSONArray jsonArray;

    public static  ArrayList<Reply> replies;
    public static boolean isOverflow = false;
    public static ArrayList<WechatMsg> overflowRecorder = new ArrayList<WechatMsg>();
    public static  int overflowLevel = 0;


    public  static  String sessionToken = "eyJhbGciOiJkaXIiLCJlbmMiOiJBMjU2R0NNIn0..U-DWKuY5E2i3iwCA.IL2s3CXT_-Ucrs9ksJN46d906rVAb-F-SLh-ThfNcTZT32u38_vcv0NU1PhrlRtF5DMIaDzRsrNTjcSZk-tLlvRDhFDrlOwmucAtINiJkZZJuJREbeP7GFdqifx9MIatv4MHoDUYuHYeXn1qoLqANKOAbGcODRiJ2pdhWtBeWXZ5ER3dwyDF2LNLrEt8AVstop72E8lgrxOaCkANOC0i2vnfbYC7mNn6eWjjbDruIz_CiBXOHnxvPUiVtxygTYfpHOwIGERyFb5ra2tWPPQeXw9ve4Ju1GesvyYPXKELEWKgAhp4FuLtqJlLuwe8FpprHwSr7v5fEXbRW6hpMCexHqy5KIOPhA6sf3E5S3_79gYrhBnAVSghGSI68D6SFMKdhtkQ6DD8EivQFHoYrwaEcklEEKFvkR5pDtKwSD9XKt0lofKI0oGx3DLSDthTSPWEnUonCceEK05udsyewnzB_BBgTD6StLzly9GI3XZscZ5BKZBC6XTg7bX299cEMyUPDNgZK64olzPzTxshXO1g4MMAhicRUstuyf89wZanCqNI-fMgAG0go22yESkFl88yj5V_nM-n2U2MqGs2WN-7HIG4W-P6ZDMhWxPbOcpAi9ZWHHJLCcPIFBGDvtuQXufhQAc0Ofvyf5OKYG07jIbNXi3gjqoogqwVcSUW55WZRU6TMohQpMLa5lnNO66xPNtKUaSWboxTi8zgunnOAOZ83wzYLJLW-Y30TWPSllEsiyxMs1PlpMDL5QpyAitgBgX2Ltwry-gnyE89asKuv9kcAEAwyR9END_cbkeTZFCVn0JJlrSzLVkIvGNBmhjpZT4hle0JYnyAPDUJHKzts0ff78jGSVM7pF9Gc5EeKV0h-3icT9s_cMrpbkJtMLPiadCmj64Xun4StpJhBireXhL2v8mVgA2dQUmJ8ITTOp4z95DT4QEspULfKzSqmdACLreIv0f7hIjElO8taEMOJEi_Emr6qLLRurBOcZyKfTjYRFrpCB6cE2IMJitmI4BdPA-DVwgwka3xzPh5fdFAAGLRN1WdZkg3JgsIJgXBqDTZzjUkaHHeyTviH3yBjHfQWk2aI1lHtH1rgdC1pGQgmXT9Fi43ub5NBoUZu27MdebcSKIbYCmQwpv8XAB8PVlhdZFqQ2eaK5aH1kUHo85lksZHXvho0hwobVJQ0esBT56ustc4dgYv9w4jOrWtPvztIx_t_tINqbV3VGdmaDBhnUyOpxP1pLjSCsyv2a1kc0WZ35HR8BSj-XxlZrC0vqIeJz585CjhbIKFnQIZ6pXCalpPY_4l5Hp2PMC3YEvh25pfGwPZxYlqKWzZJTNsTx2FZ3-mJASOk7dcTBrjYTfAfA3IaHcRyIhpKpIMsAUwm14W72zHR3Dv_kaBMjiQZVmybP4KUYJ_W6fcqTIpNrahg1pFZZEc5W6a02Rv4KojnqbZMIggIQnBsIujJn267TffLR-f8ErvlWx40psNUCrO20dClsYPDOODpLGFwSUINPakZy0X_p1sUzdFy9nq7l1Qt5digdPieRF_rvY9ntMP75ybgds4CaaMCx9WiagzmIEIP_4NmHjx1eCAgDw_rGY0c3ZgM7s3dNEjQWZSLd-l8HExCQa6QC1uf1Dq4caqdOh6jyz_av52F3OCkBuM8EXBs3oIa2aCxHAfeueY-r6GfUL6u_8ecHF5b-y5qWCAFSEMT5d3K9uf-fVXOM-UbzRPNXY6feYtMiF_qystmZ40Vb6tTTz-oeqv3JwclFN37pBKRe5NODXrRzu23H5WPY0mQ9PW5StcMwn2cRd4f8unE57SAbyH8idW8zdDz91zt5gvMltKZxfkb3dAtP3d7j8lSghXJt-BpeTlPAPpvWIrDM3ysatqt6LADIQNYYS0FErD0Mqxo4fWiNriesIvUdaXz0aE1MmDNuLHnPb4lIcqP4EWHYXrtpbQDypS5O9Z6NvQ8mIslO-e2Ye2plGFtB8Gf11jN-7vZY5qD4oxrk07I5upRXO6B6fcGZfl3jLepv3-4tZG_sst9WbS6vFZCuM5gg9TZVAc_goC2HKh2bH7ksPfESOVt-QElqmHqiEqeLcH5iiCxQZqC6UCutFkgPtk1QVkQJ8g8JDoCMenJIRPv1VjwFPR0x-_U9N3UM-__JOodUTWS3G4o7UyCV9qhvAL1myk_03DQ9N0RYh3gfngjbFatsTWA0MPLdg0D5Gl1S438wJmoTcMh4ZXAR90d63ns0iz_r7EclBSmWRpUfOrbRR9N1OPKNM3xwFoGnvPFNK5l6naX0XMFw0utow26CUfCQFY5dAOEiRN1nQqwqhon-IKF2D_7rafUhpC3g.eriXSBNYWKstqngN92Cs8g";
    public static String cf_clearance = "3JnyuK6DjKHldmQG3FsVbQJ4gnXi9B7h8r4siLnb8Yc-1671104555-0-1-5febb43b.2ca69f86.8eaebdb-160";
    public static String ua = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36 Edg/108.0.1462.46";


    //public static Chatbot chatbot;
    public static Proxy proxy = Proxys.http("127.0.0.1", 7890);
    public static ChatGPT chatbot = ChatGPT.builder()

            .apiKey("sess-u5IjjSNjXJBeh6yLNwE6q3FW7z0npwQHKrQyrjyw")
            .proxy(proxy)
            .timeout(900)
            //.apiHost("https://api.openai.com/") //反向代理地址
            .build()
            .init();



}
