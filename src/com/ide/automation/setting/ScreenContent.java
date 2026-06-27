package com.ide.automation.setting;

import com.ide.as400.Session5250;
import com.ide.as400.framework.tn5250.Screen5250;
import org.python.antlr.ast.Str;

import java.util.ArrayList;
import java.util.List;

public class ScreenContent {

    private final Screen5250 screen;
    private final char[] chars;
    private final String contents;
    public final static String  UNICODE_CHARACTER_PROPERTIES_OTHER="\\p{C}";

    public ScreenContent(Session5250 session){
        this.screen=session.getScreen();
        this.chars=screen.getCharacters();
        this.contents=new String(chars);
    }

    /*
    * El metodo getLines() es el encargado de retornar todo el texto de la pantalla como una lista de cadenas de texto
    * que construye al usar el metodo getline
    * */
    public List<String> getLines(){
        List<String> lines = new ArrayList<>(screen.getRows());
        for(int row = 0; row<screen.getRows(); row++){
            lines.add(getLine(row).trim());
        }
        return lines;
    }

    /*
    * El metodo de getScreen() es el encargado de retornar todo el texto de la pantalla como una cadena de texro, realizando
    * Saltos de linea para visualizarlo de la misma manera que en la terminal
    * */
    public String getScreen(){
        StringBuilder sb = new StringBuilder();
        for(int row= 0; row <screen.getRows();row++){
            sb.append(getLine(row));
            sb.append('\n');
        }
        return sb.toString();
    }

/*
* Se encarga de retornar una linea de texto la cual de pendera de la fima ingresada
* */
    public String getLine(int row){
        String screenRow = String.copyValueOf(chars,row * screen.getColumns(),screen.getColumns());
        screenRow = screenRow.replaceAll(UNICODE_CHARACTER_PROPERTIES_OTHER," ");
        return screenRow;
    }

    /*
    * es el encargado de retornar el indice de un texto en especifico en la pantalla de la terminal
    * */
    public int indexOf(String text){
        if(!contents.contains(text)){
            throw  new IllegalStateException(String.format("Cloud not find text [%s] on screen",text));
        }
        return contents.indexOf(text);
    }
    /*
    * El metodo es el encargado de retornar la fila en la cual se encuentra un texto en especifico en la pantalla de la terminal
    * */
    public int lieneOf(String text){
        List<String> lines = getLines();
        for(int row = 0; row<lines.size(); row++){
            if(lines.get(row).contains(text)){
                return row;
            }
        }
        throw new IllegalStateException(String.format("Cloud not find row with text [%s] on screen",text));
    }
}
