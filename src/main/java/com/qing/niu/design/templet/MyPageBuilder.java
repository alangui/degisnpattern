package com.qing.niu.design.templet;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/10/25
 */
public class MyPageBuilder extends AbstractPageBuilder{

    @Override
    protected void appendMeta(StringBuffer stringBuffer){
        stringBuffer.append("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\"/>");
    }

    @Override
    protected void appendTitle(StringBuffer stringBuffer) {
        stringBuffer.append("<title>你好</title>");
    }

    @Override
    protected void appendBody(StringBuffer stringBuffer) {
        stringBuffer.append("<body><h1>你好，世界！</h1></body>");
    }
}
