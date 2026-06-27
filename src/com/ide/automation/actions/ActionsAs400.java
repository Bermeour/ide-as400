package com.ide.automation.actions;

import com.ide.as400.event.ScreenOIAListener;
import com.ide.as400.keyboard.KeyMnemonic;
import com.ide.automation.exception.InvalidPositionException;
import com.ide.automation.exception.InvalidSenKeyException;
import com.ide.automation.setting.ScreenContent;
import com.ide.automation.setting.TerminalConfiguraction;
import com.ide.automation.utils.ScreenElement;
import org.python.antlr.ast.Str;

import java.util.List;
import java.util.NoSuchElementException;

public class ActionsAs400 {

    private final TerminalConfiguraction configTerminla;
    private final long screenTimeout;

    //Se encarga de  inicializar la terminla
    public ActionsAs400(TerminalConfiguraction configTerminla, long screenTimeot){
        this.configTerminla=configTerminla;
        this.screenTimeout=screenTimeot;
    }

    //Se encarga de retornar la instancia de la clase de ScreenConten para usar los metodos
    private ScreenContent getScreenContent(){return new ScreenContent(configTerminla.session);}

    //es el encargado de ubicar la posicion espesificada por el screenElement
    public void setPosition(ScreenElement screenElement){
        try{
            configTerminla.hostSpace().setCursor(screenElement.getRow(),screenElement.getCol());
            waitForCursor(screenTimeout);
        }catch (Exception e){
            throw new RuntimeException("No se puede ubicar el cursor en la cordenadas ingresadas "+  screenElement);
        }
    }
    //
    public  void textWrite(ScreenElement screenElement, String text){
       setPosition(screenElement);
       textWrite(text);
    }
    //se encarga de escribir sobre la terminal de manera secuencial en orden sobre los campos disponible como input
    public void textWrite(String text){
        try{
            configTerminla.hostSpace().sendKeys(text);
            if (isContentPresent("El cursor esta en una area protegida de la pantalla")){
                throw new InvalidPositionException("El cursor esta en una area protegida ");
            }
        }catch (Exception e){
            throw new InvalidPositionException("Se genera error en el metodo []txtWrite " +e);
        }
    }

    //es para escribir de manera masiva pide cordenadas
    public void textWrite(List<ScreenElement> elementList){
        for(ScreenElement element:elementList){
            setPosition(element);
            textWrite(element.getWrite());
        }
    }

    //escribe en la posicion indicada y preciona enter
    public void textWriteKeyEnter(ScreenElement screenElement, String text){
    setPosition(screenElement);
        textWriteKeyEnter(text);
    }
    /**
     * Es el encargado de escribir sobre la terminal de manera secuencial en orden sobre los camposdisponibles
     * @param text
     */
    public void textWriteKeyEnter(String text){
        textWrite(text);
        keyEnter();
    }

    //preciona la tecla enter
    public void keyEnter(){
        try{
            configTerminla.hostSpace().sendKeys(KeyMnemonic.ENTER);
            waitForKeyFunction();
        }catch (Exception e){
            throw new InvalidPositionException("Se genera error en el metodo de keyEnter "+ e);
        }
    }
    /**
     * es el encargado de avPag
     */
    public void keyAvPage(){
        try{
            configTerminla.hostSpace().sendKeys(KeyMnemonic.PAGE_DOWN);
            waitForKeyFunction();
        }catch (Exception e){
            throw new InvalidPositionException("Se genera error en el metodo de keyRePage "+ e);
        }
    }
    /**
     * en cargado de hacer rePag
     */
    public void keyRePage(){
        try{
            configTerminla.hostSpace().sendKeys(KeyMnemonic.PAGE_UP);
            waitForKeyFunction();
        }catch (Exception e){
            throw new InvalidPositionException("Se genera error en el metodo de keyRePage "+ e);
        }
    }

    /**
     * Teclasespeciales como las f1 o f12
     * @param key
     */
    public void keyFunctions(String key){
        try{
            configTerminla.hostSpace().sendKeys("[p"+key.toLowerCase().trim()+"]");
            waitForKeyFunction();
        }catch (Exception e){
            throw new InvalidPositionException("Se genera error en el metodo de keyFunctions "+ e);
        }
    }

