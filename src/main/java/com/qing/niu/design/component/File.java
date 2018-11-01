package com.qing.niu.design.component;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/11/1
 */
@Slf4j
public class File implements IFile{

    private String name;

    private IFile folder;

    public File(String name, IFile folder){
        super();
        this.name = name;
        this.folder = folder;
    }

    @Override
    public void delete() {
        folder.deleteFile(name);
        log.info("--删除文件【{}】--",name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void createNewFile(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteFile(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IFile getIFile(int index) {
        throw  new UnsupportedOperationException();
    }
}
