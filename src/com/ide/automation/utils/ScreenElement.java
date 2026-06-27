package com.ide.automation.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScreenElement {
    private int row;
    private int col;
    private String write;

    public ScreenElement setWriteText(String write){
        setWrite(write);
        return this;
    }
}
