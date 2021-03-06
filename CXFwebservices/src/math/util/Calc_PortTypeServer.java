
package math.util;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 3.1.11
 * 2017-06-04T16:16:35.834+08:00
 * Generated source version: 3.1.11
 * 
 */
 
public class Calc_PortTypeServer{

    protected Calc_PortTypeServer() throws Exception {
        System.out.println("Starting Server");
        Object implementor = new math.util.Calc();
        String address = "http://localhost:9090/CalcPort";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws Exception { 
        new Calc_PortTypeServer();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
 
 