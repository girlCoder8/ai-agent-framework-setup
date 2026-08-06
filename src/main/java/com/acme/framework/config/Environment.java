package com.acme.framework.config;
public final class Environment {
  private Environment() {}
  public static String required(String name) { String value=System.getenv(name); if(value==null||value.isBlank()) throw new IllegalStateException("Missing environment variable: "+name); return value; }
  public static String optional(String name,String fallback){ String value=System.getenv(name); return value==null||value.isBlank()?fallback:value; }
}
