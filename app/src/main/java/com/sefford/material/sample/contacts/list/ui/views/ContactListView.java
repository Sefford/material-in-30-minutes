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

package com.sefford.material.sample.contacts.list.ui.views;

import android.content.res.Resources;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.sefford.brender.adapters.RecyclerRendererAdapter;
import com.sefford.brender.interfaces.Renderable;
import com.sefford.material.sample.R;
import com.sefford.material.sample.common.model.Contact;
import com.sefford.material.sample.common.ui.components.DividerItemDecoration;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

/**
 * View for {@link com.sefford.material.sample.contacts.list.ui.activities.ContactListActivity ContactListActivity}.
 */
public class ContactListView {

    final RecyclerRendererAdapter adapter;
    final List<Renderable> contacts;
    final Resources resources;

    @InjectView(R.id.rv_data)
    RecyclerView rvData;
    @InjectView(R.id.tb_main)
    Toolbar toolbar;

    @Inject
    public ContactListView(RecyclerRendererAdapter adapter, List<Renderable> contacts, Resources resources) {
        this.adapter = adapter;
        this.contacts = contacts;
        this.resources = resources;
    }

    public void bind(View view) {
        ButterKnife.inject(this, view);
        toolbar.setTitle(R.string.app_name);
        rvData.setAdapter(adapter);
        rvData.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        rvData.addItemDecoration(new DividerItemDecoration(resources.getDrawable(R.drawable.list_horizontal_divider), DividerItemDecoration.VERTICAL_LIST));
        rvData.setItemAnimator(new DefaultItemAnimator());
    }

    public void release() {
        ButterKnife.reset(this);
    }

    public void setContacts(Collection<Contact> contacts) {
        if (this.contacts.isEmpty()) {
            this.contacts.addAll(contacts);
            adapter.notifyItemRangeInserted(0, contacts.size());
        }
    }
}
