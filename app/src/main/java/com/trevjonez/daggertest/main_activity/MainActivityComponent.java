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

package com.trevjonez.daggertest.main_activity;

import com.trevjonez.daggertest.dagger_base_types.ActivityComponentBuilder;
import com.trevjonez.daggertest.dagger_base_types.PlainComponent;

import dagger.Subcomponent;

/**
 * @author TrevJonez
 */
@MainActivityScope
@Subcomponent(modules = {MainActivityModule.class})
public interface MainActivityComponent extends PlainComponent<MainActivity> {

    @Subcomponent.Builder
    interface Builder extends ActivityComponentBuilder<MainActivity, MainActivityModule, MainActivityComponent> { }
}
