package com.ide.as400;

import com.ide.as400.interfaces.ConfigureFactory;
import com.ide.as400.interfaces.OptionAccessFactory;
import com.ide.as400.keyboard.KeyMnemonicResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Clase: OptionAccess.java
 * Descripción: Singleton que gestiona el control de acceso a las opciones del emulador, cargando desde la configuración global la lista de opciones restringidas y exponiendo métodos para verificar si una opción está permitida o bloqueada.
 */
public class OptionAccess extends OptionAccessFactory {

   /**
    * A handle to the unique OptionAccess class
    */
   static private OptionAccess _instance;

   /**
    * A handle to non valid options.
    */
   static private List<String> restricted = new ArrayList<String>();

   private final KeyMnemonicResolver keyMnemonicResolver = new KeyMnemonicResolver();

   /**
    * The constructor is made protected to allow overriding.
    */
   public OptionAccess() {
       if (_instance == null) {
           // initialize the settings information
           initialize();
           // set our instance to this one.
           _instance = this;
       }
   }

   /**
    *
    * @return The unique instance of this class.
    */
   static public OptionAccess instance() {

      if (_instance == null) {
         _instance = new OptionAccess();
      }
      return _instance;

   }

   /**
    * Initialize the properties registry for use later.
    *
    */
   private void initialize() {

      loadOptions();
   }

   /**
    * Load a list of available options
    */
   private void loadOptions() {

      restricted.clear();
		String restrictedProp =
			ConfigureFactory.getInstance().getProperties(
				ConfigureFactory.SESSIONS).getProperty("emul.restricted");

      if (restrictedProp != null) {
         StringTokenizer tokenizer = new StringTokenizer(restrictedProp, ";");
         while (tokenizer.hasMoreTokens()) {
            restricted.add(tokenizer.nextToken());
         }
      }

   }

   public boolean isValidOption(String option) {

      return !restricted.contains(option);
   }

   public boolean isRestrictedOption(String option) {

      return restricted.contains(option);
   }

   public int getNumberOfRestrictedOptions() {

      return restricted.size();
   }

   public void reload() {
      loadOptions();
   }
}
