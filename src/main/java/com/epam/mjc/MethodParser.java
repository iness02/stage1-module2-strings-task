package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String accessModifier;
        String ReturnType;
        String methodName;
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        signatureString = signatureString.replaceAll("[^a-zA-Z0-9]", "/");
        String[] arr = signatureString.split("[/\\//]");
        int count=0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].equals("")){
                count++;
            }
        }
        String arr2[] = new String[arr.length-count];
        int k=0;
        for(int i =0;i<arr.length;i++){
            if(!arr[i].equals("")){
                arr2[k]=arr[i];
                k++;
            }
        }
        accessModifier = arr[0];
        ReturnType = arr[1];
        methodName = arr[2];
        for (int i = 3; i < arr2.length - 1; i += 2) {

            arguments.add(new MethodSignature.Argument(arr2[i], arr2[i + 1]));
        }
        MethodSignature methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setAccessModifier(accessModifier);
        methodSignature.setReturnType(ReturnType);
        return methodSignature;
        //throw new UnsupportedOperationException("You should implement this method.");
    }
}