    /**
     * En cargado de hacer la tecla fin
     */
    public void keyEnd( ){
        try{
            configTerminla.hostSpace().sendKeys(KeyMnemonic.END_OF_FIELD);
            waitForKeyFunction();
        }catch (Exception e){
            throw new InvalidPositionException("Se genera error en el metodo de keyEnd "+ e);
        }
    }
    /**
     *
     * @param mnemonic el mnemonic a ejecutar
     */
    public void keySendKeysMneminc(String mnemonic){
        try{
            configTerminla.hostSpace().sendKeys("["+mnemonic.toLowerCase()+"]");
            waitForKeyFunction();
        }catch (Exception e){
            throw new InvalidPositionException("Se genera error en el metodo de keySendKeysMneminc "+ e);
        }
    }
    /**
     * se encaga de ejecutar el mnemonic en las cordenadas indicadas
     * @param mnemonic es el valor del memonoic de la terminal
     * @param row
     * @param col
     */
    public void keySendKeysMneminicRowCol(String mnemonic,int row,int col){
        try{
            configTerminla.hostSpace().setCursor(row,col);
            waitForKeyFunction();
            configTerminla.hostSpace().sendKeys("["+mnemonic+"]");
        }catch (Exception e){
            throw new InvalidPositionException("Se genera error en el metodo de keySendKeysMneminicRowCol "+ e);
        }
    }

    //usado para eliminar contenido en la posicion donde este el cursor
    public void keyDelete(){
        try{
            configTerminla.hostSpace().sendKeys(KeyMnemonic.DELETE);
            if(isContentPresent("El cursor esta en un area protegida de la pantalla ")){
                throw new InvalidPositionException("El cursor esta en un area protegida de la pantalla, no se puede ingresar la informacion");
            }
        }catch (Exception e){
            throw new InvalidPositionException("Se genera error en el metodo de keyDelete "+e);
        }
    }

    //Funciona para borrar el contenido de as400
    public void keyErase(ScreenElement screenElement){
        try{
           setPosition(screenElement);
           configTerminla.hostSpace().sendKeys(KeyMnemonic.ERASE_FIELD);
           if(isContentPresent("El curson esta en una area protegida de la pantalla")){
               throw new InvalidPositionException("El cursor esta en una area protegida de la pantalla , no se puede ingresar el texto");
           }
        }catch (Exception e){
            throw new InvalidPositionException("Se genera error en el metodo keyErase "+  e);
        }
    }
    /**
     *
     * @param screenElement correponde a las cordenada de as400
     * @param count el numero de caracteres
     * @return la cadena el cual contendra el texto requerido
     */
    public String getText(ScreenElement screenElement, int count){
        String buffString;
        try{
            buffString = getValueOf(screenElement,count);
        }catch (Exception e){
            throw new InvalidPositionException("No se logra obtener texto en las cordenada ingresadas" + screenElement);
        }
        return  buffString;
    }

    /**
     *
     * @param row corresponde a la fila de la cual se va obtener el texto debe de corresponder de la 1 a la 24
     * @return retorna un String el cual contendra el texto requerido
     */
    public String getTextRow(int row){
        String buffString;
        int finalRow=row-1;
        try {
            buffString=getScreenContent().getLine(finalRow);
        }catch (Exception e){
            throw new InvalidPositionException("No se logra obtener texto en la fila ingresando"+ finalRow);
        }
        return buffString;
    }
    //Este metodo se encarga de ontener un valor de una cadena de caracteres
    public String getValueOf(ScreenElement screenElement,int countLine){
        String screenRow;
        int finalRow=screenElement.getRow()-1;
        int column=screenElement.getCol()-1;
        screenRow= getScreenContent().getLine(finalRow);
        try {
            return screenRow.substring(column,column +countLine).trim();
        }catch (IndexOutOfBoundsException e){
            return screenRow.substring(column);
        }
    }

    //Usa el tiempo de espera basado en el properties
    public void waitForScreen(){
        waitForScreen(screenTimeout);
    }

