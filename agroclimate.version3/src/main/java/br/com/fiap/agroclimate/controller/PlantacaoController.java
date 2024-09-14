package br.com.fiap.agroclimate.controller;

import br.com.fiap.agroclimate.dto.fazenda.DetalhesFazendaDto;
import br.com.fiap.agroclimate.dto.plantacao.AtualizarInfoPlantacaoDto;
import br.com.fiap.agroclimate.dto.plantacao.CadastrarInfoPlantacaoDto;
import br.com.fiap.agroclimate.dto.plantacao.DetalhePlantacaoDto;
import br.com.fiap.agroclimate.model.InfoPlantacao;
import br.com.fiap.agroclimate.repository.InfoPlantacaoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("infoplantacoes")
@Tag(name="Plantação", description = "Operações de gerenciamento de uma plantação")
public class PlantacaoController {

    @Autowired
    private InfoPlantacaoRepository InfoPlantacaoRepository;


    @PostMapping
    @Transactional
    @Operation(description = "Cadastra uma nova plantação"
            , summary = "Cadastrar uma nova plantação")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Plantação cadastrada!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DetalhePlantacaoDto.class))),
            @ApiResponse(responseCode = "404", description = "Revise seus dados")}
    )
    public ResponseEntity<DetalhePlantacaoDto> post(@RequestBody CadastrarInfoPlantacaoDto PlantacaoDto, UriComponentsBuilder uriBuilder) {
        var InfoPlantacao = new InfoPlantacao(PlantacaoDto);
        InfoPlantacaoRepository.save(InfoPlantacao);
        var uri = uriBuilder.path("/infoplantacaoes/{id}").buildAndExpand(InfoPlantacao.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhePlantacaoDto(InfoPlantacao));
    }

    @DeleteMapping("{id}")
    @Operation(description = "Deleta o registro de uma plantação"
            , summary = "Deletar uma plantação")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Plantação deletada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DetalhePlantacaoDto.class))),
            @ApiResponse(responseCode = "404", description = "Revise seus dados")}
    )
    @org.springframework.transaction.annotation.Transactional
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        InfoPlantacaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Transactional
    @Operation(description = "Atualiza o registro de uma plantação"
            , summary = "Atualizar uma plantação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Plantação atualizada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DetalhePlantacaoDto.class))),
            @ApiResponse(responseCode = "404", description = "Revise seus dados")}
    )
    public ResponseEntity<DetalhePlantacaoDto> put(@PathVariable("id") Long id,
                                                   @RequestBody AtualizarInfoPlantacaoDto dto) {
        var InfoPlantacao = InfoPlantacaoRepository.getReferenceById(id);
        InfoPlantacao.atualizarInfoPlantacao(dto);
        return ResponseEntity.ok(new DetalhePlantacaoDto(InfoPlantacao));
    }

    @GetMapping("{id}")
    @Operation(description = "Recupera o registro de uma plantação"
            , summary = "Retornar uma plantação")
    @ApiResponses({
            @ApiResponse(responseCode = "Ok", description = "Dados retornados!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DetalhePlantacaoDto.class))),
            @ApiResponse(responseCode = "404", description = "Revise seus dados")}
    )
    public ResponseEntity<DetalhePlantacaoDto> get(@PathVariable("id") Long id) {
        var InfoPlantacao = InfoPlantacaoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhePlantacaoDto(InfoPlantacao));
    }
}
