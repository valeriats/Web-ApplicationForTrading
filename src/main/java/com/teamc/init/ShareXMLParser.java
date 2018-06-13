package com.teamc.init;

import com.teamc.jms.TransactionProducer;
import com.teamc.model.Share;
import com.teamc.service.ShareService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.net.*;

@Configuration
@NoArgsConstructor
@Slf4j
public class ShareXMLParser {

    private ShareService shareService;

    private TransactionProducer transactionProducer;

    private static final String SECID = "SECID";
    private static final String PREVPRICE = "PREVPRICE";
    private static final String SECNAME = "SECNAME";
    private static final String PATHROW = "/document/data/rows/row";
    private static final String URLCB = "https://iss.moex.com/iss/engines/stock/markets/shares/securities";



    @Autowired
    public ShareXMLParser(ShareService shareService, TransactionProducer transactionProducer) {
        this.shareService = shareService;
        this.transactionProducer = transactionProducer;
    }

    private Document getDocument() throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(ConnectionAndDocument.getConnection(new URL(URLCB)).getInputStream());
        doc.getDocumentElement().normalize();
        return doc;
    }

    @Scheduled(fixedRate = 300000)
    public void makeNewShareDB() throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();
        NodeList nodeList = (NodeList) xpath.evaluate(PATHROW, getDocument(), XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            if (!element.getAttribute(PREVPRICE).equals("")) {
                Share share = new Share(element.getAttribute(SECID), element.getAttribute(SECNAME), 1000.0, Double.parseDouble(element.getAttribute(PREVPRICE)));
                shareService.update(element.getAttribute(SECID), share);
                //transactionProducer.send(share);
            }
        }
        log.info("Произведено обновление списка стоимости акций");
    }
}
