import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Coap_Client {
    public static void main(String[] args){
        URI uri = null;
        if (args.length>0){
            try{
                uri = new URI(args[0]);
            }
            catch (URISyntaxException e){
                System.err.println("Invalid URI: "+e.getMessage());
                System.exit(-1);
            }
            CoapClient client = new CoapClient(uri);
            CoapResponse response = client.get();

            if (response!= null){
                System.out.println(response.getCode());
                System.out.println(response.getOptions());
                if (args.length>1){
                    try(FileOutputStream out = new FileOutputStream(args[1])){
                        out.write(response.getPayload());
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(response.getResponseText());
                    System.out.println("\nAdvanced\n");
                    System.out.println(Utils.prettyPrint(response));
                }
            } else{
                System.out.println("NO response received");
            }
        }
    }
}
