import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;

import java.text.SimpleDateFormat;
import java.util.Date;

    public class Coap_Server {
        public static void main(String[] args){
            CoapServer server = new CoapServer();//主机为localhost,默认端口为5683

            //创建资源"hello"   请求格式为 主机：端口/hello
            server.add(new CoapResource("hello"){
                @Override
                public void handleGET(CoapExchange exchange){    //重写处理GET请求的方法
                    //System.out.println("hello -- "+exchange.toString());
                    exchange.respond(CoAP.ResponseCode.CONTENT,"hello Coap !!");
                }
            });

            //创建资源"Link"
            server.add(new CoapResource("Link",true){
                @Override
                public void handleGET(CoapExchange exchange){  //重写处理GET请求的方法
                    Date date = new Date();
                    System.out.println("time -port- "+exchange.getSourcePort());
                    System.out.println("time -code- "+exchange.getRequestCode());
                    exchange.respond(CoAP.ResponseCode.CONTENT,"response time:"+
                            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
                }
                @Override
                public void handlePOST(CoapExchange exchange){
                    String payload = exchange.getRequestText();
                    System.out.println("客户端发送数据 --- string --- : "+payload);
                    byte [] payloadBytes = exchange.getRequestPayload();
                    System.out.println("客户端发送数据 --- byte --- : "+new String(payloadBytes));
                    exchange.respond("msg have recevied !");
                }
            });
            //启动服务器
            server.start();
            System.out.println("Coap 服务端启动");
        }
    }


