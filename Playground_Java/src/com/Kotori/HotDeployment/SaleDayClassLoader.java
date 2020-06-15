package com.Kotori.HotDeployment;

import java.io.*;

public class SaleDayClassLoader extends ClassLoader {
    private String path;
    public SaleDayClassLoader(String path) {
        this.path = path;
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classPath = this.path + name+ ".class";

        ByteArrayOutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(classPath);
            outputStream = new ByteArrayOutputStream();
            int temp = 0;
            while((temp = inputStream.read()) != -1){
                outputStream.write(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        byte[] bytes = outputStream.toByteArray();

        return defineClass(null,bytes,0,bytes.length);
    }
}
