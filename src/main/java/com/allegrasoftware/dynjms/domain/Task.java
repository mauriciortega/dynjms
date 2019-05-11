package com.allegrasoftware.dynjms.domain;


public class Task {

  private long id;
  private java.sql.Timestamp dateTimeCompletedOk;
  private java.sql.Timestamp dateTimeCreated;
  private java.sql.Timestamp datetimeLastExecution;
  private long numberOfExecutions;
  private int source;
  private int status;
  private int target;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public java.sql.Timestamp getDateTimeCompletedOk() {
    return dateTimeCompletedOk;
  }

  public void setDateTimeCompletedOk(java.sql.Timestamp dateTimeCompletedOk) {
    this.dateTimeCompletedOk = dateTimeCompletedOk;
  }


  public java.sql.Timestamp getDateTimeCreated() {
    return dateTimeCreated;
  }

  public void setDateTimeCreated(java.sql.Timestamp dateTimeCreated) {
    this.dateTimeCreated = dateTimeCreated;
  }


  public java.sql.Timestamp getDatetimeLastExecution() {
    return datetimeLastExecution;
  }

  public void setDatetimeLastExecution(java.sql.Timestamp datetimeLastExecution) {
    this.datetimeLastExecution = datetimeLastExecution;
  }


  public long getNumberOfExecutions() {
    return numberOfExecutions;
  }

  public void setNumberOfExecutions(long numberOfExecutions) {
    this.numberOfExecutions = numberOfExecutions;
  }


  public int getSource() {
    return source;
  }

  public void setSource(int source) {
    this.source = source;
  }


  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }


  public int getTarget() {
    return target;
  }

  public void setTarget(int target) {
    this.target = target;
  }

}
