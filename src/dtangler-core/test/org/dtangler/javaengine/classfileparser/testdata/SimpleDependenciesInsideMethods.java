// This product is provided under the terms of EPL (Eclipse Public License) 
// version 1.0.
//
// The full license text can be read from: http://www.eclipse.org/org/documents/epl-v10.php 

package org.dtangler.javaengine.classfileparser.testdata;

public class SimpleDependenciesInsideMethods {

	void foo() {
		SimpleClassWithStaticMethod.doNothing();
	}

	void bar() {
		new SimpleClass();
	}

}
