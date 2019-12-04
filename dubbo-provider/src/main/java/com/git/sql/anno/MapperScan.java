package com.git.sql.anno;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

public class MapperScan implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Set<String> metaAnnotationTypes = importingClassMetadata.getMetaAnnotationTypes(MyMapperScan.class.getName());
        System.out.println(metaAnnotationTypes);
        return new String[0];
    }
}
