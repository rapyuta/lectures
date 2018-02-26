/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ohhoonim.common;

import egovframework.rte.fdl.cmmn.exception.handler.ExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcepHndlr implements ExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExcepHndlr.class);

	@Override
	public void occur(Exception ex, String packageName) {
		LOGGER.error(ex.getMessage() + " | " + matchedTrace(ex, packageName) );
	}
	
	private String matchedTrace(Exception ex, String packageName) {
		String matchedTrace = "";
		StackTraceElement[] els = ex.getStackTrace();
		for(StackTraceElement ste: els) {
			if(packageName.contains(ste.getClassName())) {
				matchedTrace = packageName + " (line: " + ste.getLineNumber() + ")";
				break;
			}
		}
		return matchedTrace;
	}
}
