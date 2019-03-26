package bn.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import bn.vo.RssVO;

public class RssParser {
	
	private static RssParser rp;
	
	private RssParser() {
		
	}
	
	public static RssParser getInstance() {
		
		if(rp == null) {
			rp = new RssParser();
		}
		
		return rp;
	}
	
	public List<RssVO> newsFlashXMLParsing() throws MalformedURLException, JDOMException, IOException {
		List<RssVO> list = new ArrayList<RssVO>();
		
		// 1. Parser ����
		SAXBuilder sb = new SAXBuilder();
		
		// 2. XML�� ���� ��ü�� ����
		Document doc = sb.build(new URL("http://fs.jtbc.joins.com//RSS/newsflash.xml"));
		
		// 3. ������ü���� �ֻ��� �θ� ��� ���
		Element rssNode = doc.getRootElement();
		
		// 4. �θ� ���κ��� �ڽ� ��� ���
		Element channelNode = rssNode.getChild("channel");
		
		// �ڽ� ��� ��ȸ, �ʿ��� ��� ã�Ƽ� �� �� VO�� ���� �� ����Ʈ�� �߰�..
		Iterator<Element> channelSubNodes = channelNode.getChildren().iterator();
		
		Element channelSubNode = null;
		Element itemSubNode = null;
		while(channelSubNodes.hasNext()) {
			
			channelSubNode = channelSubNodes.next();
			
			if ("item".equals(channelSubNode.getName())) {
				
				Iterator<Element> itemSubNodes = channelSubNode.getChildren().iterator();
				
				RssVO rvo = new RssVO();
				while(itemSubNodes.hasNext()) {
					
					itemSubNode = itemSubNodes.next();
					
					if ("title".equals(itemSubNode.getName())) {
						rvo.setTitle(itemSubNode.getText());
					}
					if ("link".equals(itemSubNode.getName())) {
						rvo.setLink(itemSubNode.getText());
					}
					if ("description".equals(itemSubNode.getName())) {
						
						if(itemSubNode.getText().length() > 100) {
							// 100�ڰ� ���� �� ... ó��
							rvo.setDescription(itemSubNode.getText().substring(1, 100)+"...");
						} else {
							rvo.setDescription(itemSubNode.getText());
						}
						
					}
					if ("pubDate".equals(itemSubNode.getName())) {
						rvo.setPubDate(itemSubNode.getText());
					}
				}
				list.add(rvo);
			}
		}
		
		return list;
	}
}
