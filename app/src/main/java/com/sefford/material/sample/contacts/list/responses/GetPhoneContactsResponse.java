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

package com.sefford.material.sample.contacts.list.responses;

import com.sefford.kor.responses.Response;
import com.sefford.material.sample.common.model.Contact;

import java.util.Collection;

/**
 * Response element for GetPhoneContacts which contains all the available contacts of the phone.
 */
public class GetPhoneContactsResponse implements Response {

    final Collection<Contact> contacts;

    public GetPhoneContactsResponse(Collection<Contact> contacts) {
        this.contacts = contacts;
    }

    public Collection<Contact> getContacts() {
        return contacts;
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public boolean isFromNetwork() {
        return false;
    }
}