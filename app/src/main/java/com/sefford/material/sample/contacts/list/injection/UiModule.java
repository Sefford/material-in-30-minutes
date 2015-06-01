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

package com.sefford.material.sample.contacts.list.injection;

import android.content.res.Resources;
import com.sefford.brender.adapters.RecyclerRendererAdapter;
import com.sefford.brender.data.RecyclerAdapterData;
import com.sefford.brender.interfaces.Postable;
import com.sefford.brender.interfaces.Renderable;
import com.sefford.material.sample.common.injection.modules.LocalBusModule;
import com.sefford.material.sample.common.ui.renderers.MaterialRendererFactory;
import com.sefford.material.sample.contacts.list.ui.activities.ContactListActivity;
import com.sefford.material.sample.contacts.list.ui.views.ContactListView;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import java.util.ArrayList;

/**
 * UI Module for injection of {@link ContactListActivity ContactListActivity}
 */
@Module(injects = {ContactListActivity.class},
        library = true,
        complete = false)
public class UiModule {

    @Provides
    public ContactListView providePresenter(@Named(LocalBusModule.LOCAL_BUS) Postable bus, MaterialRendererFactory factory,
                                            Resources resources) {
        final ArrayList<Renderable> renderables = new ArrayList<Renderable>();
        return new ContactListView(new RecyclerRendererAdapter(new RecyclerAdapterData(renderables), factory, bus), renderables, resources);
    }
}
