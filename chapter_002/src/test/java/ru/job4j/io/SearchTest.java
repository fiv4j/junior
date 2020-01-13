package ru.job4j.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SearchTest {

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
    }

    @Test(expected = FileNotFoundException.class)
    public void whenParentNotExistsThenThrowException() throws FileNotFoundException {
        new Search().files(
                "not_existed_dir",
                List.of("xml"),
                List.of("xml")::contains
        );
    }

    @Test
    public void whenCreatedOneXMLThenReturnListWithOneElement() throws FileNotFoundException {
        List<File> resultList = new Search().files(
                tmpDir,
                List.of("xml"),
                List.of("xml")::contains
        );
        String result = resultList.get(0).getPath();

        assertThat(resultList.size(), is(1));
        assertThat(result, is(tmpDir + "/01/example.xml"));
    }

    @Test
    public void whenCreatedOneXMLAndOneTXTThenReturnListWithTwoElements()
            throws FileNotFoundException {
        List<File> resultList = new Search().files(
                tmpDir,
                List.of("xml", "doc"),
                List.of("xml", "doc")::contains
        );

        assertThat(resultList.size(), is(2));
        assertThat(
                resultList.contains(new File(tmpDir + "/01/example.xml")),
                is(true)
        );
        assertThat(
                resultList.contains(new File(tmpDir + "/01/02/example.doc")),
                is(true)
        );
    }
}
