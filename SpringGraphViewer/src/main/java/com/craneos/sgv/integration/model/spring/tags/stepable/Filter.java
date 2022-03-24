package com.craneos.sgv.integration.model.spring.tags.stepable;

public class Filter implements IStep{
    private String id;
    private String ref;
    private String method;
    private String inputChannel;
    private String outputChannel;
    private String discardChannel;
    private String expression;
    private boolean throwExceptionOnRejection;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getInputChannel() {
        return inputChannel;
    }

    public void setInputChannel(String inputChannel) {
        this.inputChannel = inputChannel;
    }

    public String getOutputChannel() {
        return outputChannel;
    }

    public void setOutputChannel(String outputChannel) {
        this.outputChannel = outputChannel;
    }

    public String getDiscardChannel() {
        return discardChannel;
    }

    public void setDiscardChannel(String discardChannel) {
        this.discardChannel = discardChannel;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public boolean isThrowExceptionOnRejection() {
        return throwExceptionOnRejection;
    }

    public void setThrowExceptionOnRejection(boolean throwExceptionOnRejection) {
        this.throwExceptionOnRejection = throwExceptionOnRejection;
    }
}
