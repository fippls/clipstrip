package com.github.fippls.clipstrip;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Optional;

/**
 * ClipStrip - Strip formatting from clipboard text.
 * @author github.com/fippls
 */
public class ClipStrip {
    private static final String VERSION = "1.0";
    private static final Clipboard CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();

    public static void main(String[] args) {
        if (args.length > 0 && ("--help".equals(args[0]) || "-h".equals(args[0]))) {
            System.out.println(ClipStrip.class.getSimpleName() + ' ' + VERSION + " (github.com/fippls)");
            System.exit(1);
        }

        Optional.ofNullable(readClipboardData())
                .ifPresent(ClipStrip::writeClipboardData);
    }

    private static String readClipboardData() {
        try {
            return (String) CLIPBOARD.getData(DataFlavor.stringFlavor);
        }
        catch (UnsupportedFlavorException | IOException e) {
            return null;
        }
    }

    private static void writeClipboardData(String data) {
        CLIPBOARD.setContents(new StringSelection(data), null);
    }
}
