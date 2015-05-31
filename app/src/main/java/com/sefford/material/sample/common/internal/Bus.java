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

package com.sefford.material.sample.common.internal;

import com.sefford.kor.common.interfaces.Postable;
import de.greenrobot.event.EventBus;

import javax.inject.Inject;

/**
 * Bus handles the communication between the back and the front of the application
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class Bus implements Postable, com.sefford.brender.interfaces.Postable {

    final EventBus bus;

    @Inject
    public Bus(EventBus bus) {
        this.bus = bus;
    }

    public void register(Object target) {
        if (!bus.isRegistered(target)) {
            bus.register(target);
        }
    }

    public void unregister(Object target) {
        if (bus.isRegistered(target)) {
            bus.unregister(target);
        }
    }

    @Override
    public void post(Object message) {
        bus.post(message);
    }
}
