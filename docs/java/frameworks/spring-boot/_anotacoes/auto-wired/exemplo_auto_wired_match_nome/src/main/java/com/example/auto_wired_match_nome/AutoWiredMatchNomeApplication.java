package com.example.auto_wired_match_nome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class AutoWiredMatchNomeApplication {

	public static void main(String[] args) {

		SpringApplication.run(AutoWiredMatchNomeApplication.class, args);

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(AutoWiredMatchNomeApplication.class.getPackage().getName());
		context.refresh();

		ClassA classA = context.getBean(ClassA.class);
		classA.metodo();
	}
}

@Component
class ClassA {

	/*
	 * Injeção ocorrerá verificando @Component(value="testeClassB")
	 */
	@Autowired
	ClassB testeClassB;

	void metodo() {
		System.out.println(testeClassB.getClass().getSimpleName().substring(0, 7));
	}
}

@Configuration(value="testeClassB")
class ClassB1 extends ClassB { }

@Configuration
class ClassB2 extends ClassB { }

abstract class ClassB { }

// Saida:
// > ClassB1