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
package adamstroud.me.taggedstrings.sample;

import android.graphics.Color;
import android.text.style.ForegroundColorSpan;

import me.adamstroud.taggedstrings.BaseTagHandler;

/**
 * Processes the tags for the sample app.
 *
 * @author Adam Stroud &#60;<a href="mailto:adam.stroud@gmail.com">adam.stroud@gmail.com</a>&#62;
 */
public class SampleTagHandler extends BaseTagHandler {
    private static final String TAG_RED = "red";
    private static final Object[] RED_SPANS = new Object[] {new ForegroundColorSpan(Color.RED)};

    private static final String TAG_BLUE = "blue";
    private static final Object[] BLUE_SPANS = new Object[] {new ForegroundColorSpan(Color.BLUE)};

    private static final String TAG_GREEN = "green";
    private static final Object[] GREEN_SPANS = new Object[] {new ForegroundColorSpan(Color.GREEN)};

    @Override
    protected Object[] getSpansForTag(String tag) {
        Object[] spans;

        switch (tag) {
            case TAG_RED:
                spans = RED_SPANS;
                break;
            case TAG_BLUE:
                spans = BLUE_SPANS;
                break;
            case TAG_GREEN:
                spans = GREEN_SPANS;
                break;
            default:
                spans = null;
        }

        return spans;
    }
}
