package com.vibe.core.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthUser { private String userId; private String role; }
