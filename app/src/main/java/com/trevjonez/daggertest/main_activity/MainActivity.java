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
import com.trevjonez.daggertest.dagger_base_types.ActivityComponentBuilderHost;

public class MainActivity extends AppCompatActivity {

    MainActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        component = ((ActivityComponentBuilderHost) getApplication()).getComponentBuilder(MainActivity.class, MainActivityComponent.Builder.class).build();
        component.inject(this);
    }
}
