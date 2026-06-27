package com.ide.automation.screenshot;

import com.ide.automation.setting.ConfigProperties;
import com.ide.automation.utils.Utils;
import org.python.antlr.ast.Str;

import javax.imageio.ImageIO;
import javax.rmi.CORBA.Util;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class TakeScreenshotAs400 {

    private File evidenceFormat;
    private String nameCase;
    private Utils util;

    public void pathTakeScreenshot(String nameCase){
        ConfigProperties config = new ConfigProperties();
        util = new Utils();
        this.nameCase=nameCase;
        String pathFormat = System.getProperty("user.dir") + config.getPathFormat() + nameCase;
        evidenceFormat = new File(pathFormat);
        if(!evidenceFormat.exists()){
            evidenceFormat.mkdir();
        }
    }

    private final static HashMap<RenderingHints.Key, Object> renderingProperties = new HashMap<>();


    public String takePhoto(String screenText){
        String nameScreenshot = util.nameImagen();
        String pathScreenshot;

        renderingProperties.put(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        renderingProperties.put(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_PURE);
        renderingProperties.put(RenderingHints.KEY_FRACTIONALMETRICS,RenderingHints.VALUE_FRACTIONALMETRICS_ON);

        Font font= new Font("Consolas",Font.PLAIN,12);
        FontRenderContext fontRenderContext= new FontRenderContext(null, true,true);
        BufferedImage bufferedImage= new BufferedImage(getWidthScreen(screenText),320,BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D= bufferedImage.createGraphics();
        graphics2D.setRenderingHints(renderingProperties);
        graphics2D.setBackground(Color.black);
        graphics2D.setColor(Color.GREEN);
        graphics2D.setFont(font);
        graphics2D.clearRect(0,0,bufferedImage.getWidth(),bufferedImage.getHeight());
        TextLayout textLayout =new TextLayout(screenText,font,fontRenderContext);
        drawString(graphics2D,screenText,textLayout);
        graphics2D.dispose();

        File ScrFile = new File(evidenceFormat +"\\"+nameScreenshot+".png");
        pathScreenshot= nameCase+"/" + nameScreenshot+".png";
        try {
            ImageIO.write(bufferedImage,"PNG",ScrFile);
        }catch (IOException e){
            e.fillInStackTrace();
        }
        return pathScreenshot;
    }

    private static void drawString(Graphics graphics2D, String screenText, TextLayout textLayout){
        int count=0;
        for(String line :screenText.split("\\n")){
            graphics2D.drawString(line,15,(int)(15+count*textLayout.getBounds().getHeight() + 0.5));
            count++;
        }
    }

    private int getWidthScreen(String screenText){
        String[] rowScreen= screenText.split("\\n");
        for(int i=0; i<rowScreen.length -1; i++){
            if(!rowScreen[i].substring(80).trim().isEmpty()){
                return 900;
            }
        }
        return 600;
    }
}
