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

package com.sefford.material.sample.common.injection.modules;

import android.content.Context;
import com.sefford.material.sample.common.ui.activities.BaseActivity;
import com.sefford.material.sample.common.ui.application.MaterialApplication;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;

/**
 * Application Module handles all the Application-related elements on the injection graph, in this case
 * the wide-app singleton Application context.
 * <p/>
 * We do not feat about leaking this context, as it is alive for as long as Android keeps the App in memory.
 */
@Module(
        includes = {
                CoreModule.class,
                DevelopmentLoggingModule.class,
        },
        injects = {
                // Application
                MaterialApplication.class,
                // Activities
                BaseActivity.class,
        },
        complete = true,
        library = true
)
public class ApplicationModule {

    public static final String APPLICATION = "app";

    final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    @Named(APPLICATION)
    public Context provideApplicationContext() {
        return context;
    }

}
