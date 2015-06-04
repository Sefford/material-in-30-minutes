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

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.sefford.brender.interfaces.Renderer;
import com.sefford.material.sample.R;
import com.sefford.material.sample.contacts.details.ui.model.ContactData;

/**
 * Created by sefford on 4/06/15.
 */
public abstract class ContactDataRenderer extends RecyclerView.ViewHolder implements Renderer<ContactData> {

    @InjectView(R.id.ll_container)
    LinearLayout llContainer;

    LayoutInflater inflater;

    public ContactDataRenderer(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
        inflater = LayoutInflater.from(llContainer.getContext());
    }

    @Override
    public void hookUpListeners(ContactData contactData) {
        for (int i = 0; i < llContainer.getChildCount(); i++) {
            llContainer.getChildAt(i).setOnClickListener(createElementListener(contactData.getElements().get(i)));
        }
    }

    protected abstract View.OnClickListener createElementListener(String element);

    @Override
    public void render(ContactData contactData, int pos, boolean first, boolean last) {
        llContainer.removeAllViews();
        for (String element : contactData.getElements()) {
            final TextView view = (TextView) inflater.inflate(R.layout.listitem_contact_data_element, llContainer, false);
            view.setText(element);
            view.setCompoundDrawablesWithIntrinsicBounds(0, 0, contactData.getResource(), 0);
            llContainer.addView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    @Override
    public int getId() {
        return R.layout.listitem_contact_data_element;
    }
}
