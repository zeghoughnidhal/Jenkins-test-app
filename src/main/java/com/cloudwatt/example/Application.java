package com.cloudwatt.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application implements CommandLineRunner {

/*	@Autowired
	private FolderRepository folderRepository;
*/
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

	}



	@Override
	public void run(String... args) throws Exception {

		/*folderRepository.save(new FolderEntity( "test1_description",  "test1_displayName", "test1_fullDisplayName", "test1_fullName", "test1_name", "test1_url", "test1_color", "test1_env", "test1_folderName"));
		folderRepository.save(new FolderEntity( "test2_description",  "test2_displayName", "test2_fullDisplayName", "test2_fullName", "test2_name", "test2_url", "test2_color", "test2_env", "test2_folderName"));
		folderRepository.save(new FolderEntity( "test2_description",  "test2_displayName", "test2_fullDisplayName", "test2_fullName", "test2_name", "test2_url", "test2_color", "test2_env", "test2_folderName"));

		folderRepository.findAll().forEach(f ->{
			log.info("--foldr is : {}", f.getDescription());
		});*/

	}
}
