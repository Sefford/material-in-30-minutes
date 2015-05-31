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

package com.sefford.material.sample.contacts.details.ui.model;

import com.sefford.brender.interfaces.Renderable;
import com.sefford.material.sample.R;

/**
 * UI Model for Mail classes.
 */
public class Mail implements Renderable {
    final String mail;

    public Mail(String mail) {
        this.mail = mail;
    }

    @Override
    public int getRenderableId() {
        return R.layout.listitem_mail;
    }

    @Override
    public String toString() {
        return mail;
    }
}
