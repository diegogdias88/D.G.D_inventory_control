package dgdsoft.model.domain;

/**
 *
 * @author diego
 */
public class Usuario {

    private Integer id;
    private String user;
    private String password;
    private Integer categoria;
    private String nome;
    private Integer matricula;
    
    public Usuario(){
        
    }
    
    public Usuario(Integer id, String user, String password, Integer categoria, String nome, Integer matricula){
        this.id = id;
        this.user = user;
        this.password = password;
        this.categoria = categoria;
        this.nome = nome;
        this.matricula = matricula;
    }
      
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }
    
}
