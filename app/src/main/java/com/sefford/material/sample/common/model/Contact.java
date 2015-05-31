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

package com.sefford.material.sample.common.model;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.ContactsContract;
import com.sefford.brender.interfaces.Renderable;
import com.sefford.kor.repositories.interfaces.RepoElement;
import com.sefford.kor.repositories.interfaces.Updateable;
import com.sefford.material.sample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Contact model. As this is a simple sample app, I have decided not to divide UI Model with business model.
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class Contact implements Comparable<Contact>, Renderable, RepoElement<Long>, Updateable<Contact> {

    long id;
    Uri thumb;
    Uri photo;
    String name;
    List<String> phones = new ArrayList<String>();
    List<String> emails = new ArrayList<String>();

    @Override
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        final Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id);
        thumb = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
        photo = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.DISPLAY_PHOTO);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public void addPhone(String phone) {
        this.phones.add(phone);
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public void addEmail(String email) {
        this.emails.add(email);
    }

    public Uri getThumbnail() {
        return thumb;
    }

    public Uri getPhoto() {
        return photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != contact.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public int compareTo(Contact another) {
        return name.compareTo(another.name);
    }

    @Override
    public int getRenderableId() {
        return R.layout.listitem_contact;
    }

    @Override
    public Contact update(Contact other) {
        return this;
    }
}
