package com.synapse_processor;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.synapse_annotations.Feature;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class Processor extends AbstractProcessor {


    private Filer filer;
    private static final String PARAMETERS = "parameters";
    private static final String CLASS_NAME = "className";
    private static final String METHOD_NAME = "methodName";

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
    }


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder("FeatureManager")
                .addModifiers(Modifier.PUBLIC);

        MethodSpec.Builder methodBuilder = MethodSpec.
                methodBuilder("initFeature")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC);

        methodBuilder.beginControlFlow("switch(" + CLASS_NAME + "+'_'+" + METHOD_NAME + ")");

        for (Element typeElement : roundEnv.getElementsAnnotatedWith(Feature.class)) {
            if (typeElement.getKind() == ElementKind.METHOD) {
                ExecutableElement method = (ExecutableElement) typeElement;


                String method_keyword = ((TypeElement) method.getEnclosingElement()).getQualifiedName()
                        + "_"
                        + method.getSimpleName().toString();

                methodBuilder.addCode("case $S : \n", method_keyword);

                if (method.getReturnType().toString().equalsIgnoreCase("void")) {
                    methodBuilder.addStatement("    " + ((TypeElement) method.getEnclosingElement()).getQualifiedName()
                            + "."
                            + method.getSimpleName()
                            + "(" + PARAMETERS + ")");
                    methodBuilder.addStatement("    return null");
                } else {
                    methodBuilder.addStatement("    return " + ((TypeElement) method.getEnclosingElement()).getQualifiedName()
                            + "."
                            + method.getSimpleName()
                            + "(" + PARAMETERS + ")");
                }

            }
        }

        methodBuilder.addStatement("default :\nreturn null");

        methodBuilder.endControlFlow();

        MethodSpec method = methodBuilder
                .addParameter(String.class, CLASS_NAME)
                .addParameter(String.class, METHOD_NAME)
                .addParameter(Object[].class, PARAMETERS)
                .varargs(true)
                .returns(TypeName.OBJECT)
                .build();

        typeSpecBuilder.addMethod(method);

        try {
            JavaFile.builder("com.synapse", typeSpecBuilder.build()).build().writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new TreeSet<>(Collections.singleton(Feature.class.getCanonicalName()));
    }
}
