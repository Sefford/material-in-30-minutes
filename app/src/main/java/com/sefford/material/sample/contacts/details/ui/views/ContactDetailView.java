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

import android.content.res.Resources;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.sefford.brender.adapters.RecyclerRendererAdapter;
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

    final RecyclerRendererAdapter adapter;
    final List<Renderable> contactData;
    final LetterTileDrawable placeholder;
    final Resources resources;

    @InjectView(R.id.rv_data)
    RecyclerView rvData;
    @InjectView(R.id.iv_cover)
    ImageView ivCover;
    @InjectView(R.id.tb_main)
    Toolbar toolbar;
    @InjectView(R.id.ctl_container)
    CollapsingToolbarLayout ctlContainer;

    Picasso picasso;

    String name;
    String color;

    public ContactDetailView(RecyclerRendererAdapter adapter, List<Renderable> contactData, Resources resources) {
        this.adapter = adapter;
        this.contactData = contactData;
        this.resources = resources;
        this.placeholder = new LetterTileDrawable(resources, 0);
    }

    public void bind(View view) {
        ButterKnife.inject(this, view);
        picasso = Picasso.with(view.getContext());
        ctlContainer.setTitle(name);
        toolbar.setTitle(name);
        rvData.setAdapter(adapter);
        rvData.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        ivCover.setBackground(placeholder);
    }

    public void configurePlaceholder(String name, String color) {
        this.name = name;
        this.color = color;
        placeholder.setContactDetails(name, color);
    }

    public void release() {
        ButterKnife.reset(this);
    }

    public void setCover(Contact contact) {
        picasso.load(contact.getPhoto())
                .placeholder(placeholder)
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

    public void addTrolling() {
        this.contactData.add(new Trolling());
        adapter.notifyDataSetChanged();
    }
}
