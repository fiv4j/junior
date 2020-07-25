package ru.job4j.io.archiver;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ZipTest {
    String tmpDir = System.getProperty("java.io.tmpdir");

    @Before
    public void before() throws IOException {
        String dir01 = tmpDir + "/01";
        String dir02 = dir01 + "/02";

        new File(dir01).mkdir();
        new File(dir01 + "/example.tmp").createNewFile();
        new File(dir01 + "/example.txt").createNewFile();
        new File(dir01 + "/example.xml").createNewFile();

        new File(dir02).mkdir();
        new File(dir02 + "/example.doc").createNewFile();
        new File(dir02 + "/example.com").createNewFile();
    }

    @After
    public void after() {
        String dir01 = tmpDir + "/01";
        String dir02 = dir01 + "/02";

        new File(dir02 + "/example.doc").delete();
        new File(dir02 + "/example.com").delete();
        new File(dir02).delete();

        new File(dir01 + "/example.tmp").delete();
        new File(dir01 + "/example.txt").delete();
        new File(dir01 + "/example.xml").delete();
        new File(dir01).delete();

        File zipFile = new File(tmpDir + "/out.zip");
        if (zipFile.exists()) {
            zipFile.delete();
        }
    }

    @Ignore
    @Test
    public void whenUsePackDirThenZipFileAppear() throws FileNotFoundException {
        new Zip().packDir(
                tmpDir + "/01",
                tmpDir + "/out.zip",
                "*.doc");

        boolean result = new File(tmpDir + "/out.zip").exists();
        boolean expected = true;

        assertThat(result, is(expected));
    }

    @Test(expected = FileNotFoundException.class)
    public void whenRootNotExistsThenFNFException() throws FileNotFoundException {
        new Zip().packDir(
                tmpDir + "/not_exists_dir",
                tmpDir + "/out.zip",
                "*.doc");

    }
}