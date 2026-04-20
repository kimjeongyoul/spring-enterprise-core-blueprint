package com.vibe.core.helper;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * [Business] ?좎쭨 諛??쒓컙 怨듯넻 ?ы띁
 * ?⑥닚 ?좏떥由ы떚瑜??섏뼱, ?쒖뒪???꾩껜???쒓컙 洹쒓꺽???뺤쓽?⑸땲??
 */
@Component
public class DateHelper {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public String formatNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
    }

    public LocalDateTime startOfDay(LocalDateTime dateTime) {
        return dateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    public LocalDateTime endOfDay(LocalDateTime dateTime) {
        return dateTime.withHour(23).withMinute(59).withSecond(59).withNano(999999999);
    }
}

