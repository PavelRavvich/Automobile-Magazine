package ru.pravvich.util;

import org.apache.commons.fileupload.FileItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Receive text data from list FileItem.
 */
public class TextReceiver {

    /**
     * Receive text data.
     *
     * @param items common data file with test.
     * @return in Map: key - name test data, value - value text data.
     */
    public Map<String, String> receive(final List<FileItem> items) {

        final Map<String, String> result = new HashMap<>();

        for (final FileItem item : items) {

            if (item.isFormField()) {

                final String name = item.getFieldName();

                final String value = item.getString();


                result.put(name, value);
            }
        }

        return result;
    }
}
