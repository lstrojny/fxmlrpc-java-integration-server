package fXmlRpc;

import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

public class Server {

    public static void main(String[] args) throws Exception {

        Integer port = args.length > 0 ? Integer.parseInt(args[0]) : 8080;

        WebServer webServer = new WebServer(port);

        XmlRpcServer server = webServer.getXmlRpcServer();

        PropertyHandlerMapping mapping = new PropertyHandlerMapping();
        mapping.addHandler("system", SystemHandler.class);
        server.setHandlerMapping(mapping);

        XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) server.getConfig();
        serverConfig.setEnabledForExtensions(true);
        serverConfig.setContentLengthOptional(false);

        webServer.start();
    }
}