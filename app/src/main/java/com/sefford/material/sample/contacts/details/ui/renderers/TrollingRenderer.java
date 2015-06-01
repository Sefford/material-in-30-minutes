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
import android.view.View;
import com.sefford.brender.interfaces.Renderer;
import com.sefford.material.sample.R;
import com.sefford.material.sample.contacts.details.ui.model.Trolling;

/**
 * Renderer for {@link Trolling Trolling} elements
 */
public class TrollingRenderer extends RecyclerView.ViewHolder implements Renderer<Trolling> {

    public TrollingRenderer(View itemView) {
        super(itemView);
    }

    @Override
    public void hookUpListeners(Trolling trolling) {

    }

    @Override
    public void render(Trolling trolling, int i, boolean b, boolean b1) {

    }

    @Override
    public int getId() {
        return R.layout.listitem_trolling;
    }
}
