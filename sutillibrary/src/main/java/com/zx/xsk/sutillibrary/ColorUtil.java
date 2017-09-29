package com.zx.xsk.sutillibrary;

import android.graphics.Color;

/**
 * Created by sjy on 2017/5/16.
 */

public class ColorUtil {
    /**
     * 颜色渐变过渡
     * 例子： getCurrentColor((float)a/(float)b,0x123234,0xffffff)
     * @param fraction 百分比 从0%-100%   0%:startColor;100% :endColor
     * @param startColor 开始颜色
     * @param endColor 结束颜色
     * @return 颜色值
     */
    public static int getCurrentColor(float fraction, int startColor, int endColor) {
        int redCurrent;
        int blueCurrent;
        int greenCurrent;
        int alphaCurrent;

        int redStart = Color.red(startColor);
        int blueStart = Color.blue(startColor);
        int greenStart = Color.green(startColor);
        int alphaStart = Color.alpha(startColor);

        int redEnd = Color.red(endColor);
        int blueEnd = Color.blue(endColor);
        int greenEnd = Color.green(endColor);
        int alphaEnd = Color.alpha(endColor);

        int redDifference = redEnd - redStart;
        int blueDifference = blueEnd - blueStart;
        int greenDifference = greenEnd - greenStart;
        int alphaDifference = alphaEnd - alphaStart;

        redCurrent = (int) (redStart + fraction * redDifference);
        blueCurrent = (int) (blueStart + fraction * blueDifference);
        greenCurrent = (int) (greenStart + fraction * greenDifference);
        alphaCurrent = (int) (alphaStart + fraction * alphaDifference);

        return Color.argb(alphaCurrent, redCurrent, greenCurrent, blueCurrent);
    }

    /**
     * Color对象转换成字符串
     * @param color Color对象
     * @return 16进制颜色字符串#FF0000FF
     * */
    public static String toHexFromColor(int color,boolean isAlp){
        String a,r,g,b;
        StringBuilder su = new StringBuilder();
        a=Integer.toHexString(Color.alpha(color));
        r = Integer.toHexString(Color.red(color));
        g = Integer.toHexString(Color.green(color));
        b = Integer.toHexString(Color.blue(color));
        a = a.length() == 1 ? "0" + a : a;
        r = r.length() == 1 ? "0" + r : r;
        g = g.length() ==1 ? "0" +g : g;
        b = b.length() == 1 ? "0" + b : b;
        a=a.toUpperCase();
        r = r.toUpperCase();
        g = g.toUpperCase();
        b = b.toUpperCase();
        su.append("#");
        if(isAlp) {
            su.append(a);
        }
        su.append(r);
        su.append(g);
        su.append(b);
        //#FF0000FF
        return su.toString();
    }

    /**
     * Color对象转换成字符串
     * @param color Color对象
     * @return 16进制颜色字符串0xFF0000FF
     * */
    public static String toHexCodeFromColor(int color,boolean isAlp){
        String a,r,g,b;
        StringBuilder su = new StringBuilder();
        a=Integer.toHexString(Color.alpha(color));
        r = Integer.toHexString(Color.red(color));
        g = Integer.toHexString(Color.green(color));
        b = Integer.toHexString(Color.blue(color));
        a = a.length() == 1 ? "0" + a : a;
        r = r.length() == 1 ? "0" + r : r;
        g = g.length() ==1 ? "0" +g : g;
        b = b.length() == 1 ? "0" + b : b;
        a=a.toUpperCase();
        r = r.toUpperCase();
        g = g.toUpperCase();
        b = b.toUpperCase();
        su.append("0x");
        if(isAlp) {
            su.append(a);
        }
        su.append(r);
        su.append(g);
        su.append(b);
        //0xFF0000FF
        return su.toString();
    }
}
