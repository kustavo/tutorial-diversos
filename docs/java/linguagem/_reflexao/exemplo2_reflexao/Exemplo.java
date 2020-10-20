package _reflexao.exemplo2_reflexao;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Exemplo {

    public static void main(String[] args) {

        Pessoa pessoa = new Pessoa();
        pessoa.setSexo('M');
        pessoa.setNome("Gabriel");

        Map<String, Object> attributes = Reflexao.getMapaCampos(pessoa);

        for (String key : attributes.keySet()) {
            System.out.println(key + ": " + attributes.get(key));
        }
    }
}

class Reflexao {

    public static Map<String, Object> getMapaCampos(Object obj) {

        Map<String, Object> attributesMap = new HashMap<>();

        Class<?> classeObj = obj.getClass();
        Method[] metodos = classeObj.getMethods();

        for (Method metodo : metodos) {

            if (metodo.getName().startsWith("get") && metodo.getReturnType() != void.class) {

                String nomeCampo = metodo.getName().substring(3);

                try {

                    /*
                     * Invoca o método corrente passando o objeto como argumento e recebe o retorno
                     * do método.
                     */
                    Object value = metodo.invoke(obj);

                    /*
                     * Adiciona as chaves 'nome método sem get' + 'retorno do método'
                     */
                    attributesMap.put(nomeCampo, value);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return attributesMap;
    }
}

class Pessoa {

    String nome;
    char sexo;

    Pessoa() {
    }

    public String getNome() {
        return (sexo == 'M' ? "Sr. " : "Sra. ") + nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
}

// Saida
// > Class: class _reflexao.exemplo2_reflexao.Pessoa
// > Nome: Sr. Gabriel
// > Sexo: M