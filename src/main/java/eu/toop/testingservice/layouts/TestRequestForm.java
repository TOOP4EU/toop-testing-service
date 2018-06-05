package eu.toop.testingservice.layouts;

import com.helger.datetime.util.PDTXMLConverter;
import com.vaadin.data.Binder;
import com.vaadin.ui.*;
import eu.toop.commons.codelist.EPredefinedDocumentTypeIdentifier;
import eu.toop.commons.codelist.EPredefinedProcessIdentifier;
import eu.toop.commons.concept.ConceptValue;
import eu.toop.commons.dataexchange.TDEAddressType;
import eu.toop.commons.dataexchange.TDEDataRequestSubjectType;
import eu.toop.commons.dataexchange.TDENaturalPersonType;
import eu.toop.commons.jaxb.ToopXSDHelper;
import eu.toop.iface.ToopInterfaceClient;
import eu.toop.testingservice.bean.TestCase;
import eu.toop.testingservice.bean.TestRequest;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

public class TestRequestForm extends VerticalLayout {

  private Binder<TestRequest> binder = new Binder<> (TestRequest.class);

  private final ToopConnectorDetailsForm toopConnectorDetailsForm;
  private final IdentityForm identityForm;
  private final ComboBox<TestCase> testSelector = new ComboBox<> ("Select test case");
  private final Button executeTestCaseButton = new Button ("Execute test case!");
  private final ProgressBar spinner = new ProgressBar ();

  public TestRequestForm (final TestRequest testRequest, final boolean readOnly) {

    toopConnectorDetailsForm = new ToopConnectorDetailsForm (testRequest.getToopConnectorDetails (), false);

    identityForm = new IdentityForm (testRequest.getIdentity (), false);

    List<TestCase> testCases = new ArrayList<> ();
    testCases.add (new TestCase ("TestCase.Name 1", "TestCase.Description 1", "TestCase.AsicName 1"));
    testCases.add (new TestCase ("TestCase.Name 2", "TestCase.Description 2", "TestCase.AsicName 2"));
    testCases.add (new TestCase ("TestCase.Name 3", "TestCase.Description 3", "TestCase.AsicName 3"));

    testSelector.setItems (testCases);
    testSelector.setPlaceholder ("Select a test");
    testSelector.setItemCaptionGenerator (TestCase::getName);
    testSelector.setEmptySelectionAllowed (false);

    binder.bind (testSelector, TestRequest::getTestCase, TestRequest::setTestCase);
    binder.setBean(testRequest);

    spinner.setStyleName ("spinner");
    spinner.setIndeterminate (true);
    spinner.setVisible (false);
    spinner.setCaption ("Please wait while your request for data is processed...");

    executeTestCaseButton.addClickListener ((Button.ClickListener) clickEvent -> {
      spinner.setVisible (true);
      executeTestCaseButton.setVisible (false);

      executeTestRequest(testRequest);

      new java.util.Timer().schedule(
          new java.util.TimerTask() {
            @Override
            public void run() {
              spinner.setVisible (false);
              executeTestCaseButton.setVisible (false);
              addComponent (new Label ("Test case timed out..."));
            }
          },
          5000
      );
    });

    addComponent (toopConnectorDetailsForm);
    addComponent (identityForm);
    addComponent (testSelector);
    addComponent (executeTestCaseButton);
    addComponent (spinner);
  }

  public void executeTestRequest(TestRequest testRequest) {

    // Send the request to the Message-Processor
    try {
      final List<ConceptValue> conceptList = new ArrayList<> ();

      conceptList.add (new ConceptValue ("http://example.register.fre/freedonia-business-register",
          "FreedoniaAddress"));
      conceptList.add (new ConceptValue ("http://example.register.fre/freedonia-business-register",
          "FreedoniaSSNumber"));
      conceptList.add (new ConceptValue ("http://example.register.fre/freedonia-business-register",
          "FreedoniaBusinessCode"));
      conceptList.add (new ConceptValue ("http://example.register.fre/freedonia-business-register",
          "FreedoniaVATNumber"));
      conceptList.add (new ConceptValue ("http://example.register.fre/freedonia-business-register",
          "FreedoniaCompanyType"));
      conceptList.add (new ConceptValue ("http://example.register.fre/freedonia-business-register",
          "FreedoniaRegistrationDate"));
      conceptList.add (new ConceptValue ("http://example.register.fre/freedonia-business-register",
          "FreedoniaRegistrationNumber"));
      conceptList.add (new ConceptValue ("http://example.register.fre/freedonia-business-register",
          "FreedoniaCompanyName"));
      conceptList.add (new ConceptValue ("http://example.register.fre/freedonia-business-register",
          "FreedoniaCompanyNaceCode"));
      conceptList.add (new ConceptValue ("http://example.register.fre/freedonia-business-register",
          "FreedoniaActivityDeclaration"));
      conceptList.add (new ConceptValue ("http://example.register.fre/freedonia-business-register",
          "FreedoniaRegistrationAuthority"));

      final TDEDataRequestSubjectType aDS = new TDEDataRequestSubjectType ();
      aDS.setDataRequestSubjectTypeCode (ToopXSDHelper.createCode ("12345"));
      {
        final TDENaturalPersonType aNP = new TDENaturalPersonType ();
        aNP.setPersonIdentifier (ToopXSDHelper.createIdentifier (testRequest.getIdentity ().getIdentifier ()));
        aNP.setFamilyName (ToopXSDHelper.createText (testRequest.getIdentity ().getFamilyName ()));
        aNP.setFirstName (ToopXSDHelper.createText (testRequest.getIdentity ().getFirstName ()));
        aNP.setBirthDate (PDTXMLConverter.getXMLCalendarDateNow ()); // TODO: Use testRequest.getIdentity().getBirthDate() instead.
        final TDEAddressType aAddress = new TDEAddressType ();
        // Destination country to use
        aAddress.setCountryCode (ToopXSDHelper.createCode ("SV"));
        aNP.setNaturalPersonLegalAddress (aAddress);
        aDS.setNaturalPerson (aNP);
      }

      ToopInterfaceClient.createRequestAndSendToToopConnector (aDS,
          ToopXSDHelper.createIdentifier ("iso6523-actorid-upis",
              "9999:freedonia"),
          "SV",
          EPredefinedDocumentTypeIdentifier.REQUEST_REGISTEREDORGANIZATION,
          EPredefinedProcessIdentifier.DATAREQUESTRESPONSE, conceptList);
    } catch (final IOException ex) {
      // Convert from checked to unchecked
      throw new UncheckedIOException (ex);
    }
  }

}
