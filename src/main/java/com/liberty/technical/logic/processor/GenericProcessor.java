package com.liberty.technical.logic.processor;

/**
 * @author M-AKI
 */
public class GenericProcessor implements IProcessor {

    private GenericProcessor instanse;

    private GenericProcessor()
    {}


    public GenericProcessor getInstanse()
    {
        if (instanse == null) {
            instanse = new GenericProcessor();
        }
        return instanse;
    }


}
