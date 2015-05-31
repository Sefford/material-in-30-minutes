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

package com.sefford.material.sample.contacts.details.ui.views;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.sefford.brender.adapters.RendererAdapter;
import com.sefford.brender.interfaces.Renderable;
import com.sefford.material.sample.R;
import com.sefford.material.sample.common.model.Contact;
import com.sefford.material.sample.common.ui.components.LetterTileDrawable;
import com.sefford.material.sample.contacts.details.ui.model.Mail;
import com.sefford.material.sample.contacts.details.ui.model.Phone;
import com.sefford.material.sample.contacts.details.ui.model.Trolling;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * View component of {@link com.sefford.material.sample.contacts.details.ui.activities.ContactDetailsActivity ContactDetailsActivity}
 */
public class ContactDetailView {

    final RendererAdapter adapter;
    final List<Renderable> contactData;
    final LetterTileDrawable placeholder;
    final Resources resources;

    @InjectView(R.id.av_data)
    AdapterView avData;
    @InjectView(R.id.iv_cover)
    ImageView ivCover;

    Picasso picasso;

    public ContactDetailView(RendererAdapter adapter, List<Renderable> contactData, Resources resources) {
        this.adapter = adapter;
        this.contactData = contactData;
        this.contactData.add(new Trolling());
        this.resources = resources;
        placeholder = new LetterTileDrawable(resources);
    }

    public void bind(View view) {
        ButterKnife.inject(this, view);
        picasso = Picasso.with(view.getContext());
        avData.setAdapter(adapter);
        avData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                if (contactData.get(position) instanceof Phone) {
                    intent.setData(Uri.parse("tel:" + contactData.get(position).toString()));
                    view.getContext().startActivity(intent);
                } else if (contactData.get(position) instanceof Mail) {
                    Uri data = Uri.parse(resources.getString(R.string.mail_uri,
                            contactData.get(position).toString(),
                            "Hey, this a mail sent from Material in 30 minutes sample app!",
                            "Check this cool sample app"));
                    intent.setType("plain/text");
                    intent.setData(data);
                    view.getContext().startActivity(Intent.createChooser(intent, ""));
                }
            }
        });
    }

    public void release() {
        ButterKnife.reset(this);
    }

    public void setCover(Contact contact, String color) {
        placeholder.setContactDetails(contact.getName(), color);
        picasso.load(contact.getPhoto())
                .placeholder(R.color.primary_pressed_50)
                .error(placeholder)
                .into(ivCover);
    }

    public void setMails(List<Mail> mails) {
        this.contactData.addAll(mails);
        adapter.notifyDataSetChanged();
    }

    public void setPhones(List<Phone> phones) {
        this.contactData.addAll(phones);
        adapter.notifyDataSetChanged();
    }
}