package com.ide.automation.assistant;

import com.ide.automation.actions.ActionsAs400;
import com.ide.automation.screenshot.TakeScreenshotAs400;
import com.ide.automation.setting.ConfigProperties;
import com.ide.automation.setting.TerminalConfiguraction;
import com.ide.automation.utils.Utils;
import lombok.Getter;

public class ObjAssistant {
    private TerminalConfiguraction confgTerminla;
    private TakeScreenshotAs400 takeScreenshot;

    @Getter
    private ActionsAs400 actionsAs400;

    @Getter
    private Utils util;

    @Getter
    private ConfigProperties configProperties;


    public ObjAssistant() {
properties();
confgTerminla();
actionsAs400();
takeScreenshotAs400();
        utils();
    }

    private void confgTerminla(){
        confgTerminla= new TerminalConfiguraction();
        confgTerminla.startTerminal();
    }
    public void getDisconnectHost(){
        confgTerminla.disconnectHot();
    }
    private void takeScreenshotAs400(){takeScreenshot= new TakeScreenshotAs400();}
    public TakeScreenshotAs400 getTakeScreenshotAs400(){return takeScreenshot;}

    private void actionsAs400(){
        actionsAs400= new ActionsAs400(confgTerminla,configProperties.getScreenTimeout());
    }
    private void utils(){
        util= new Utils();
    }
    private void properties(){
        configProperties= new ConfigProperties();
    }
}
