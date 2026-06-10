/**
 * Clase: SSLInterface.java
 * Descripción: Interfaz que define el contrato para las implementaciones de conexión SSL,
 * especificando los métodos de inicialización del contexto de seguridad y de creación
 * de sockets SSL hacia el servidor AS/400.
 */
package com.ide.as400.framework.transport;

import java.net.Socket;

public interface SSLInterface {

	/**
	 * Initialize the components required to create a new client socket
	 * when createSSLSocket is called.
	 * @param sslType The ssl socket type (SSLv2, SSLv3, TLS)
	 */
   public abstract void init(String sslType);

   /**
    * Create a new socket
    * @param destination a destination
    * @param port a port to connect to
    * @return new socket, or null if none could be created.
    */
   public abstract Socket createSSLSocket(String destination, int port);

}
