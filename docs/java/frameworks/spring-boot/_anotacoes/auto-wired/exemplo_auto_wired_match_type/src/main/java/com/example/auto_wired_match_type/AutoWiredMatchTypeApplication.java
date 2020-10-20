package com.example.auto_wired_match_type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class AutoWiredMatchTypeApplication {

	public static void main(String[] args) {

		SpringApplication.run(AutoWiredMatchTypeApplication.class, args);

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(AutoWiredMatchTypeApplication.class.getPackage().getName());
		context.refresh();

		ClassA classA = context.getBean(ClassA.class);
		classA.metodo();
	}
}

@Component
class ClassA {

	/*
	 * Injeção ocorrerá apartir da ClasseC, que retorna o bean de tipo classB
	 */
	@Autowired
	ClassB classB;

	void metodo() {
		classB.metodoB();
	}
}

@Configuration
class ClassB {

	void metodoB() {
		System.out.println("Método B");
	}
}

@Configuration
class ClassC {

	@Bean
	ClassB getClassB(){
		return new ClassB();
	}
}

// Saida:
// > Método B