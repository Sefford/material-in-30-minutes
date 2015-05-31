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

package com.sefford.material.sample.common.injection.modules;

import com.sefford.brender.interfaces.Postable;
import com.sefford.material.sample.common.internal.Bus;
import com.sefford.material.sample.contacts.details.ui.activities.ContactDetailsActivity;
import com.sefford.material.sample.contacts.list.ui.activities.ContactListActivity;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Handles the local, independent bus per screen that communicates the business logic components and its views and
 * presenters
 */
@Module(injects = {ContactListActivity.class,
        ContactDetailsActivity.class},
        library = true,
        complete = false)
public class LocalBusModule {

    public static final String LOCAL_BUS = "Local";

    @Singleton
    @Provides
    @Named(LOCAL_BUS)
    public Bus provideLocalBus(Bus bus) {
        return bus;
    }

    @Singleton
    @Provides
    @Named(LOCAL_BUS)
    public Postable provideLocalPostable(@Named(LOCAL_BUS) Bus bus) {
        return bus;
    }
}
