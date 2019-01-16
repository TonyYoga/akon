package ru.job4j.searchspecificfiles;

import java.io.File;
import java.util.List;

public interface ISearch {
    List<File> files(String parent, List<String> exts);
}
