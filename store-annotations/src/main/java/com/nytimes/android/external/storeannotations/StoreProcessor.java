package com.nytimes.android.external.storeannotations;

import com.nytimes.android.external.store.base.annotation.Resize;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@SupportedAnnotationTypes("com.nytimes.android.external.store.base.annotation.Resize")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class StoreProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {


        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(Resize.class)) {
            TypeElement typeElement = (TypeElement) annotatedElement;

            try {
                new Generator(typeElement, processingEnv).generateFile();
            } catch (IllegalArgumentException exception) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "error:" + exception.getMessage());
                return false;

            }
        }
        return true;
    }

}
