package com.ide.as400.event;

import java.util.EventListener;

public interface BootListener extends EventListener {

    public abstract void bootOptionsReceived(BootEvent bootevent);

}
