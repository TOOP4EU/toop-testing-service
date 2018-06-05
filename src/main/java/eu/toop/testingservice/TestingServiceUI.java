/**
 * Copyright (C) 2018 toop.eu
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.toop.testingservice;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.*;
import com.vaadin.ui.UI;

import eu.toop.testingservice.endpoints.TestingServiceToopInterfaceDC;
import eu.toop.testingservice.endpoints.TestingServiceToopInterfaceDP;
import eu.toop.testingservice.view.TestView;
import eu.toop.iface.ToopInterfaceManager;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@SuppressWarnings ("javadoc")
@Theme ("TestingServiceTheme")
public class TestingServiceUI extends UI {

  private Navigator navigator;

  @Override
  protected void init (final VaadinRequest vaadinRequest) {

    setPollInterval (1000);
    getPage ().setTitle ("TOOP Testing Service");

    ToopInterfaceManager.setInterfaceDC (new TestingServiceToopInterfaceDC (this));
    ToopInterfaceManager.setInterfaceDP (new TestingServiceToopInterfaceDP (this));

    navigator = new Navigator (this, this);
    navigator.addView ("", new TestView ());
    navigator.addView ("testing", new TestView ());

  }

  @Override
  public void close () {
    super.close ();
  }

  @WebServlet (urlPatterns = {"/testing/*", "/VAADIN/*"}, asyncSupported = true)
  @VaadinServletConfiguration (ui = TestingServiceUI.class, productionMode = false)
  public static class DCUIServlet extends VaadinServlet {

  }
}
