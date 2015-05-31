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

package com.sefford.material.sample.common.ui.renderers;

import android.view.View;
import com.sefford.brender.interfaces.Postable;
import com.sefford.brender.interfaces.Renderer;
import com.sefford.brender.interfaces.RendererFactory;
import com.sefford.material.sample.R;
import com.sefford.material.sample.contacts.details.ui.renderers.MailRenderer;
import com.sefford.material.sample.contacts.details.ui.renderers.PhoneRenderer;
import com.sefford.material.sample.contacts.details.ui.renderers.TrollingRenderer;
import com.sefford.material.sample.contacts.list.ui.renderers.ContactRenderer;

import javax.inject.Inject;

/**
 * Renderer factory to handle the rendererers. We have a single one
 * for all the application, but we could create an ad-hoc version for
 * each screen.
 */
public class MaterialRendererFactory implements RendererFactory {

    @Inject
    public MaterialRendererFactory() {
    }

    @Override
    public Renderer getRenderer(int id, Postable postable, View view) {
        switch (id) {
            case R.layout.listitem_contact:
                return new ContactRenderer(view.getResources(), view);
            case R.layout.listitem_mail:
                return new MailRenderer(view);
            case R.layout.listitem_phone:
                return new PhoneRenderer(view);
            case R.layout.listitem_trolling:
                return new TrollingRenderer();
        }
        return null;
    }
}
