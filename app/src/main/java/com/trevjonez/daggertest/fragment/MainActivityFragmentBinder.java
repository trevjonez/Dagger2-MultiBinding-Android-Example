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

package com.trevjonez.daggertest.fragment;

import com.trevjonez.daggertest.fragment.fragment_one.FragmentOne;
import com.trevjonez.daggertest.fragment.fragment_one.FragmentOneComponent;
import com.trevjonez.daggertest.fragment.fragment_two.FragmentTwo;
import com.trevjonez.daggertest.fragment.fragment_two.FragmentTwoComponent;
import com.trevjonez.inject.fragment.FragmentComponentBuilder;
import com.trevjonez.inject.fragment.FragmentKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module(subcomponents ={
        FragmentOneComponent.class,
        FragmentTwoComponent.class
})
public abstract class MainActivityFragmentBinder {
    @Binds
    @IntoMap
    @FragmentKey(FragmentOne.class)
    abstract FragmentComponentBuilder fragmentOneBuilder(FragmentOneComponent.Builder impl);

    @Binds
    @IntoMap
    @FragmentKey(FragmentTwo.class)
    abstract FragmentComponentBuilder fragmentTwoBuilder(FragmentTwoComponent.Builder impl);
}