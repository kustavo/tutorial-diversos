package com.example.auto_wired;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;


@SpringBootApplication
public class AutoWiredApplication {

	public static void main(String[] args) {

		SpringApplication.run(AutoWiredApplication.class, args);

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(AutoWiredApplication.class.getPackage().getName());
		context.refresh();

		/**
		 * Com @AutoWired
		 */
		UserClassWired objWired = context.getBean(UserClassWired.class);
		objWired.doSomething();

		/**
		 * Sem @AutoWired
		 */
		UserClassNoWired objNoWired = context.getBean(UserClassNoWired.class);
		objNoWired.doSomething();
	}
}

@Component
class ClassA {
	ClassB classB;

	ClassA(ClassB classB){
		this.classB = classB;
	}

	void metodo() {
		System.out.println("Metodo do A");
		this.classB.metodo();
	}
}

@Component
class ClassB {
	ClassC classC;

	ClassB(ClassC classC){
		this.classC = classC;
	}

	void metodo() {
		System.out.println("Metodo do B");
	}
}

@Component
class ClassC {

	ClassC(){}
}

@Component
class UserClassWired {

	ClassA classA;

	@Autowired
	UserClassWired(ClassA classA) {
		this.classA = classA;
	}

	UserClassWired() {}

	void doSomething() {
		classA.metodo();
	}
}

@Component
class UserClassNoWired {

	ClassA classA;

	UserClassNoWired(ClassA classA) {
		this.classA = classA;
	}

	UserClassNoWired() {}

	/**
	 * Sem @AutoWired o UserClassNoWired passa a ter responsabilidade de saber todas as
	 * dependências para instanciar um objeto de ClassA.
	 */
	void doSomething(){

		ClassC classC = new ClassC();
		ClassB classB = new ClassB(classC);
		this.classA = new ClassA(classB);
		classA.metodo();
	}
}

// Saida:
// > Metodo do A
// > Metodo do B
// > Metodo do A
// > Metodo do B