/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthome.utilities;

import java.util.Set;
import org.apache.wicket.request.IRequestParameters;

/**
 *
 * @author Kaspa
 */
public class ApplicationUtilities {

    public static String parseRequestParams(IRequestParameters params) {
        Set<String> paramSet = params.getParameterNames();
        String ret = "";
        String paramPair;

        for (String param : paramSet) {
            if (!param.equals("pass")) {
                String paramValue = nvl(params.getParameterValue(param).toString());
                paramPair = "|" + param + "=" + paramValue + "|";

                ret += paramPair;
            }
        }
        return ret;
    }

    private static String nvl(String inpValue) {
        return nvl(inpValue, null);
    }

    private static String nvl(String inpValue, String nullValue) {
        if (inpValue == null || inpValue.length() == 0) {
            return nullValue == null ? "{null}" : nullValue.length() == 0 ? "{null}" : nullValue;
        } else {
            return inpValue;
        }
    }
}
