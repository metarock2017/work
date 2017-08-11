package org.redrock.util;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
//import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

//import javax.swing.text.Document;
//import javax.swing.text.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
//import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//public class Test {
//    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException{
////        String url = "";
////        String xml = CurlUtill.getContent(url, null, "GET");
////        //把str 转化为Document对象
////        StringReader reader = new StringReader(xml);
////        InputSource source = new InputSource(reader);
//        File file = new File("score.xml");
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        //Document document = builder.parse(source);
//        Document document = builder.parse(file);
//        Element element = document.getDocumentElement();
//        NodeList list = element.getChildNodes();
//        for (int i = 0; i<list.getLength(); i++) {
//            Node node = list.item(i);
//            String name = node.getNodeName();
//            if (name.equals("data")) {
//                NodeList items = node.getChildNodes();
//                List<Map<String, String>> data = new ArrayList<>();
//                for (int j = 0; j < items.getLength(); j++) {
//                    Node item = items.item(j);
//                    NodeList itemList = item.getChildNodes();
//                    Map<String, String> attrs = new HashMap<>();
//                    //List<Integer> mylist = new ArrayList<>();
//                    for (int k = 0; k < itemList.getLength(); k++) {
//                        Node attr = itemList.item(k);
//                        String attrName = attr.getNodeName();
//                        String attrValue = attr.getTextContent();
//                        attrs.put(attrName, attrValue);
////                        if (attrName.equals("成绩")) {
////                            try {
////                                Integer a = Interger.parseInt(attrValue);
////                            } catch (NumberFormateException e) {
////                                e.printStackTrace();
////                            }
////                            mylist.add(a);
////                        }
//                    }
//                    data.add(attrs);
//                }
//                int grades = 0;
//                for (Map<String, String> d : data) {
//                    int score = Integer.parseInt(d.get("成绩"));
//                    grades = grades + score;
//
//                }
//
//                //Integer sum = 0;
////                for (int i=0; i<mylist.size(); i++) {
////                    sum += mylist.get(i).InValue();
////                }
////                if ((Integer avg = sum/mylist.size()) > 80) {
////                    System.out.println("he is a good student,his average grade is " + avg);
////                }
//                System.out.println(grades/data.size());
//                System.out.println(data.toString());
//            }
//        }
//
////        if (grades/data.size() >= 80) {
////            System.out.println("he is a good student,his average grade is ");
////        } else {
////            System.out.println("he is not a good student,his average grade is ");
////        }
//
//    }
//}
//
public class Test {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException{
//        String url = "";
//        String xml = CurlUtill.getContent(url, null, "GET");
//        //把str 转化为Document对象
//        StringReader reader = new StringReader(xml);
//        InputSource source = new InputSource(reader);
        File file = new File("score.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        //Document document = builder.parse(source);
        Document document = builder.parse(file);
        Element element = document.getDocumentElement();
        NodeList list = element.getChildNodes();
        for (int i = 0; i<list.getLength(); i++) {
            Node node = list.item(i);
            String name = node.getNodeName();
            if (name.equals("data")) {
                NodeList items = node.getChildNodes();
                List<Map<String, String>> data = new ArrayList<>();
                for (int j = 0; j < items.getLength(); j++) {
                    Node item = items.item(j);
                    NodeList itemList = item.getChildNodes();
                    Map<String, String> attrs = new HashMap<>();
                    //List<Integer> mylist = new ArrayList<>();
                    for (int k = 0; k < itemList.getLength(); k++) {
                        Node attr = itemList.item(k);
                        String attrName = attr.getNodeName();
                        String attrValue = attr.getTextContent();
                        attrs.put(attrName, attrValue);
//                        if (attrName.equals("成绩")) {
//                            try {
//                                Integer a = Interger.parseInt(attrValue);
//                            } catch (NumberFormateException e) {
//                                e.printStackTrace();
//                            }
//                            mylist.add(a);
//                        }
                    }
                    data.add(attrs);
                }
                int grades = 0;
                for (Map<String, String> d : data) {
                    System.out.println(d.get("成绩"));
                    int score = Integer.parseInt(d.get("成绩"));
                    grades = grades + score;

                }
//        if (grades/data.size() >= 80) {
//            System.out.println("he is a good student,his average grade is ");
//        } else {
//            System.out.println("he is not a good student,his average grade is ");
//        }
                System.out.println((float)grades/data.size());
                System.out.println(data.toString());
                //Integer sum = 0;
//                for (int i=0; i<mylist.size(); i++) {
//                    sum += mylist.get(i).InValue();
//                }
//                if ((Integer avg = sum/mylist.size()) > 80) {
//                    System.out.println("he is a good student,his average grade is " + avg);
//                }

            }
        }

    }
}
