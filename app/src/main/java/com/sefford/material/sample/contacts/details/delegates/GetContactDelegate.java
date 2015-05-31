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

package com.sefford.material.sample.contacts.details.delegates;

import com.sefford.kor.interactors.interfaces.CacheDelegate;
import com.sefford.kor.repositories.interfaces.Repository;
import com.sefford.material.sample.common.model.Contact;
import com.sefford.material.sample.contacts.details.errors.GetContactError;
import com.sefford.material.sample.contacts.details.responses.GetContactResponse;
import com.sefford.material.sample.contacts.details.ui.model.Mail;
import com.sefford.material.sample.contacts.details.ui.model.Phone;

import java.util.ArrayList;
import java.util.List;

/**
 * Retrieves a a {@link Contact Contact} from the {@link com.sefford.material.sample.common.repository.ContactRepository ContactCache}
 * and extracts the relevant information to be processed by the UI.
 */
public class GetContactDelegate implements CacheDelegate<GetContactResponse, GetContactError> {

    static final String NAME = "GetContact(%d)";

    final Repository<Long, Contact> repository;
    final long id;

    public GetContactDelegate(Repository<Long, Contact> repository, long id) {
        this.repository = repository;
        this.id = id;
    }

    @Override
    public boolean isCacheValid() {
        return true;
    }

    @Override
    public GetContactResponse execute() throws Exception {
        final Contact contact = repository.get(id);
        final List<Phone> phones = new ArrayList<>();
        for (String phone : contact.getPhones()) {
            phones.add(new Phone(phone));
        }
        final List<Mail> mails = new ArrayList<>();
        for (String mail : contact.getEmails()) {
            mails.add(new Mail(mail));
        }
        return new GetContactResponse(contact, phones, mails);
    }

    @Override
    public GetContactError composeErrorResponse(Exception error) {
        return new GetContactError();
    }

    @Override
    public String getInteractorName() {
        return String.format(NAME, id);
    }
}
