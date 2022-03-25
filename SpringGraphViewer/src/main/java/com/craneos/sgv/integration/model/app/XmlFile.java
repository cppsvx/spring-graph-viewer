package com.craneos.sgv.integration.model.app;

import com.craneos.sgv.integration.model.spring.defs.Import;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class XmlFile {

    protected Path path;
    protected String absolutePath;
    protected String filename;
    protected String content;
    protected List<Import> imports;

    public XmlFile() {
        this.path = null;
        this.absolutePath = null;
        this.filename = null;
        this.content = null;
        this.imports = null;
    }

    public void addImport(Import item){
        if (imports==null){
            imports = new ArrayList<>();
        }
        imports.add(item);
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Import> getImports() {
        return imports;
    }

    public void setImports(List<Import> imports) {
        this.imports = imports;
    }
}
