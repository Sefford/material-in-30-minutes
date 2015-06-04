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

package com.sefford.material.sample.contacts.details.ui.presenters;

import com.sefford.material.sample.common.injection.modules.LocalBusModule;
import com.sefford.material.sample.common.internal.Bus;
import com.sefford.material.sample.contacts.details.interactors.GetContact;
import com.sefford.material.sample.contacts.details.responses.GetContactResponse;
import com.sefford.material.sample.contacts.details.ui.views.ContactDetailView;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Presenter that absorbs all the UI-related lifecycle for {@link com.sefford.material.sample.contacts.details.ui.activities.ContactDetailsActivity ContactDetailActivity}
 */
public class ContactDetailPresenter {

    final Bus bus;
    final GetContact getContact;

    ContactDetailView view;

    long id;

    @Inject
    public ContactDetailPresenter(@Named(LocalBusModule.LOCAL_BUS) Bus bus, GetContact getContact) {
        this.bus = bus;
        this.getContact = getContact;
    }

    public void bind(ContactDetailView view) {
        this.view = view;
        this.bus.register(this);
        getContact.execute(bus, id);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void release() {
        this.view.release();
        this.view = null;
        this.bus.unregister(this);
    }

    public void onEventMainThread(GetContactResponse response) {
        this.view.setPhones(response.getPhones());
        this.view.setMails(response.getMails());
        this.view.addTrolling();
    }
}
