package edu.snhu.airbook.utils;

import edu.snhu.airbook.dto.ItineraryDto;

import java.util.Comparator;

public class DateComparator implements Comparator<ItineraryDto> {
    @Override
    public int compare(ItineraryDto i1, ItineraryDto i2) {
        return i1.getDepartDateTime().compareTo(i2.getDepartDateTime());
    }
}
