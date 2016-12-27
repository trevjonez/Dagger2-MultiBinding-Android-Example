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

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.trevjonez.daggertest.R;
import com.trevjonez.daggertest.fragment.fragment_one.FragmentOne;
import com.trevjonez.daggertest.fragment.fragment_two.FragmentTwo;
import com.trevjonez.inject.PlainComponent;
import com.trevjonez.inject.activity.ActivityComponentBuilderHost;
import com.trevjonez.inject.fragment.FragmentComponentBuilder;
import com.trevjonez.inject.fragment.FragmentComponentBuilderHost;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

public class MainActivity extends AppCompatActivity implements FragmentComponentBuilderHost{
    TextView textView;

    MainActivityComponent component;
    @Inject
    Map<Class<? extends Fragment>, Provider<FragmentComponentBuilder>> fragmentComponentBuilderMap;
    @Inject @ActivityText String textMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.textView_mainActivity);

        component = ((ActivityComponentBuilderHost) getApplication())
                .getActivityComponentBuilder(MainActivity.class, MainActivityComponent.Builder.class)
                .activityModule(new ActivityTextModule("Text from MainActivity"))
                .build();
        component.inject(this);


        textView.setText(textMainActivity);

        getFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentLayout_fragmentOne,new FragmentOne())
                .add(R.id.fragmentLayout_fragmentTwo,new FragmentTwo())
                .commit();
    }

    @Override
    public <F extends Fragment, B extends FragmentComponentBuilder<F, ? extends PlainComponent<F>>> B getFragmentComponentBuilder(Class<F> fragmentKey, Class<B> builderType) {
        return builderType.cast(fragmentComponentBuilderMap.get(fragmentKey).get());
    }
}
