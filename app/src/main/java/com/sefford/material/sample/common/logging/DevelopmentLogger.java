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

package com.sefford.material.sample.common.logging;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Development Logger based on Jorge Rodriguez <tylosan@gmail.com> on how things should log on debug
 *
 * @author Saul Diaz <sefford@gmail.com>
 * @author Jorge Rodriguez <tylosan@gmail.com>
 */
@Singleton
public class DevelopmentLogger implements Logger {

    @Inject
    public DevelopmentLogger() {
    }

    @Override
    public void bind(Context context) {
        // Nothing for DevelopmentLogger
    }

    @Override
    public void identify(long userId) {
        // This is a Production feature
    }

    @Override
    public void e(String logtag, String message) {
        Log.e(logtag, message);
    }

    @Override
    public void e(String logtag, String message, Throwable e) {
        Log.e(logtag, message, e);
    }

    @Override
    public void d(String logtag, String message) {
        Log.d(logtag, message);
    }

    @Override
    public void w(String logtag, String message) {
        Log.w(logtag, message);
    }

    @Override
    public void w(String logtag, String message, Throwable e) {
        Log.w(logtag, message, e);
    }

    @Override
    public void v(String logtag, String message) {
        Log.v(logtag, message);
    }

    @Override
    public void printPerformanceLog(String tag, String element, long start) {
        d(tag, "(" + element + "):" + (System.currentTimeMillis() - start) + "ms");
    }
}
