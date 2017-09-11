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
}
