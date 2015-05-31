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

package com.sefford.material.sample.common.interactors;

import android.content.Context;
import com.sefford.kor.common.interfaces.Loggable;
import com.sefford.kor.common.interfaces.Postable;
import com.sefford.kor.interactors.CacheInteractor;
import com.sefford.kor.interactors.Interactor;
import com.sefford.kor.interactors.StandaloneInteractor;
import com.sefford.kor.repositories.interfaces.Repository;
import com.sefford.material.sample.common.model.Contact;
import com.sefford.material.sample.contacts.list.delegates.GetPhoneContactsDelegate;

import javax.inject.Inject;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Fetches the contacts from the phone's Content Resolver
 */
public class GetContacts extends StandaloneInteractor<Context> {

    final Repository<Long, Contact> cache;

    /**
     * Creates a new instance of the Standalone Interactor.
     *
     * @param log      Logging facilities
     * @param executor Execution element
     * @param cache Contact cache for saving the elements
     */
    @Inject
    public GetContacts(Loggable log, ThreadPoolExecutor executor, Repository<Long, Contact> cache) {
        super(log, executor);
        this.cache = cache;
    }

    @Override
    protected Interactor instantiateInteractor(Postable bus, Context context) {
        return new CacheInteractor(bus, log, new GetPhoneContactsDelegate(context, cache));
    }
}
