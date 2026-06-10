/**
 * Clase: WizardEvent.java
 * Descripción: Evento generado por las páginas de un asistente (wizard), almacenando la página actual, la nueva página, si es la última página y si se permite continuar la navegación.
 */
package com.ide.as400.event;

import java.awt.Component;

/**
 * The event object for Wizard pages.
 */
public class WizardEvent extends java.util.EventObject {

   private static final long serialVersionUID = 1L;
protected Component currentPage;
   protected Component newPage;
   protected boolean isLastPage;
   protected boolean allowChange;

   public WizardEvent(Object source, Component current_page, Component new_page,
                        boolean is_last_page, boolean allow_change) {

      super(source);
      this.currentPage = current_page;
      this.newPage = new_page;
      this.isLastPage = is_last_page;
      this.allowChange = allow_change;
   }

   /**
    * Returns whether the page is the last page.
    * @return true if page is the last one
    */
   public boolean isLastPage() {
      return isLastPage;
   }

   /**
    * Returns whether the event should be allowed to finish processing.
    * @return true if the vent can finish the process
    */
   public boolean getAllowChange() {
      return allowChange;
   }

   /**
    * Sets whether the event should be allowed to finish processing.
    * @param v flag
    */
   public void setAllowChange(boolean v) {
      allowChange = v;
   }

   /**
    * Returns the next page.
    * @return component
    */
   public Component getNewPage() {
      return newPage;
   }

   /**
    * Sets the next page.
    * @param p component
    */
   public void setNewPage(Component p) {
      newPage = p;
   }

   /**
    * Returns the current page on which the <code>JCWizardEvent</code> occured.
    * @return component
    */
   public Component getCurrentPage() {
      return currentPage;
   }

}
