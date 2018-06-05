package eu.toop.testingservice.bean;

import java.io.Serializable;

public class TestRequest implements Serializable {

  private ToopConnectorDetails toopConnectorDetails;
  private Identity identity;
  private TestCase testCase;

  public ToopConnectorDetails getToopConnectorDetails () {

    return toopConnectorDetails;
  }

  public void setToopConnectorDetails (ToopConnectorDetails toopConnectorDetails) {

    this.toopConnectorDetails = toopConnectorDetails;
  }

  public Identity getIdentity () {

    return identity;
  }

  public void setIdentity (Identity identity) {

    this.identity = identity;
  }

  public TestCase getTestCase () {

    return testCase;
  }

  public void setTestCase (TestCase testCase) {

    this.testCase = testCase;
  }
}
