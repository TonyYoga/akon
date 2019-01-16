package ru.job4j.searchspecificfiles;

import java.io.File;
import java.util.*;

/**
 * Method #files is search files in defined directory  with extensions that are define in array exts
 * String parent - path.
 * List<String> exts - list of extension of the files to search.
 * @author Konkin Anton
 * @since 16/01/2019
 */

public class Search implements ISearch {
    @Override
    public List<File> files(String parent, List<String> exts) {
        Iterable<File> fileList = getFileList(parent);
        ArrayList<File> result = new ArrayList<>();
        String curExtension;
        for (File file : fileList) {
            curExtension = file.getName().substring(file.getName().indexOf(".") + 1);
            if (exts.contains(curExtension)) {
                result.add(file);
            }
        }
        return result;
    }

    private Iterable<File> getFileList(String parent) {
        ArrayList<File> result = new ArrayList<>();
        Deque<File> fileList = new ArrayDeque<>();
        fileList.offerFirst(new File(parent));
        File currentFile;
        while (!fileList.isEmpty()) {
            currentFile = fileList.pollFirst();
            if (currentFile.isDirectory()) {
                fileList.addAll(Arrays.asList(currentFile.listFiles()));
            } else {
                result.add(currentFile);
            }
        }
        return result;
    }
}
