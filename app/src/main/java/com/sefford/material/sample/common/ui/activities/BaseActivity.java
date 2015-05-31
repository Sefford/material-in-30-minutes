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

package com.sefford.material.sample.common.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import com.sefford.material.sample.common.ui.application.MaterialApplication;
import dagger.ObjectGraph;

/**
 * Base activity with injection facilities
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ObjectGraph originalGraph = ((MaterialApplication) getApplication()).getGraph();
        extend(originalGraph, getExtensions(originalGraph, getIntent().getExtras())).inject(this);
    }

    /**
     * Extends and returns the ObjectGraph with ad-hoc extensions or returns the original one for dynamic injection
     *
     * @param graph      Original ObjectGraph
     * @param extensions Potential ad-hoc extensions.
     * @return The ObjectGraph
     */
    public ObjectGraph extend(ObjectGraph graph, Object[] extensions) {
        return extensions.length == 0 ? graph : graph.plus(extensions);
    }

    /**
     * This allows the child Activities to configure their own extension modules to fit their needs
     *
     * @param originalGraph Original ObjectGraph, in case it requires some decision-making from higher-level compnents
     * @param arguments     Configuration arguments of the Activity itself
     * @return Array with extensions
     */
    protected abstract Object[] getExtensions(ObjectGraph originalGraph, Bundle arguments);
}