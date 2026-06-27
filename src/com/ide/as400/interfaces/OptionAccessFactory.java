/**
 * Clase: OptionAccessFactory.java
 * Descripción: Clase abstracta de fábrica para el control de acceso a opciones del emulador, permitiendo verificar si una opción es válida o está restringida para el usuario actual.
 */
package com.ide.as400.interfaces;

import java.util.Vector;
/**
 * An interface defining objects that can create OptionAccess
 * instances.
 */
public abstract class OptionAccessFactory {

  private static OptionAccessFactory  factory;
   /**
    * @return An instance of the OptionAccess.
    */
  public static OptionAccessFactory  getInstance()
  {
    OptionAccessFactory.setFactory();
    return factory;
  }

  private static final void setFactory()
  {
    if (factory == null)
    {
      try
      {
        String  className = System.getProperty(OptionAccessFactory.class.getName());
        if (className != null)
        {
          Class<?> classObject = Class.forName(className);
          Object  object = classObject.newInstance();
          if (object instanceof OptionAccessFactory)
          {
            OptionAccessFactory.factory = (OptionAccessFactory) object;
          }
        }
      }
      catch (Exception  ex)
      {
        ;
      }
      if (OptionAccessFactory.factory == null)
      { //take the default
        OptionAccessFactory.factory = new com.ide.as400.OptionAccess();
      }
    }
  }

   abstract public boolean isValidOption(String option);
   abstract public boolean isRestrictedOption(String option);
   abstract public int getNumberOfRestrictedOptions();
   abstract public void reload();

}
