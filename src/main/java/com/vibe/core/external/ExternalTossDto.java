package com.vibe.core.external;

import lombok.Getter;

@Getter
public class ExternalTossDto {
    private String payment_key;
    private Long tot_amt;
    private String stat_cd;
}

