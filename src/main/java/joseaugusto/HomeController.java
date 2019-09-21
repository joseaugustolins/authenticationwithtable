package joseaugusto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@CrossOrigin //permite acessar de requisições externas
public class HomeController {

    //http://localhost:8080/permitir
    @GetMapping("/permitir")
    public ResponseEntity<?> permiteAcesso(){
        Usuario usu = new Usuario("jose","permite acesso sem login", "1234");
        return new ResponseEntity<Usuario>(usu, HttpStatus.OK);
    }
    //http://localhost:8080/permitir2?s1=aaafjksd
    @GetMapping("/permitir2")
    public ResponseEntity<?> permiteAcesso2(@RequestParam("s1") String s1){
        Usuario usu = new Usuario("joao",s1+"permite acesso passando variável", "1234");
        System.out.println(s1);
        return new ResponseEntity<Usuario>(usu, HttpStatus.OK);
    }

    //http://localhost:8080/publico/listaUsuarios
    @GetMapping("/publico/listaUsuarios")
    public ResponseEntity<List<Usuario>> listaUsuariosPublico(){
        Usuario usu1 = new Usuario("maria","usu 1", "1234");
        Usuario usu2 = new Usuario("marco","usu 2", "1234");
        List ul = new ArrayList<Usuario>();
        ul.add(usu1);
        ul.add(usu2);
        return new ResponseEntity<List<Usuario>>(ul, HttpStatus.OK);
    }

    // http://localhost:8080/publico/teste
    @GetMapping("/publico/teste")
    public ResponseEntity<?> permiteAcesso2(){
        Usuario usu = new Usuario("caio","publico", "1234");
        return new ResponseEntity<Usuario>(usu, HttpStatus.OK);
    }


    //http://localhost:8080/publico/555
    @GetMapping("/publico/{s1}")
    public ResponseEntity<?> permiteAcesso3(@PathVariable("s1") String s1){
        Usuario usu = new Usuario("logg",s1+"permite acesso passando variável", "1234");
        System.out.println(s1);
        return new ResponseEntity<Usuario>(usu, HttpStatus.OK);
    }


    //http://localhost:8080/autenticado
    @GetMapping("/autenticado")
    public ResponseEntity<?> onlyAuthenticated(@AuthenticationPrincipal UserDetails auth){
        Usuario usu = new Usuario("ana",auth.getUsername()+"Autenticado passando detalhes do usuario", "1234");
        System.out.println(auth.getUsername());
        System.out.println(auth);
        return new ResponseEntity<Usuario>(usu, HttpStatus.OK);
    }

    //http://localhost:8080/listaUsuarios
    @GetMapping("/listaUsuarios")
    public ResponseEntity<List<Usuario>> listaUsuarios(){
        Usuario usu1 = new Usuario("aa","usu 1", "1234");
        Usuario usu2 = new Usuario("bb","usu 2", "1234");
        List ul = new ArrayList<Usuario>();
        ul.add(usu1);
        ul.add(usu2);
        return new ResponseEntity<List<Usuario>>(ul, HttpStatus.OK);
    }

    //http://localhost:8080/admin/inicio
    @GetMapping("/admin/inicio")
    public ResponseEntity<?> onlyAdmin(){
        Usuario usu = new Usuario("caar","Apenas ADMIN", "1234");
        return new ResponseEntity<Usuario>(usu, HttpStatus.OK);
    }


    //METODO DELETAR em http://localhost:8080/admin/deletar/8
    @DeleteMapping("/admin/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Integer id){
        Usuario usu = new Usuario(""+id ,"Apenas ADMIN", "1234");
        return new ResponseEntity<String>(usu+"DELETAR - APENAS ADMIN", HttpStatus.OK);
    }


    //http://localhost:8080/soadmin
    @GetMapping("/soadmin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> soadmin(){
        return new ResponseEntity<String>("SO ADMIN (UTILIZANDO AUTHORIZE)", HttpStatus.OK);
    }


    /**POST em http://localhost:8080/cadastro
     * {
     * 	"id": 1,
     * 	"nome": "josaaaae",
     * 	"senha": "123"
     * }
     * */
    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastro(@Valid @RequestBody Usuario usuario, BindingResult result){
        if(result.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error: result.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
        }
        Usuario usu = usuario;
        System.out.println(usuario.getNome());
        return new ResponseEntity<Usuario>(usu, HttpStatus.CREATED);
    }

}

