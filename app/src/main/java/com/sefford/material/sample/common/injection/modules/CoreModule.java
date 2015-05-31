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

import android.content.Context;
import android.content.res.Resources;
import com.sefford.brender.interfaces.Postable;
import com.sefford.kor.repositories.interfaces.Repository;
import com.sefford.material.sample.BuildConfig;
import com.sefford.material.sample.common.internal.Bus;
import com.sefford.material.sample.common.model.Contact;
import com.sefford.material.sample.common.repository.ContactRepository;
import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Injection module that handles the main components that make the application work, as the execution Threadpool,
 * application Bus and Cache system.
 * <p/>
 * Note that for a more scalable architecture, some parts of this could be extracted to their own modules, as the cache
 * components.
 */
@Module(library = true,
        complete = false)
public class CoreModule {

    public static final String SYSTEM = "System";

    @Provides
    @Singleton
    public ThreadPoolExecutor provideNetworkingExecutor() {
        return new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors() * 2,
                120, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    }

    @Provides
    public EventBus provideEventBus() {
        return EventBus.builder().throwSubscriberException(BuildConfig.DEBUG).build();
    }

    @Provides
    public Resources provideResources(@Named(ApplicationModule.APPLICATION) Context context) {
        return context.getResources();
    }

    @Provides
    @Singleton
    @Named(SYSTEM)
    public Bus provideSystemBus(Bus bus) {
        return bus;
    }

    @Provides
    @Singleton
    @Named(SYSTEM)
    public Postable provideSystemPostable(@Named(SYSTEM) Bus bus) {
        return bus;
    }

    @Provides
    @Singleton
    public Repository<Long, Contact> provideContactCache() {
        return new ContactRepository();
    }

}
