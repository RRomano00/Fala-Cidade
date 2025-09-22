package br.com.faitec.fala_cidade.implementation.service.tools;

import br.com.faitec.fala_cidade.port.service.tools.ResourceFileService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class ResourceFileServiceImpl implements ResourceFileService {

    @Override
    public String read(String resourcePath) throws IOException {
        final ClassLoader classLoader = ResourceFileServiceImpl.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(resourcePath);

        if (inputStream == null){
            throw new RuntimeException("Arquivo não encontrado");
        }

        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String content = "";
        String line;

        while ((line = bufferedReader.readLine()) != null){
            System.out.println(line);
            content += line;
        }

        return content;
    }
}
