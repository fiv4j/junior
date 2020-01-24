package ru.job4j.io.archiver;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import ru.job4j.io.Search;

public class Zip {

    List<File> seekBy(String root, String ext) throws FileNotFoundException {
        return new Search().files(root, List.of(ext), fileExt -> !Objects.equals(ext, fileExt));
    }

    public void pack(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packDir(String root, String dest, String ext) throws FileNotFoundException {
        File target = new File(dest);
        List<File> filesToArchive;

        filesToArchive = seekBy(root, ext);
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            for (var file : filesToArchive) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