    //es el encargado de esperar el cambio de ventana por el tiempo indicado
    public void waitForScreen(long waitSeconds){
        int safeCount=0;
        if(configTerminla.connetcted){
            configTerminla.informed=false;
            configTerminla.informChange= ScreenOIAListener.OIA_CHANGED_KEYBOARD_LOCKED;
            while (!configTerminla.informed){
                if(safeCount>=(waitSeconds*10)){
                    break;
                }
                try{
                    Thread.sleep(100L);
                }catch (InterruptedException e){
                    e.fillInStackTrace();
                }
                ++safeCount;
            }
        }
    }


    //Se encarga de esperar loego de enviar la teclas de funciones y verifica si segenera un mensaje de error con esa tecla
    public void waitForKeyFunction(){
        int safeCount=0;
        if(configTerminla.connetcted){
            configTerminla.informed=false;
            configTerminla.informChange= ScreenOIAListener.OIA_CHANGED_KEYBOARD_LOCKED;
            while (!configTerminla.informed){
                if(safeCount>=10){
                    break;
                }
                try{
                    Thread.sleep(100L);
                    if(isContentPresent("No se permite la tecla de func")){
                        throw new InvalidSenKeyException("Error al ejecutar la acción, no se permite la tecla de funcioes.");
                    }
                }catch (InterruptedException e){
                    e.fillInStackTrace();
                }
                ++safeCount;
            }
        }
    }

    //Es el encargado de realizar la espera de para la actualizacion de la ubicaciondel cursor a la posicion ingresada
    public void waitForCursor(long waitMillis){
        int safeCount=0;
        if(configTerminla.connetcted){
            configTerminla.informed=false;
            configTerminla.informChange= ScreenOIAListener.OIA_CHANGED_INPUTINHIBITED;
            while (!configTerminla.informed){
                if(safeCount>=(waitMillis)){
                    break;
                }
                try {
                    Thread.sleep(1L);
                }catch (InterruptedException e){
                    e.fillInStackTrace();
                }
                ++safeCount;
            }
        }
    }
    //Se encarga de realizar la espera a un texto y recibe los siguintes parametros
    public boolean waitForString(String textWait,ScreenElement screenElement,int waitSeconds){
        boolean waitForString= false;
        for(int i= 0; i<waitSeconds; i++){
            if(getValueOf(screenElement,textWait.length()).contains(textWait)){
                waitForString= true;
                break;
            }
            waitForScreen(1);
        }
        return waitForString;
    }

    //se encarga de realizar la espera a un texto por un tiempo de finido en el properties
    public  boolean waitForString(String textWait,ScreenElement screenElement){
        return waitForString(textWait,screenElement,(int) screenTimeout);
    }

    /*
    * se encarga de realizar una busqueda de texto en la pantalla de la terminal
    * */
    public boolean isTextPresent(final String textSearch , int waitSeconds){
        boolean status= false;
        for(int i=0; i<waitSeconds; i++){
            if(isContentPresent(textSearch)){
                status=true;
                break;
            }
            try{
                Thread.sleep(1000);
            }catch (InterruptedException ingnore){

            }
        }
        if(!status){
            throw  new NoSuchElementException(String.format("Terminal [%s] can´t find [%s] on the screen", Thread.currentThread().getName(), textSearch));
        }
        return true;
    }

    /*
    * Realiza la busqueda de un texto en toda la pantalla de la terminal de as400*/
    public boolean isContentPresent(final String textSearch){
        boolean status = false;
        List<String> linesAll= getScreenContent().getLines();

        for(String eachLine : linesAll){
            assert textSearch != null;
            if(eachLine.toLowerCase().contains(textSearch.toLowerCase())){
                status=true;
                break;
            }
        }
        return status;
    }


    //Se encarga de retornar todo el texto de la terminal
    public String getTextAll(){
        String parsedString;
        try {
            parsedString= getScreenContent().getScreen();
        }catch (Exception e){
            throw new RuntimeException("No hay contenido en la pantalla o se cerro la conexion");
        }
        return parsedString;
    }

}
