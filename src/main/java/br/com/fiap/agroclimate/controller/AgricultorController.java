package br.com.fiap.agroclimate.controller;


import br.com.fiap.agroclimate.dto.fazenda.CadastrarFazendaDto;
import br.com.fiap.agroclimate.dto.fazenda.DetalhesFazendaDto;
import br.com.fiap.agroclimate.dto.usuario.agricultor.AtualizarAgricultor;
import br.com.fiap.agroclimate.dto.usuario.agricultor.DetalhesAgricultorDto;
import br.com.fiap.agroclimate.dto.usuario.agricultor.ListagemAgricultorDto;
import br.com.fiap.agroclimate.model.Fazenda;
import br.com.fiap.agroclimate.repository.AgricultorRepository;

import br.com.fiap.agroclimate.repository.FazendaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/agricultores")
@Tag(name = "Agricultores", description = "Operações com agricultores já cadastrados")
public class AgricultorController {


    @Autowired
    private AgricultorRepository agricultorRepository;


    @DeleteMapping("{id}")
    @Transactional
    @Operation(description = "Deleta um usuário cadastrado", summary = "Remover usuário cadastrado")
    @ApiResponses({
            @ApiResponse(responseCode = "no content", description = "Usuário removido",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DetalhesAgricultorDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")}
    )
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        agricultorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Transactional
    @Operation(description = "Atualiza os dados do agricultor cadastrado"
            , summary = "Atualizar usuário já cadastrado")
    @ApiResponses({
            @ApiResponse(responseCode = "Ok", description = "Dados atualizados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DetalhesAgricultorDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")}
    )
    public ResponseEntity<DetalhesAgricultorDto> put(@PathVariable("id") Long id,
                                                     @RequestBody AtualizarAgricultor dto) {
        var agricultor = agricultorRepository.getReferenceById(id);
        agricultor.atualizarAgricultor(dto);
        return ResponseEntity.ok(new DetalhesAgricultorDto(agricultor));
    }

    @GetMapping("{id}")
    @Operation(description = "Retorna os dados de um usuário cadastrado "
            , summary = "Recuperar um usuário cadastrado")
    @ApiResponses({
            @ApiResponse(responseCode = "Ok", description = "Dados retornados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DetalhesAgricultorDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")}
    )
    public ResponseEntity<ListagemAgricultorDto> get(@PathVariable("id") Long id) {
        var agricultor = agricultorRepository.getReferenceById(id);
        return ResponseEntity.ok(new ListagemAgricultorDto(agricultor));
    }

    @GetMapping
    @Operation(description = "Retorna os dados de um usuário cadastrado "
            , summary = "Recuperar um usuário cadastrado| Paginado")
    @ApiResponses({
            @ApiResponse(responseCode = "Ok", description = "Dados recuperados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DetalhesAgricultorDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")}
    )
    public ResponseEntity<List<ListagemAgricultorDto>> get(Pageable pageable) {
        var listaDto = agricultorRepository.findAll(pageable)
                .stream().map(ListagemAgricultorDto::new).toList();
        return ResponseEntity.ok(listaDto);

    }


}
