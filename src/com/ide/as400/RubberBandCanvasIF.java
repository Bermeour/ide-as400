/**
 * Clase: RubberBandCanvasIF.java
 * Descripción: Interfaz que define el contrato que debe cumplir cualquier componente gráfico que soporte la selección de áreas mediante RubberBand, exponiendo métodos para gestionar eventos de ratón, traducir coordenadas y obtener el contexto gráfico de dibujo.
 */
package com.ide.as400;

import java.awt.*;
import java.awt.event.*;

public interface RubberBandCanvasIF {
   void addMouseListener(MouseListener l);
   void addMouseMotionListener(MouseMotionListener l);
   void areaBounded(RubberBand b, int startX, int startY, int endX, int endY);
   boolean canDrawRubberBand(RubberBand band);
   Point translateStart(Point startPoint);
   Point translateEnd(Point endPoint);
   Color getBackground();
//   Graphics getGraphics();
   Graphics getDrawingGraphics();

}
