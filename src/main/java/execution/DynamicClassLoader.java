package execution;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
public class DynamicClassLoader extends ClassLoader {

    @Value("${participant.class.binary.name}")
    private String participantClassBinaryName;

    final static Logger logger = LoggerFactory.getLogger(DynamicClassLoader.class);

    public DynamicClassLoader(ClassLoader parent) {
        super(parent);
    }

    public Class loadClass(String name) throws ClassNotFoundException {
        if(!RequestExecutor.participantClassName.equals(name))
            return super.loadClass(name);

        final String fullFilePath = "file:" + RequestExecutor.participantClassPath + name + ".class";
        try {
            URL url = new URL(fullFilePath);
            URLConnection connection = url.openConnection();
            InputStream input = connection.getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int data = input.read();

            while(data != -1) {
                buffer.write(data);
                data = input.read();
            }

            input.close();

            byte[] classData = buffer.toByteArray();

            return defineClass(participantClassBinaryName,
                    classData, 0, classData.length);

        } catch (MalformedURLException e) {
            logger.error("Malformed url " + fullFilePath + ". Error Message : " + e.getMessage());
        } catch (IOException e) {
            logger.error("Could not read file : " + e.getMessage());
        }

        return null;
    }
}
