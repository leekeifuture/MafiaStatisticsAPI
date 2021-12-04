package com.mafia.statistics.MafiaStatisticsAPI.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DigitsUtil {

    public static Double roundDouble(Double value, Integer scale) {
        return BigDecimal.valueOf(value)
                .setScale(scale, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
