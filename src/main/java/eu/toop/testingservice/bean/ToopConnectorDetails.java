package eu.toop.testingservice.bean;

import java.io.Serializable;

public class ToopConnectorDetails implements Serializable {

  private String toopConnectorDCUrl;
  private String toopConnectorDPUrl;

  public String getToopConnectorDCUrl () {

    return toopConnectorDCUrl;
  }

  public void setToopConnectorDCUrl (String toopConnectorDCUrl) {

    this.toopConnectorDCUrl = toopConnectorDCUrl;
  }

  public String getToopConnectorDPUrl () {

    return toopConnectorDPUrl;
  }

  public void setToopConnectorDPUrl (String toopConnectorDPUrl) {

    this.toopConnectorDPUrl = toopConnectorDPUrl;
  }
}
