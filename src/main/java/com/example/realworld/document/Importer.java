package com.example.realworld.document;

import java.io.File;
import java.io.IOException;

public interface Importer {
	Document importFile(File file) throws IOException;
}
