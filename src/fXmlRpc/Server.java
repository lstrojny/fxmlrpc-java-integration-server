package fXmlRpc;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    public static void main(String[] args) throws Exception {

        Integer port = args.length > 0 ? Integer.parseInt(args[0]) : 8080;
        Integer errorPort = args.length > 1 ? Integer.parseInt(args[1]) : 8081;

        WebServer webServer = new WebServer(port);

        XmlRpcServer server = webServer.getXmlRpcServer();

        PropertyHandlerMapping mapping = new PropertyHandlerMapping();
        mapping.addHandler("system", SystemHandler.class);
        server.setHandlerMapping(mapping);

        XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) server.getConfig();
        serverConfig.setEnabledForExtensions(true);
        serverConfig.setContentLengthOptional(false);
        serverConfig.setKeepAliveEnabled(true);

        webServer.start();

        HttpServer errorServer = HttpServer.create(new InetSocketAddress(errorPort), 1);
        errorServer.createContext("/", new ErrorHandler());
        errorServer.setExecutor(null);
        errorServer.start();
    }
}

class ErrorHandler implements HttpHandler
{
    public void handle(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(500, 0);
        exchange.close();
    }
}
