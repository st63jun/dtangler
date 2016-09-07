package org.dtangler.core.textui;

import org.dtangler.core.analysisresult.AnalysisResult;
import org.dtangler.core.analysisresult.Violation;
import org.dtangler.core.dsm.Dsm;
import org.dtangler.core.dsm.DsmCell;
import org.dtangler.core.dsm.DsmRow;

public class CsvWriter implements TextUI {
    private final Writer writer;

    public CsvWriter(Writer writer) {
        this.writer = writer;

    }

    public void printDsm(Dsm dsm, AnalysisResult analysisResult) {
        printColumnHeaders(dsm.getRows().size());
        int i = 1;
        for (DsmRow row : dsm.getRows()) {
            printPackage(i++, row, analysisResult);
        }

    }

    private void printColumnHeaders(int size) {
        print(",");
        for (int i = 1; i <= size; i++)
            printCell(Integer.toString(i));
        nextRow();
    }

    private void printPackage(int index, DsmRow row,
                              AnalysisResult analysisResult) {
        printRowHeader(index, row.getDependee().getDisplayName(), row
                .getDependee().getContentCount());
        for (DsmCell dep : row.getCells())
            printCell(formatDependency(dep, analysisResult));
        nextRow();
    }

    private String formatDependency(DsmCell dep, AnalysisResult analysisResult) {
        if (!dep.isValid())
            return "\"####\"";
        if (dep.getDependencyWeight() == 0)
            return "";
        String s = Integer.toString(dep.getDependencyWeight());
        // FIXME: Severity.error might mean something else than a cycle, we
        // should change the UI and show an 'E' instead
        if (!analysisResult.getViolations(dep.getDependency(), Violation.Severity.error)
                .isEmpty())
            s = s + "C";
        return s;
    }

    @Deprecated
    private void printEmptyRowHeader() {
        print(String.format("%51s", ""));
    }

    private void printRowHeader(int rowId, String name, int pkgCount) {
        print(String.format("%s,\"%s (%s)\"", new Object[] { rowId,
                name, pkgCount }));
    }

    @Deprecated
    private String formatName(String name, int length) {
        if (name.length() <= length)
            return name;
        return ".." + name.substring(name.length() - 38);
    }

    private void printCell(String content) {
        print("," + String.format("%s", content));
    }

    private void nextRow() {
        println("");
    }

    private void print(String s) {
        writer.print(s);
    }

    private void println(String s) {
        writer.println(s);
    }

}
