/*
 * Copyright (C) 2015 Saúl Díaz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sefford.material.sample.contacts.details.responses;

import com.sefford.kor.responses.Response;
import com.sefford.material.sample.common.model.Contact;
import com.sefford.material.sample.contacts.details.ui.model.ContactData;

/**
 * Contact Response with the information to populate the UI.
 */
public class GetContactResponse implements Response {

    final Contact contact;
    final ContactData phones;
    final ContactData mails;

    public GetContactResponse(Contact contact, ContactData phones, ContactData mails) {
        this.contact = contact;
        this.phones = phones;
        this.mails = mails;
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public boolean isFromNetwork() {
        return false;
    }

    public Contact getContact() {
        return contact;
    }

    public ContactData getPhones() {
        return phones;
    }

    public ContactData getMails() {
        return mails;
    }
}
