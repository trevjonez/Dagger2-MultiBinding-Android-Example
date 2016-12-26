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

package com.trevjonez.daggertest.fragment.fragment_one;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trevjonez.daggertest.R;
import com.trevjonez.inject.fragment.FragmentComponentBuilderHost;

import javax.inject.Inject;
import javax.inject.Named;

public class FragmentOne extends Fragment {
    TextView textView;

    FragmentOneComponent component;
    @Inject @Named("TextFragmentOne") String textFragmentOne;
    @Inject @Named("TextMainActivity") String textMainActivity;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = ((FragmentComponentBuilderHost) getActivity())
                .getFragmentComponentBuilder(FragmentOne.class, FragmentOneComponent.Builder.class)
                .fragmentModule(new FragmentOneComponent.FragmentOneModule("Text from FragmentOne"))
                .build();
        component.inject(this);
    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        textView = (TextView) view.findViewById(R.id.textView_fragmentOne);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(textFragmentOne);
        stringBuilder.append("\n");
        stringBuilder.append(textMainActivity);
        textView.setText(stringBuilder.toString());
    }
}
