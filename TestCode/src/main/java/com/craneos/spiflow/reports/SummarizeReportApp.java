package com.craneos.spiflow.reports;

import java.io.*;

public class SummarizeReportApp {

    private static final String INPUT_FILE =
        "/Users/sanchezc/Development/git/platform/britebill-modules/analysis/reports/reprint/britebill-integration_IntelliJ_dependencies.xml";
    private static final String OUTPUT_FILE =
        "/Users/sanchezc/Development/git/platform/britebill-modules/analysis/reports/reprint/britebill-integration_IntelliJ_dependencies.min.xml";
    private static final String IS_JAVA_SOURCE = "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/src.zip!";
    private static final String IS_MAVEN_REPO = "$MAVEN_REPOSITORY$";

    public void execute(){
        String content = readInputFile(INPUT_FILE);
        writeOutputFile(OUTPUT_FILE, content);
    }

    private String readInputFile(String inputfile){
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(inputfile))) {
            String line = br.readLine();
            while (line != null) {
                if (line.contains(IS_JAVA_SOURCE)) {
                    System.out.println("Removed line: " + line);
                } else if (line.contains(IS_MAVEN_REPO)){
                    System.out.println("Removed line: " + line);
                } else {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            return sb.toString();
        }
    }

    private boolean writeOutputFile(String outputFile, String filecontent){
        boolean isWritten = false;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputFile)))) {
            writer.write(filecontent);
            isWritten = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return isWritten;
        }
    }

}
