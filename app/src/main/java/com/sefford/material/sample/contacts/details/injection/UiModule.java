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

package com.sefford.material.sample.contacts.details.injection;

import android.content.res.Resources;
import com.sefford.brender.adapters.RendererAdapter;
import com.sefford.brender.data.DefaultAdapterData;
import com.sefford.brender.interfaces.Postable;
import com.sefford.brender.interfaces.Renderable;
import com.sefford.material.sample.common.injection.modules.LocalBusModule;
import com.sefford.material.sample.common.ui.renderers.MaterialRendererFactory;
import com.sefford.material.sample.contacts.details.ui.activities.ContactDetailsActivity;
import com.sefford.material.sample.contacts.details.ui.views.ContactDetailView;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import java.util.ArrayList;

/**
 * UI Module for {@link ContactDetailsActivity ContactDetailsActivity}
 */
@Module(injects = {ContactDetailsActivity.class},
        library = true,
        complete = false)
public class UiModule {

    @Provides
    public ContactDetailView providePresenter(@Named(LocalBusModule.LOCAL_BUS) Postable bus, MaterialRendererFactory factory, Resources resources) {
        final ArrayList<Renderable> renderables = new ArrayList<Renderable>();
        return new ContactDetailView(new RendererAdapter(new DefaultAdapterData(renderables), factory, bus), renderables,
                resources);
    }
}
