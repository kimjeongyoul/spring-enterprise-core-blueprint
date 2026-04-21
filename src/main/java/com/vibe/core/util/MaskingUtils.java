package com.vibe.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * [Security] 개인정보 마스킹 유틸리티
 * OWASP 민감 데이터 노출 방지를 위해 데이터를 필터링합니다.
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
