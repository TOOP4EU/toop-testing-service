package eu.toop.testingservice.layouts;

import com.vaadin.data.Binder;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import eu.toop.testingservice.bean.ToopConnectorDetails;

public class ToopConnectorDetailsForm extends VerticalLayout {

  private Binder<ToopConnectorDetails> binder = new Binder<> (ToopConnectorDetails.class);

  private final TextField toopConnectorDCUrl = new TextField ("Toop Connector DC Url");
  private final TextField toopConnectorDPUrl = new TextField ("Toop Connector DP Url");

  public ToopConnectorDetailsForm (ToopConnectorDetails toopConnectorDetails, boolean readOnly) {

    binder.bindInstanceFields(this);

    toopConnectorDCUrl.setReadOnly (readOnly);
    toopConnectorDPUrl.setReadOnly (readOnly);

    addComponent (toopConnectorDCUrl);
    addComponent (toopConnectorDPUrl);

    binder.setBean(toopConnectorDetails);
  }
}
