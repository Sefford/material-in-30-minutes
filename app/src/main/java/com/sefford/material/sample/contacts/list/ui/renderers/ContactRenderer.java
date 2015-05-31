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

package com.sefford.material.sample.contacts.list.ui.renderers;

import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.sefford.brender.interfaces.Renderer;
import com.sefford.material.sample.R;
import com.sefford.material.sample.common.model.Contact;
import com.sefford.material.sample.common.ui.components.LetterTileDrawable;
import com.sefford.material.sample.common.ui.transformations.RoundedBorderTransformation;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;


/**
 * Renderer for {@link Contact Contact} elements.
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class ContactRenderer implements Renderer<Contact> {

    final Picasso picasso;
    /**
     * Rounded corners transformation for the Avatar View
     */
    Transformation transformation;
    final LetterTileDrawable placeholder;
    /**
     * Avatar of the View
     */
    @InjectView(R.id.iv_avatar)
    ImageView ivAvatar;
    /**
     * Clickable area for follow button
     */
    @InjectView(R.id.tv_name)
    TextView tvName;

    public ContactRenderer(Resources resources, View view) {
        ButterKnife.inject(this, view);
        this.transformation = new RoundedBorderTransformation(resources.getDimensionPixelSize(R.dimen.avatar_rounded_corners));
        picasso = Picasso.with(view.getContext());
        placeholder = new LetterTileDrawable(view.getResources());
    }

    @Override
    public void hookUpListeners(final Contact renderable) {
        // Empty
    }

    @Override
    public void render(Contact renderable, int position, boolean first, boolean last) {
        placeholder.setContactDetails(renderable.getName(), Integer.toString(position));
        configureRequest(picasso.load(renderable.getThumbnail()));
        tvName.setText(renderable.getName());
    }

    void configureRequest(RequestCreator creator) {
        creator.placeholder(R.drawable.avatar_placeholder)
                .error(placeholder)
                .transform(transformation)
                .into(ivAvatar);
    }

    @Override
    public int getId() {
        return R.layout.listitem_contact;
    }

}