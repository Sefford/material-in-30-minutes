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
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.*;
import android.view.MenuItem;
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

    static final int[] ICONS = {R.drawable.ic_grid_white, R.drawable.ic_dashboard_white, R.drawable.ic_list_white};
    final RecyclerRendererAdapter adapter;
    final List<Renderable> contacts;
    final Resources resources;

    @InjectView(R.id.rv_data)
    RecyclerView rvData;
    @InjectView(R.id.tb_main)
    Toolbar toolbar;
    @InjectView(R.id.cl_main)
    CoordinatorLayout clMain;

    int layoutMode = 0;
    private DividerItemDecoration dividerDecoration;

    @Inject
    public ContactListView(RecyclerRendererAdapter adapter, List<Renderable> contacts, Resources resources) {
        this.adapter = adapter;
        this.contacts = contacts;
        this.resources = resources;
    }

    public void bind(View view) {
        ButterKnife.inject(this, view);

        toolbar.setTitle(R.string.app_name);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu);
        toolbar.setOnMenuItemClickListener(getMenuListener());

        dividerDecoration = new DividerItemDecoration(resources.getDrawable(R.drawable.list_horizontal_divider), DividerItemDecoration.VERTICAL_LIST);

        rvData.setAdapter(adapter);
        rvData.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        rvData.addItemDecoration(dividerDecoration);
        rvData.setItemAnimator(new DefaultItemAnimator());
    }

    Toolbar.OnMenuItemClickListener getMenuListener() {
        return new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                layoutMode++;
                for (Renderable contact : contacts) {
                    ((Contact) contact).setLayoutMode(Contact.LayoutMode.values()[layoutMode % Contact.LayoutMode.values().length]);
                }
                switch (Contact.LayoutMode.values()[layoutMode % Contact.LayoutMode.values().length]) {
                    case LIST:
                        rvData.setLayoutManager(new LinearLayoutManager(toolbar.getContext(), LinearLayoutManager.VERTICAL, false));
                        break;
                    case GRID:
                        rvData.setLayoutManager(new GridLayoutManager(toolbar.getContext(), 2));
                        break;
                    case STAGGER:
                        rvData.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                        break;
                }
                if (Contact.LayoutMode.LIST.equals(Contact.LayoutMode.values()[layoutMode % Contact.LayoutMode.values().length])) {
                    rvData.addItemDecoration(dividerDecoration);
                } else {
                    rvData.removeItemDecoration(dividerDecoration);
                }
                menuItem.setIcon(ICONS[layoutMode % ICONS.length]);
                return true;
            }
        };
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
