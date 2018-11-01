package com.qing.niu.design.component;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/11/1
 */
@Slf4j
public class Folder implements IFile{

    private String name;

    private IFile folder;

    private List<IFile> files;

    public Folder(String name){
        this(name,null);
    }

    public Folder(String name, IFile folder){
        super();
        this.name = name;
        this.folder = folder;
        files = new ArrayList<IFile>();
    }

    @Override
    public void delete() {
        List<IFile> copy = new ArrayList<>(files);
        log.info("-------删除子文件-------");
        for (IFile file : copy){
            file.delete();
        }
        log.info("-------删除子文件结束-------");
        if (null != folder){
            folder.deleteFile(name);
        }
        log.info("---删除文件夹【{}】---",name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void createNewFile(String name) {
        if (name.contains(".")){
            files.add(new File(name,this));
        }else {
            files.add(new Folder(name,this));
        }
    }

    @Override
    public void deleteFile(String name) {
        for (IFile file : files){
            if (file.getName().equals(name)){
                files.remove(file);
                break;
            }
        }
    }

    @Override
    public IFile getIFile(int index) {
        return files.get(index);
    }
}
