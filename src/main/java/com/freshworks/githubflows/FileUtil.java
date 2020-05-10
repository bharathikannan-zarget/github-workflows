package com.freshworks.githubflows;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

/** @author bk */
public class FileUtil {

  public static void listFiles(String dir) {
    Collection<File> files =
        FileUtils.listFiles(
            new File(dir),
            new WildcardFileFilter("*.txt", IOCase.SENSITIVE),
            new NotFileFilter(DirectoryFileFilter.DIRECTORY));

    files.stream().forEach(System.out::println);
  }

  public static void print(String str) {
	System.out.println("Hello");
  } 

  public static void copyFile(File file1, File file2) throws IOException {

    FileUtils.copyFile(file1, file2);

    if (FileUtils.contentEquals(file1, file2)) {

      System.out.println("The files have equal content");
    } else {

      System.out.println("The files do not have equal content");
    }

    File docs = new File("src/main/resources/docs");
    FileUtils.forceMkdir(docs);

    FileUtils.copyFileToDirectory(file2, docs);
  }

  public void deleteFile(File f) {
    if (f.exists()) {
      f.delete();
    }
  }
}
