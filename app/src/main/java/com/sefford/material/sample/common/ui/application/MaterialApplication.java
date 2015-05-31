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

package com.sefford.material.sample.common.ui.application;

import android.app.Application;
import com.sefford.material.sample.common.injection.modules.ApplicationModule;
import com.sefford.material.sample.common.injection.modules.DevelopmentLoggingModule;
import dagger.ObjectGraph;

/**
 * MaterialApplication application with the initialization of app-wide injection modules.
 */
public class MaterialApplication extends Application {


    /**
     * Object Injection Graph for development/production.
     * <p/>
     * We don't care this is static as the Application will live as long as the execution.
     */
    ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Dagger's Dependency injection object Graph
        objectGraph = initializeGraph();
        objectGraph.inject(this);
    }

    ObjectGraph initializeGraph() {
        return ObjectGraph.create(new ApplicationModule(this),
                new DevelopmentLoggingModule());
    }

    /**
     * Injection facility for the elements.
     *
     * @param instance Instance of the object to inject dependencies
     * @param <T>      Class that will be injected
     */
    public <T> void inject(T instance) {
        objectGraph.inject(instance);
    }

    /**
     * Provider facility for the elements.
     *
     * @param type Type of the instance to get
     * @param <T>  Class that will be injected
     */
    public <T> T get(Class<T> type) {
        return objectGraph.get(type);
    }

    public ObjectGraph getGraph() {
        return objectGraph;
    }
}
