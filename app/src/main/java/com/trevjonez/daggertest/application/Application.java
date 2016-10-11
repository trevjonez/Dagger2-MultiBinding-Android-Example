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

import android.app.Activity;

import com.trevjonez.daggertest.dagger_base_types.ActivityComponentBuilder;
import com.trevjonez.daggertest.dagger_base_types.ActivityComponentBuilderHost;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * @author TrevJonez
 */
public class Application extends android.app.Application implements ActivityComponentBuilderHost {

    AppComponent appComponent;

    @Inject
    Map<Class<? extends Activity>, Provider<ActivityComponentBuilder>> componentBuilders;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.create();
        appComponent.inject(this);
    }

    @Override
    public <ActivityType extends Activity, ComponentBuilderType extends ActivityComponentBuilder> ComponentBuilderType getComponentBuilder(Class<? extends ActivityType> key, Class<? extends ComponentBuilderType> builderType) {
        return builderType.cast(componentBuilders.get(key).get());
    }
}
