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
package eu.toop.testingservice.layouts;

import com.vaadin.data.Binder;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

import eu.toop.testingservice.bean.Identity;

public class IdentityForm extends FormLayout {

  private Binder<Identity> binder = new Binder<> (Identity.class);

  private final TextField firstName = new TextField ("First name");
  private final TextField familyName = new TextField ("Family Name");
  private final TextField birthPlace = new TextField ("Birth place");
  private final TextField identifier = new TextField ("Identifier");
  private final DateField birthDate = new DateField ("Birth date");
  private final TextField nationality = new TextField ("Nationality");

  public IdentityForm (final Identity identity, final boolean readOnly) {

    binder.bindInstanceFields(this);

    firstName.setReadOnly (readOnly);
    familyName.setReadOnly (readOnly);
    birthPlace.setReadOnly (readOnly);
    identifier.setReadOnly (readOnly);
    birthDate.setReadOnly (readOnly);
    nationality.setReadOnly (readOnly);

    addComponent (firstName);
    addComponent (familyName);
    addComponent (birthPlace);
    addComponent (identifier);
    addComponent (birthDate);
    addComponent (nationality);

    binder.setBean(identity);
  }
}
