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

import java.util.List;

/**
 * Created by sefford on 4/06/15.
 */
public class ContactData implements Renderable {

    final List<String> elements;
    final int resource;
    final int renderableId;

    public ContactData(List<String> elements, int resource, int renderableId) {
        this.elements = elements;
        this.resource = resource;
        this.renderableId = renderableId;
    }

    public List<String> getElements() {
        return elements;
    }

    public int getResource() {
        return resource;
    }


    @Override
    public int getRenderableId() {
        return renderableId;
    }
}
