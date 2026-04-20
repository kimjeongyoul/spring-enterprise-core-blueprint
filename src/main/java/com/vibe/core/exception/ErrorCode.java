package com.vibe.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * [Standard] ?꾩뿭 ?먮윭 肄붾뱶 ?뺤쓽
 * ?먮윭???깃꺽???곕씪 HTTP ?곹깭 肄붾뱶? 鍮꾩쫰?덉뒪 肄붾뱶瑜?留ㅽ븨?⑸땲??
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 怨듯넻 ?먮윭
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "COMMON_001", "?섎せ???낅젰媛믪엯?덈떎."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "COMMON_002", "?덉슜?섏? ?딆? 硫붿꽌?쒖엯?덈떎."),
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON_003", "議댁옱?섏? ?딅뒗 由ъ냼?ㅼ엯?덈떎."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_004", "?쒕쾭 ?대? ?ㅻ쪟媛 諛쒖깮?덉뒿?덈떎."),

    // ?몄쬆/沅뚰븳 愿??
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "AUTH_001", "?몄쬆?섏? ?딆? ?ъ슜?먯엯?덈떎."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "AUTH_002", "?묎렐 沅뚰븳???놁뒿?덈떎."),

    // ?꾨찓???뱁솕 ?먮윭 (?? 寃곗젣)
    ALREADY_APPROVED(HttpStatus.CONFLICT, "PAY_001", "?대? ?뱀씤??寃곗젣嫄댁엯?덈떎."),
    EXCEEDED_AMOUNT(HttpStatus.BAD_REQUEST, "PAY_002", "?쒕룄瑜?珥덇낵?섎뒗 湲덉븸?낅땲??");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

