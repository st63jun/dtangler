package org.dtangler.core.textui;

import org.dtangler.core.analysisresult.AnalysisResult;
import org.dtangler.core.dsm.Dsm;

public interface TextUI {
    void printDsm(Dsm dsm, AnalysisResult analysisResult);
}
