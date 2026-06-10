/**
 * Clase: SessionConfigEvent.java
 * Descripción: Evento de cambio de propiedad de configuración de sesión, extiende PropertyChangeEvent para notificar modificaciones en los parámetros de configuración del emulador.
 */

package com.ide.as400.event;

import java.beans.PropertyChangeEvent;

public class SessionConfigEvent extends PropertyChangeEvent {


   private static final long serialVersionUID = 1L;

/**
   * Constructs a new <code>SessionConfigChangeEvent</code>.
   *
   * @param source  The bean that fired the event.
   * @param propertyName  The programmatic name of the property
   *		that was changed.
   * @param oldValue  The old value of the property.
   * @param newValue  The new value of the property.
   */
   public SessionConfigEvent(Object source, String propertyName,
              Object oldValue, Object newValue) {

      super(source, propertyName, oldValue, newValue);

   }

}
