package com.treinaWeb.curse.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.treinaWeb.curse.domain.Pais;
import com.treinaWeb.curse.domain.Post;
import com.treinaWeb.curse.domain.Telefone;
import com.treinaWeb.curse.domain.User;
import com.treinaWeb.curse.dto.AuthorDTO;
import com.treinaWeb.curse.dto.CommentDTO;
import com.treinaWeb.curse.repository.PaisRepository;
import com.treinaWeb.curse.repository.PostRepository;
import com.treinaWeb.curse.repository.TelefoneRepository;
import com.treinaWeb.curse.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private PaisRepository paisRepository;

	@Autowired
	private TelefoneRepository telefoneRepository;
	
		

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		//userRepository.deleteAll();
		//postRepository.deleteAll();
		//paisRepository.deleteAll();
		//telefoneRepository.deleteAll();

		Pais pais1 = new Pais("AF", "Afeganistao", "af", "+93");
		Pais pais2 = new Pais("STP", "Sao Tome e Principe", "stp", "+239");
		Pais pais3 = new Pais( "BR", "Brazil", "br", "+55");

		Telefone telefone1 = new Telefone("AF", "Afeganistao", "af", "+93", "+9391919191");
		Telefone telefone2 = new Telefone("STP", "Sao Tome e Principe", "stp", "+239", "+2399291929292");
		Telefone telefone3 = new Telefone("BR", "Brazil", "br", "+55", "+5593993933933");
		
		User maria = new User(null, "Maria"," Brown", "maria@gmail.com", "xxx", pais1, telefone1,"");
		User alex = new User(null, "Alex"," Green", "alex@gmail.com", "xxx", pais2, telefone2, "");
		User bob = new User(null, "Ailton"," Oliveira", "ailton@gmail.com", "xxx", pais3, telefone3, "");

		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!",new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));

		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(alex));
		

		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));

		postRepository.saveAll(Arrays.asList(post1, post2));

		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}
}
