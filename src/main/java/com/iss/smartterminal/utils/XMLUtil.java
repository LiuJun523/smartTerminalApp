package com.iss.smartterminal.utils;

import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLUtil{

    /**
     * 加载doc
     *
     * @param filename
     * @return
     */
    public static Document load( String filename ){

        org.w3c.dom.Document document = null;

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            document = builder.parse( new File( filename ) );
            document.normalize();
        }
        catch( Exception ex ){
            LogUtil.logger.info( "loading xml error：" + ex );
        }

        return document;
    }

    /**
     * 装载xml文件
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static Element GetXmlDoc( String filePath ) throws Exception{

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse( filePath );

        return doc.getDocumentElement();
    }

    /**
     * 装载xml文件
     *
     * @param is
     * @return
     * @throws Exception
     */
    public static Element GetXmlDoc( InputStream is ) throws Exception{

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse( is );

        return doc.getDocumentElement();
    }

    /**
     * 获取单个节点
     *
     * @param express
     * @param source
     * @return
     * @throws javax.xml.xpath.XPathExpressionException
     */
    public static Node selectSingleNode( String express, Object source ) throws XPathExpressionException{

        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();

        Node result = ( Node )xpath.evaluate( express, source, XPathConstants.NODE );
        return result;
    }

    /**
     * 获取一组节点
     *
     * @param express
     * @param source
     * @return
     * @throws javax.xml.xpath.XPathExpressionException
     */
    public static NodeList selectNodes( String express, Object source ) throws XPathExpressionException{

        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();

        NodeList result = ( NodeList )xpath.evaluate( express, source, XPathConstants.NODESET );
        return result;
    }

    /**
     * 获取xml的属性节点的值
     *
     * @param node
     * @param key
     * @return
     */
    public static String getNodeAttributeValue( Node node, String key ){

        if( node == null || key == null ) return null;

        NamedNodeMap nm = node.getAttributes();
        if( nm == null ) return null;

        Node nameNode = nm.getNamedItem( key );
        if( nameNode == null ) return null;

        return nameNode.getNodeValue();
    }
}
