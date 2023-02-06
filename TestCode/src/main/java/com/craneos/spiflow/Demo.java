package com.craneos.spiflow;

import java.util.ArrayList;
import java.util.List;

public class Demo {

    public void main(String[] args){
        List<Metadata> metadata = new ArrayList<>();
        metadata.add(createMetadata());
        metadata.add(createMetadata());
        metadata.add(createMetadata());
    }

    private Metadata createMetadata(){
        Metadata metadata = new Metadata();
        metadata.setDescription("aa");
        metadata.setColumnName("aa");
        metadata.setIndex(1);
        metadata.setSubGroup("SOC");
        metadata.setOptimizationHint(1);
        metadata.setFactName("aaa");
        return metadata;
    }

    static class Metadata{
        private String columnName; // the name of the column in the lookup table
        private String subGroup; // sub-group to be used extracting fact groups
        private Integer index; // this is the index of the column in the CSV file (starting from 0)
        private Integer optimizationHint; // this is order of index which should be used to perform request in the lookup table, if is null then column is not indexed
        private String description; // just a friendly explanation what this boundary means
        private String factName; // a fact name on that or any parent levels

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public String getSubGroup() {
            return subGroup;
        }

        public void setSubGroup(String subGroup) {
            this.subGroup = subGroup;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public Integer getOptimizationHint() {
            return optimizationHint;
        }

        public void setOptimizationHint(Integer optimizationHint) {
            this.optimizationHint = optimizationHint;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getFactName() {
            return factName;
        }

        public void setFactName(String factName) {
            this.factName = factName;
        }
    }



}
