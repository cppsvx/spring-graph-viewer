package com.craneos.sgv.integration.parser.builders;

import com.craneos.sgv.integration.model.spring.SpringBaseItem;
import com.craneos.sgv.integration.model.spring.main.Bean;
import com.craneos.sgv.integration.model.spring.main.Beans;
import com.craneos.sgv.integration.model.spring.main.Channel;
import com.craneos.sgv.integration.model.spring.main.Import;
import com.craneos.sgv.integration.model.spring.main.SubscribeChannel;
import com.craneos.sgv.integration.model.spring.subs.Bridge;
import com.craneos.sgv.integration.model.spring.subs.Interceptor;
import com.craneos.sgv.integration.model.spring.subs.WireTap;
import com.craneos.sgv.integration.parser.IntegrationType;

import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SpringItemIntegrationBuilder extends BaseIntegrationBuilder {

    protected static final String NODE_BEAN = "bean";
    protected static final String NODE_BEANS = "beans";
    protected static final String NODE_CHANNEL = "int:channel";
    protected static final String NODE_INTERCEPTORS = "int:interceptors";
    protected static final String NODE_WIRE_TAP = "int:wire-tap";
    protected static final String NODE_SUBSCRIBE_CHANNEL = "int:publish-subscribe-channel";
    protected static final String NODE_INBOUND_CHANNEL_ADAPTER= "int:inbound-channel-adapter";
    protected static final String NODE_IMPORT = "import";
    protected static final String NODE_BRIDGE = "int:bridge";

    public static SpringItemIntegrationBuilder instantiate(Document document){
        SpringItemIntegrationBuilder springItemBuilder = new SpringItemIntegrationBuilder();
        springItemBuilder.init(document);
        return springItemBuilder;
    }

    public SpringBaseItem buildItem(Path file, Node node){
        if (ignore(node)){
            return null;
        } else if (node.getNodeName().contains(NODE_BEANS)){
            return buildBeansStep(file, node);
        } else if (node.getNodeName().contains(NODE_BEAN)){
            return buildBean(file, IntegrationType.BEAN, node);
        } else if (node.getNodeName().contains(NODE_SUBSCRIBE_CHANNEL)){
            return buildSubscribeChannel(file, IntegrationType.SUBSCRIBE_CHANNEL, node);
        } else if (node.getNodeName().contains(NODE_CHANNEL)){
            return buildChannel(file, IntegrationType.CHANNEL, node);
        } else if (node.getNodeName().contains(NODE_IMPORT)){
            return buildImport(file, IntegrationType.IMPORT, node);
        } else if (node.getNodeName().contains(NODE_INBOUND_CHANNEL_ADAPTER)) {
            // TODO URGENTISIMO DE LA MUERTE, DEATH URGENCY --> return buildImport(file, IntegrationType.INBOUND_CHANNEL_ADAPTER, node);
        } else {
            System.out.println("not implemented yet --> "+node.getNodeName());
            return null;
        }
        return null;
    }

    private Import buildImport(Path file, IntegrationType springItem, Node node){
        NamedNodeMap nodeMap = node.getAttributes();
        Node namedItemResource = nodeMap.getNamedItem(ATT_RESOURCE);
        //
        Import simport = new Import(null, file);
        simport.setItem(springItem);
        if (namedItemResource.getNodeValue().contains("classpath:")){
            String nameandpath = namedItemResource.getNodeValue().replace("classpath:","");
            String onlyname = FilenameUtils.getName(nameandpath);
            simport.setResource(onlyname);
            simport.setPathResource(nameandpath);
        } else if (namedItemResource.getNodeValue().contains("classpath*:")){
            String nameandpath = namedItemResource.getNodeValue().replace("classpath*:","");
            String onlyname = FilenameUtils.getName(nameandpath);
            simport.setResource(onlyname);
            simport.setPathResource(nameandpath);
        } else {
            String nameandpath = namedItemResource.getNodeValue();
            String onlyname = FilenameUtils.getName(nameandpath);
            simport.setResource(onlyname);
            simport.setPathResource(nameandpath);
        }
        return simport;
    }

    private Bean buildBean(Path file, IntegrationType springItem, Node parentNode){
        NamedNodeMap nodeMap = parentNode.getAttributes();
        Node namedItemId = nodeMap.getNamedItem(ATT_ID);
        Node namedItemParent = nodeMap.getNamedItem(ATT_PARENT_ID);
        Node namedItemClass = nodeMap.getNamedItem(ATT_CLASS);
        String id = namedItemId==null?null:namedItemId.getNodeValue();
        //
        Bean bean = new Bean(id, file);
        bean.setItem(springItem);
        if (namedItemParent!=null){
            bean.setParent(namedItemParent.getNodeValue());
        }
        if (namedItemClass!=null){
            bean.setFullClass(namedItemClass.getNodeValue());
            bean.setClassName(FilenameUtils.getExtension(namedItemClass.getNodeValue()));
        }
        return bean;
    }

    private SpringBaseItem buildBeansStep(Path file, Node parentNode){
        Node nodeProfile = parentNode.getAttributes().getNamedItem(ATT_PROFILE);
        String id = nodeProfile==null?null:nodeProfile.getNodeValue();
        Beans beans = new Beans(id, file);
        for (int i = 0; i < parentNode.getChildNodes().getLength(); i++) {
            Node node = parentNode.getChildNodes().item(i);
            if (node.getNodeName().equals(NODE_BRIDGE)){
                Bridge bridge = buildBridge(file, node);
                beans.addBridges(bridge);
            }
        }
        return beans;
    }

    private Bridge buildBridge(Path file, Node parentNode){
        Node nodeInputChannel = parentNode.getAttributes().getNamedItem(ATT_INPUT_CHANNEL);
        Node nodeOutputChannel = parentNode.getAttributes().getNamedItem(ATT_OUTPUT_CHANNEL);
        //
        Bridge bridge = new Bridge(null, file);
        bridge.setInputChannel(nodeInputChannel.getNodeValue());
        bridge.setOutputChannel(nodeOutputChannel.getNodeValue());
        return bridge;
    }

    private SubscribeChannel buildSubscribeChannel(Path file, IntegrationType springItem, Node node){
        NamedNodeMap nodeMap = node.getAttributes();
        Node namedItemId = nodeMap.getNamedItem(ATT_ID);
        String id = namedItemId==null?null:namedItemId.getNodeValue();
        //
        SubscribeChannel subscribeChannel = new SubscribeChannel(id, file);
        subscribeChannel.setItem(springItem);
        return subscribeChannel;
    }

    private Channel buildChannel(Path file, IntegrationType springItem, Node parentNode){
        Channel channel = new Channel();
        //
        NamedNodeMap nodeMap = parentNode.getAttributes();
        Node namedItemId = nodeMap.getNamedItem(ATT_ID);
        for (int i = 0; i < parentNode.getChildNodes().getLength(); i++) {
            Node node = parentNode.getChildNodes().item(i);
            if (node.getNodeName().equals(NODE_INTERCEPTORS)){
                Interceptor interceptor = buildInterceptors(file, IntegrationType.INTERCEPTOR, node);
                channel.setInterceptors(interceptor);
            }
        }
        //
        channel.setItem(springItem);
        channel.setFilename(file.getFileName().toString());
        channel.setAbsolutePath(file.toString());
        channel.setId(namedItemId.getNodeValue());
        return channel;
    }

    private Interceptor buildInterceptors(Path file, IntegrationType springItem, Node parentNode){
        List<WireTap> wireTapList = new ArrayList<>();
        //
        for (int i = 0; i < parentNode.getChildNodes().getLength(); i++) {
            Node node = parentNode.getChildNodes().item(i);
            if (node.getNodeName().equals(NODE_WIRE_TAP)){
                WireTap wireTap = buildWireTap(node);
                wireTapList.add(wireTap);
            }
        }
        //
        Interceptor interceptor = new Interceptor();
        interceptor.setWireTapType(wireTapList);
        return interceptor;
    }

    private WireTap buildWireTap(Node parentNode){
        Node channel = parentNode.getAttributes().getNamedItem(ATT_CHANNEL);
        //
        WireTap wireTap = new WireTap();
        wireTap.setChannel(channel.getNodeValue());
        return wireTap;
    }

}
