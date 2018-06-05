package eu.toop.testingservice.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.VerticalLayout;
import eu.toop.testingservice.layouts.TestPage;

public class TestView extends VerticalLayout implements View {

  public TestView () {
    addComponent (new TestPage ());
  }
}
