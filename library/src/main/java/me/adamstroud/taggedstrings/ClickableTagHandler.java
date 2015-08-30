/**
 * Copyright 2015 Adam Stroud
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.adamstroud.taggedstrings;

import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Processed clickable tags in string resources.
 *
 * @author Adam Stroud &#60;<a href="mailto:adam.stroud@gmail.com">adam.stroud@gmail.com</a>&#62;
 */
public class ClickableTagHandler extends BaseTagHandler {

    private final Object[] spans;

    public ClickableTagHandler(final View.OnClickListener listener) {

        this.spans = new Object[] {new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                listener.onClick(widget);
            }
        }};
    }

    @Override
    protected Object[] getSpansForTag(String tag) {
        return spans;
    }
}
