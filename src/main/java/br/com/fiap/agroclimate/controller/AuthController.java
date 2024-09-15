package br.com.fiap.agroclimate.controller;

import br.com.fiap.agroclimate.dto.autenticacao.LoginRequestDto;
import br.com.fiap.agroclimate.dto.autenticacao.RegisterRequestDto;
import br.com.fiap.agroclimate.dto.autenticacao.ResponseDto;
import br.com.fiap.agroclimate.dto.usuario.agricultor.DetalhesAgricultorDto;
import br.com.fiap.agroclimate.model.Agricultor;
import br.com.fiap.agroclimate.repository.AgricultorRepository;
import br.com.fiap.agroclimate.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Autenticacao", description = "Processo de cadastro e autenticação de usuários")
public class AuthController {

    private final AgricultorRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    @Operation(description = "Realiza o login de um usuário que já se se registrou na aplicação "
            , summary = "Realizar login e gerar token")
    @ApiResponses({
            @ApiResponse(responseCode = "Ok", description = "Login concluido, token gerado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")}
    )
    public ResponseEntity login(@RequestBody LoginRequestDto body){
        Agricultor agricultor = this.repository.findByLogin(body.login())
                .orElseThrow(()->new RuntimeException("Usuario não encontrado"));
        if (passwordEncoder.matches( body.senha(),agricultor.getSenha())){
            String token = this.tokenService.generateToken(agricultor);
            log.info("Bem vindo de volta! Seu token foi gerado com sucesso!");
            return ResponseEntity.ok(new ResponseDto(agricultor.getId(),agricultor.getNome(),token));
    }
    return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    @Operation(description = "Registra/Cadastra um usuário. "
            , summary = "Registro de um usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "Ok", description = "Agricultor cadastrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RegisterRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")}
    )
    public ResponseEntity register(@RequestBody RegisterRequestDto body){
        Optional<Agricultor> agricultor = this.repository.findByLogin(body.login());
        if (agricultor.isEmpty()){
            Agricultor newAgricultor = new Agricultor();
            newAgricultor.setSenha(passwordEncoder.encode(body.senha()));
            newAgricultor.setLogin(body.login());
            newAgricultor.setNome(body.nome());
            this.repository.save(newAgricultor);

            String token = this.tokenService.generateToken(newAgricultor);
            return ResponseEntity.ok(new ResponseDto(newAgricultor.getId(), newAgricultor.getNome(),token));
        }
        return ResponseEntity.badRequest().build();
        }

    }

