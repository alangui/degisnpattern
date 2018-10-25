package com.qing.niu.design.templet;

/**
 * <p>
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/10/25
 */
public abstract class AbstractPageBuilder implements PageBuilder{

    private StringBuffer stringBuffer = new StringBuffer();

    @Override
    public String buildHtml(){
        stringBuffer.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        stringBuffer.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        stringBuffer.append("<head>");
        appendTitle(stringBuffer);
        appendMeta(stringBuffer);
        appendLink(stringBuffer);
        appendScript(stringBuffer);
        stringBuffer.append("</head>");
        appendBody(stringBuffer);
        stringBuffer.append("</html>");
        return stringBuffer.toString();
    }

    protected void appendMeta(StringBuffer stringBuffer){}

    protected void appendLink(StringBuffer stringBuffer){}

    protected void appendScript(StringBuffer stringBuffer){}

    protected abstract void appendTitle(StringBuffer stringBuffer);

    protected abstract void appendBody(StringBuffer stringBuffer);
}
