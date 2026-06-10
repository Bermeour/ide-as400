package com.ide.as400.interfaces;

import com.ide.as400.*;
import com.ide.as400.Session5250;

/**
 * Clase: SessionsInterface.java
 * Descripción: Interfaz que proporciona acceso a la colección de sesiones activas del emulador, permitiendo obtener el número de sesiones y acceder a ellas por índice o por nombre.
 */
public interface SessionsInterface {

   public abstract int getCount();
   public abstract Session5250 item (int index);
   public abstract Session5250 item (String sessionName);
   public abstract void refresh();

}
