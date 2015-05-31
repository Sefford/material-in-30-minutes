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

package com.sefford.material.sample.contacts.list.delegates;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import com.sefford.kor.interactors.interfaces.CacheDelegate;
import com.sefford.kor.repositories.interfaces.Repository;
import com.sefford.material.sample.contacts.list.errors.GetPhoneContactsError;
import com.sefford.material.sample.common.model.Contact;
import com.sefford.material.sample.contacts.list.responses.GetPhoneContactsResponse;

import java.util.*;

/**
 * Retrieves the Contacts from the Phone provider and returns it on a {@link Contact Contact} form.
 *
 * Probably not the most elegant algorithm out there, basically queries all the phones and mails from the provider and
 * merges them into a single Contact.
 */
public class GetPhoneContactsDelegate implements CacheDelegate<GetPhoneContactsResponse, GetPhoneContactsError> {

    final Context context;
    final Repository<Long, Contact> cache;

    public GetPhoneContactsDelegate(Context context, Repository<Long, Contact> cache) {
        this.context = context;
        this.cache = cache;
    }

    @Override
    public boolean isCacheValid() {
        return true;
    }

    @Override
    public GetPhoneContactsResponse execute() throws Exception {
        Map<Long, Contact> contactList = new HashMap<Long, Contact>();
        Cursor phones = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC");
        while (phones.moveToNext()) {
            long id = Long.valueOf(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)));
            String phone = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER));
            if (phone != null && isVisible(phones)) {
                final Contact contact = getContact(contactList, phones, id);
                contact.addPhone(phone);
                contactList.put(contact.getId(), contact);
            }
        }
        phones.close();
        Cursor emails = context.getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                null, null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC");
        while (emails.moveToNext()) {
            long id = Long.valueOf(emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.CONTACT_ID)));
            String mail = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
            if (mail != null && isVisible(emails)) {
                final Contact contact = getContact(contactList, emails, id);
                contact.addEmail(mail);
                contactList.put(contact.getId(), contact);
            }
        }
        emails.close();

        final List<Contact> contacts = new ArrayList<Contact>(contactList.values());
        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact lhs, Contact rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
        cache.saveAll(contacts);
        final GetPhoneContactsResponse response = new GetPhoneContactsResponse(contacts);
        return response;
    }

    private boolean isVisible(Cursor cursor) {
        return cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.IN_VISIBLE_GROUP)) == 1;
    }

    Contact getContact(Map<Long, Contact> contactList, Cursor phones, long id) {
        Contact contact;
        if (contactList.containsKey(id)) {
            contact = contactList.get(id);
        } else {
            contact = new Contact();
            contact.setId(id);
            contact.setName(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
        }
        return contact;
    }

    @Override
    public GetPhoneContactsError composeErrorResponse(Exception error) {
        return new GetPhoneContactsError();
    }

    @Override
    public String getInteractorName() {
        return "GetPhoneContactsDelegate";
    }
}
