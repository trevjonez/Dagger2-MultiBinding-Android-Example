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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.trevjonez.daggertest.R;
import com.trevjonez.daggertest.dagger_base_types.ActivityComponentBuilder;
import com.trevjonez.daggertest.dagger_base_types.ComponentBuilderHost;

import javax.inject.Inject;
import javax.inject.Named;

public class MainActivity extends AppCompatActivity {

    @Inject
    @Named("ScopedData")
    String scopedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        injectViaMultiBoundMap();
    }

    private void injectViaMultiBoundMap() {

        //Using the base interface here because of type inference failure if we make it a MainActivityComponent.Builder
        ActivityComponentBuilder<MainActivity, MainActivityModule, MainActivityComponent> builder = ((ComponentBuilderHost) getApplication()).getComponentBuilder(MainActivity.class);
        builder.module(new MainActivityModule("Some data from the intent extras most likely. Use Dart & Henson https://github.com/f2prateek/dart"));

        MainActivityComponent component = builder.build();
        component.inject(this);
    }
}
