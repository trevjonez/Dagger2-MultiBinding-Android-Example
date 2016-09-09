/*
 *    Copyright 2016 Trevor Jones
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.trevjonez.daggertest.application;

import com.trevjonez.daggertest.dagger_base_types.PlainComponent;
import com.trevjonez.daggertest.main_activity.MainActivityComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author TrevJonez
 */
@Singleton
@Component(modules = {ActivityBindingModule.class})
public interface AppComponent extends PlainComponent<Application> {

    /**
     * TODO Remove this once dagger2 supports doing things without it
     * This is required in order to get dagger to actually create the sub-component code
     */
    MainActivityComponent.Builder mainActivityComponentBuilder();
}
