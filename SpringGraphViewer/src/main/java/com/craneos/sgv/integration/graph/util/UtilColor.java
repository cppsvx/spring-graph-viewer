package com.craneos.sgv.integration.graph.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class UtilColor {

    private int index;
    private Map<String, Color> colors;

    public UtilColor(){
        this.index = 0;
        this.colors = new HashMap<>();
    }

    public Color getColor(String key){
        Color color = colors.get(key);
        if (color == null) {
            color = generateColor();
            this.index++;
            colors.put(key, color);
        }
        return color;
    }

    public Color generateColor(){
        Color color;
        if (index==0){
            color = Color.black;
        } else if (index==1){
            color = Color.pink;
        } else if (index==2){
            color = Color.orange;
        } else if (index==3){
            color = Color.yellow;
        } else if (index==4){
            color = Color.magenta;
        } else if (index==5){
            color = Color.cyan;
        } else if (index==6){
            color = Color.blue;
        } else if (index==7){
            color = Color.darkGray;
        } else {
            color = new Color((int)(Math.random() * 0x1000000));
        }
        return color;
    }
}
