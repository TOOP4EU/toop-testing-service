package eu.toop.testingservice.bean;

import java.io.Serializable;

public class TestCase implements Serializable {

  private String name;
  private String description;
  private String asicName;

  public TestCase (String name, String description, String asicName) {
    setName (name);
    setDescription (description);
    setAsicName (asicName);
  }

  public String getName () {

    return name;
  }

  public void setName (String name) {

    this.name = name;
  }

  public String getDescription () {

    return description;
  }

  public void setDescription (String description) {

    this.description = description;
  }

  public String getAsicName () {

    return asicName;
  }

  public void setAsicName (String asicName) {

    this.asicName = asicName;
  }
}
