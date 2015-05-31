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

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.sefford.brender.adapters.RendererAdapter;
import com.sefford.brender.interfaces.Renderable;
import com.sefford.material.sample.R;
import com.sefford.material.sample.common.model.Contact;
import com.sefford.material.sample.contacts.details.ui.activities.ContactDetailsActivity;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

/**
 * View for {@link com.sefford.material.sample.contacts.list.ui.activities.ContactListActivity ContactListActivity}.
 */
public class ContactListView {

    final RendererAdapter adapter;
    final List<Renderable> contacts;

    @InjectView(R.id.av_data)
    AdapterView avData;

    @Inject
    public ContactListView(RendererAdapter adapter, List<Renderable> contacts) {
        this.adapter = adapter;
        this.contacts = contacts;
    }

    public void bind(View view) {
        ButterKnife.inject(this, view);
        avData.setAdapter(adapter);
        avData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), ContactDetailsActivity.class);
                intent.putExtra(ContactDetailsActivity.EXTRA_ID, ((Contact) contacts.get(position)).getId());
                intent.putExtra(ContactDetailsActivity.EXTRA_COLOR, Integer.toString(position));
                view.getContext().startActivity(intent);
            }
        });
    }

    public void release() {
        ButterKnife.reset(this);
    }

    public void setContacts(Collection<Contact> contacts) {
        this.contacts.addAll(contacts);
        adapter.notifyDataSetChanged();
    }
}
