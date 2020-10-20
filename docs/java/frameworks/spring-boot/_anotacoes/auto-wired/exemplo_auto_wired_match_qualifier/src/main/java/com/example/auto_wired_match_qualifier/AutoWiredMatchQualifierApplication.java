package com.example.auto_wired_match_qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class AutoWiredMatchQualifierApplication {

	public static void main(String[] args) {

		SpringApplication.run(AutoWiredMatchQualifierApplication.class, args);

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(AutoWiredMatchQualifierApplication.class.getPackage().getName());
		context.refresh();

		ClassA classA = context.getBean(ClassA.class);
		classA.metodo();
	}
}

@Component
class ClassA {

	/*
	 * Indicando a classe que retornou o bean
	 */
	@Autowired
	@Qualifier("classB1")
	ClassB classB;

	/*
	 * Indicando o método que retornou o bean
	 */
	@Autowired
	@Qualifier("getClassC1")
	ClassC classC;

	void metodo() {
		System.out.println(classB.getClass().getSimpleName().substring(0, 7));
		System.out.println(classC.metodoUsado);
	}
}

@Configuration
class ClassB1 extends ClassB {

	@Bean
	ClassB1 getClassB1(){
		return new ClassB1();
	}
}

@Configuration
class ClassB2 extends ClassB {

	@Bean
	ClassB2 getClassB2(){
		return new ClassB2();
	}
}

class ClassB { }

@Configuration
class ClassC {

	String metodoUsado;

	ClassC() {}

	ClassC (String metodoUsado) {
		this.metodoUsado = metodoUsado;
	}

	@Bean
	ClassC getClassC1(){
		return new ClassC("metodo getClassC1");
	}

	@Bean
	ClassC getClassC2(){
		return new ClassC("metodo getClassC2");
	}
}

// Saida:
// > ClassB1
// > metodo getClassC1
