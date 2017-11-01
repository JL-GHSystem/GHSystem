package com.clinet.frame;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class MyXMLReader2DOM {
	public static void main(String arge[]) {

		long lasting = System.currentTimeMillis();

		try {
			File f = new File("E:\\eclipse\\workspace\\GHClinet\\src\\com\\clinet\\frame\\Test.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(f);
			NodeList nl = doc.getElementsByTagName("VALUE");
			for (int i = 0; i < nl.getLength(); i++) {
				System.out.print("���ƺ���:" + doc.getElementsByTagName("NO").item(i).getFirstChild().getNodeValue());
				System.out.println("������ַ:" + doc.getElementsByTagName("ADDR").item(i).getFirstChild().getNodeValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}