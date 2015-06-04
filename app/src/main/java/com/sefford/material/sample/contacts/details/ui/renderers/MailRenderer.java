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

package com.sefford.material.sample.contacts.details.ui.renderers;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.view.View;
import com.sefford.material.sample.R;
import com.sefford.material.sample.contacts.details.ui.model.ContactData;

/**
 * Renderer for {@link ContactData Mail} elements
 */
public class MailRenderer extends ContactDataRenderer {

    final Resources resources;

    public MailRenderer(View itemView, Resources resources) {
        super(itemView);
        this.resources = resources;
    }

    @Override
    protected View.OnClickListener createElementListener(final String element) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse(resources.getString(R.string.mail_uri,
                        element,
                        "Hey, this a mail sent from Material in 30 minutes sample app!",
                        "Check this cool sample app"));
                intent.setType("plain/text");
                intent.setData(data);
                view.getContext().startActivity(Intent.createChooser(intent, ""));
            }
        };
    }

    @Override
    public int getId() {
        return R.layout.listitem_mail;
    }
}
