package com.allegrasoftware.dynjms.domain;


public class MsgServer {

  private long id;
  private String brokerType;
  private String host;
  private String password;
  private String port;
  private String username;
  private String prefix;
  private int type;

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getBrokerType() {
    return brokerType;
  }

  public void setBrokerType(String brokerType) {
    this.brokerType = brokerType;
  }


  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getPort() {
    return port;
  }

  public void setPort(String port) {
    this.port = port;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}
