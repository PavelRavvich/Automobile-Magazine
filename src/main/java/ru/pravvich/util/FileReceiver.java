package ru.pravvich.util;

import org.apache.commons.fileupload.FileItem;

import java.util.ArrayList;
import java.util.List;

public class FileReceiver {

    public byte[] receive(final List<FileItem> items) {

        final List<Byte> entire = new ArrayList<>();

        for (final FileItem item : items) {

            if (!item.isFormField()) {

                final byte[] bytes = item.get();

                for (byte b : bytes) entire.add(b);
            }
        }

        final Byte[] bytes = entire.toArray(new Byte[entire.size()]);

        byte[] result = new byte[bytes.length];

        for (int i = 0; i < result.length; i++) result[i] = bytes[i];

        return result;
    }
}
