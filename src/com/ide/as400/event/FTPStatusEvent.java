package com.ide.as400.event;

import java.util.EventObject;

/**
 * Clase: FTPStatusEvent.java
 * Descripción: Evento que transporta información de estado de una operación FTP, incluyendo el mensaje, el tipo de mensaje, la longitud del archivo y el registro actual transferido.
 */
public class FTPStatusEvent extends EventObject {

   private static final long serialVersionUID = 1L;
public FTPStatusEvent(Object obj){
      super(obj);
   }

   public FTPStatusEvent(Object obj, String s) {
      super(obj);
      message = s;
      messageType = OK;
   }

   public FTPStatusEvent(Object obj, String s, int messageType) {
      super(obj);
      message = s;
      this.messageType = messageType;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String s) {
      message = s;
   }

   public int getMessageType() {
      return messageType;
   }

   public void setMessageType(int type) {
      messageType = type;
   }

   public int getFileLength() {

      return fileLength;
   }
   public void setFileLength(int len) {

      fileLength = len;
   }

   public int getCurrentRecord() {

      return currentRecord;
   }

   public void setCurrentRecord(int current) {

      currentRecord = current;
   }
   private String message;
   private int fileLength;
   private int currentRecord;
   private int messageType;

   static final int OK = 0;
   static final int ERROR = 1;
   static final int ERROR_NULLS_ALLOWED = 2;


}
