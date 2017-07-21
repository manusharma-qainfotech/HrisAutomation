package Readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.omg.CORBA.portable.InputStream;
import org.yaml.snakeyaml.Yaml;

import model.Credential;

public class CredentialsReader {
	Credential obj;
	public CredentialsReader() throws FileNotFoundException {
        String path=  System.getProperty("user.dir");
        FileReader reader = new FileReader(path+"\\src\\test\\resources\\Credentials.yaml");
		Yaml yaml = new Yaml();
		Map<String, Object>	map = (Map<String, Object>) yaml.load(reader);
    Map<String,Object> map1 = (Map) map.get("credentials");
      obj = new Credential(map1);
	}
	public Credential getCredentialObj()
	{
		return obj;
	}
}
