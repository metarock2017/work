package misson2;

import jdk.internal.org.xml.sax.InputSource;
import jdk.internal.org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class TestMeetingInterface {
    private String getXmlInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        sb.append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
        sb.append("<soap:Body>");
        sb.append("<GetUserPostTypes xmlns=\"http://172.20.2.52:84/\" />");
        sb.append("</soap:Body>\n");
        sb.append("</soap:Envelope>");
        return sb.toString();
    }



    public static Map<String, String> parseXml(HttpServletRequest request) throws IOException {
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
//            Document document = builder.parse(
//                    new InputSource(new StringReader(b.toString()))
//            );
            Document document = builder.parse(
                    new org.xml.sax.InputSource(new StringReader(b.toString()))
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
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (org.xml.sax.SAXException e) {
            e.printStackTrace();
        }
        return result;
    }
}
