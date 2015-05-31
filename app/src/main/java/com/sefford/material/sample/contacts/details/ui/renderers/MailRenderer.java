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

import android.view.View;
import android.widget.TextView;
import com.sefford.brender.interfaces.Renderer;
import com.sefford.material.sample.R;
import com.sefford.material.sample.contacts.details.ui.model.Mail;

/**
 * Renderer for {@link Mail Mail} elements
 */
public class MailRenderer implements Renderer<Mail> {

    final TextView tvMail;

    public MailRenderer(View view) {
        this.tvMail = (TextView) view;
    }

    @Override
    public void hookUpListeners(Mail mail) {

    }

    @Override
    public void render(Mail mail, int position, boolean first, boolean last) {
        tvMail.setText(mail.toString());
    }

    @Override
    public int getId() {
        return R.layout.listitem_mail;
    }
}