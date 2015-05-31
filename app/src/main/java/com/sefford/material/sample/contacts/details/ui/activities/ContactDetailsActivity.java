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

package com.sefford.material.sample.contacts.details.ui.activities;

import android.os.Bundle;
import com.sefford.material.sample.R;
import com.sefford.material.sample.common.injection.modules.LocalBusModule;
import com.sefford.material.sample.common.ui.activities.BaseActivity;
import com.sefford.material.sample.contacts.details.injection.UiModule;
import com.sefford.material.sample.contacts.details.ui.presenters.ContactDetailPresenter;
import com.sefford.material.sample.contacts.details.ui.views.ContactDetailView;
import dagger.ObjectGraph;

import javax.inject.Inject;

/**
 * Contact Details Activity.
 */
public class ContactDetailsActivity extends BaseActivity {

    public static final String EXTRA_ID = "extra_id";
    public static final String EXTRA_COLOR = "extra_color";

    @Inject
    ContactDetailView view;
    @Inject
    ContactDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_detail);
    }

    @Override
    protected void onResume() {
        super.onResume();
        view.bind(getWindow().findViewById(android.R.id.content));
        presenter.setId(getIntent().getLongExtra(EXTRA_ID, 0));
        presenter.setUserColor(getIntent().getStringExtra(EXTRA_COLOR));
        presenter.bind(view);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.release();
    }

    @Override
    protected Object[] getExtensions(ObjectGraph originalGraph, Bundle arguments) {
        final Object[] extensions = new Object[2];
        extensions[0] = new LocalBusModule();
        extensions[1] = new UiModule();
        return extensions;
    }
}
