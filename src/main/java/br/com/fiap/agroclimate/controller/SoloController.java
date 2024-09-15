package br.com.fiap.agroclimate.controller;


import br.com.fiap.agroclimate.dto.safra.InformacaoSafraDto;
import br.com.fiap.agroclimate.dto.solo.AtualizarSoloDto;
import br.com.fiap.agroclimate.dto.solo.CadastrarSoloDto;
import br.com.fiap.agroclimate.dto.solo.DetalheSoloDto;
import br.com.fiap.agroclimate.dto.solo.ListagemSoloDto;
import br.com.fiap.agroclimate.model.Solo;
import br.com.fiap.agroclimate.repository.SoloRepository;
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
@RequestMapping("solos")
@Tag(name="Solo", description = "Gerenciamento de solo onde a fazenda se encontra")
public class SoloController {

    @Autowired
    private SoloRepository soloRepository;

    @PostMapping
    @Transactional
    @Operation(description = "Cadastra uma novo tipo de solo"
            , summary = "Cadastrar um novo solo")
    @ApiResponses({
            @ApiResponse(responseCode = "Ok", description = "Solo cadastrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DetalheSoloDto.class))),
            @ApiResponse(responseCode = "404", description = "Revise seus dados")}
    )
    public ResponseEntity<DetalheSoloDto> post(@RequestBody @Valid CadastrarSoloDto soloDto,
                                               UriComponentsBuilder uriBuilder) {
        var solo = new Solo(soloDto);
        soloRepository.save(solo);
        var uri = uriBuilder.path("/solos/{id}").buildAndExpand(solo.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalheSoloDto(solo));
    }

    @DeleteMapping("{id}")
    @Transactional
    @Operation(description = "Deleta o solo cadastrado anteriormente"
            , summary = "Deletar um solo")
    @ApiResponses({
            @ApiResponse(responseCode = "Ok", description = "Solo deletado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DetalheSoloDto.class))),
            @ApiResponse(responseCode = "404", description = "Revise seus dados")}
    )
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        soloRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Transactional
    @Operation(description = "Atualiza os dados do solo"
            , summary = "Atualizar um solo já cadastrado")
    @ApiResponses({
            @ApiResponse(responseCode = "Ok", description = "Solo atualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DetalheSoloDto.class))),
            @ApiResponse(responseCode = "404", description = "Revise seus dados")}
    )
    public ResponseEntity<DetalheSoloDto> put(@PathVariable("id") Long id,
                                              @RequestBody AtualizarSoloDto dto) {
        var solo = soloRepository.getReferenceById(id);
        solo.atualizarSolo(dto);
        return ResponseEntity.ok(new DetalheSoloDto(solo));
    }

    @GetMapping("{id}")
    @Operation(description = "Retorna os dados do solo"
            , summary = "Retornar um solo já cadastrado")
    @ApiResponses({
            @ApiResponse(responseCode = "Ok", description = "Solo recuperado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DetalheSoloDto.class))),
            @ApiResponse(responseCode = "404", description = "Revise seus dados")}
    )
    public ResponseEntity<DetalheSoloDto> get(@PathVariable("id") Long id) {
        var solo = soloRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalheSoloDto(solo));
    }

    @GetMapping
    @Operation(description = "Retorna os dados do solo com paginação"
            , summary = "Retornar um solo já cadastrado| Paginado")
    @ApiResponses({
            @ApiResponse(responseCode = "Ok", description = "Solo recuperado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DetalheSoloDto.class))),
            @ApiResponse(responseCode = "404", description = "Revise seus dados")}
    )
    public ResponseEntity<List<ListagemSoloDto>> get(Pageable pageable) {
        var listaDto = soloRepository.findAll(pageable)
                .stream().map(ListagemSoloDto::new).toList();
        return ResponseEntity.ok(listaDto);

    }
}
