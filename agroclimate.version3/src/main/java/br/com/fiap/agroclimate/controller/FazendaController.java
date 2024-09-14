package br.com.fiap.agroclimate.controller;

import br.com.fiap.agroclimate.dto.colheita.InformacaoColheitaDto;
import br.com.fiap.agroclimate.dto.fazenda.AtualizarFazendaDto;
import br.com.fiap.agroclimate.dto.fazenda.CadastrarFazendaDto;
import br.com.fiap.agroclimate.dto.fazenda.DetalhesFazendaDto;
import br.com.fiap.agroclimate.dto.fazenda.ListagemFazendaDto;
import br.com.fiap.agroclimate.model.Fazenda;
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
@RequestMapping("fazendas")
@Tag(name = "Fazenda", description = "Cadastro de fazendas e edição de dados cadastrais")
public class FazendaController {

    @Autowired
    private FazendaRepository fazendaRepository;

    @PostMapping
    @Transactional
    @Operation(description = "Realiza o cadastro de uma nova fazenda "
            , summary = "Cadastra uma fazenda")
    @ApiResponses({
            @ApiResponse(responseCode = "Ok", description = "Fazenda cadastrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DetalhesFazendaDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")}
    )
    public ResponseEntity<DetalhesFazendaDto> post(@RequestBody @Valid CadastrarFazendaDto fazendaDto, UriComponentsBuilder uriBuilder) {
        var fazenda = new Fazenda(fazendaDto);
        fazendaRepository.save(fazenda);
        var uri = uriBuilder.path("/fazendas/{id}").buildAndExpand(fazenda.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesFazendaDto(fazenda));
    }

    @DeleteMapping("{id}")
    @Transactional
    @Operation(description = "Realiza a exclusão de uma fazenda "
            , summary = "Deleta uma fazenda")
    @ApiResponses({
            @ApiResponse(responseCode = "No Content", description = "Fazenda deletada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DetalhesFazendaDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")}
    )
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        fazendaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Transactional
    @Operation(description = "Realiza a atualização de uma fazenda "
            , summary = "Atualiza uma fazenda")
    @ApiResponses({
            @ApiResponse(responseCode = "Ok", description = "Fazenda atualizada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DetalhesFazendaDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")}
    )
    public ResponseEntity<DetalhesFazendaDto> put(@PathVariable("id") Long id,
                                                  @RequestBody AtualizarFazendaDto dto) {
        var fazenda = fazendaRepository.getReferenceById(id);
        fazenda.atualizarFazenda(dto);
        return ResponseEntity.ok(new DetalhesFazendaDto(fazenda));
    }

    @GetMapping("{id}")
    @Operation(description = "Recupera os dados de uma fazenda "
            , summary = "Retorna uma fazenda cadastrada")
    @ApiResponses({
            @ApiResponse(responseCode = "Ok", description = "Dados retornados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DetalhesFazendaDto.class))),
            @ApiResponse(responseCode = "404", description = "Fazenda não encontrada, revise seus dados")}
    )
    public ResponseEntity<DetalhesFazendaDto> get(@PathVariable("id") Long id) {
        var fazenda = fazendaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesFazendaDto(fazenda));
    }

    @GetMapping
    @Operation(description = "Recupera fazendas paginadas"
            , summary = "Retornar uma fazenda cadastrada| Paginado")
    @ApiResponses({
            @ApiResponse(responseCode = "Ok", description = "Dados retornados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DetalhesFazendaDto.class))),
            @ApiResponse(responseCode = "404", description = "Fazenda não encontrada, revise seus dados")}
    )
    public ResponseEntity<List<ListagemFazendaDto>> get(Pageable pageable) {
        var listaDto = fazendaRepository.findAll(pageable)
                .stream().map(ListagemFazendaDto::new).toList();
        return ResponseEntity.ok(listaDto);

    }
}

