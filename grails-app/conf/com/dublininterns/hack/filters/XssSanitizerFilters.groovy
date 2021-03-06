package com.dublininterns.hack.filters

import com.dublininterns.hack.utils.XssSanitizerUtil

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class XssSanitizerFilters {

    def filters = {
        all(controller: '*', action: '*') {
            before = {
                // Call the sanitize method for each request.
                sanitizeParameters(params, request, response)
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }

    /**
     * Loop through each of the Grails request parameters
     * and "sanitize" each value.
     */
    private void sanitizeParameters(parameters, HttpServletRequest request, HttpServletResponse response) {
        // Sanitize Header Parameters
        //Enumeration<String> headerNames = request.getHeaderNames();
        //while (headerNames.hasMoreElements()) {
        //	String headerName = (String) headerNames.nextElement();
        //	String headerValue = request.getHeader(headerName);
        //	int headerValueLength = headerValue.length();
        //	String newHeaderValue = stripXSS(headerValue);
        //	int newHeaderValueLength = newHeaderValue.length();
        //	// Throw an exception. This is illegal.
        //	if (headerValueLength != newHeaderValueLength) {
        //		response.sendError 500
        //	}
        //}

        // Sanitize Request Parameters
        parameters.each { entry ->
            if (entry.value instanceof String) {
                entry.value = XssSanitizerUtil.stripXSS(entry.value)
            }
        }
    }

}

