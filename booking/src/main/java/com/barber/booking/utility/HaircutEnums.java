package com.barber.booking.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class HaircutEnums {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public enum HaircutTypeEnum {
        SHAPE_UP(15), HAIRCUT(40), HAIRCUT_AND_BEARD(55);

        private int value;
    }
}
