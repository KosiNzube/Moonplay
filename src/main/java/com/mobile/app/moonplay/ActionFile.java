package com.mobile.app.moonplay;


import com.google.android.exoplayer2.util.AtomicFile;
import com.google.android.exoplayer2.util.Util;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Stores and loads {@link DownloadAction}s to/from a file.
 */
public final class ActionFile {

    /* package */ static final int VERSION = 0;

    private final AtomicFile atomicFile;
    private final File actionFile;

    /**
     * @param actionFile File to be used to store and load {@link DownloadAction}s.
     */
    public ActionFile(File actionFile) {
        this.actionFile = actionFile;
        atomicFile = new AtomicFile(actionFile);
    }

    /**
     * Loads {@link DownloadAction}s from file.
     *
     * @param deserializers {@link DownloadAction.Deserializer}s to deserialize DownloadActions.
     * @return Loaded DownloadActions. If the action file doesn't exists returns an empty array.
     * @throws IOException If there is an error during loading.
     */
    public DownloadAction[] load(DownloadAction.Deserializer... deserializers) throws IOException {
        if (!actionFile.exists()) {
            return new DownloadAction[0];
        }
        InputStream inputStream = null;
        try {
            inputStream = atomicFile.openRead();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            int version = dataInputStream.readInt();
            if (version > VERSION) {
                throw new IOException("Unsupported action file version: " + version);
            }
            int actionCount = dataInputStream.readInt();
            DownloadAction[] actions = new DownloadAction[actionCount];
            for (int i = 0; i < actionCount; i++) {
                actions[i] = DownloadAction.deserializeFromStream(deserializers, dataInputStream);
            }
            return actions;
        } finally {
            Util.closeQuietly(inputStream);
        }
    }

    /**
     * Stores {@link DownloadAction}s to file.
     *
     * @param downloadActions DownloadActions to store to file.
     * @throws IOException If there is an error during storing.
     */
    public void store(DownloadAction... downloadActions) throws IOException {
        DataOutputStream output = null;
        try {
            output = new DataOutputStream(atomicFile.startWrite());
            output.writeInt(VERSION);
            output.writeInt(downloadActions.length);
            for (DownloadAction action : downloadActions) {
                DownloadAction.serializeToStream(action, output);
            }
            atomicFile.endWrite(output);
            // Avoid calling close twice.
            output = null;
        } finally {
            Util.closeQuietly(output);
        }
    }

}