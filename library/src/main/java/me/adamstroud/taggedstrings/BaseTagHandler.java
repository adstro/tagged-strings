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

import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;

import org.xml.sax.XMLReader;

import java.util.HashMap;
import java.util.Map;

/**
 * The base class for all other tag handlers.
 *
 * @author Adam Stroud &#60;<a href="mailto:adam.stroud@gmail.com">adam.stroud@gmail.com</a>&#62;
 */
public abstract class BaseTagHandler implements Html.TagHandler {
    private static final String OUTER_TAG = "tagged";
    private final Map<String, Object[]> resolvedTags = new HashMap<>();

    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        if (!tag.equals(OUTER_TAG)) {
            Object[] spans;

            if (resolvedTags.containsKey(tag)) {
                spans = resolvedTags.get(tag);
            } else {
                spans = getSpansForTag(tag);
                resolvedTags.put(tag, spans);
            }

            if (spans != null) {
                if (opening) {
                    processOpenTag(output, spans);
                } else {
                    processCloseTag(output, spans);
                }
            }
        }
    }

    /**
     * Resolves the given tag into spans for the tag.
     *
     * @param tag The as specified in the string resource.

     * @return The Array of spans to apply to the text defined by the tag.
     */
    protected abstract Object[] getSpansForTag(String tag);

    private void processOpenTag(Editable output, Object... spans) {
        for (Object span : spans) {
            int length = output.length();
            output.setSpan(span, length, length, Spannable.SPAN_MARK_MARK);
        }
    }

    private void processCloseTag(Editable output, Object... spans) {
        for (Object span : spans) {
            output.setSpan(span, output.getSpanStart(getLastSpanForType(output, span.getClass())), output.length(), 0);
        }
    }

    private <T> T getLastSpanForType(Editable editable, Class<T> spanType) {
        T lastSpan = null;

        T[] spans = editable.getSpans(0, editable.length(), spanType);

        if (spans.length > 0) {
            for (int i = spans.length; i > 0; i--) {
                T currentSpan = spans[i - 1];

                if (editable.getSpanFlags(currentSpan) == Spannable.SPAN_MARK_MARK) {
                    lastSpan = currentSpan;
                    break;
                }
            }
        }

        return lastSpan;
    }

    public Spanned bindTags(String string) {
        return Html.fromHtml(string, null, this);
    }
}
