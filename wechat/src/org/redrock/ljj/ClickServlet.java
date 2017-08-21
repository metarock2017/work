package org.redrock.ljj;
import org.redrock.messageCl.messageCl;
import org.redrock.util.Const;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "ClickServlet", value = "/")
public class ClickServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        Map<String, String> requestMap = ClickServlet.parseXml(request);

        messageCl mc = new messageCl();
        mc.Message(requestMap);
        String respContent = mc.getRespContent();
        String xml = "<xml>" +
                "<ToUserName><![CDATA[%s]]></ToUserName>" +
                "<FromUserName><![CDATA[%s]]></FromUserName>" +
                "<CreateTime>%s</CreateTime>" +
                "<MsgType><![CDATA[%s]]></MsgType>" +
                "<Content><![CDATA[%s]]></Content>" +
                "</xml>";
        String fromUserName = requestMap.get("ToUserName");
        String toUserName = requestMap.get("FromUserName");
        String createTime = System.currentTimeMillis() / 1000 + "";
        String res = String.format(xml, toUserName, fromUserName, createTime, "text", respContent);
        response.getWriter().println(res);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    public static void sedMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        String respContent = "你的请求不在服务范围内";
        Map<String, String> requestMap = ClickServlet.parseXml(request);
        String Content = requestMap.get("Content");
        ClickServlet.Redrock(request, response, requestMap, Content);
        String xml = "<xml>" +
                "<ToUserName><![CDATA[%s]]></ToUserName>" +
                "<FromUserName><![CDATA[%s]]></FromUserName>" +
                "<CreateTime>%s</CreateTime>" +
                "<MsgType><![CDATA[%s]]></MsgType>" +
                "<Content><![CDATA[%s]]></Content>" +
                "</xml>";
        String fromUserName = requestMap.get("ToUserName");
        String toUserName = requestMap.get("FromUserName");
        String createTime = System.currentTimeMillis() / 1000 + "";
        String msgType = requestMap.get("MsgType");

        if (msgType.equals(Const.REQ_MESSAGE_TYPE_TEXT)) {
            respContent = "您发送的是文本消息！";
        }
        else if (msgType.equals(Const.REQ_MESSAGE_TYPE_IMAGE)) {
            respContent = "您发送的是图片消息！";
        }
        else if (msgType.equals(Const.REQ_REQ_MESSAGE_TYPE_LOCATION)) {
            respContent = "您发送的是地理位置消息！";
        }
        else if (msgType.equals(Const.REQ_MESSAGE_TYPE_LINK)) {
            respContent = "您发送的是链接消息！";
        }
        else if (msgType.equals(Const.REQ_MESSAGE_TYPE_VOICE)) {
            respContent = "您发送的是音频消息！";
        }
        else if (msgType.equals(Const.REQ_MESSAGE_TYPE_EVENT)) {
            String eventType = requestMap.get("Event");
            if (eventType.equals(Const.EVENT_TYPE_SUBSCRIBE)) {
                respContent = "谢谢您的关注！";
            }
            else if (eventType.equals(Const.EVENT_TYPE_UNSUBSCRIBE)) {
                //
            }
            else if (eventType.equals(Const.EVENT_TYPE_CLICK)) {
                System.out.println("事件");
                String eventKey = requestMap.get("EventKey");
                if (eventKey.equals("1")) {
                    respContent = "正在创建谁是卧底，请输入游戏人数（4-13之间，不包括法官哦）";
                } else if (eventKey.equals("2")) {
                    respContent = "杀人游戏被点击";
                } else if (eventKey.equals("3")) {
                    respContent = "狼人游戏被点击";
                }
            }
        }
        String res = String.format(xml, toUserName, fromUserName, createTime, "text", respContent);
        response.getWriter().println(res);
    }

    public static  void Redrock(HttpServletRequest request, HttpServletResponse response, Map<String, String> requestMap, String Content) throws IOException{

        String fromUserName = requestMap.get("ToUserName");
        String toUserName = requestMap.get("FromUserName");
        String createTime = System.currentTimeMillis() / 1000 + "";
        String msgType = "news";
        String msgType1 = requestMap.get("MsgType");
        String xml = "<xml>" +
                "<ToUserName><![CDATA[%s]]></ToUserName>" +
                "<FromUserName><![CDATA[%s]]></FromUserName>" +
                "<CreateTime>%s</CreateTime>" +
                "<MsgType><![CDATA[%s]]></MsgType>" +
                "<ArticleCount>%s</ArticleCount>" +
                "<Articles>" +
                "<item>" +
                "<Title><![CDATA[%s]]></Title>" +
                "<Description><![CDATA[%s]]></Description>" +
                "<PicUrl><![CDATA[%s]]></PicUrl>" +
                "<Url><![CDATA[%s]]></Url>" +
                "</item>" +
                "</xml>";
        String articalCount = "1";
        if (Content.equals("Redrock")) {

            String title = "Redrock";
            String description = "这是重庆邮电大学最牛逼的学生组织没有之一";
            String picurl = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1241031823,2247922563&fm=26&gp=0.jpg";
            String url = "http://hongyan.cqupt.edu.cn/";
            String res = String.format(xml, toUserName, fromUserName, createTime, msgType, articalCount, title, description, picurl, url);
            response.getWriter().println(res);

        }
        if(msgType1.equals(Const.REQ_MESSAGE_TYPE_EVENT)) {
            String eventType = requestMap.get("Event");
            if(eventType.equals(Const.EVENT_TYPE_SUBSCRIBE)){

            String title = "谢谢您的关注！";
            String description = "谢谢您的关注！";
            String picurl = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2570900197,294100678&fm=26&gp=0.jpg";
            String url = "http://www.iqiyi.com/v_19rr700mq0.html?vfm=2008_aldbd";
            String res = String.format(xml, toUserName, fromUserName, createTime, msgType, articalCount, title, description, picurl, url);
            response.getWriter().println(res);
            }
        }
    }

    public static  void guanzhu(HttpServletRequest request, HttpServletResponse response, Map<String, String> requestMap, String Content) throws IOException{
        String fromUserName = requestMap.get("ToUserName");
        String toUserName = requestMap.get("FromUserName");
        String createTime = System.currentTimeMillis() / 1000 + "";
        String msgType = "news";
        String msgType1 = requestMap.get("MsgType");
        String xml = "<xml>" +
                "<ToUserName><![CDATA[%s]]></ToUserName>" +
                "<FromUserName><![CDATA[%s]]></FromUserName>" +
                "<CreateTime>%s</CreateTime>" +
                "<MsgType><![CDATA[%s]]></MsgType>" +
                "<ArticleCount>%s</ArticleCount>" +
                "<Articles>" +
                "<item>" +
                "<Title><![CDATA[%s]]></Title>" +
                "<Description><![CDATA[%s]]></Description>" +
                "<PicUrl><![CDATA[%s]]></PicUrl>" +
                "<Url><![CDATA[%s]]></Url>" +
                "</item>" +
                "</xml>";
        String articalCount = "1";
                String title = "谢谢您的关注！";
                String description = "谢谢您的关注！";
                String picurl = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2570900197,294100678&fm=26&gp=0.jpg";
                String url = "http://www.iqiyi.com/v_19rr700mq0.html?vfm=2008_aldbd";
                String res = String.format(xml, toUserName, fromUserName, createTime, msgType, articalCount, title, description, picurl, url);
                response.getWriter().println(res);
            }



    public static Map<String, String> parseXml(HttpServletRequest request) throws IOException{
        Map<String, String> result = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            request.getInputStream(), "UTF-8"
                    )
            );
            StringBuilder b = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                b.append(line);
            }
            reader.close();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(
                    new InputSource(new StringReader(b.toString()))
            );

            //获取根节点
            Element rootNode = document.getDocumentElement();
            //根节点名
            String name = rootNode.getNodeName();
            //获取子节点数组
            NodeList items = rootNode.getChildNodes();
            ///////////////////
            //子节点遍历
            for (int i=0; i<items.getLength(); i++) {
                Node item = items.item(i);
                String iName = item.getNodeName();
                //<ToUserName><![CDATA[gh_b6a171776f25]></ToUserName>
                //注意ToUserName标签内部的文本内容实际上也是一个节点， 这里不能通过getNodeValue直接获取节点内容
                String value = item.getTextContent();
                if (iName.equals("#text")) {
                    continue;
                }
                result.put(iName, value);
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return result;
    }


}
