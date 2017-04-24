package com.nytimes.android.external.fs;

import com.nytimes.android.external.fs.filesystem.FileSystem;
import com.nytimes.android.external.store.base.impl.BarCode;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileNotFoundException;
import java.io.IOException;

import okio.BufferedSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class SourcePersisterTest {
    private static final BarCode simple = new BarCode("type", "key");
    private final BarCodePathResolver resolver = new BarCodePathResolver();
    @Mock
    FileSystem fileSystem;
    @Mock
    BufferedSource bufferedSource;
    private SourcePersister sourcePersister;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        sourcePersister = new SourcePersister(fileSystem);
    }

    @Test
    public void readExists() throws FileNotFoundException {
        when(fileSystem.exists(resolver.resolve(simple)))
                .thenReturn(true);
        when(fileSystem.read(resolver.resolve(simple))).thenReturn(bufferedSource);

        BufferedSource returnedValue = sourcePersister.read(simple).toBlocking().single();
        assertThat(returnedValue).isEqualTo(bufferedSource);
    }

    @Test
    public void readDoesNotExist() throws FileNotFoundException {
        when(fileSystem.exists(resolver.resolve(simple)))
                .thenReturn(false);

        sourcePersister.read(simple)
                .test()
                .awaitTerminalEvent()
                .assertError(FileNotFoundException.class);
    }

    @Test
    public void write() throws IOException {
        assertThat(sourcePersister.write(simple, bufferedSource).toBlocking().single()).isTrue();
    }

    @Test
    public void pathForBarcode() {
        assertThat(resolver.resolve(simple)).isEqualTo("type/key");
    }
}
