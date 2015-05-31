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

import com.sefford.kor.common.interfaces.Loggable;
import com.sefford.material.sample.common.logging.DevelopmentLogger;
import com.sefford.material.sample.common.logging.Logger;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * This module handles the Logging system. As this application is not intended for production purposes, handles the
 * Development logging module.
 * <p/>
 * This is copied from other applications which allow the logger to be configured dynamically per BuildConfig.DEBUG
 * and uses abstraction to enable logcat or Crashlytics modules.
 */
@Module(library = true,
        complete = true)
public class DevelopmentLoggingModule {

    @Provides
    @Singleton
    public Logger provideLogger(DevelopmentLogger logger) {
        return logger;
    }

    @Provides
    @Singleton
    public Loggable provideLog(Logger log) {
        return log;
    }
}
