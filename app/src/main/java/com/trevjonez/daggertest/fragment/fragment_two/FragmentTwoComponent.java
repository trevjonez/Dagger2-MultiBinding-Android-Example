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

package com.trevjonez.daggertest.fragment.fragment_two;

import com.trevjonez.daggertest.fragment.FragmentScope;
import com.trevjonez.inject.PlainComponent;
import com.trevjonez.inject.fragment.FragmentComponentBuilder;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

@Subcomponent (modules = FragmentTwoComponent.FragmentTwoModule.class)
@FragmentScope
public interface FragmentTwoComponent extends PlainComponent<FragmentTwo> {

    @Subcomponent.Builder
    interface Builder extends FragmentComponentBuilder<FragmentTwo, FragmentTwoComponent> {
        Builder fragmentModule(FragmentTwoModule module);
    }

    @Module
    class FragmentTwoModule{
        String text;
        public FragmentTwoModule(String text) {
            this.text=text;
        }

        @Provides @FragmentScope @Named("TextFragmentTwo")
        public String provideActivityText() {
            return text;
        }
    }
}