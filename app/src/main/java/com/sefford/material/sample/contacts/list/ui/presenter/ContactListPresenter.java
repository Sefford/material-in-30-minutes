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

package com.sefford.material.sample.contacts.list.ui.presenter;

import android.content.Context;
import com.sefford.material.sample.common.injection.modules.ApplicationModule;
import com.sefford.material.sample.common.injection.modules.LocalBusModule;
import com.sefford.material.sample.common.interactors.GetContacts;
import com.sefford.material.sample.common.internal.Bus;
import com.sefford.material.sample.contacts.list.responses.GetPhoneContactsResponse;
import com.sefford.material.sample.contacts.list.ui.views.ContactListView;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Presenter that absorbs the Android lifecycle and interactions with the business part of the app for
 * {@link com.sefford.material.sample.contacts.list.ui.activities.ContactListActivity ContactListActivity}
 */
public class ContactListPresenter {

    final GetContacts getContacts;
    final Bus bus;
    final Context context;

    ContactListView view;

    @Inject
    public ContactListPresenter(@Named(ApplicationModule.APPLICATION) Context context, GetContacts getContacts, @Named(LocalBusModule.LOCAL_BUS) Bus bus) {
        this.context = context;
        this.getContacts = getContacts;
        this.bus = bus;
    }

    public void bind(ContactListView view) {
        this.view = view;
        this.bus.register(this);
        this.getContacts.execute(bus, context);
    }

    public void release() {
        this.view.release();
        this.bus.unregister(this);
        this.view = null;
    }

    public void onEventMainThread(GetPhoneContactsResponse response) {
        this.view.setContacts(response.getContacts());
    }
}
