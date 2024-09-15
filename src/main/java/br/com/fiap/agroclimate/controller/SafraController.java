package br.com.fiap.agroclimate.controller;

import br.com.fiap.agroclimate.dto.plantacao.DetalhePlantacaoDto;
import br.com.fiap.agroclimate.dto.safra.AtualizarSafraDto;
import br.com.fiap.agroclimate.dto.safra.CadastrarSafraDto;
import br.com.fiap.agroclimate.dto.safra.InformacaoSafraDto;
import br.com.fiap.agroclimate.dto.safra.ListagemSafraDto;
import br.com.fiap.agroclimate.model.Safra;
import br.com.fiap.agroclimate.repository.SafraRepository;
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
@RequestMapping("safras")
@Tag(name="Safra", description = "Operações de gerenciamento de safras")
public class SafraController {

    @Autowired
    private SafraRepository safraRepository;


    @PostMapping
    @Transactional
    @Operation(description = "Cadastra uma nova safra"
            , summary = "Cadastrar uma nova safra")
    @ApiResponses({
            @ApiResponse(responseCode = "Ok", description = "Safra registrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InformacaoSafraDto.class))),
            @ApiResponse(responseCode = "404", description = "Revise seus dados")}
    )
    public ResponseEntity<InformacaoSafraDto> post(@RequestBody @Valid CadastrarSafraDto safraDto, UriComponentsBuilder uriBuilder) {
        var safra = new Safra(safraDto);
        safraRepository.save(safra);
        var uri = uriBuilder.path("/safras/{id}").buildAndExpand(safra.getId()).toUri();
        return ResponseEntity.created(uri).body(new InformacaoSafraDto(safra));
    }

    @DeleteMapping("{id}")
    @org.springframework.transaction.annotation.Transactional
    @Operation(description = "Deleta uma safra"
            , summary = "Deletar uma safra")
    @ApiResponses({
            @ApiResponse(responseCode = "No Content", description = "Safra deletada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InformacaoSafraDto.class))),
            @ApiResponse(responseCode = "404", description = "Revise seus dados")}
    )
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        safraRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Transactional
    @Operation(description = "Atualiza uma safra"
            , summary = "Atualizar uma safra")
    @ApiResponses({
            @ApiResponse(responseCode = "Ok", description = "Safra atualizada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InformacaoSafraDto.class))),
            @ApiResponse(responseCode = "404", description = "Revise seus dados")}
    )
    public ResponseEntity<InformacaoSafraDto> put(@PathVariable("id") Long id,
                                                  @RequestBody AtualizarSafraDto dto) {
        var safra = safraRepository.getReferenceById(id);
        safra.atualizarSafra(dto);
        return ResponseEntity.ok(new InformacaoSafraDto(safra));
    }

    @GetMapping("{id}")
    @Operation(description = "Retorna uma safra"
            , summary = "Recuperar uma safra")
    @ApiResponses({
            @ApiResponse(responseCode = "Ok", description = "Safra retornada com sucesso!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InformacaoSafraDto.class))),
            @ApiResponse(responseCode = "404", description = "Revise seus dados")}
    )
    public ResponseEntity<InformacaoSafraDto> get(@PathVariable("id") Long id) {
        var safra = safraRepository.getReferenceById(id);
        return ResponseEntity.ok(new InformacaoSafraDto(safra));
    }

    @GetMapping
    @Operation(description = "Retorna uma safra com paginação"
            , summary = "Recuperar uma safra| Paginado")
    @ApiResponses({
            @ApiResponse(responseCode = "Ok", description = "Safra retornada com sucesso!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InformacaoSafraDto.class))),
            @ApiResponse(responseCode = "404", description = "Revise seus dados")}
    )
    public ResponseEntity<List<ListagemSafraDto>> get(Pageable pageable) {
        var listaDto = safraRepository.findAll(pageable)
                .stream().map(ListagemSafraDto::new).toList();
        return ResponseEntity.ok(listaDto);

    }
}
