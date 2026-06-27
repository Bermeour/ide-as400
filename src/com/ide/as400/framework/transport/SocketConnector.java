/**
 * Clase: SocketConnector.java
 * Descripción: Fábrica de conexiones de red que crea sockets TCP planos o sockets SSL
 * según el tipo de conexión configurado, sirviendo como punto de entrada unificado
 * para establecer la comunicación de red con el servidor AS/400.
 */
package com.ide.as400.framework.transport;

import java.net.Socket;

import com.ide.as400.tools.logging.TN5250jLogFactory;
import com.ide.as400.tools.logging.TN5250jLogger;
import com.ide.as400.TN5250jConstants;

public class SocketConnector {

  String sslType = null;

  TN5250jLogger logger;

  /**
   * Creates a new instance that creates a plain socket by default.
   */
  public SocketConnector() {
  	logger = TN5250jLogFactory.getLogger(getClass());
  }

  /**
   * Set the type of SSL connection to use.  Specify null or an empty string
   * to use a plain socket.
   * @param type The SSL connection type
   */
  public void setSSLType(String type) {
    sslType = type;
  }

  /**
   * Create a new client Socket to the given destination and port.  If an SSL
   * socket type has not been specified <i>(by setSSLType(String))</i>, then
   * a plain socket will be created.  Otherwise, a new SSL socket of the
   * specified type will be created.
   * @param destination destination host
   * @param port a port to connect to
   * @return a new client socket, or null if
   */
  public Socket createSocket(String destination, int port) {

  	Socket socket = null;
  	Exception ex = null;

      if (sslType == null || sslType.trim().length() == 0 ||
      		sslType.toUpperCase().equals(TN5250jConstants.SSL_TYPE_NONE)) {
        	logger.info("Creating Plain Socket");
        try {
			// Use Socket Constructor!!! SocketFactory for jdk 1.4
			socket = new Socket(destination,port);
		} catch (Exception e) {
			ex = e;
		}
      } else {  //SSL SOCKET

   		logger.info("Creating SSL ["+sslType+"] Socket");

      	SSLInterface sslIf = null;

      	String sslImplClassName =
      		"com.ide.as400.framework.transport.SSL.SSLImplementation";
		try {
			Class<?> c = Class.forName(sslImplClassName);
			sslIf = (SSLInterface)c.newInstance();
		} catch (Exception e) {
			ex = new Exception("Failed to create SSLInterface Instance. " +
					"Message is ["+e.getMessage()+"]");
		}

      	if (sslIf != null) {
      		sslIf.init(sslType);
      		socket = sslIf.createSSLSocket(destination,port);
      	}
      }

      if (ex != null) {
      	logger.error(ex);
      }
      if (socket == null) {
      	logger.warn("No socket was created");
      }
      return socket;
  }


}
