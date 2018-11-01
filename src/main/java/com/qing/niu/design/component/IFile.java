package com.qing.niu.design.component;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/11/1
 */
public interface IFile {

    void delete();

    String getName();

    void createNewFile(String name);

    void deleteFile(String name);

    IFile getIFile(int index);
}
