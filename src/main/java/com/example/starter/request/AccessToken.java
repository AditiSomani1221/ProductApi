package com.example.starter.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccessToken {
  private String accessToken;

  private String name;
  private long expiresAt;
  private String tokenType;
}
