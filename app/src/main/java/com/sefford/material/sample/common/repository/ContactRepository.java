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

package com.sefford.material.sample.common.repository;

import com.sefford.kor.repositories.MemoryRepository;
import com.sefford.material.sample.common.model.Contact;

import java.util.HashMap;

/**
 * Saves and retrieves Contacts in Memory via Hashmap
 */
public class ContactRepository extends MemoryRepository<Long, Contact> {
    /**
     * Creates a new instance of Memory repository
     */
    public ContactRepository() {
        super(new HashMap<Long, Contact>());
    }
}
