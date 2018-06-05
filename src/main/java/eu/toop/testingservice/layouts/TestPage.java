package eu.toop.testingservice.layouts;

import com.vaadin.ui.*;

import eu.toop.testingservice.bean.Identity;
import eu.toop.testingservice.bean.TestCase;
import eu.toop.testingservice.bean.TestRequest;
import eu.toop.testingservice.bean.ToopConnectorDetails;

import java.time.LocalDate;

public class TestPage extends VerticalLayout {

  public TestPage () {

    setCaption ("TestPage");

    TestRequestForm testRequestForm = new TestRequestForm (getMockupTestRequestBean(), false);

    addComponent (testRequestForm);
  }

  public TestRequest getMockupTestRequestBean() {
    TestRequest testRequest = new TestRequest ();

    final ToopConnectorDetails toopConnectorDetails = new ToopConnectorDetails ();
    toopConnectorDetails.setToopConnectorDCUrl ("http://193.10.8.213:9083/from-dc");
    toopConnectorDetails.setToopConnectorDPUrl ("http://193.10.8.213:8083/from-dp");
    testRequest.setToopConnectorDetails (toopConnectorDetails);

    final Identity identity = new Identity ();
    identity.setFirstName ("Maximillian");
    identity.setFamilyName ("Stern");
    identity.setBirthPlace ("Sample birth place");
    identity.setBirthDate (LocalDate.of (2018, 05, 10));
    identity.setIdentifier ("SV/GF/12345");
    identity.setNationality ("SV");
    testRequest.setIdentity (identity);

    final TestCase testCase = new TestCase ("TestCase.Name 2",
        "TestCase.Description 2",
        "TestCase.AsicName 2");
    testRequest.setTestCase (testCase);

    return testRequest;
  }
}
