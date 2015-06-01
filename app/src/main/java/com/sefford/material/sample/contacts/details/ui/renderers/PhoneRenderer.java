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
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.sefford.brender.interfaces.Renderer;
import com.sefford.material.sample.R;
import com.sefford.material.sample.contacts.details.ui.model.Phone;

/**
 * Renderer for {@link Phone Phone} elements
 */
public class PhoneRenderer extends RecyclerView.ViewHolder implements Renderer<Phone> {

    final TextView tvPhone;

    public PhoneRenderer(View view) {
        super(view);
        this.tvPhone = (TextView) view;
    }

    @Override
    public void hookUpListeners(final Phone phone) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel:" + phone.toString()));
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void render(Phone phone, int pos, boolean first, boolean last) {
        tvPhone.setText(phone.toString());
    }

    @Override
    public int getId() {
        return R.layout.listitem_phone;
    }
}
