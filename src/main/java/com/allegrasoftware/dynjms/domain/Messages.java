package com.allegrasoftware.dynjms.domain;


public class Messages {

  private long id;
  private int action;
  private byte[] message;
  private long taskId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public int getAction() {
    return action;
  }

  public void setAction(int action) {
    this.action = action;
  }


  public byte[] getMessage() {
    return message;
  }

  public void setMessage(byte[] message) {
    this.message = message;
  }


  public long getTaskId() {
    return taskId;
  }

  public void setTaskId(long taskId) {
    this.taskId = taskId;
  }

}
