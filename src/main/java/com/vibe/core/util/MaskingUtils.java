package com.vibe.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * [Security] 媛쒖씤?뺣낫 留덉뒪???좏떥由ы떚
 * OWASP 誘쇨컧 ?곗씠???몄텧 諛⑹?瑜??꾪빐 ?곗씠?곕? ?꾪꽣留곹빀?덈떎.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MaskingUtils {

    public static String maskEmail(String email) {
        if (!StringUtils.hasText(email) || !email.contains("@")) return email;
        String[] parts = email.split("@");
        return parts[0].replaceAll("(?<=.{2}).", "*") + "@" + parts[1];
    }

    public static String maskPhone(String phone) {
        if (!StringUtils.hasText(phone)) return phone;
        return phone.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-****-$3");
    }

    public static String maskName(String name) {
        if (!StringUtils.hasText(name)) return name;
        if (name.length() <= 2) return name.charAt(0) + "*";
        return name.charAt(0) + "*".repeat(name.length() - 2) + name.charAt(name.length() - 1);
    }
}

